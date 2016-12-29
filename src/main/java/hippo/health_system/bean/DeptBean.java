package hippo.health_system.bean;

/**
 * Created by huangchaoyuan on 16/12/5.
 */
public class DeptBean {
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName.replaceAll("\\.","").trim();
    }

    public String getDeptIntro() {
        return deptIntro;
    }

    public void setDeptIntro(String deptIntro) {
        this.deptIntro = deptIntro;
    }

    private String deptId;
    private String deptName;
    private String deptIntro;



}
