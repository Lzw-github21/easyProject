package easy.project.Mapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author xuliukai
 * @since 2023/10/26 16:55
 */
@Setter
@Getter
@Data
public class EntitySql {
    private String sql;
    private HashMap<String, Object> params;
}
