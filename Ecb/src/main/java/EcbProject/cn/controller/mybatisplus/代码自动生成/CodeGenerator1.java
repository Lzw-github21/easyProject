package EcbProject.cn.controller.mybatisplus.代码自动生成;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.query.SQLQuery;

import java.util.Collections;

public class CodeGenerator1 {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/eca_mysql?useSSL=false&serverTimezone=UTC", "root", "root")
                .globalConfig(builder -> {
                    builder.author("李志威") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(System.getProperty("user.dir") + "/Ecb" + "/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> {
                    builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                        int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                        if (typeCode == java.sql.Types.SMALLINT) {
                            return DbColumnType.INTEGER;
                        }
                        return typeRegistry.getColumnType(metaInfo);
                    });
                    builder.databaseQueryClass(SQLQuery.class)
                            .typeConvert(new MySqlTypeConvert())
                            .dbQuery(new MySqlQuery());
                })
                .packageConfig(builder ->
                        builder.parent("EcbProject.cn.controller.mybatisplus") // 设置父包名
                                .moduleName("generator") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/Ecb"+ "/src/main/resources/mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder -> {
                    //添加了一下配置无法正常转驼峰
//                            // 配置实体类生成策略
//                            builder.entityBuilder()
//                                    .enableLombok() // 启用 Lombok
//                                    .enableRemoveIsPrefix() // 去掉字段名的 is 前缀
//                                    .enableTableFieldAnnotation() // 启用表字段注解
//                                    .naming(NamingStrategy.no_change) // 表名生成策略
//                                    .columnNaming(NamingStrategy.underline_to_camel); // 列名生成策略
//
//                            // 配置 Controller 生成策略
//                            builder.controllerBuilder()
//                                    .enableRestStyle() // 开启 REST 风格
////                                    .enableHyphenStyle() // 开启驼峰转连字符
//                                    .formatFileName("%sController"); // 格式化文件名
//
//                            // 配置 Service 生成策略
//                            builder.serviceBuilder()
//                                    .formatServiceFileName("%sService") // 格式化 Service 文件名
//                                    .formatServiceImplFileName("%sServiceImpl"); // 格式化 ServiceImpl 文件名
//
//                            // 配置 Mapper 生成策略
//                            builder.mapperBuilder()
//                                    .enableBaseResultMap() // 启用 BaseResultMap
//                                    .enableBaseColumnList() // 启用 BaseColumnList
//                                    .formatMapperFileName("%sMapper") // 格式化 Mapper 文件名
//                                    .formatXmlFileName("%sMapper"); // 格式化 XML 文件名

                            // 配置需要生成的表
                            builder.addInclude("hurc_projectinfo", "azx_hidden_danger"); // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_") // 设置过滤表前缀
                        }
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
