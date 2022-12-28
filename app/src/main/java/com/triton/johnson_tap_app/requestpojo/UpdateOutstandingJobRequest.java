package com.triton.johnson_tap_app.requestpojo;

public class UpdateOutstandingJobRequest {

    private String job_no;
    private String service_type;
    private String comp_no;
    private String user_mobile_no;
    private String pause_time;

    public String getJob_no() {
        return job_no;
    }

    public void setJob_no(String job_no) {
        this.job_no = job_no;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getComp_no() {
        return comp_no;
    }

    public void setComp_no(String comp_no) {
        this.comp_no = comp_no;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public String getPause_time() {
        return pause_time;
    }

    public void setPause_time(String pause_time) {
        this.pause_time = pause_time;
    }
}
