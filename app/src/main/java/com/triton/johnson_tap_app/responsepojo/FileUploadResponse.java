package com.triton.johnson_tap_app.responsepojo;

public class FileUploadResponse {

    /**
     * Status : Success
     * Message : file upload success
     * Data : http://52.25.163.13:3000/api/uploads/andoridscreen.jpg
     * Code : 200
     */

    private String Status;
    private String Message;
    private String Data;
    private int Code;

    public FileUploadResponse(String status, String message, String data, int code) {
        Status = status;
        Message = message;
        Data = data;
        Code = code;
    }

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

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}
