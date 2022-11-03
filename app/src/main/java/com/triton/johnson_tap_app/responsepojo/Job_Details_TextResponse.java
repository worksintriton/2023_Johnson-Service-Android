package com.triton.johnson_tap_app.responsepojo;

public class Job_Details_TextResponse {

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
        private String text_value;

        public String getText_value() {
            return text_value;
        }

        public void setText_value(String text_value) {
            this.text_value = text_value;
        }

    }
}
