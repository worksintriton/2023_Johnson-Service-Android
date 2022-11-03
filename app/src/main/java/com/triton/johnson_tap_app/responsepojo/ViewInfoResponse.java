package com.triton.johnson_tap_app.responsepojo;

public class ViewInfoResponse {

    /**
     * Status : Success
     * Message : Respose Data
     * Data : {"ST_MDH_SEQNO":433757,"ST_MDH_DCNO":"FDTN252111159","ST_MDH_DCDT":"2022-03-01T10:06:04.000Z","ST_MDH_STATUS":"G","ST_MDH_VEHICLENO":"AP28TC3358","ST_MDH_GPNO":"GPTN252111158","ST_MDH_GPDT":"2022-03-01T10:06:04.000Z","ST_MDH_BILLTO":"TG01"}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * ST_MDH_SEQNO : 433757
     * ST_MDH_DCNO : FDTN252111159
     * ST_MDH_DCDT : 2022-03-01T10:06:04.000Z
     * ST_MDH_STATUS : G
     * ST_MDH_VEHICLENO : AP28TC3358
     * ST_MDH_GPNO : GPTN252111158
     * ST_MDH_GPDT : 2022-03-01T10:06:04.000Z
     * ST_MDH_BILLTO : TG01
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
        private int ST_MDH_SEQNO;
        private String ST_MDH_DCNO;
        private String ST_MDH_DCDT;
        private String ST_MDH_STATUS;
        private String ST_MDH_VEHICLENO;
        private String ST_MDH_GPNO;
        private String ST_MDH_GPDT;
        private String ST_MDH_BILLTO;

        public int getST_MDH_SEQNO() {
            return ST_MDH_SEQNO;
        }

        public void setST_MDH_SEQNO(int ST_MDH_SEQNO) {
            this.ST_MDH_SEQNO = ST_MDH_SEQNO;
        }

        public String getST_MDH_DCNO() {
            return ST_MDH_DCNO;
        }

        public void setST_MDH_DCNO(String ST_MDH_DCNO) {
            this.ST_MDH_DCNO = ST_MDH_DCNO;
        }

        public String getST_MDH_DCDT() {
            return ST_MDH_DCDT;
        }

        public void setST_MDH_DCDT(String ST_MDH_DCDT) {
            this.ST_MDH_DCDT = ST_MDH_DCDT;
        }

        public String getST_MDH_STATUS() {
            return ST_MDH_STATUS;
        }

        public void setST_MDH_STATUS(String ST_MDH_STATUS) {
            this.ST_MDH_STATUS = ST_MDH_STATUS;
        }

        public String getST_MDH_VEHICLENO() {
            return ST_MDH_VEHICLENO;
        }

        public void setST_MDH_VEHICLENO(String ST_MDH_VEHICLENO) {
            this.ST_MDH_VEHICLENO = ST_MDH_VEHICLENO;
        }

        public String getST_MDH_GPNO() {
            return ST_MDH_GPNO;
        }

        public void setST_MDH_GPNO(String ST_MDH_GPNO) {
            this.ST_MDH_GPNO = ST_MDH_GPNO;
        }

        public String getST_MDH_GPDT() {
            return ST_MDH_GPDT;
        }

        public void setST_MDH_GPDT(String ST_MDH_GPDT) {
            this.ST_MDH_GPDT = ST_MDH_GPDT;
        }

        public String getST_MDH_BILLTO() {
            return ST_MDH_BILLTO;
        }

        public void setST_MDH_BILLTO(String ST_MDH_BILLTO) {
            this.ST_MDH_BILLTO = ST_MDH_BILLTO;
        }
    }
}
