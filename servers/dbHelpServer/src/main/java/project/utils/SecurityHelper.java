package project.utils;

public interface SecurityHelper {

    /**
     * MD5加密
     *
     * @param textToHash
     * @return
     */
    String MD5HashString(String textToHash) throws Exception;

    /**
     * 加密
     *
     * @param plainText 待加密字符串
     * @param useHash   是否使用hash
     * @return 加密完成字符串
     * @throws Exception
     */
    String Encrypt(String plainText, boolean useHash) throws Exception;

    /**
     * 解密
     *
     * @param base64  待解密字符串
     * @param useHash 是否使用hash
     * @return 解密完成字符串
     * @throws Exception
     */
    String Decrypt(String base64, boolean useHash) throws Exception;

    /**
     * 解密链接密码
     *
     * @param base64 待解密字符串
     * @return 解密完成字符串
     * @throws Exception
     */
    String DecryptConnPwd(String base64) throws Exception;

    /**
     * MD5加密32位小写
     *
     * @throws Exception
     */
    String Md5Digest(String encryptStr) throws Exception;

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @return
     * @throws Exception
     */
    String AESDecrypt(String cipherText);

    /**
     * AES 解密
     *
     * @param cipherText 密文
     * @param key        密钥
     * @param iv         偏移量
     * @return
     * @throws Exception
     */
    String AESDecrypt(String cipherText, String key, String iv) throws Exception;

    /**
     * AES 加密操作
     *
     * @param plainText 内容明文
     * @return
     * @throws Exception
     */
    String AESEncrypt(String plainText);

    /**
     * AES 加密操作
     *
     * @param plainText 内容明文
     * @param key       密钥
     * @param iv        偏移量
     * @return
     * @throws Exception
     */
    String AESEncrypt(String plainText, String key, String iv) throws Exception;

    /**
     * SHA256加密
     *
     * @param str
     * @return
     */
    String SHA256Encrypt(String str);
}
