package com.triton.johnson_tap_app.requestpojo;

public class SkipJobDetailRequest {

    private String key_no;
    private String service_type;
    private String remarks;
    private String time;
    private String user_mobile_number;
    private String status;
    private String job_id;

    public String getKey_no() {
        return key_no;
    }

    public void setKey_no(String key_no) {
        this.key_no = key_no;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser_mobile_number() {
        return user_mobile_number;
    }

    public void setUser_mobile_number(String user_mobile_number) {
        this.user_mobile_number = user_mobile_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
}
