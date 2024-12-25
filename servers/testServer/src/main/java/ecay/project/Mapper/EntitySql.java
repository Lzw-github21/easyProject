package ecay.project.Mapper;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author xuliukai
 * @since 2023/10/26 16:55
 */
@Setter
@Getter
public class EntitySql {
    private String sql;
    private HashMap<String, Object> params;
}
