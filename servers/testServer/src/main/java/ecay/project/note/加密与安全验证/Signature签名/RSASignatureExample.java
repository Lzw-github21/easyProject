package ecay.project.note.加密与安全验证.Signature签名;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class RSASignatureExample {
    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println("私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // 要签名的数据
        String data = "Hello, RSA Signature!";

        // 生成签名
        byte[] signature = signData(data.getBytes(), privateKey);
        System.out.println("生成的签名: " +  Base64.getEncoder().encodeToString(signature));

        // 验证签名
        boolean isVerified = verifySignature(data.getBytes(), signature, publicKey);
        System.out.println("签名验证结果: " + isVerified);
    }

    /**
     * 生成RSA密钥对
     */
    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // 密钥长度为2048位
        return keyGen.generateKeyPair();
    }

    /**
     * 生成签名
     */
    public static byte[] signData(byte[] data, PrivateKey privateKey) throws Exception {
        /**
         * Signature 是 Java 中用于生成和验证数字签名的工具类。它支持多种签名算法，包括：
         * MD5withRSA MD5已被证明存在碰撞漏洞，容易受到攻击。
         * SHA256withRSA SHA-256 是一种安全的哈希算法，目前尚未发现有效的碰撞攻击。
         * SHA512withRSA SHA-512 比 SHA-256 更慢，性能影响较大。
         * SHA256withECDSA 基于椭圆曲线加密（ECC），提供与 RSA 相当的安全性，但密钥长度更短。资源受限的环境（如物联网设备、移动设备）。
         * SHA512withECDSA
         *
         */
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 验证签名
     */
    public static boolean verifySignature(byte[] data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature signatureVerifier = Signature.getInstance("SHA256withRSA");
        signatureVerifier.initVerify(publicKey);
        signatureVerifier.update(data);
        return signatureVerifier.verify(signature);
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}