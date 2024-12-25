package ecay.project.note.eca授权码生成;

import java.security.*;
import java.util.Base64;

public class AuthorisationCodeGenerator {

    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 获取序列号
        String sequence = "12345"; // 替换为实际的序列号获取逻辑

        // 设置授权到期日期（例如：2024-12-31）
        String licenceEnd = "2024-12-31";

        // 生成授权码
        String authorisationCode = generateAuthorisationCode(sequence, licenceEnd, privateKey);

        System.out.println("生成的授权码: " + authorisationCode);
    }

    private static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public static String generateAuthorisationCode(String sequence, String licenceEnd, PrivateKey privateKey) throws Exception {
        // 生成JSON数据
        String jsonData = String.format("{\"DeviceID\":\"%s\",\"LicenceEnd\":\"%s\"}", sequence, licenceEnd);

        // Base64编码JSON数据
        String jsonBase64 = Base64.getEncoder().encodeToString(jsonData.getBytes("UTF-8"));

        // 对Base64编码的JSON数据进行签名
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(jsonBase64.getBytes("UTF-8"));
        byte[] signedData = signature.sign();

        // Base64编码签名数据
        String signBase64 = Base64.getEncoder().encodeToString(signedData);

        // 拼接授权码
        return jsonBase64 + " " + signBase64;
    }
}