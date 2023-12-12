package ecay.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
/**
 * @author 李志威
 * @Description
 * @date 2023/8/22
 */
@RestController
public class RedisTestController {
    public static void main(String[] args) {
        System.out.println("SELECT distinct zjk_ws_ry_jbxx.IDCard FROM( " +
                "SELECT IDCard " +
                "FROM zjk_ws_ry_zhuce b " +
                "UNION ALL SELECT IDCard FROM zjk_ws_ry_xianchangrenyuan " +
                "UNION ALL SELECT IDCard FROM zjk_ws_ry_jishugongren " +
                "UNION ALL SELECT IDCard FROM zjk_ws_ry_zhicheng) AS JIHE " +
                "INNER JOIN zjk_ws_ry_jbxx ON JIHE.IDCard = zjk_ws_ry_jbxx.IDCARD " +
                "LEFT JOIN zjk_sw_qyjbxx ON zjk_ws_ry_jbxx.CORPCODE = zjk_sw_qyjbxx.CORPCODE " +
                "WHERE 1 = 1 ");
    }
}
