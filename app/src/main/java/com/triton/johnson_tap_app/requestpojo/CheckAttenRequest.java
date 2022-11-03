package com.triton.johnson_tap_app.requestpojo;

public class CheckAttenRequest {


    /**
     * user_id : 12345
     * user_password : 12345
     * last_login_time : 20-10-2021 11:00 AM
     */

    private String user_mobile_no;
    private String att_date;

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public String getAtt_date() {
        return att_date;
    }

    public void setAtt_date(String att_date) {
        this.att_date = att_date;
    }

}
