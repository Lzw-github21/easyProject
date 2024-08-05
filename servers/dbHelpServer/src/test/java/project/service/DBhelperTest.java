package project.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.entity.basic.datatable.DataTable;
import project.entity.configproperties.EcaSqlsConfig;

import java.util.HashMap;

/**
 * @author 李志威
 * @Description
 * @date 2024/2/1
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class DBhelperTest {


    @Autowired
    DBhelper dBhelper;
    @Autowired
    private EcaSqlsConfig ecaSqlsConfig;

    @Test
    void queryDataTable() throws Exception {
        DataTable dataRows = dBhelper.QueryDataTable("", "selecttest3", new HashMap<>());
        System.out.println(dataRows);
    }

    @Test
    void getIntTest() throws Exception {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sgxkGuid", null);
        ecaSqlsConfig.getMap().put("select_CorpPrjStatusNum_sgxkGuid", "select IFNULL(max(prjstatusnum),0) as prjstatusnum from Re_TBSFCorpInfo_Report where sgxkguid = 'e8fc34c8-4886-456b-b6b9-10e0d2e8c31a' and (isdelete = 0 or isdelete is null)");
        DataTable corpStatusInfo = dBhelper.QueryDataTable("12345678-1111-467d-8609-473195a02fcc", "select_CorpPrjStatusNum_sgxkGuid", hashMap);
        ecaSqlsConfig.getMap().remove("select_CorpPrjStatusNum_sgxkGuid");
        System.out.println(corpStatusInfo);
    }


}