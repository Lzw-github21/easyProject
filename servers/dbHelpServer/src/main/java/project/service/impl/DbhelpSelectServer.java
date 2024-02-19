package project.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.basic.datatable.DataTable;
import project.service.DBhelper;

import java.util.HashMap;

/**
 * @author 李志威
 * @Description
 * @date 2024/2/1
 */
@Slf4j
@Service
public class DbhelpSelectServer {

    @Autowired
    DBhelper dBhelper;

    public void test1() throws Exception {
        System.out.println("开始执行!");
        DataTable dataRows;
        dataRows = dBhelper.QueryDataTable("","selecttest1",new HashMap<>());
        System.out.println("输出结果"+dataRows);
    }
    public void test2() throws Exception {
        DataTable dataRows;
        dataRows = dBhelper.QueryDataTable("","selecttest2",new HashMap<>());
        System.out.println(dataRows);
    }

    public void test3() throws Exception {
        DataTable dataRows;
        dataRows = dBhelper.QueryDataTable("","selecttest3",new HashMap<>());
        System.out.println(dataRows);
    }
    public void test4() throws Exception {
        System.out.println("开始执行!");
        DataTable dataRows;
        dataRows = dBhelper.QueryDataTable("","selecttest4",new HashMap<>());
        System.out.println("输出结果"+dataRows);
    }
}
