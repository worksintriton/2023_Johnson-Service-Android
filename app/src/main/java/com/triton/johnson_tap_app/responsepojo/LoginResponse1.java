package com.triton.johnson_tap_app.responsepojo;

public class LoginResponse1 {

    private String Status;
    private String Message;

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
        private String user_password;
        private String user_per_mob;
        private String user_name;
        private String user_email;
        private String user_introduced_by;
        private String user_location;
        private String user_mob_model;
        private String user_mob_os;
        private String user_mob_make;
        private String device_no;
        private String organisation_name;
        private String status;
        private String mobile_issue_date;
        private String Documents;
        private String delete_status;
        private String last_login_time;
        private String last_logout_time;
        private String user_token;
        private String user_type;
        private String user_id;
        private int __v;
        private String emp_type;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

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

        public String getUser_per_mob() {
            return user_per_mob;
        }

        public void setUser_per_mob(String user_per_mob) {
            this.user_per_mob = user_per_mob;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_introduced_by() {
            return user_introduced_by;
        }

        public void setUser_introduced_by(String user_introduced_by) {
            this.user_introduced_by = user_introduced_by;
        }

        public String getUser_location() {
            return user_location;
        }

        public void setUser_location(String user_location) {
            this.user_location = user_location;
        }

        public String getUser_mob_model() {
            return user_mob_model;
        }

        public void setUser_mob_model(String user_mob_model) {
            this.user_mob_model = user_mob_model;
        }

        public String getUser_mob_os() {
            return user_mob_os;
        }

        public void setUser_mob_os(String user_mob_os) {
            this.user_mob_os = user_mob_os;
        }

        public String getUser_mob_make() {
            return user_mob_make;
        }

        public void setUser_mob_make(String user_mob_make) {
            this.user_mob_make = user_mob_make;
        }


        public String getDevice_no() {
            return device_no;
        }

        public void setDevice_no(String device_no) {
            this.device_no = device_no;
        }

        public String getOrganisation_name() {
            return organisation_name;
        }

        public void setOrganisation_name(String organisation_name) {
            this.organisation_name = organisation_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMobile_issue_date() {
            return mobile_issue_date;
        }

        public void setMobile_issue_date(String mobile_issue_date) {
            this.mobile_issue_date = mobile_issue_date;
        }

        public String getDocuments() {
            return Documents;
        }

        public void setDocuments(String Documents) {
            this.Documents = Documents;
        }

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
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

        public String getEmp_type() {
            return emp_type;
        }

        public void setEmp_type(String emp_type) {
            this.emp_type = emp_type;
        }
    }
}
