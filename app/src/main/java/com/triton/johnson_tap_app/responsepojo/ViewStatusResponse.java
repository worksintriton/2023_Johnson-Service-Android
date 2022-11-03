package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class ViewStatusResponse {

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

        private List<Service_listBean> service_listdata;
        public List<Service_listBean> getService_listdata() {
            return service_listdata;
        }

        private String status_title;
        private String count;

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;

        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;

        }

        public static class Service_listBean {
            private String service_name;
            private String count;


            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }

    }
}
