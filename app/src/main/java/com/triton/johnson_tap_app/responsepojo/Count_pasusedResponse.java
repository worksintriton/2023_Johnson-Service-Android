package com.triton.johnson_tap_app.responsepojo;

public class Count_pasusedResponse {

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
        private String paused_count;

        public String getPaused_count() {
            return paused_count;
        }

        public void setPaused_count(String paused_count) {
            this.paused_count = paused_count;
        }

    }
}
