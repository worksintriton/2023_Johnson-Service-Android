package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class SubGroupDetailManagementResponse {


    /**
     * Status : Success
     * Message : State List
     * Data : [{"_id":"61c2f6e404704a43d87eda11","group_id":"61c2f5a104704a43d87ed9fe","sub_group_detail_name":"Sub Name 01","sub_group_detail_created_at":"Group 02","sub_group_detail_update_at":"23-10-2021 11:00 AM","sub_group_detail_created_by":"23-10-2021 11:00 AM","sub_group_detail_updated_by":"Admin","form_type":"1","__v":0},{"_id":"61c2f6e804704a43d87eda13","group_id":"61c2f5a104704a43d87ed9fe","sub_group_detail_name":"Sub Name 02","sub_group_detail_created_at":"Group 02","sub_group_detail_update_at":"23-10-2021 11:00 AM","sub_group_detail_created_by":"23-10-2021 11:00 AM","sub_group_detail_updated_by":"Admin","form_type":"2","__v":0},{"_id":"61c2f6ed04704a43d87eda15","group_id":"61c2f5a104704a43d87ed9fe","sub_group_detail_name":"Sub Name 03","sub_group_detail_created_at":"Group 02","sub_group_detail_update_at":"23-10-2021 11:00 AM","sub_group_detail_created_by":"23-10-2021 11:00 AM","sub_group_detail_updated_by":"Admin","form_type":"3","__v":0},{"_id":"61c2f6f204704a43d87eda17","group_id":"61c2f5a104704a43d87ed9fe","sub_group_detail_name":"Sub Name 04","sub_group_detail_created_at":"Group 02","sub_group_detail_update_at":"23-10-2021 11:00 AM","sub_group_detail_created_by":"23-10-2021 11:00 AM","sub_group_detail_updated_by":"Admin","form_type":"4","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 61c2f6e404704a43d87eda11
     * group_id : 61c2f5a104704a43d87ed9fe
     * sub_group_detail_name : Sub Name 01
     * sub_group_detail_created_at : Group 02
     * sub_group_detail_update_at : 23-10-2021 11:00 AM
     * sub_group_detail_created_by : 23-10-2021 11:00 AM
     * sub_group_detail_updated_by : Admin
     * form_type : 1
     * __v : 0
     */

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
        private String _id;
        private String group_id;
        private String sub_group_detail_name;
        private String sub_group_detail_created_at;
        private String sub_group_detail_update_at;
        private String sub_group_detail_created_by;
        private String sub_group_detail_updated_by;
        private String form_type;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getSub_group_detail_name() {
            return sub_group_detail_name;
        }

        public void setSub_group_detail_name(String sub_group_detail_name) {
            this.sub_group_detail_name = sub_group_detail_name;
        }

        public String getSub_group_detail_created_at() {
            return sub_group_detail_created_at;
        }

        public void setSub_group_detail_created_at(String sub_group_detail_created_at) {
            this.sub_group_detail_created_at = sub_group_detail_created_at;
        }

        public String getSub_group_detail_update_at() {
            return sub_group_detail_update_at;
        }

        public void setSub_group_detail_update_at(String sub_group_detail_update_at) {
            this.sub_group_detail_update_at = sub_group_detail_update_at;
        }

        public String getSub_group_detail_created_by() {
            return sub_group_detail_created_by;
        }

        public void setSub_group_detail_created_by(String sub_group_detail_created_by) {
            this.sub_group_detail_created_by = sub_group_detail_created_by;
        }

        public String getSub_group_detail_updated_by() {
            return sub_group_detail_updated_by;
        }

        public void setSub_group_detail_updated_by(String sub_group_detail_updated_by) {
            this.sub_group_detail_updated_by = sub_group_detail_updated_by;
        }

        public String getForm_type() {
            return form_type;
        }

        public void setForm_type(String form_type) {
            this.form_type = form_type;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
