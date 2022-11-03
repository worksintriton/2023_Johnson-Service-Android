package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class JobListResponse {

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
        private String customer_name;
        private String pm_date;
        private String status;
        private String SMU_SCH_COMPNO;
        private String SMU_SCH_SERTYPE;
        private String SMU_SCQH_QUOTENO;
        private String SMU_ACK_COMPNO;
        private String OM_OSA_COMPNO;
        private String name;
        private String date;

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

        public String getPm_date() {
            return pm_date;
        }

        public void setPm_date(String pm_date) {
            this.pm_date = pm_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;

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

        public String getOM_OSA_COMPNO() {
            return OM_OSA_COMPNO;
        }

        public void setOM_OSA_COMPNO(String OM_OSA_COMPNO) {
            this.OM_OSA_COMPNO = OM_OSA_COMPNO;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
