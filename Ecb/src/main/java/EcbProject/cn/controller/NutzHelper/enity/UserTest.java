package EcbProject.cn.controller.NutzHelper.enity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 *
 */
@Data
@Table("t_user_test")
public class UserTest  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Name()
    @Column("row_guid")
    private String rowGuid;
    @Id //(auto = false) //自增不需要设置
    private Integer id;
    @Column
    private Integer recordType;
}
