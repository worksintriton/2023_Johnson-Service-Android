package com.triton.johnson_tap_app.responsepojo;

import com.triton.johnson_tap_app.responsepojo.CountResponse;

import java.util.List;

public class JobnoFindResponse {

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
        private String customer_name;
        private String contract_no;

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;

        }

        public String getContract_no() {
            return contract_no;
        }

        public void setContract_no(String contract_no) {
            this.contract_no = contract_no;

        }

    }


//    private String Status;
//    private String Message;
//
//    private DataBean Data;
//    private int Code;
//
//    public String getStatus() {
//        return Status;
//    }
//
//    public void setStatus(String Status) {
//        this.Status = Status;
//    }
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public void setMessage(String Message) {
//        this.Message = Message;
//    }
//
//    public DataBean getData() {
//        return Data;
//    }
//
//    public void setData(DataBean Data) {
//        this.Data = Data;
//    }
//
//    public int getCode() {
//        return Code;
//    }
//
//    public void setCode(int Code) {
//        this.Code = Code;
//    }
//
//    public static class DataBean {
//        private String JOBNO;
//        private String CUSNAME;
//        private String CONTNO;
//
//        public String getJOBNO() {
//            return JOBNO;
//        }
//
//        public void setJOBNO(String JOBNO) {
//            this.JOBNO = JOBNO;
//        }
//
//        public String getCUSNAME() {
//            return CUSNAME;
//        }
//
//        public void setCUSNAME(String CUSNAME) {
//            this.CUSNAME = CUSNAME;
//        }
//
//        public String getCONTNO() {
//            return CONTNO;
//        }
//
//        public void setCONTNO(String CONTNO) {
//            this.CONTNO = CONTNO;
//        }
//    }
}
