package ecay.project.设计模式.桥接模式;

public class IPayFaceModeImpl implements IPayMode {
    @Override
    public boolean security(String uId) {
        System.out.println("人脸支付,风控校验-脸部识别");
        return true;
    }
}
