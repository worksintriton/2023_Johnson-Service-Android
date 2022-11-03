package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class Pasused_ListResponse {

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
        private String job_id;
        private String paused_time;
        private String paused_at;
        private String SMU_SCH_COMPNO;
        private String SMU_SCH_SERTYPE;
        private String SMU_SCQH_QUOTENO;
        private  String SMU_ACK_COMPNO;


        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;

        }

        public String getPaused_time() {
            return paused_time;
        }

        public void setPaused_time(String paused_time) {
            this.paused_time = paused_time;

        }

        public String getPaused_at() {
            return paused_at;
        }

        public void setPaused_at(String paused_at) {
            this.paused_at = paused_at;
        }

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

        public String getSMU_SCQH_QUOTENO() {
            return SMU_SCQH_QUOTENO;
        }

        public void setSMU_SCQH_QUOTENO(String SMU_SCQH_QUOTENO) {
            this.SMU_SCQH_QUOTENO = SMU_SCQH_QUOTENO;
        }

        public String getSMU_ACK_COMPNO() {
            return SMU_ACK_COMPNO;
        }

        public void setSMU_ACK_COMPNO(String SMU_ACK_COMPNO) {
            this.SMU_ACK_COMPNO = SMU_ACK_COMPNO;
        }
    }
}
