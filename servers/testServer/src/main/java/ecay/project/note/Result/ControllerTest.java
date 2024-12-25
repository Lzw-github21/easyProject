package ecay.project.note.Result;

import cn.ecasoft.basic.datatable.DataRow;
import cn.ecasoft.basic.datatable.DataTable;
import com.alibaba.fastjson.JSONObject;
import ecay.project.Mapper.CommonMapperByDBHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/result")
public class ControllerTest {

    @Autowired
    private CommonMapperByDBHelp commonMapperByDBHelp;

    @GetMapping("/test")
    public R test(){
        return R.ok();
    }
    @GetMapping("/test2")
    public R test2(){
        return R.ok("测试");
    }
    @GetMapping("/test3")
    public R test3(){
        return R.ok("测试","成功了");
    }
    @GetMapping("/test4")
    public R test4(){
        return R.failed();
    }
    @GetMapping("/test5")
    public R test5(){
        return R.failed("失败了");
    }
    @GetMapping("/test6")
    public R test6(){
        return R.failed(ErrorCodeEnum.SYSTEM_ERROR_B0001);
    }
    @GetMapping("/test7")
    public R test7(){
        return R.failed("失败了",ErrorCodeEnum.SYSTEM_ERROR_B0001);
    }
    @GetMapping("/test8")
    public R test8(){
        //没有引入ecacore前，所有返回结果
//        返回："data": "2024-12-19T03:21:42.423+00:00"
//        return R.failed(new Date());
//        返回："data": "2024-12-19T11:24:11.011"
//        return R.failed(LocalDateTime.now());
        //返回："data": 1703008451011
        return R.failed(System.currentTimeMillis());
    }
    @GetMapping("/test9")
    public R test9() throws ParseException {
        HashMap<String, Object> params = new HashMap<>();
        String selectSql = "select * from azx_sync_log where row_guid = '25d69d86-c890-4824-b675-ccd47dcac750'";
        DataTable dataRows = commonMapperByDBHelp.executeSqlForDataTable("", selectSql, params);
        DataRow dataRow = dataRows.getFirst();
        Date time = dataRow.getDate("create_time");
        JSONObject json = new JSONObject();
        //引入ecacore后，所有返回的时间都变成了统一格式：2024-12-24 17:21:31，只有data3返回的是时间戳：1735032091666
        json.put("data1",new Date());
        json.put("data2",LocalDateTime.now());
        json.put("data3",System.currentTimeMillis());
        json.put("data4",time);
        return R.failed(json);
    }
}
