package com.triton.johnson_tap_app.responsepojo;

public class MR_DetailsResponse {

    private String Status;
    private String Message;

    private DataBean Data;
    private int Code;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public static class DataBean {

        private String mr_no;
        private String ack_date;
        private String customer_name;
        private String address1;
        private String address2;
        private String address3;
        private String address4;
        private String pin;
        private String mobile_no;
        private String service_type;
        private String mechanic_name;


        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getAddress3() {
            return address3;
        }

        public void setAddress3(String address3) {
            this.address3 = address3;
        }

        public String getAddress4() {
            return address4;
        }

        public void setAddress4(String address4) {
            this.address4 = address4;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getMechanic_name() {
            return mechanic_name;
        }

        public void setMechanic_name(String mechanic_name) {
            this.mechanic_name = mechanic_name;
        }

        public String getMr_no() {
            return mr_no;
        }

        public void setMr_no(String mr_no) {
            this.mr_no = mr_no;
        }

        public String getAck_date() {
            return ack_date;
        }

        public void setAck_date(String ack_date) {
            this.ack_date = ack_date;
        }
    }
}
