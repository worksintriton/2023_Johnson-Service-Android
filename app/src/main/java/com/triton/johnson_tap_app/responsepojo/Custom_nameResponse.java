package com.triton.johnson_tap_app.responsepojo;

public class Custom_nameResponse {

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
        private String customer_name;
        private String FRDT;
        private String TODT;

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getFRDT() {
            return FRDT;
        }

        public void setFRDT(String FRDT) {
            this.FRDT = FRDT;
        }

        public String getTODT() {
            return TODT;
        }

        public void setTODT(String TODT) {
            this.TODT = TODT;
        }
    }
}
