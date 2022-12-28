package com.triton.johnson_tap_app.responsepojo;

public class CheckOutstandingJobResponse {

    private String Status;
    private String Message;
    private Data Data;
    private Integer Code;

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

    public CheckOutstandingJobResponse.Data getData() {
        return Data;
    }

    public void setData(CheckOutstandingJobResponse.Data data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public class Data {

        private String job_no;
        private String start_time;
        private String service_type;
        private String comp_no;

        public String getJob_no() {
            return job_no;
        }

        public void setJob_no(String job_no) {
            this.job_no = job_no;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getComp_no() {
            return comp_no;
        }

        public void setComp_no(String comp_no) {
            this.comp_no = comp_no;
        }
    }
}
