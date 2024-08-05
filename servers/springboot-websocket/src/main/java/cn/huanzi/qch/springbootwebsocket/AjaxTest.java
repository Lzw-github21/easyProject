package cn.huanzi.qch.springbootwebsocket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李志威
 * @Description
 * @date 2023/4/19
 */
@RestController
@RequestMapping("/test")
public class AjaxTest {
    /**
     * 登录
     */
    @RequestMapping("/ajaxTest")
    public ModelAndView ajaxTest(HttpServletRequest request) {
        return new ModelAndView("跨域测试页面.html");
    }

    public static void main(String[] args) {

        System.out.println("{\"addBuildingArea\":\"14470.64\",\"apply_date\":\"2023-11-20 10:56:40\",\"bank_loan\":0.0000,\"build_interest\":0.0000,\"building_area\":14.1105,\"built_area\":14470.64,\"catalog_name\":\"工业项目\",\"civil_cost\":4000.0000,\"contact_name\":\"项权\",\"contact_tel\":\"13735402400\",\"deal_time\":\"\",\"device_cost\":2350.0000,\"division_code\":\"330109\",\"end_year\":\"202702\",\"enterprise_area\":\"浙江省杭州市萧山区义桥镇七里店村\",\"enterprise_date\":\"202306\",\"enterprise_fund\":\"1000.000000\",\"enterprise_name\":\"杭州民升农业科技有限公司\",\"enterprise_scope\":\"173\",\"enterprise_type\":\"\",\"finace_fund\":0.0000,\"financial_service_kind\":\"\",\"fm_dept_name\":\"区发展和改革局\",\"fm_uniqueCoding\":\"330109ZF080000\",\"foreign_land_way\":\"A00005\",\"foreign_total_rate\":1.0000,\"gdzctz\":6750.0000,\"ground_building_area\":14470.64,\"industry\":\"1373\",\"industry_name\":\"制造业 - 农副食品加工业 - 蔬菜、菌类、水果和坚果加工 - 水果和坚果加工\",\"instal_cost\":0.0000,\"is_child_project\":\"0\",\"is_foreign\":\"0\",\"is_xzjsyd\":\"1\",\"ispromiseproject\":\"0\",\"isstandardland\":\"0\",\"land_area\":14.1105,\"landcontractno\":\"3301812023B000864\",\"lerep_certno\":\"91330109MACM7U404B\",\"lerep_certtype\":\"A05300\",\"other_cost\":400.0000,\"other_fund\":0.0000,\"own_fund\":6750.0000,\"pavage_fund\":0.0000,\"place_code\":\"330109\",\"place_code_detail\":\"东至七里店村土地，南至七里店村土地，西至厂房，北至七里店村土地\",\"prepare_cost\":0.0000,\"principal_name\":\"项权\",\"principal_tel\":\"13735402400\",\"project_attributes\":\"A00001\",\"project_code\":\"2311-330109-04-01-789813\",\"project_name\":\"萧政工出〔2023〕51号年产3000吨农副食品加工项目\",\"project_nature\":\"0\",\"project_parent_code\":\"\",\"project_source\":\"2\",\"project_type\":\"A00003\",\"scale_content\":\"项目为农副食品加工业厂房，用于生产农副食品加工产品，农业机械零部件加工，生产规模用地面积9407平方米，建筑面积14470.64平方米。新增地上建筑面积14470.64㎡，新增地下建筑面积0㎡。形成年产3000吨农副食品加工生产能力。(最终以规划和自然资源部门审核为准)\",\"start_year\":\"202402\",\"the_industry\":\"A0000102\",\"total_money\":6750.0000,\"validity_flag\":\"1\",\"zyprojecttype\":\"01\"}");
    }
}
