package EcbProject.cn.controller.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@TableName("`user`")
public class User2 {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
