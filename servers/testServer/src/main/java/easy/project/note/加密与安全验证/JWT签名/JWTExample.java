package easy.project.note.加密与安全验证.JWT签名;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTExample {

    // 密钥（实际应用中应使用更安全的密钥管理方式）
    private static final String SECRET_KEY = "my-secret-key";

    public static void main(String[] args) {
        // 生成 JWT
        String token = generateJWT("1234567890", "John Doe", 3600); // 有效期为 1 小时
        System.out.println("生成的 JWT: " + token);

        // 验证并解析 JWT
        DecodedJWT decodedJWT = verifyJWT(token);
        if (decodedJWT != null) {
            System.out.println("JWT 验证成功！");
            System.out.println("Subject: " + decodedJWT.getSubject());
            System.out.println("Name: " + decodedJWT.getClaim("name").asString());
            System.out.println("过期时间: " + decodedJWT.getExpiresAt());
        } else {
            System.out.println("JWT 验证失败！");
        }
    }

    /**
     * 生成 JWT
     *
     * @param subject  主题（通常是用户 ID）
     * @param name     用户名称
     * @param expireIn 过期时间（秒）
     * @return 生成的 JWT
     */
    public static String generateJWT(String subject, String name, int expireIn) {
        // 使用 HMAC SHA256 算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        // 计算过期时间
        Date expireDate = new Date(System.currentTimeMillis() + expireIn * 1000);

        // 生成 JWT
        return JWT.create()
                .withSubject(subject) // 主题
                .withClaim("name", name) // 自定义字段
                .withIssuedAt(new Date()) // 签发时间
                .withExpiresAt(expireDate) // 过期时间
                .sign(algorithm); // 签名
    }

    /**
     * 验证并解析 JWT
     *
     * @param token JWT 令牌
     * @return 解析后的 JWT 对象，如果验证失败则返回 null
     */
    public static DecodedJWT verifyJWT(String token) {
        try {
            // 使用相同的密钥和算法验证 JWT
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (Exception e) {
            System.err.println("JWT 验证失败: " + e.getMessage());
            return null;
        }
    }
}