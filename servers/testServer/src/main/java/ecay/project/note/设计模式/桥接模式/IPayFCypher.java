package ecay.project.note.设计模式.桥接模式;

public class IPayFCypher implements IPayMode {
    @Override
    public boolean security(String uId) {
        System.out.println("密码支付,风控校验-环境安全");
        return false;
    }
}
