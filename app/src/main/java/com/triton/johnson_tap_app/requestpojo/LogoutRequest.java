package com.triton.johnson_tap_app.requestpojo;

public class LogoutRequest {

    /**
     * _id : 5fd30a701978e618628c966c
     * user_feedback :
     * user_rate : 0
     */

    private String _id;
    private String att_end_time;
    private String att_reason;
    private String att_end_lat;
    private String att_end_long;
    private String att_no_of_hrs;
    private String user_mobile_no;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAtt_end_time() {
        return att_end_time;
    }

    public void setAtt_end_time(String att_end_time) {
        this.att_end_time = att_end_time;
    }

    public String getAtt_reason() {
        return att_reason;
    }

    public void setAtt_reason(String att_reason) {
        this.att_reason = att_reason;
    }


    public String getAtt_end_lat() {
        return att_end_lat;
    }

    public void setAtt_end_lat(String att_end_lat) {
        this.att_end_lat = att_end_lat;
    }

    public String getAtt_end_long() {
        return att_end_long;
    }

    public void setAtt_end_long(String att_end_long) {
        this.att_end_long = att_end_long;
    }

    public String getAtt_no_of_hrs() {
        return att_no_of_hrs;
    }

    public void setAtt_no_of_hrs(String att_no_of_hrs) {
        this.att_no_of_hrs = att_no_of_hrs;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }
}
