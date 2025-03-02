package project.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.entity.basic.datatable.DataTable;
import project.entity.configproperties.EcaSqlsConfig;
import project.service.DBhelper;

import java.util.HashMap;

@Controller
public class QueryTest {

    @Autowired
    DBhelper dbHelper;
    @Autowired
    EcaSqlsConfig ecaSqlsConfig;

    public void test() throws Exception {
        String sql = "select * from sys_user_identity limit 10";
        ecaSqlsConfig.getMap().put("sql", sql);
        DataTable dataRows = dbHelper.QueryDataTable("",sql,new HashMap<>());
        System.out.println(JSON.toJSONString(dataRows));
    }
}
