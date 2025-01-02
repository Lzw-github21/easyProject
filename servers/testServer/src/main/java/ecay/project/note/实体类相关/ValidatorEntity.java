package ecay.project.note.实体类相关;

import ecay.project.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 如果对象需要通过网络传输（例如 RPC 调用）或持久化存储（例如保存到文件或数据库中），则必须实现 Serializable
 * 示例场景：
 * 1.将对象存储到文件中。
 * 2.将对象通过网络发送到另一个 JVM。
 * 3.将对象存储到分布式缓存（如 Redis）中。
 * 4.是否建议为所有实体类指定 serialVersionUID：建议显式指定，这是一种防御性编程的做法，可以避免未来的潜在问题。
 */
/*
常用的Validator校验只有这么多，其他的几乎用不到
 */
@Data
public class ValidatorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在 6 到 20 之间")
    private String password;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Min(value = 18, message = "年龄必须大于 18 岁")
    private int age;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @URL(message = "个人主页 URL 格式不正确")
    private String website;

    @Length(min = 10, max = 100, message = "地址长度必须在 10 到 100 之间")
    private String address;

    @Range(min = 0, max = 100, message = "分数必须在 0 到 100 之间")
    private int score;

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ValidatorEntity user = new ValidatorEntity();
//        user.setUsername(null);//NotNull会触发
        user.setUsername("");//NotNull和NotBlank都会触发
        user.setPassword("123");
        user.setEmail("invalid-email");
        user.setAge(17);
        user.setPhone("12345678901");
        user.setWebsite("invalid-url");
        user.setAddress("short");
        user.setScore(101);

        Set<ConstraintViolation<ValidatorEntity>> violations = validator.validate(user);
        for (ConstraintViolation<ValidatorEntity> violation : violations) {
            System.out.println(violation.getMessage());
        }
        /*
        输出结果：
          个人主页 URL 格式不正确
          分数必须在 0 到 100 之间
          地址长度必须在 10 到 100 之间
          密码长度必须在 6 到 20 之间
          邮箱格式不正确
          手机号格式不正确
          年龄必须大于 18 岁
          用户名不能为空
         */
    }
}
