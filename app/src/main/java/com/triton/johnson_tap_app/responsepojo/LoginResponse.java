package com.triton.johnson_tap_app.responsepojo;

public class LoginResponse {


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
        private String user_id;
        private String user_again_code;
        private String user_email_id;
        private String user_password;
        private String user_name;
        private String user_designation;
        private String user_type;
        private String user_status;
        private String user_role;
        private String reg_date_time;
        private String user_token;
        private String last_login_time;
        private String last_logout_time;
        private int __v;

        public String getUser_role() {
            return user_role;
        }

        public void setUser_role(String user_role) {
            this.user_role = user_role;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_again_code() {
            return user_again_code;
        }

        public void setUser_again_code(String user_again_code) {
            this.user_again_code = user_again_code;
        }

        public String getUser_email_id() {
            return user_email_id;
        }

        public void setUser_email_id(String user_email_id) {
            this.user_email_id = user_email_id;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_designation() {
            return user_designation;
        }

        public void setUser_designation(String user_designation) {
            this.user_designation = user_designation;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getReg_date_time() {
            return reg_date_time;
        }

        public void setReg_date_time(String reg_date_time) {
            this.reg_date_time = reg_date_time;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_logout_time() {
            return last_logout_time;
        }

        public void setLast_logout_time(String last_logout_time) {
            this.last_logout_time = last_logout_time;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
