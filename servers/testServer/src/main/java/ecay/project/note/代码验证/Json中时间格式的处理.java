package ecay.project.note.代码验证;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/json")
public class Json中时间格式的处理 {
    //过去json数据
    @GetMapping("/test")
    public JSONObject test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", System.currentTimeMillis());
        jsonObject.put("date2",new Date());
        jsonObject.put("date3",new Date().getTime());
        return jsonObject;
    }
    //现在json数据
    @GetMapping("/test2")
    public String test2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", System.currentTimeMillis());
        jsonObject.put("date2",new Date());
        jsonObject.put("date3",new Date().getTime());
        return jsonObject.toJSONString();
    }

    public static void main(String[] args) {
        System.out.println("SELECT DISTINCT TBBuilderLicenceManage.prjCode,TBBuilderLicenceManage.ContractMoney,TBBuilderLicenceManage.BUILDERLICENCENUM, TBProjectInfo.PRJNUM, TBProjectInfo.PRJNAME AS NAME,\n" +
                "TBProjectInfo.PrjTypeNum AS Category, BUILDCORPNAME, BUILDCORPCODE, '' AS ContractorCorpCode, '' as CorpTypeNum, sheng.AdminAreaName AS PROVINCENAME,\n" +
                "shi.AdminAreaName AS CITYNAME, countynum, ALLINVEST AS Invest, ALLAREA AS BuildingArea, ifnull(TO_CHAR(PlanBDate, 'yyyy-mm-dd'), '') AS StartDate, \n" +
                "ifnull(TO_CHAR (planedate, 'yyyy-mm-dd'), '') AS CompleteDate, TBPrjPropertyDic.PrjPropertyName AS PRJPROPERTYNAME, TBPrjFunctionDic.PrjFunctionName AS PRJFUNCTIONNAME,\n" +
                "Re_TBBuilderLicenceManage_report.ISFINISH,Re_TBProjectFinishCheckInfo_Report.edate, ADDRESS, LEGALMAN, BUILDCORPADDRESS, BUILDERCORPLEADER, TBEconTypeDic.EconTypeName AS ECONTYPENAME, LEGALNAME, TBProjectInfo.PRJCODE AS PrjAssignCode, TBProjectInfo.updatetime,\n" +
                "TBProjectInfo.createtime, to_char (TBBuilderLicenceManage.releasedate, 'yyyy-MM-dd') releasedate \n" +
                "FROM TBProjectInfo INNER JOIN TBBuilderLicenceManage ON TBProjectInfo.ROW_GUID = TBBuilderLicenceManage.PrjGuid \n" +
                "left join Re_TBBuilderLicenceManage_report on Re_TBBuilderLicenceManage_report.BUILDERLICENCENUM = TBBuilderLicenceManage.BUILDERLICENCENUM \n" +
                "left join Re_TBProjectFinishCheckInfo_Report on TBBuilderLicenceManage.BUILDERLICENCENUM = Re_TBProjectFinishCheckInfo_Report.BUILDERLICENCENUM\n" +
                "LEFT JOIN TBXzqdmDic sheng ON sheng.AdminAreaClassID = tbprojectinfo.provincenum \n" +
                "LEFT JOIN TBXzqdmDic shi ON shi.AdminAreaClassID = tbprojectinfo.citynum \n" +
                "LEFT JOIN TBPrjFunctionDic ON TBPrjFunctionDic.PrjFunctionNum = TBProjectInfo.PrjFunctionNum \n" +
                "LEFT JOIN TBPrjPropertyDic ON TBPrjPropertyDic.PrjPropertyNum = TBProjectInfo.PrjPropertyNum \n" +
                "LEFT JOIN TBEconTypeDic ON TBEconTypeDic.EconTypeNum = TBProjectInfo.EconTypeNum $condition and isdate(TBBuilderLicenceManage.releasedate) and (Re_TBProjectFinishCheckInfo_Report.isdelete is null or  Re_TBProjectFinishCheckInfo_Report.isdelete = 0 )limit @offset, @pageSize");
    }
}
