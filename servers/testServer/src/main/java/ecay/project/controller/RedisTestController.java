package ecay.project.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李志威
 * @Description
 * @date 2023/8/22
 */
@RestController
public class RedisTestController {
    public static void main(String[] args) {
        System.out.println("SELECT\n" +
                "\tDISTINCT zjk_gongchengxiangmujibenxinxi.PrjName,\n" +
                "\tCityName,\n" +
                "\tPrjNum,\n" +
                "\tPrjTypeName,\n" +
                "\tBuildCorpName,\n" +
                "\tSjcorp,\n" +
                "\tManagedepDate,\n" +
                "\tAllinvest,\n" +
                "\tAllarea,\n" +
                "\tGuid,\n" +
                "\tzjk_jungongyanshou.prjGuid AS IsJGYS\n" +
                "FROM  zjk_gongchengxiangmujibenxinxiNew zjk_gongchengxiangmujibenxinxi\n" +
                "INNER JOIN  zjk_shigongxuke_qiyezhuti ON\n" +
                "\tzjk_gongchengxiangmujibenxinxi.Guid = zjk_shigongxuke_qiyezhuti.prjGuid\n" +
                "LEFT JOIN  zjk_jungongyanshou ON\n" +
                "\tzjk_gongchengxiangmujibenxinxi.Guid = zjk_jungongyanshou.prjGuid\n" +
                "WHERE 1=1 \n");

        String corpCode = "9133000014823804XD";
        String code = corpCode.substring(8, corpCode.length() - 2) + "-" + corpCode.substring(16, 17);
        System.out.println(code);
        List<String> list = new ArrayList<>();
        Boolean result = ((ArrayList)list).equals("");
    }
}
