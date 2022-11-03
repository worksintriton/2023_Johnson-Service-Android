package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class JobFetchAddressResponse {

    /**
     * Status : Success
     * Message : Respose Data
     * Data : ["RACHIT HEMWANI","234","VISHAL KHAND","GOMTI NAGAR","GOMTI NAGAR","LUCKNOW","PIN: 226010"]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    private List<String> Data;

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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<String> getData() {
        return Data;
    }

    public void setData(List<String> Data) {
        this.Data = Data;
    }
}
