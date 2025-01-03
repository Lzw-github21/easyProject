package easy.project.note.代码验证;

import easy.project.note.代码验证.entity.RetainCert;
import easy.project.note.代码验证.entity.RetainDetail;
import easy.project.note.代码验证.entity.TbCorpCertDetailJSB;
import easy.project.note.代码验证.entity.TbCorpCertInfoJSB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class peak代码验证 {
    static final Logger logger = LoggerFactory.getLogger(peak代码验证.class);

    public static void main(String[] args) {
        peak代码验证 peak = new peak代码验证();
//        peak.test1();
        peak.test2();
    }

    public void test1(){
        List<TbCorpCertDetailJSB> detailList = new ArrayList<>();
        for(int i = 0;i < 7;i++) {
            TbCorpCertDetailJSB detail = new TbCorpCertDetailJSB();
            detail.setCertid("certid" + i);
            detail.setTradetypenum(714);
            detail.setTradeboundnum(714);
            if(i == 5) {
                detail.setTradeboundnum(11402018);
                detail.setTradetypeboundchildmark("机电工程");
            }
            if(i == 6) {
                detail.setTradeboundnum(11402036);
                detail.setTradetypeboundchildmark("结构补强");
            }
            detailList.add(detail);
        }
        System.out.println(detailList);
        //1.3将资质详情按certid分类
        Map<String, List<TbCorpCertDetailJSB>> detailMap = detailList.stream().peek(d -> {
            //资质序列号
            String tradeTypeStr = String.valueOf(d.getTradetypenum());
            Integer tradeboundnum = d.getTradeboundnum();
            //资质专业号
            String tradeboundStr = String.valueOf(tradeboundnum);
            //字段映射成诚信平台的编码
            if (tradeTypeStr.contains("714")) {
                d.setTradetypenum(Integer.parseInt(tradeTypeStr.replace("714", "114")));
            }
            if (tradeboundStr.contains("714")) {
                d.setTradeboundnum(Integer.parseInt(tradeboundStr.replace("714", "114")));
            }
            //20240129-由于特殊映射改动
            if (d.getTradeboundnum() == 11402018) {
                String tradetypeboundchildmark = d.getTradetypeboundchildmark();
                if (tradetypeboundchildmark.contains("机电工程")) {
                    d.setTradeboundnum(11402040);
                }
            }
            //20240129-由于特殊映射改动
            if (d.getTradeboundnum() == 11402036) {
                String tradetypeboundchildmark = d.getTradetypeboundchildmark();
                if (tradetypeboundchildmark.contains("结构补强")) {
                    d.setTradeboundnum(11402037);
                } else if (tradetypeboundchildmark.contains("建筑物纠偏和平移")) {
                    d.setTradeboundnum(11402036);
                } else if (tradetypeboundchildmark.contains("起重吊装")) {
                    d.setTradeboundnum(11402038);
                } else if (tradetypeboundchildmark.contains("特种防雷")) {
                    d.setTradeboundnum(11402039);
                } else {
                    d.setTradeboundnum(11402041);
                }
            }
            //设置字段
            d.setZizhimark(d.getMark());
            d.setVersion(1);
            d.setIsdelete(0);
        }).collect(Collectors.groupingBy(TbCorpCertDetailJSB::getCertid));
        logger.info("资质证书数据处理:{}",detailMap);
    }

    public void test2(){
        List<RetainCert> retainCertList = new ArrayList<>();
        List<TbCorpCertInfoJSB> certList = new ArrayList<>();
        List<RetainDetail> retainDetails = new ArrayList<>();
        for(int i = 0;i < 4;i++) {
            RetainCert retainCert = new RetainCert();
            TbCorpCertInfoJSB cert = new TbCorpCertInfoJSB();
            retainCert.setCertid("certid" + i);
            cert.setCertid("certid" + i);
            retainCert.setCerttypenum(714);
            if (i == 2) {
                retainCert.setCertid("certid");
                cert.setCertid("certid");
            }
            retainCert.setRow_guid(UUID.randomUUID().toString());
            retainCertList.add(retainCert);
            certList.add(cert);
        }

        for(int i = 0;i < 4;i++) {
            RetainDetail retainDetail = new RetainDetail();
            retainDetail.setCertid("certid" + i);
            if (i == 2) {
                retainDetail.setCertid("certid" + 1);
            }
            retainDetails.add(retainDetail);
        }
        //2.1 提取库中该企业所有证书编号
        List<String> retainCertIds = retainCertList.stream()
                //非安许
                .filter(c -> c.getCerttypenum() != 8)
                .map(RetainCert::getCertid).collect(Collectors.toList());
        logger.info("提取库中该企业所有证书编号:{}",retainCertIds);
        //2.2 提取部里与库中公共部分 证书编号
        List<String> commentCertIds = certList.stream()
                .map(TbCorpCertInfoJSB::getCertid)
                .filter(retainCertIds::contains).collect(Collectors.toList());
        logger.info("提取部里与库中公共部分 证书编号:{}",commentCertIds);

        logger.info("库里证书信息 证书编号:{}",retainCertList);
        //2.3 提取部里与库中公共部分 证书编号-certGuid
        Map<String, String> commentCertIdGuidMap = retainCertList.stream().filter(c -> c.getCerttypenum() != 8 && commentCertIds.contains(c.getCertid()))
                .collect(Collectors.toMap(c -> c.getCertid(), c -> c.getRow_guid()));
        logger.info("提取部里与库中公共部分 证书编号-certGuid:{}",commentCertIdGuidMap);

        //3.2查询这些公共证书的证书详情标识字段
        Map<String, List<RetainDetail>> retainDetailMapTemp = null;
        //3.2 将公共证书详情按certid分组
        retainDetailMapTemp = retainDetails.stream()
                .collect(Collectors.groupingBy(RetainDetail::getCertid));
        logger.info("将公共证书详情按certid分组:{}",retainDetailMapTemp);
    }
}
