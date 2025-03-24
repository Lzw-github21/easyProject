package EcbProject.cn.controller.NutzHelper.Exception;

import EcbProject.cn.controller.NutzHelper.Enum.AppHttpCodeEnum;

/**
 * @author Dee
 * @date 2022/7/5
 * <p>Description:
 */
public class NutzHelperException extends RuntimeException {

    private AppHttpCodeEnum appHttpCodeEnum;

    public NutzHelperException(AppHttpCodeEnum appHttpCodeEnum){
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public AppHttpCodeEnum getAppHttpCodeEnum() {
        return appHttpCodeEnum;
    }
}
