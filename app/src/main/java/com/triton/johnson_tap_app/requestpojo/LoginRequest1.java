package com.triton.johnson_tap_app.requestpojo;

public class LoginRequest1 {


    /**
     * user_id : 12345
     * user_password : 12345
     * last_login_time : 20-10-2021 11:00 AM
     */

    private String user_mobile_no;
    private String user_password;
    private String device_id;

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
