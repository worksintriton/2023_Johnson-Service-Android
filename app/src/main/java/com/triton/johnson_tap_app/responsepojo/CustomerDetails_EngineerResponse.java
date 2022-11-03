package com.triton.johnson_tap_app.responsepojo;

public class CustomerDetails_EngineerResponse {

    private String Status;
    private String Message;

    private DataBean Data;
    private int Code;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public class DataBean {

        private String job_id;
        private String customer_name;
        private String address_one;
        private String address_two;
        private String address_three;
        private String pin_code;
        private String contract_type;
        private String number;
        private String data_and_time;
        private String type;
        private String mech_name;

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getAddress_one() {
            return address_one;
        }

        public void setAddress_one(String address_one) {
            this.address_one = address_one;
        }

        public String getAddress_two() {
            return address_two;
        }

        public void setAddress_two(String address_two) {
            this.address_two = address_two;
        }

        public String getAddress_three() {
            return address_three;
        }

        public void setAddress_three(String address_three) {
            this.address_three = address_three;
        }

        public String getPin_code() {
            return pin_code;
        }

        public void setPin_code(String pin_code) {
            this.pin_code = pin_code;
        }

        public String getContract_type() {
            return contract_type;
        }

        public void setContract_type(String contract_type) {
            this.contract_type = contract_type;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getData_and_time() {
            return data_and_time;
        }

        public void setData_and_time(String data_and_time) {
            this.data_and_time = data_and_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMech_name() {
            return mech_name;
        }

        public void setMech_name(String mech_name) {
            this.mech_name = mech_name;
        }
    }
}
