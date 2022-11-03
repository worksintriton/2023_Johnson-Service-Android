package com.triton.johnson_tap_app.responsepojo;

public class SubordActivityFormResponse {
    /**
     * Status : Success
     * Message : Added successfully
     * Data : {}
     * Code : 200
     */
    private String Status;
    private String Message;
    private DataBean Data;
    private int Code;

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


    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean data) {
        Data = data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public static class DataBean {
    }
}
