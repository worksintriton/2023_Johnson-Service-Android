package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class Feedback_DetailsResponse {

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
        private String feedback_group_code;
        private String feedback_group_title;
        private String codes;
        private String title;
        private boolean isSelected ;


        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

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
