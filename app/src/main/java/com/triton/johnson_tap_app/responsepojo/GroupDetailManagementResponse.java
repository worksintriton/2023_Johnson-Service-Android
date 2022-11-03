package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class GroupDetailManagementResponse {


    /**
     * Status : Success
     * Message : group_detailModel List
     * Data : [{"_id":"61c2f59604704a43d87ed9fc","activity_id":"61c1e5e09934282617679543","sub_group_status":"false","group_detail_name":"Group 04","group_detail_created_at":"23-10-2021 11:00 AM","group_detail_update_at":"23-10-2021 11:00 AM","group_detail_created_by":"Admin","group_detail_updated_by":"Admin","form_type":"1","__v":0},{"_id":"61c2f5a104704a43d87ed9fe","activity_id":"61c1e5e09934282617679543","sub_group_status":"true","group_detail_name":"Group 1","group_detail_created_at":"23-10-2021 11:00 AM","group_detail_update_at":"23-10-2021 11:00 AM","group_detail_created_by":"Admin","group_detail_updated_by":"Admin","form_type":"2","__v":0},{"_id":"61c2f5a604704a43d87eda00","activity_id":"61c1e5e09934282617679543","sub_group_status":"true","group_detail_name":"Group 3","group_detail_created_at":"23-10-2021 11:00 AM","group_detail_update_at":"23-10-2021 11:00 AM","group_detail_created_by":"Admin","group_detail_updated_by":"Admin","form_type":"3","__v":0},{"_id":"61c2f5ae04704a43d87eda02","activity_id":"61c1e5e09934282617679543","sub_group_status":"false","group_detail_name":"Group 4","group_detail_created_at":"23-10-2021 11:00 AM","group_detail_update_at":"23-10-2021 11:00 AM","group_detail_created_by":"Admin","group_detail_updated_by":"Admin","form_type":"4","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 61c2f59604704a43d87ed9fc
     * activity_id : 61c1e5e09934282617679543
     * sub_group_status : false
     * group_detail_name : Group 04
     * group_detail_created_at : 23-10-2021 11:00 AM
     * group_detail_update_at : 23-10-2021 11:00 AM
     * group_detail_created_by : Admin
     * group_detail_updated_by : Admin
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
        private String activity_id;
        private String job_detail_id;

        public String getJob_detail_id() {
            return job_detail_id;
        }

        public void setJob_detail_id(String job_detail_id) {
            this.job_detail_id = job_detail_id;
        }

        private String sub_group_status;
        private String group_detail_name;
        private String group_detail_created_at;
        private String group_detail_update_at;
        private String group_detail_created_by;
        private String group_detail_updated_by;
        private String form_type;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getSub_group_status() {
            return sub_group_status;
        }

        public void setSub_group_status(String sub_group_status) {
            this.sub_group_status = sub_group_status;
        }

        public String getGroup_detail_name() {
            return group_detail_name;
        }

        public void setGroup_detail_name(String group_detail_name) {
            this.group_detail_name = group_detail_name;
        }

        public String getGroup_detail_created_at() {
            return group_detail_created_at;
        }

        public void setGroup_detail_created_at(String group_detail_created_at) {
            this.group_detail_created_at = group_detail_created_at;
        }

        public String getGroup_detail_update_at() {
            return group_detail_update_at;
        }

        public void setGroup_detail_update_at(String group_detail_update_at) {
            this.group_detail_update_at = group_detail_update_at;
        }

        public String getGroup_detail_created_by() {
            return group_detail_created_by;
        }

        public void setGroup_detail_created_by(String group_detail_created_by) {
            this.group_detail_created_by = group_detail_created_by;
        }

        public String getGroup_detail_updated_by() {
            return group_detail_updated_by;
        }

        public void setGroup_detail_updated_by(String group_detail_updated_by) {
            this.group_detail_updated_by = group_detail_updated_by;
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
