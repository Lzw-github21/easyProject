package easy.project.util;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 整合AES128加密算法，key长度为32位，安全度较高，加密解密效率较低。
 * @author 李志威
 * @date 2023/4/20
 */
public class AES256 {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 256;

    public static byte[] encrypt(byte[] key, byte[] iv, byte[] data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] key, byte[] iv, byte[] encryptedData) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(encryptedData);
    }

    public static void main(String[] args) throws Exception {
        byte[] key = "0123456789abcdef0123456789abcdef".getBytes();
        byte[] iv = "abcdef0123456789".getBytes();
        byte[] data = "测试企业统一信用代码".getBytes();
        //对数据加密
        byte[] encryptedData = encrypt(key, iv, data);
        //对加密后数据进行base64编码，输出
        String aesEncoder = Base64.getEncoder().encodeToString(encryptedData);// 输出加密后的数据
        System.out.println(aesEncoder);

        //对编码后数据进行解码
        byte[] base64 = Base64.getDecoder().decode(aesEncoder.getBytes());
        System.out.println(base64);

        //对解码数据进行解密
        byte[] aesDecoder =decrypt(key,iv,base64);
        System.out.println(aesDecoder);

        //将解密数据转化成String
        String result = new String(aesDecoder, Charset.defaultCharset());
        System.out.println(result);

        //查看系统默认的编码方式
        System.out.println(Charset.defaultCharset());
    }

}