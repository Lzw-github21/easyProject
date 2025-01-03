package easy.project.note.设计模式.桥接模式;

import java.math.BigDecimal;

public class Test01 {
    public static void main(String[] args) {
        System.out.println("测试场景1: 微信支付、人脸方式.");
        Pay wxpay = new WxPay(new IPayFaceModeImpl());
        wxpay.transfer("wx_00100100","10001900",new BigDecimal(100));

        System.out.println();

        System.out.println("测试场景2: 支付宝支付、指纹方式");
        Pay zfbPay = new ZfbPay(new IPayFingerprintModeImpl());
        zfbPay.transfer("jlu1234567","567689999999",new BigDecimal(200));
    }
}
