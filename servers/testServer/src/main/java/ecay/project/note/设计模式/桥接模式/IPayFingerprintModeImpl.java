package ecay.project.note.设计模式.桥接模式;

public class IPayFingerprintModeImpl implements IPayMode {
    @Override
    public boolean security(String uId) {
        System.out.println("指纹支付,风控校验-指纹信息");
        return true;
    }
}
