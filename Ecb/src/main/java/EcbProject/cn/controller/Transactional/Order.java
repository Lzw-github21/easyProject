package EcbProject.cn.controller.Transactional;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("orders")
@Data
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private StatusEnum status;
    private BigDecimal totalAmount;
    @TableLogic
    private String remark;
}