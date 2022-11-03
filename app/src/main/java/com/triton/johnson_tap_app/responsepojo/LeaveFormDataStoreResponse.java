package com.triton.johnson_tap_app.responsepojo;

public class LeaveFormDataStoreResponse {
    /**
     * Status : Success
     * Message : Request Send Successfully
     * Data : {}
     * Code : 200
     */
    private String Status;
    private String Message;
    private LeaveFormDataStoreResponse.DataBean Data;
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

    public LeaveFormDataStoreResponse.DataBean getData() {
        return Data;
    }

    public void setData(LeaveFormDataStoreResponse.DataBean data) {
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
