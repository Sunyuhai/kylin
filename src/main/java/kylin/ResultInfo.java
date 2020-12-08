package kylin;

import java.io.Serializable;



public class ResultInfo implements Serializable {
    private boolean flag;
    private String liebie;
    private String ziwangshu;
    private String youxiaoziwang;
    private String zhujishu;
    private String ziwangIP;
    private String guangpodizhi;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLiebie() {
        return liebie;
    }

    public void setLiebie(String liebie) {
        this.liebie = liebie;
    }

    public String getZiwangshu() {
        return ziwangshu;
    }

    public void setZiwangshu(String ziwangshu) {
        this.ziwangshu = ziwangshu;
    }

    public String getYouxiaoziwang() {
        return youxiaoziwang;
    }

    public void setYouxiaoziwang(String youxiaoziwang) {
        this.youxiaoziwang = youxiaoziwang;
    }

    public String getZhujishu() {
        return zhujishu;
    }

    public void setZhujishu(String zhujishu) {
        this.zhujishu = zhujishu;
    }

    public String getZiwangIP() {
        return ziwangIP;
    }

    public void setZiwangIP(String ziwangIP) {
        this.ziwangIP = ziwangIP;
    }

    public String getGuangpodizhi() {
        return guangpodizhi;
    }

    public void setGuangpodizhi(String guangpodizhi) {
        this.guangpodizhi = guangpodizhi;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", liebie='" + liebie + '\'' +
                ", ziwangshu='" + ziwangshu + '\'' +
                ", youxiaoziwang='" + youxiaoziwang + '\'' +
                ", zhujishu='" + zhujishu + '\'' +
                ", ziwangIP='" + ziwangIP + '\'' +
                ", guangpodizhi='" + guangpodizhi + '\'' +
                '}';
    }
}
