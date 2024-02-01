package project.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import project.entity.configproperties.EcaSecurityKeyConfig;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class SecurityHelperImpl implements SecurityHelper {
    final Base64.Decoder decoder = Base64.getDecoder();
    final Base64.Encoder encoder = Base64.getEncoder();

    @Autowired
    private EcaSecurityKeyConfig ecaSecurityKeyConfig;

    public SecurityHelperImpl() {
    }

    /**
     * 设置java支持PKCS7Padding
     */
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

    }

    private final String ALGORITHM = "DESede";
    /**
     * 默认为 DESede/ECB/PKCS7Padding
     */
    private final String CIPHER_TRANSFORMAT = "DESede/ECB/PKCS7Padding";
    private final String ENCODING = "UTF-8";

    private final String AESKEY = "xRH8EVswh6WdG5IC";
    private final String AESIV = "LAxl9zHb4kBvzEDa";

    @Override
    public String MD5HashString(String textToHash) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
        byte[] keyArray = md5.digest(textToHash.getBytes("utf-8"));
        return encoder.encodeToString(keyArray);
    }

    @Override
    public String Encrypt(String plainText, boolean useHash) throws Exception {
        String key = ecaSecurityKeyConfig.getMd5Key();
        byte[] keyArray = null;
        if (useHash) {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
            keyArray = md5.digest(key.getBytes("utf-8"));
        } else {
            keyArray = key.getBytes("utf-8");
        }
        SecretKey deskey = new SecretKeySpec(keyArray, ALGORITHM);
        Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] result = c1.doFinal(plainText.getBytes(ENCODING));
        return encoder.encodeToString(result);
    }

    @Override
    public String Decrypt(String base64, boolean useHash) throws Exception {
        String key = ecaSecurityKeyConfig.getMd5Key();
        byte[] keyArray = null;
        if (useHash) {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
            keyArray = md5.digest(key.getBytes("utf-8"));
        } else {
            keyArray = key.getBytes("utf-8");
        }
        SecretKey deskey = new SecretKeySpec(keyArray, ALGORITHM);
        Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        byte[] result = c1.doFinal(decoder.decode(base64));
        return new String(result, ENCODING);
    }

    @Override
    public String DecryptConnPwd(String base64) throws Exception {
        String key = ecaSecurityKeyConfig.getMd5Pwdkey();
        byte[] keyArray = null;

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
        keyArray = md5.digest(key.getBytes("utf-8"));

        SecretKey deskey = new SecretKeySpec(keyArray, ALGORITHM);
        Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        byte[] result = c1.doFinal(decoder.decode(base64));
        return new String(result, ENCODING);
    }

    @Override
    public String Md5Digest(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @return
     * @throws Exception
     */
    @Override
    public String AESDecrypt(String cipherText) {
        try {
            return this.AESDecrypt(cipherText, AESKEY, AESIV);
        } catch (Exception e) {
            log.error("AES 解密错误！" + e.getMessage());
            log.error(e.getMessage());
            return cipherText;
        }
    }

    /**
     * AES 加密操作
     *
     * @param plainText 内容明文
     * @return
     * @throws Exception
     */
    @Override
    public String AESEncrypt(String plainText) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> strings = Arrays.asList(plainText.split("%20| "));
            strings.forEach(text ->{
                if (StringUtils.isNotBlank(text)) {
                    sb.append(text);
                }
            });
            return this.AESEncrypt(sb.toString(), AESKEY, AESIV);
        } catch (Exception e) {
            log.error("AES 加密错误！" + e.getMessage());
            log.error(e.getMessage());
            return plainText;
        }
    }

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @param key        密钥
     * @param iv         偏移量
     * @return
     * @throws Exception
     */
    @Override
    public String AESDecrypt(String cipherText, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 密钥
//        String key = param.get("key");
        // 偏移量
//        String iv = param.get("iv");
        if (StringUtils.isBlank(key) || StringUtils.isBlank(cipherText) || StringUtils.isBlank(iv)) {
            return cipherText;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), "AES");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec IV = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, IV);
        byte[] buffer = new BASE64Decoder().decodeBuffer(cipherText);
        byte[] encrypted = cipher.doFinal(buffer);
        // 此处使用BASE64做转码。
        return new String(encrypted, StandardCharsets.UTF_8);
    }

    /**
     * AES 加密操作
     *
     * @param plainText 内容明文
     * @param key       密钥
     * @param iv        偏移量
     * @return
     * @throws Exception
     */
    @Override
    public String AESEncrypt(String plainText, String key, String iv) throws Exception {

        if (StringUtils.isBlank(key) || StringUtils.isBlank(plainText) || StringUtils.isBlank(iv)) {
            return plainText;
        }

        try {
            /*
             * 新建一个密码编译器的实例，由三部分构成，用"/"分隔，分别代表如下
             * 1. 加密的类型(如AES，DES，RC2等)
             * 2. 模式(AES中包含ECB，CBC，CFB，CTR，CTS等)
             * 3. 补码方式(包含nopadding/PKCS5Padding等等)
             * 依据这三个参数可以创建很多种加密方式
             */
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //偏移量
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            byte[] byteContent = plainText.getBytes(StandardCharsets.UTF_8);

            //使用加密秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            //SecretKeySpec skeySpec = getSecretKey(key);

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv); // 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent); // 加密

            return Base64Utils.encodeToString(result); // 通过Base64转码返回
        } catch (Exception ex) {
            throw new Exception("AES加密发生异常");
        }
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str 需要加密的报文
     * @return
     */
    @Override
    public String SHA256Encrypt(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
