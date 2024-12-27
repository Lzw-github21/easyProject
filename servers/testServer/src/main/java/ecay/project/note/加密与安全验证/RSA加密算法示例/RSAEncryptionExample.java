package ecay.project.note.加密与安全验证.RSA加密算法示例;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAEncryptionExample {

    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPair keyPair = generateRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 要加密的明文数据
        String plainText = "Hello, RSA Encryption!";

        // 使用公钥加密数据
        byte[] encryptedText = encrypt(plainText, publicKey);
        System.out.println("加密后的数据 (Base64): " + Base64.getEncoder().encodeToString(encryptedText));

        // 使用私钥解密数据
        String decryptedText = decrypt(encryptedText, privateKey);
        System.out.println("解密后的数据: " + decryptedText);
    }

    /**
     * 生成RSA密钥对
     */
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//        1024 位：已被认为不安全，可以在合理时间内被破解。
//        2048 位：目前广泛使用，安全性较高。
//        4096 位：提供更高的安全性，但计算速度较慢。
        keyGen.initialize(2048); // 密钥长度为2048位
        return keyGen.generateKeyPair();
    }

    /**
     * 使用公钥加密数据
     */
    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    /**
     * 使用私钥解密数据
     */
    public static String decrypt(byte[] encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedText);
        return new String(decryptedBytes);
    }
}