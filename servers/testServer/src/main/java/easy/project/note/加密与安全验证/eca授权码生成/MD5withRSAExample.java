package easy.project.note.加密与安全验证.eca授权码生成;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class MD5withRSAExample {
    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 要签名的数据
        String data = "Hello, World!";

        // 生成签名
        byte[] signature = signData(data.getBytes(), privateKey);
        System.out.println("生成的签名: " + bytesToHex(signature));

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
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 验证签名
     */
    public static boolean verifySignature(byte[] data, byte[] signature, PublicKey publicKey) throws Exception {
        Signature signatureVerifier = Signature.getInstance("MD5withRSA");
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