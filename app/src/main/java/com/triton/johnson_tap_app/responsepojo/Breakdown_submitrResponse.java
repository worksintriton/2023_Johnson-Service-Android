package com.triton.johnson_tap_app.responsepojo;


import java.util.List;

public class Breakdown_submitrResponse {


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



        private List<Feedback_detailsBean> feedback_details;
        /**
         * health_issue : [""]
         * pic : [{"image":"http://35.165.75.97:3000/api/uploads/1643865952185.jpg"}]
         * _id : 61fb676dd014ed6b74d3f0ba
         * user_id : 61fb66fad014ed6b74d3f0b6
         * name : Lavanya
         * gender : Female
         * relation_type : Friend
         * dateofbirth : 06-04-2000
         * anymedicalinfo : Have diabetes.
         * covide_vac :
         * weight : 60
         * delete_status : false
         * createdAt : 2022-02-03T05:26:05.648Z
         * updatedAt : 2022-02-03T06:18:57.712Z
         * __v : 0
         */


        public List<Feedback_detailsBean> getFeedback_details() {
            return feedback_details;
        }

        public void setFeedback_details(List<Feedback_detailsBean> feedback_details) {
            this.feedback_details = feedback_details;
        }

        public static class Feedback_detailsBean {
            private String feedback_group_code;
            private String feedback_group_title;
            private String codes;
            private String title;

            public String getFeedback_group_code() {
                return feedback_group_code;
            }

            public void setFeedback_group_code(String feedback_group_code) {
                this.feedback_group_code = feedback_group_code;
            }

            public String getFeedback_group_title() {
                return feedback_group_title;
            }

            public void setFeedback_group_title(String feedback_group_title) {
                this.feedback_group_title = feedback_group_title;
            }

            public String getCodes() {
                return codes;
            }

            public void setCodes(String codes) {
                this.codes = codes;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}