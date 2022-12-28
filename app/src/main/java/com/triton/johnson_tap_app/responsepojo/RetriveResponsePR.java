package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class RetriveResponsePR {

    private String Status;
    private String Message;
    private Data Data;
    private Integer Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public RetriveResponsePR.Data getData() {
        return Data;
    }

    public void setData(RetriveResponsePR.Data data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }


    public class Data {

        private String SMU_SCH_COMPNO;
        private String SMU_SCH_SERTYPE;
        private String action_req_customer;
        private String customer_name;
        private String customer_number;
        private String customer_signature;
        private List<FieldValueDatum> field_value_data = null;
        private String job_date;
        private String job_id;
        private String job_status_type;
        private String mr_1;
        private String mr_10;
        private String mr_2;
        private String mr_3;
        private String mr_4;
        private String mr_5;
        private String mr_6;
        private String mr_7;
        private String mr_8;
        private String mr_9;
        private String mr_status;
        private String pm_status;
        private String preventive_check;
        private String tech_signature;
        private String user_mobile_no;
        private int page_number;
        private int subPage_number;
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

        public String getAction_req_customer() {
            return action_req_customer;
        }

        public void setAction_req_customer(String action_req_customer) {
            this.action_req_customer = action_req_customer;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getCustomer_number() {
            return customer_number;
        }

        public void setCustomer_number(String customer_number) {
            this.customer_number = customer_number;
        }

        public String getCustomer_signature() {
            return customer_signature;
        }

        public void setCustomer_signature(String customer_signature) {
            this.customer_signature = customer_signature;
        }

        public List<FieldValueDatum> getField_value_data() {
            return field_value_data;
        }

        public void setField_value_data(List<FieldValueDatum> field_value_data) {
            this.field_value_data = field_value_data;
        }

        public String getJob_date() {
            return job_date;
        }

        public void setJob_date(String job_date) {
            this.job_date = job_date;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getJob_status_type() {
            return job_status_type;
        }

        public void setJob_status_type(String job_status_type) {
            this.job_status_type = job_status_type;
        }

        public String getMr_1() {
            return mr_1;
        }

        public void setMr_1(String mr_1) {
            this.mr_1 = mr_1;
        }

        public String getMr_10() {
            return mr_10;
        }

        public void setMr_10(String mr_10) {
            this.mr_10 = mr_10;
        }

        public String getMr_2() {
            return mr_2;
        }

        public void setMr_2(String mr_2) {
            this.mr_2 = mr_2;
        }

        public String getMr_3() {
            return mr_3;
        }

        public void setMr_3(String mr_3) {
            this.mr_3 = mr_3;
        }

        public String getMr_4() {
            return mr_4;
        }

        public void setMr_4(String mr_4) {
            this.mr_4 = mr_4;
        }

        public String getMr_5() {
            return mr_5;
        }

        public void setMr_5(String mr_5) {
            this.mr_5 = mr_5;
        }

        public String getMr_6() {
            return mr_6;
        }

        public void setMr_6(String mr_6) {
            this.mr_6 = mr_6;
        }

        public String getMr_7() {
            return mr_7;
        }

        public void setMr_7(String mr_7) {
            this.mr_7 = mr_7;
        }

        public String getMr_8() {
            return mr_8;
        }

        public void setMr_8(String mr_8) {
            this.mr_8 = mr_8;
        }

        public String getMr_9() {
            return mr_9;
        }

        public void setMr_9(String mr_9) {
            this.mr_9 = mr_9;
        }

        public String getMr_status() {
            return mr_status;
        }

        public void setMr_status(String mr_status) {
            this.mr_status = mr_status;
        }

        public String getPm_status() {
            return pm_status;
        }

        public void setPm_status(String pm_status) {
            this.pm_status = pm_status;
        }

        public String getPreventive_check() {
            return preventive_check;
        }

        public void setPreventive_check(String preventive_check) {
            this.preventive_check = preventive_check;
        }

        public String getTech_signature() {
            return tech_signature;
        }

        public void setTech_signature(String tech_signature) {
            this.tech_signature = tech_signature;
        }

        public String getUser_mobile_no() {
            return user_mobile_no;
        }

        public void setUser_mobile_no(String user_mobile_no) {
            this.user_mobile_no = user_mobile_no;
        }

        public int getPage_number() {
            return page_number;
        }

        public void setPage_number(int page_number) {
            this.page_number = page_number;
        }

        public int getSubPage_number() {
            return subPage_number;
        }

        public void setSubPage_number(int subPage_number) {
            this.subPage_number = subPage_number;
        }
    }
    public class FieldValueDatum {

        private String field_cat_id;
        private String field_comments;
        private String field_group_id;
        private String field_name;
        private String field_value;

        public String getField_cat_id() {
            return field_cat_id;
        }

        public void setField_cat_id(String field_cat_id) {
            this.field_cat_id = field_cat_id;
        }

        public String getField_comments() {
            return field_comments;
        }

        public void setField_comments(String field_comments) {
            this.field_comments = field_comments;
        }

        public String getField_group_id() {
            return field_group_id;
        }

        public void setField_group_id(String field_group_id) {
            this.field_group_id = field_group_id;
        }

        public String getField_name() {
            return field_name;
        }

        public void setField_name(String field_name) {
            this.field_name = field_name;
        }

        public String getField_value() {
            return field_value;
        }

        public void setField_value(String field_value) {
            this.field_value = field_value;
        }
    }
}
