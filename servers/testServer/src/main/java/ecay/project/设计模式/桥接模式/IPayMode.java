package ecay.project.设计模式.桥接模式;

/**
 * 支付模式接口
 * @author spikeCong
 * @date 2022/9/26
 **/
public interface IPayMode {

    //安全校验功能: 对各种支付模式进行风控校验
    boolean security(String uId);
}
