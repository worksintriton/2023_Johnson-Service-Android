package com.triton.johnson_tap_app.responsepojo;

public class Agent_pop_statusResponse {

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
        private String last_login;
        private String last_job_act;
        private String pending_job_today;
        private String pending_job_total;
        private String completed_job_today;
        private String completed_job_monthly;

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getLast_job_act() {
            return last_job_act;
        }

        public void setLast_job_act(String last_job_act) {
            this.last_job_act = last_job_act;
        }

        public String getPending_job_total() {
            return pending_job_today;
        }

        public void setPending_job_total(String pending_job_today) {
            this.pending_job_today = pending_job_today;
        }

        public String getPending_job_today() {
            return pending_job_total;
        }

        public void setPending_job_today(String pending_job_total) {
            this.pending_job_total = pending_job_total;
        }

        public String getCompleted_job_today() {
            return completed_job_today;
        }

        public void setCompleted_job_today(String completed_job_today) {
            this.completed_job_today = completed_job_today;
        }

        public String getCompleted_job_monthly() {
            return completed_job_monthly;
        }

        public void setCompleted_job_monthly(String completed_job_monthly) {
            this.completed_job_monthly = completed_job_monthly;
        }

    }
}
