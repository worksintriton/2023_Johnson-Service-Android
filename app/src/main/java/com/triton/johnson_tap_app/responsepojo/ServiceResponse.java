package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class ServiceResponse {

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
        private String service_name;
        private String last_used_time;
        private String uploaded_count;
        private String pending_count;
        private String failur_count;
        private String paused_count;
        private String job_count;

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;

        }

        public String getLast_used_time() {
            return last_used_time;
        }

        public void setLast_used_time(String last_used_time) {
            this.last_used_time = last_used_time;

        }

        public String getUploaded_count() {
            return uploaded_count;
        }

        public void setUploaded_count(String uploaded_count) {
            this.uploaded_count = uploaded_count;
        }

        public String getPending_count() {
            return pending_count;
        }

        public void setPending_count(String pending_count) {
            this.pending_count = pending_count;

        }

        public String getFailur_count() {
            return failur_count;
        }

        public void setFailur_count(String failur_count) {
            this.failur_count = failur_count;

        }

        public String getPaused_count() {
            return paused_count;
        }

        public void setPaused_count(String paused_count) {
            this.paused_count = paused_count;

        }

        public String getJob_count() {
            return job_count;
        }

        public void setJob_count(String job_count) {
            this.job_count = job_count;

        }

    }
}
