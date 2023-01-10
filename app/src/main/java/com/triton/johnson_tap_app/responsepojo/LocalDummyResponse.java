package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class LocalDummyResponse {

    private String Status;
    private String Message;
    private List<DataBean> Data;

    public LocalDummyResponse() {
    }

    public LocalDummyResponse(String status, String message, List<DataBean> data) {
        Status = status;
        Message = message;
        Data = data;
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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String employee_name;
        private String employee_no;

        public DataBean() {
        }

        public DataBean(String employee_name, String employee_no) {
            this.employee_name = employee_name;
            this.employee_no = employee_no;
        }

        public String getEmployee_name() {
            return employee_name;
        }

        public void setEmployee_name(String employee_name) {
            this.employee_name = employee_name;
        }

        public String getEmployee_no() {
            return employee_no;
        }

        public void setEmployee_no(String employee_no) {
            this.employee_no = employee_no;
        }
    }

}
