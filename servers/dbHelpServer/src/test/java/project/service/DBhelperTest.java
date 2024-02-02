package project.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.entity.basic.datatable.DataTable;

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

    @Test
    void queryDataTable() throws Exception {
        DataTable dataRows = dBhelper.QueryDataTable("", "selecttest3", new HashMap<>());
        System.out.println(dataRows);
    }
}