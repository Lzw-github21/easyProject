package easy.project.note.代码验证.测试;

public class ceshi1 {
    public static void main(String[] args) {


            System.out.println("insert into TB_ProjectYJinfo(ROW_GUID,PrjYjName,PrjType,SGXKFZdate,HTJE,RecordGuid,PrjGuid,SCDate,sgxkNum)( select guid() ROW_GUID,yj.PrjName,yj.PrjTypeNum,yj.ReleaseDate,yj.ContractMoney,cp.ROW_GUID RecordGuid,yj.PrjGuid,@dateNum SCDate,yj.BuilderLicenceNum sgxkNum\n" +
                    "        from(select ROW_GUID, CorpName, CityNum, CorpCode, scdate, ProjectType, ID, OrganizeCode from TB_CreditScore\n" +
                    "                where scdate = @today )cp\n" +
                    "        inner join\n" +
                    "        (select distinct replace (sf.QYName, ' ', '')CorpName,case when len\n" +
                    "        (replace(sf.QYCode, ' ', '')) = 18 then SUBString (replace(sf.QYCode, ' ', ''), 9, 8) ||\n" +
                    "        '-' || SUBString(replace(sf.QYCode, ' ', ''), 17, 1) else replace(sf.QYCode, ' ', '') end CorpCode, prj.\n" +
                    "        PrjName, prj.PrjNum, sgxk.ManageDepAuditDate ReleaseDate, prj.Row_Guid PrjGuid, sgxk.BuilderLicenceNum,\n" +
                    "        case prj.PrjTypeNum when\n" +
                    "        '02' then '02' when '2' then '02' else'01' end as PrjTypeNum,\n" +
                    "        case prj.PrjTypeNum when\n" +
                    "        '03' then sgxk.ContractMoney else sgxk.ContractMoney end as ContractMoney\n" +
                    "        from Re_TBProjectInfo_report prj \n" +
                    "        left join Re_TBBuilderLicenceManage_report sgxk on sgxk.PrjGuid = prj.Row_Guid\n" +
                    "        left join Re_TBSFCorpInfo_report sf on sf.SgxkGuid = sgxk.Row_Guid\n" +
                    "        where prj.sf_StatusNum = 104 and sf.CorpTypeNum = 3 and CensorStatusNum = 128\n" +
                    "        and sgxk.OptionType in (0, 2, 4)and sgxk.ManageDepAuditDate is not null\n" +
                    "        and getdate () < dateadd(m, 24, sgxk.ManageDepAuditDate)\n" +
                    // "        union all\n" +
                    //"        select  a.CorpName, case when len(replace(a.CorpCode, ' ', ''))=18 then SUBSTRING(replace(a.CorpCode, ' ', ''),9,8) ||'-'||SUBSTRING(replace(a.CorpCode, ' ', ''),17,1) else replace(a.CorpCode, ' ', '')  end  CorpCode,a.PerfName as PrjName,a.PrjNum,b.ManageDepAuditDate ReleaseDate,a.PrjGuid,a.BuilderLicenceNum,case a.zzzy when '11401001' then '01' else '02' end  as PrjTypeNum,replace(b.ContractMoney,'万元','') AS ContractMoney  from  Re_TBCorpPerformance a left join Re_TBBuilderLicenceManage_report b on a.BuilderLicenceNum=b.BuilderLicenceNum where dateadd(m,24,a.BDate)>getdate() and PerfTypeNum=3 and zzxl like '%10'  and dateadd(m,24,BDate)>getdate() AND zzzy in ('11401001','11401010')\n" +
                    ")yj on yj.CorpCode = cp.OrganizeCode\n" +
                    "                -- (yj.CorpCode = cp.CorpCode or yj.CorpCode = cp.OrganizeCode)\n" +
                    "        and yj.PrjTypeNum = cp.ProjectType )");

         System.out.println("update TB_JLCreditScore a inner join \n" +
                " (select b.*,\n" +
                " --CASE WHEN scores1 + scores2 + scores3 + scores4 + scores5 + scores6 + scores7 > 3 THEN 3\n" +
                " --ELSE scores1 + scores2 + scores3 + scores4 + scores5 + scores6 + scores7 \n" +
                " --同时具有甲级乙级只取甲级分数\n" +
                " CASE WHEN scores1 > 0 THEN scores1 WHEN scores2 > 0 THEN scores2 WHEN scores3 > 0 THEN scores3 WHEN scores4 > 0 THEN scores4 \n" +
                " WHEN scores5 > 0 THEN scores5 WHEN scores6 > 0 THEN scores6 WHEN scores7 > 0 THEN scores7   \n" +
                " END  totalScores\n" +
                " from (select row_guid,CorpCode,CorpName,mark,\n" +
                "    CASE WHEN mark LIKE '%工程监理综合资质%' THEN 3 ELSE 0 END scores1,\n" +
                "    CASE WHEN mark LIKE '%房屋建筑工程监理甲级%' or mark LIKE '%市政公用工程监理甲级%' THEN 2 ELSE 0 END scores2,\n" +
                "    CASE WHEN mark LIKE '%房屋建筑工程监理乙级%' or mark LIKE '%市政公用工程监理乙级%' THEN 1 ELSE 0 END scores3,\n" +
                "    CASE WHEN mark LIKE '%房屋建筑工程监理丙级%' or mark LIKE '%市政公用工程监理丙级%' THEN 1 ELSE 0 END scores4,\n" +
                "    CASE WHEN mark LIKE '%房屋建筑工程监理丁级%' or mark LIKE '%市政公用工程监理丁级%' THEN 1 ELSE 0 END scores5,\n" +
                "    CASE WHEN mark LIKE '%房屋建筑甲级资质%' or mark LIKE '%市政公用工程乙级资质%' THEN 1 ELSE 0 END scores6,\n" +
                "    CASE WHEN mark LIKE '%房屋建筑乙级资质%' or mark LIKE '%市政公用工程甲级资质%' THEN 1 ELSE 0 END scores7\n" +
                "from  \n" +
                "（select r.row_guid,r.CorpCode,r.CorpName,LISTAGG(cd.mark, ',') WITHIN GROUP (ORDER BY cd.mark ASC) as mark\n" +
                "from TBCorpBasicInfo a \n" +
                "inner join TBRecordInfo r on a.RecordGuid=r.ROW_GUID \n" +
                "left join TBCORPCERTINFO c on a.row_guid=c.torowguid \n" +
                "left join TBCORPCERTDETAILINFO cd on c.ROW_GUID=cd.RECORDGUID \n" +
                "where cd.mark != '' and cd.mark is not null \n" +
                "group by r.ROW_GUID,r.CorpCode,r.CorpName ) a ) b ) staticsScore on a.CorpCode = staticsScore.CorpCode and a.corpName = staticsScore.corpName\n" +
                "set a.JiChuScore = a.JiChuScore + staticsScore.totalScores\n" +
                "where DateDiff(dd, a.scdate, getdate()) = 0");
    }
}
