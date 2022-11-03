package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class ActivityGetListNumberResponse {

    private String Status;
    private String Message;
    private int Code;

    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String UKEY;
        private String UKEY_DESC;
        private String CELL_NUMBER;
        private int NEW_ACTIVITY;
        private int WIP_ACTIVITY;
        private String _id;
        private int form_type;

        public String getUKEY() {
            return UKEY;
        }

        public void setUKEY(String UKEY) {
            this.UKEY = UKEY;
        }

        public String getUKEY_DESC() {
            return UKEY_DESC;
        }

        public void setUKEY_DESC(String UKEY_DESC) {
            this.UKEY_DESC = UKEY_DESC;
        }

        public String getCELL_NUMBER() {
            return CELL_NUMBER;
        }

        public void setCELL_NUMBER(String CELL_NUMBER) {
            this.CELL_NUMBER = CELL_NUMBER;
        }

        public int getNEW_ACTIVITY() {
            return NEW_ACTIVITY;
        }

        public void setNEW_ACTIVITY(int NEW_ACTIVITY) {
            this.NEW_ACTIVITY = NEW_ACTIVITY;
        }

        public int getWIP_ACTIVITY() {
            return WIP_ACTIVITY;
        }

        public void setWIP_ACTIVITY(int WIP_ACTIVITY) {
            this.WIP_ACTIVITY = WIP_ACTIVITY;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getForm_type() {
            return form_type;
        }

        public void setForm_type(int form_type) {
            this.form_type = form_type;
        }
    }
}
