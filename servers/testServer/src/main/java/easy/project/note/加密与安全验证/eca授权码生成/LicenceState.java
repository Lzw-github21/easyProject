package easy.project.note.加密与安全验证.eca授权码生成;

public class LicenceState {

    private int status = 0;  //0:无授权  1:开发环境 2:即将过期 3:无效授权 4:授权已过期  5:正常授权
    private String desc = "";
    private License licence;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public License getLicence() {
        return licence;
    }

    public void setLicence(License licence) {
        this.licence = licence;
    }
}
