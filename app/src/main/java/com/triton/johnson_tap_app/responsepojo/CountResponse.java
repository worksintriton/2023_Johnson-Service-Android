package com.triton.johnson_tap_app.responsepojo;

public class CountResponse {

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
        private String services_count;
        private String view_status;
        private String notificaion_count;


        public String getServices_count() {
            return services_count;
        }

        public void setServices_count(String services_count) {
            this.services_count = services_count;
        }

        public String getView_status() {
            return view_status;
        }

        public void setView_status(String view_status) {
            this.view_status = view_status;
        }


        public String getNotificaion_count() {
            return notificaion_count;
        }

        public void setNotificaion_count(String notificaion_count) {
            this.notificaion_count = notificaion_count;
        }
    }
}
