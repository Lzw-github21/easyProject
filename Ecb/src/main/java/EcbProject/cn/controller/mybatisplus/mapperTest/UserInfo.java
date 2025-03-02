package EcbProject.cn.controller.mybatisplus.mapperTest;

import lombok.Data;

@Data
public class UserInfo {

    private String id;
    private String username;
    private Integer age;
}
//    SET NAMES utf8mb4;
//
//        -- ----------------------------
//        -- Table structure for user
//        -- ----------------------------
//        DROP TABLE IF EXISTS `userinfo`;
//        CREATE TABLE `userinfo`  (
//        `id` int NOT NULL auto_increment COMMENT '主键id',
//        `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
//        `age` int NOT NULL COMMENT '年龄',
//        PRIMARY KEY (`id`) USING BTREE
//        ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;
//
//        INSERT into userinfo(name, age) values('张三', 32);
//        INSERT into userinfo(name, age) values('李四', 18);
//        INSERT into userinfo(name, age) values('王五', 23);

