package com.triton.johnson_tap_app.responsepojo;

import androidx.annotation.NonNull;

import java.util.List;

public class GetFetchAttendanceListResponse {
    /*"Status": "Success",
    "Message": "Updated",
    "Code": 200*/
    private String Status;
    private String Message;
    private String Code;
    private List<DataBean> Data;

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

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    /*  "EMPNO": "E16622",
        "ENAME": "SHEIK MOHAMMED SHAHINSHA B",
        "DATE": "",
        "FN": "",
        "AN": "",
        "PER_IN_HR": "",
        "PER_OFF": "",
         "REASON": ""

    */



    public static class DataBean
    {
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

        @NonNull
        @Override
        public String toString() {
            return super.toString();
        }
    }

}
