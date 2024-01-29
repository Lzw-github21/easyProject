package project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 李志威
 * @Description
 * @date 2024/1/26
 */
@Data   //lombok注解
@TableName("user1")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}