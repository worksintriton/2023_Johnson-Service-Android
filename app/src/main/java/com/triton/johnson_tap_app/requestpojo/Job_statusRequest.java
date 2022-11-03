package com.triton.johnson_tap_app.requestpojo;

public class Job_statusRequest {

    private String user_mobile_no;
    private String service_name;
    private String job_id;
    private String SMU_SCH_COMPNO;
    private String SMU_SCH_SERTYPE;
    private String OM_OSA_COMPNO;
    private  String SMU_SCQH_QUOTENO;
    private String SMU_ACK_COMPNO;


    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getSMU_SCH_COMPNO() {
        return SMU_SCH_COMPNO;
    }

    public void setSMU_SCH_COMPNO(String SMU_SCH_COMPNO) {
        this.SMU_SCH_COMPNO = SMU_SCH_COMPNO;
    }

    public String getSMU_SCH_SERTYPE() {
        return SMU_SCH_SERTYPE;
    }

    public void setSMU_SCH_SERTYPE(String SMU_SCH_SERTYPE) {
        this.SMU_SCH_SERTYPE = SMU_SCH_SERTYPE;
    }

    public String getOM_OSA_COMPNO() {
        return OM_OSA_COMPNO;
    }

    public void setOM_OSA_COMPNO(String OM_OSA_COMPNO) {
        this.OM_OSA_COMPNO = OM_OSA_COMPNO;
    }

    public String getSMU_SCQH_QUOTENO() {
        return SMU_SCQH_QUOTENO;
    }

    public void setSMU_SCQH_QUOTENO(String SMU_SCQH_QUOTENO) {
        this.SMU_SCQH_QUOTENO = SMU_SCQH_QUOTENO;
    }

    public String getSMU_ACK_COMPNO() {
        return SMU_ACK_COMPNO;
    }

    public void setSMU_ACK_COMPNO(String SMU_ACK_COMPNO) {
        this.SMU_ACK_COMPNO = SMU_ACK_COMPNO;
    }
}
