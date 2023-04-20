package easy.project.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
        byte[] data = "Hello, AES-256!".getBytes();

        byte[] encryptedData = encrypt(key, iv, data);
        byte[] decryptedData = decrypt(key, iv, encryptedData);

        System.out.println(new String(encryptedData,"utf-8")); // 输出加密后的数据
        System.out.println(new String(decryptedData)); // 输出解密后的数据
    }

}