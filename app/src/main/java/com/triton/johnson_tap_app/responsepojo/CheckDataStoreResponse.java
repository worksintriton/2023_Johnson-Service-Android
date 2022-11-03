package com.triton.johnson_tap_app.responsepojo;

public class CheckDataStoreResponse {

    /**
     * Status : Success
     * Message : Storage List
     * Data : {"work_status":"Started","start_time":"","pause_time":""}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * work_status : Started
     * start_time :
     * pause_time :
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
        private String work_status;
        private String start_time;
        private String pause_time;

        public String getWork_status() {
            return work_status;
        }

        public void setWork_status(String work_status) {
            this.work_status = work_status;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getPause_time() {
            return pause_time;
        }

        public void setPause_time(String pause_time) {
            this.pause_time = pause_time;
        }
    }
}
