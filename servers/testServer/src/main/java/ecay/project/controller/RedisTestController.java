package ecay.project.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 李志威
 * @Description
 * @date 2023/8/22
 */
@RestController
public class RedisTestController {
    public static void main(String[] args) {
        System.out.println("insert into TB_GoodInfo(ROW_GUID,RecordGuid,IsGlPrj,PtjName,Mark,LhType,LhNeiRong,LhDate,YouXiaoTime,Lhfs,scDate)( select guid() ROW_GUID,cp.ROW_GUID RecordGuid,t.Isglgcxm,t.Gcxmmc,t.Mark,t.RewardType,t.RewardNeiRong,t.FPraiseDate,t.YouXiaoTime,t.FenShu,getdate() scDate\n" +
                "        from(select row_number()over(partition by--qylh.CorpName, qylh.CorpCode,\n" +
                "                        qylh.RecordGuid, xmlh.Gcxmmc, xmlh.RewardType order by xmlh.RewardNeiRong)rn,\n" +
                "                --qylh.CorpName, qylh.CorpCode,\n" +
                "                qylh.RecordGuid, xmlh.Isglgcxm, xmlh.Gcxmmc, xmlh.Mark, xmlh.RewardType, xmlh.RewardNeiRong, xmlh.FPraiseDate, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime)YouXiaoTime,\n" +
                "        case when IsCJDW=1 then convert (numeric(8, 2), jl.FenShu)/2 else jl.FenShu end FenShu,case when xmlh.Gcxmlx = 2\n" +
                "        then 2 when xmlh.Gcxmlx = 3 then 1 when xmlh.Gcxmlx = 1 then 1 when xmlh.Gcxmlx = 99 then 99 else NULL end\n" +
                "        Gcxmlx, xmlh.IsCJDW\n" +
                "        from TB_GoodCreditinfo qylh\n" +
                "        left join TB_XMGoodCreditinfo xmlh on qylh.ROW_GUID = xmlh.RecodeGuid\n" +
                "        left join RewardContentDic jl on xmlh.RewardNeiRong = jl.RewardContentNum\n" +
                "        where xmlh.Isglgcxm = 1 and xmlh.RewardType in (1001, 1002, 1003, 1009)\n" +
                "        and getdate () < dateadd(m, jl.YouXiaoMonth, xmlh.FPraiseDate))t\n" +
                "        inner join (\n" +
                "                select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, RecordGuid, ID from TB_CreditScore\n" +
                "        where DateDiff (dd, scdate, getdate())=0 \n" +
                ")cp on cp.RecordGuid = t.RecordGuid and cp.ProjectType = t.Gcxmlx\n" +
                "                -- and cp.CorpName = t.CorpName and cp.CorpCode = t.CorpCode\n" +
                "        where t.rn = 1\n" +
                "        union all\n" +
                "        select guid () ROW_GUID, cp.ROW_GUID\n" +
                "        RecordGuid, t.Isglgcxm, t.Gcxmmc, t.Mark, t.RewardType, t.RewardNeiRong, t.FPraiseDate, t.YouXiaoTime, t.FenShu, getdate()\n" +
                "        scDate\n" +
                "        from(select row_number()over(partition by--qylh.CorpName, qylh.CorpCode,\n" +
                "                        qylh.RecordGuid, xmlh.RewardType order by xmlh.RewardNeiRong)rn,\n" +
                "                --qylh.CorpName, qylh.CorpCode,\n" +
                "                qylh.RecordGuid, xmlh.Isglgcxm, xmlh.Gcxmmc, xmlh.Mark, xmlh.RewardType, xmlh.RewardNeiRong, xmlh.FPraiseDate, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime)YouXiaoTime, jl.FenShu,\n" +
                "        case when xmlh.Gcxmlx = 2 then 2 when xmlh.Gcxmlx = 3 then 1 when xmlh.Gcxmlx = 1 then 1 when xmlh.Gcxmlx = 99\n" +
                "        then 99 else NULL end Gcxmlx, xmlh.IsCJDW\n" +
                "        from TB_GoodCreditinfo qylh\n" +
                "        left join TB_XMGoodCreditinfo xmlh on qylh.ROW_GUID = xmlh.RecodeGuid\n" +
                "        left join RewardContentDic jl on xmlh.RewardNeiRong = jl.RewardContentNum\n" +
                "        where xmlh.Isglgcxm = 0 and xmlh.RewardType in (1005, 1006, 1007, 1008)\n" +
                "        and getdate () < dateadd(m, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime), xmlh.FPraiseDate)  )t\n" +
                "        inner join (\n" +
                "                select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, RecordGuid, ID from TB_CreditScore\n" +
                "        where DateDiff (dd, scdate, getdate())=0 \n" +
                ")cp on cp.RecordGuid = t.RecordGuid-- and cp.CorpName = t.CorpName and cp.CorpCode = t.CorpCode\n" +
                "        where t.rn = 1\n" +
                "        union all\n" +
                "        select guid () ROW_GUID, cp.ROW_GUID\n" +
                "        RecordGuid, t.Isglgcxm, t.Gcxmmc, t.Mark, t.RewardType, t.RewardNeiRong, t.FPraiseDate, t.YouXiaoTime, t.FenShu, getdate()\n" +
                "        scDate\n" +
                "        from(select qylh.RecordGuid, xmlh.Isglgcxm, xmlh.Gcxmmc, xmlh.Mark, xmlh.RewardType, xmlh.RewardNeiRong, xmlh.FPraiseDate, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime)YouXiaoTime,\n" +
                "        case when notRelationAwards=1 then convert (numeric(8, 2), jl.FenShu)/2 else jl.FenShu end FenShu,\n" +
                "        case when xmlh.Gcxmlx = 2 then 2 when xmlh.Gcxmlx = 3 then 1 when xmlh.Gcxmlx = 1 then 1 when xmlh.Gcxmlx = 99\n" +
                "        then 99 else NULL end Gcxmlx, xmlh.IsCJDW\n" +
                "        from TB_GoodCreditinfo qylh\n" +
                "        left join TB_XMGoodCreditinfo xmlh on qylh.ROW_GUID = xmlh.RecodeGuid\n" +
                "        left join RewardContentDic jl on xmlh.RewardNeiRong = jl.RewardContentNum\n" +
                "        where xmlh.Isglgcxm = 0 and xmlh.RewardType = 1004\n" +
                "        and getdate () < dateadd(m, isnull(jl.YouXiaoMonth, xmlh.YouXiaoTime), xmlh.FPraiseDate)  )t\n" +
                "        inner join (\n" +
                "                select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, RecordGuid, ID from TB_CreditScore\n" +
                "        where DateDiff (dd, scdate, getdate())=0 \n" +
                ")cp on cp.RecordGuid = t.RecordGuid )");
    }
}
