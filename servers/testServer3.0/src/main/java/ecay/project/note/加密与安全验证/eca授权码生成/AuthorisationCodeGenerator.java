package ecay.project.note.加密与安全验证.eca授权码生成;


import cn.ecasoft.utils.RSAUtil;
import cn.ecasoft.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

import java.security.*;
import java.time.LocalDate;
import java.util.Base64;

/**
 * 由于在 RSA 加密算法中，公钥和私钥是成对生成的，但私钥的生成过程是基于数学原理的，因此如果你只知道公钥，是无法直接推导出私钥的。
 */
public class AuthorisationCodeGenerator {

    public static void main(String[] args) throws Exception {

        generateAuthorisationCode(null, null);
    }

    //验证授权码签名
    public static boolean verifyAuthorisationCode(String licenceStr) throws Exception {
        String strlicence = "ZXlKRVpYWnBZMlZKUkNJNklrRkNRMFJGUmtjaUxDSk1hV05sYm1ObFFYQndJam9pUlVOQkxVcEJWa0V5TGpBaUxDSk1hV05sYm1ObFJXNWtJam9pTWpBeU5TMHdOaTB6TUNKOSBNYkhtSm5FYmhpMlE5WS93YzNKWVdYeDM5a1IxcUM4WjYwS1NBZ1FHOEJNaWtqR2pKSlp5UkZ5M0xKV2IvakhaZ0dGanJUa1o2bnVka1hRVWtlZVlCM0YyVG9EeXYyZERud3g1TmJTa294WnZVT0VlWm95V2NBM2duYnIrWU96K0IxdktZRzg1bDVBWTZiVUYrNTJyR3JtdWYvRnlTdUp6M2R6L2RMZ0xvOXM9";
        final Base64.Decoder decoder = Base64.getDecoder();
        final LicenceState licence = new LicenceState();
        //base64解码
        String strData = (new String(decoder.decode(strlicence), "UTF-8"));
        System.out.println(strData);
        String[] data = strData.split(" ");
        //拿到License数据
        String JsonDataBase64 = data[0];
        //拿到签名数据
        String SignData = data[1];
        System.out.println(JsonDataBase64);
        System.out.println(SignData);

        //验证签名
        PublicKey publicKey = RSAUtil.string2PublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbGEDep0lZhO9OHeYRO8XX+NTb4gU1ncUOX7M+eXLABUPSvccdT5DvnBRI+bE8/hH3cx3WSjmmvAJxgQcRRVIJpNgYelM19qXxCvrz3JeVcI/jFzDvdBYc+0QomgrqYYC8YDjulFfnFz6cWZgAXy9vVHDe4PSJrsXV5vPwPENUawIDAQAB");
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(JsonDataBase64.getBytes("UTF-8"));
        boolean isVerify = signature.verify(decoder.decode(SignData));

        System.out.println(isVerify);
        return isVerify;
    }
    //生成授权码
    public static void generateAuthorisationCode(String sequence, String licenceEnd) throws Exception {
        // 生成RSA密钥对
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        //添加默认序列号(机器码，正式要通过SequenceUtility.getSequence()方法获取，测试码ABCDEFG可以绕过)
        if(StringUtils.isBlank(sequence)){
            sequence = "ABCDEFG";
        }
        //添加默认到期时间
        if(StringUtils.isBlank(licenceEnd)){
            licenceEnd = getEndDate();
        }

        // 生成授权码
        String strlicence = generateLicenceBase64Str(sequence, licenceEnd, privateKey);

        System.out.println("生成的授权码: " + strlicence);
        //将生成的拼接授权码base64编码
        System.out.println("生成的授权码(base64编码后): " + Base64.getEncoder().encodeToString(strlicence.getBytes("UTF-8")));
    }


    /**
     * 生成授权码
     */
    public static String generateLicenceBase64Str(String sequence, String licenceEnd, PrivateKey privateKey) throws Exception {
        // 1. 生成JSON数据
        JSONObject jsonDataObj = new JSONObject();
        jsonDataObj.put("DeviceID", sequence);
        jsonDataObj.put("LicenceEnd", licenceEnd);
        String jsonData = jsonDataObj.toString();

        // 2. Base64编码JSON数据
        String jsonBase64 = Base64.getEncoder().encodeToString(jsonData.getBytes("UTF-8"));

        // 3. 对Base64编码的JSON数据进行签名
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(jsonBase64.getBytes("UTF-8"));
        byte[] signedData = signature.sign();

        // 4. Base64编码签名数据
        String signBase64 = Base64.getEncoder().encodeToString(signedData);

        // 5. 拼接授权码
        return jsonBase64 + " " + signBase64;
    }

    /**
     * 生成RSA密钥对
     */
    private static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    //获取到期时间
    public static String getEndDate(){
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 计算一年后的日期
        LocalDate oneYearLater = currentDate.plusYears(1);
        // 格式化日期为 yyyy-MM-dd
        String formattedDate = oneYearLater.toString();

        return formattedDate;
    }

}