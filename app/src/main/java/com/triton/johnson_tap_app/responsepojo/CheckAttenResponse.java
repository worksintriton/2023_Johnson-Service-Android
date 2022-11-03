package com.triton.johnson_tap_app.responsepojo;

public class CheckAttenResponse {


    /**
     * Status : Success
     * Message : User Details
     * Data : {"_id":"61a8cc5a77cefc37a966657b","user_id":"12345","user_email_id":"mohammed@gmail.com","user_password":"12345","user_name":"Mohammed","user_designation":"Admin","user_type":"Mobile","user_status":"Available","reg_date_time":"23-10-2021 11:00 AM","user_token":"","last_login_time":"20-10-2021 11:00 AM","last_logout_time":"","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 61a8cc5a77cefc37a966657b
     * user_id : 12345
     * user_email_id : mohammed@gmail.com
     * user_password : 12345
     * user_name : Mohammed
     * user_designation : Admin
     * user_type : Mobile
     * user_status : Available
     * reg_date_time : 23-10-2021 11:00 AM
     * user_token :
     * last_login_time : 20-10-2021 11:00 AM
     * last_logout_time :
     * __v : 0
     */

    private DataBean Data;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private String _id;
        private String user_mobile_no;
        private String user_name;
        private String att_date;
        private String att_start_time;
        private String att_end_time;
        private String att_status;
        private String att_reason;
        private String att_start_lat;
        private String att_start_long;
        private String att_end_lat;
        private String att_end_long;
        private String att_no_of_hrs;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

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

        public String getAtt_start_time() {
            return att_start_time;
        }

        public void setAtt_start_time(String att_start_time) {
            this.att_start_time= att_start_time;
        }

        public String getAtt_end_time() {
            return att_end_time;
        }

        public void setAtt_end_time(String att_end_time) {
            this.att_end_time = att_end_time;
        }

        public String getAtt_status() {
            return att_status;
        }

        public void setAtt_status(String att_status) {
            this.att_status = att_status;
        }

        public String getAtt_reason() {
            return att_reason;
        }

        public void setAtt_reason(String att_reason) {
            this.att_reason = att_reason;
        }

        public String getAtt_start_lat() {
            return att_start_lat;
        }

        public void setAtt_start_lat(String att_start_lat) {
            this.att_start_lat = att_start_lat;
        }

        public String getAtt_start_long() {
            return att_start_long;
        }

        public void setAtt_start_long(String att_start_long) {
            this.att_start_long = att_start_long;
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

        public void setAtt_no_of_hrs(String att_no_of_hr) {
            this.att_no_of_hrs = att_no_of_hrs;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
