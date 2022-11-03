package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class RTGS_PopResponse {

    private String Status;
    private String Message;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;

    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;

    }

    public static class DataBean {
        private String FA_BSD_UTRNO;
        private String FA_BSD_BANKDT;
        private String FA_BSD_AMOUNT;
        private String FA_BSD_CUSACNM;
        private String FA_BSD_IFSCCD;
        private String FA_BSD_BALAMT;


        public String getFA_BSD_UTRNO() {
            return FA_BSD_UTRNO;
        }

        public void setFA_BSD_UTRNO(String FA_BSD_UTRNO) {
            this.FA_BSD_UTRNO = FA_BSD_UTRNO;
        }

        public String getFA_BSD_BANKDT() {
            return FA_BSD_BANKDT;
        }

        public void setFA_BSD_BANKDT(String FA_BSD_BANKDT) {
            this.FA_BSD_BANKDT = FA_BSD_BANKDT;
        }

        public String getFA_BSD_AMOUNT() {
            return FA_BSD_AMOUNT;
        }

        public void setFA_BSD_AMOUNT(String FA_BSD_AMOUNT) {
            this.FA_BSD_AMOUNT = FA_BSD_AMOUNT;
        }

        public String getFA_BSD_CUSACNM() {
            return FA_BSD_CUSACNM;
        }

        public void setFA_BSD_CUSACNM(String FA_BSD_CUSACNM) {
            this.FA_BSD_CUSACNM = FA_BSD_CUSACNM;
        }

        public String getFA_BSD_IFSCCD() {
            return FA_BSD_IFSCCD;
        }

        public void setFA_BSD_IFSCCD(String FA_BSD_IFSCCD) {
            this.FA_BSD_IFSCCD = FA_BSD_IFSCCD;
        }

        public String getFA_BSD_BALAMT() {
            return FA_BSD_BALAMT;
        }

        public void setFA_BSD_BALAMT(String FA_BSD_BALAMT) {
            this.FA_BSD_BALAMT = FA_BSD_BALAMT;
        }
    }
}
