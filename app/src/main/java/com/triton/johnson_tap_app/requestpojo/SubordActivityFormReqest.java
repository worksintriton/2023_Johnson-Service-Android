package com.triton.johnson_tap_app.requestpojo;

import java.util.List;

public class SubordActivityFormReqest {
//    {
//        "user_phone":"9887766544321",
//            "current_date":"23-10-2022",
//            "Data":[
//        {
//            "EMPNO": "E16622",
//                "ENAME": "SHEIK MOHAMMED SHAHINSHA B",
//                "FN": "LL",
//                "AN": "",
//                "PER_IN_HR": "1",
//                "PER_OFF": "Office",
//                "REASON": "for Testing Process"
//        },
//        {
//            "EMPNO": "E15511",
//                "ENAME": "LOGANATHAN J",
//                "FN": "",
//                "AN": "PP",
//                "PER_IN_HR": "",
//                "PER_OFF": "",
//                "REASON": "All Ok"
//        }
//    ]
//    }
    private String user_phone;
    private String current_date;
    private List<DataBean> Data;
    private String EMPNO;
    private String ENAME;
    private String DATE;
    private String FN;
    private String AN;
    private String PER_IN_HR;
    private String PER_OFF;
    private String REASON;

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public String getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(String EMPNO) {
        this.EMPNO = EMPNO;
    }

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getFN() {
        return FN;
    }

    public void setFN(String FN) {
        this.FN = FN;
    }

    public String getAN() {
        return AN;
    }

    public void setAN(String AN) {
        this.AN = AN;
    }

    public String getPER_IN_HR() {
        return PER_IN_HR;
    }

    public void setPER_IN_HR(String PER_IN_HR) {
        this.PER_IN_HR = PER_IN_HR;
    }

    public String getPER_OFF() {
        return PER_OFF;
    }

    public void setPER_OFF(String PER_OFF) {
        this.PER_OFF = PER_OFF;
    }

    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON;
    }

    public static class DataBean{
        private String EMPNO;
        private String ENAME;
        private String DATE;
        private String FN;
        private String AN;
        private String PER_IN_HR;
        private String PER_OFF;
        private String REASON;

        public String getEMPNO() {
            return EMPNO;
        }

        public void setEMPNO(String EMPNO) {
            this.EMPNO = EMPNO;
        }

        public String getENAME() {
            return ENAME;
        }

        public void setENAME(String ENAME) {
            this.ENAME = ENAME;
        }

        public String getDATE() {
            return DATE;
        }

        public void setDATE(String DATE) {
            this.DATE = DATE;
        }

        public String getFN() {
            return FN;
        }

        public void setFN(String FN) {
            this.FN = FN;
        }

        public String getAN() {
            return AN;
        }

        public void setAN(String AN) {
            this.AN = AN;
        }

        public String getPER_IN_HR() {
            return PER_IN_HR;
        }

        public void setPER_IN_HR(String PER_IN_HR) {
            this.PER_IN_HR = PER_IN_HR;
        }

        public String getPER_OFF() {
            return PER_OFF;
        }

        public void setPER_OFF(String PER_OFF) {
            this.PER_OFF = PER_OFF;
        }

        public String getREASON() {
            return REASON;
        }

        public void setREASON(String REASON) {
            this.REASON = REASON;
        }
    }
}
