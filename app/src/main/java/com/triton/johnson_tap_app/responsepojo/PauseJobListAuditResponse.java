package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class PauseJobListAuditResponse {

    private String Status;
    private String Message;
    private List<PauseData> Data = null;
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

    public List<PauseData> getData() {
        return Data;
    }

    public void setData(List<PauseData> data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public class PauseData {

        private String OM_OSA_COMPNO;
        private String job_id;
        private String name;
        private String date;

        public String getOM_OSA_COMPNO() {
            return OM_OSA_COMPNO;
        }

        public void setOM_OSA_COMPNO(String OM_OSA_COMPNO) {
            this.OM_OSA_COMPNO = OM_OSA_COMPNO;
        }

        public String getJob_id() {
            return job_id;
        }

        public void setJob_id(String job_id) {
            this.job_id = job_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
