package easy.project.note.加密与安全验证.JWT签名;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @Description： jwt工具类
 * @Date：Created in 15:28 2019/10/24
 * @Modified By：
 */
@Component
public class 浙里建使用示例 {
    /**
     * 密钥 -- 根据实际项目，这里可以做成配置
     */
    @Value("${security-key.jwt-key}")
    private String KEY;

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(KEY);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param issuer
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String issuer, Map<String, Object> claims, long ttlMillis) throws Exception {

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        //SignatureAlgorithm 包含了jwt所有签名算法，jwt示例代码中的 Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);只包含jwt签名算法中对称密钥算法，HMACAlgorithm: HS256、HS384、HS512
        //HS256算法，基于 HMAC 和 SHA-256 摘要算法，生成数字签名。用于验证数据的完整性和真实性
        //AES 也是对称加密， 是用于数据加密的对称加密算法。
        //两者虽然都使用对称密钥，但解决的问题和应用场景完全不同。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名的时候使用的秘钥secret，这个秘钥不能外露。它就是你服务端的私钥，在任何场景都不应该流露出去
        // 一旦客户端得知这个secret, 那就意味着客户端可以自我签发jwt
        SecretKey key = generalKey();

        // 为payload添加标准声明和私有声明
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，先设置私有声明，覆盖标准声明
                .setClaims(claims)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击
                .setId(id)
                // iat: jwt的签发时间
                .setIssuedAt(now)
                // issuer：jwt签发人
                .setIssuer(issuer)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        // 签名秘钥，和生成的签名的秘钥一致
        SecretKey key = generalKey();
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
