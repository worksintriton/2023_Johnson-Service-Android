package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class ActivityListManagementResponse {


    /**
     * Status : Success
     * Message : activedetail_management List
     * Data : [{"_id":"61c1e43497057923644090bd","activedetail_name":"Activity 1","activedetail_created_at":"23-10-2021 11:00 AM","activedetail_update_at":"23-10-2021 11:00 AM","activedetail_created_by":"Admin","activedetail_updated_by":"Admin","__v":0},{"_id":"61c1e43c97057923644090bf","activedetail_name":"Activity 2","activedetail_created_at":"23-10-2021 11:00 AM","activedetail_update_at":"23-10-2021 11:00 AM","activedetail_created_by":"Admin","activedetail_updated_by":"Admin","__v":0},{"_id":"61c1e43f97057923644090c1","activedetail_name":"Activity 3","activedetail_created_at":"23-10-2021 11:00 AM","activedetail_update_at":"23-10-2021 11:00 AM","activedetail_created_by":"Admin","activedetail_updated_by":"Admin","__v":0},{"_id":"61c1e44297057923644090c3","activedetail_name":"Activity 4","activedetail_created_at":"23-10-2021 11:00 AM","activedetail_update_at":"23-10-2021 11:00 AM","activedetail_created_by":"Admin","activedetail_updated_by":"Admin","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 61c1e43497057923644090bd
     * activedetail_name : Activity 1
     * activedetail_created_at : 23-10-2021 11:00 AM
     * activedetail_update_at : 23-10-2021 11:00 AM
     * activedetail_created_by : Admin
     * activedetail_updated_by : Admin
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
        private String activedetail_name;
        private String activedetail_created_at;
        private String activedetail_update_at;
        private String activedetail_created_by;
        private String activedetail_updated_by;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getActivedetail_name() {
            return activedetail_name;
        }

        public void setActivedetail_name(String activedetail_name) {
            this.activedetail_name = activedetail_name;
        }

        public String getActivedetail_created_at() {
            return activedetail_created_at;
        }

        public void setActivedetail_created_at(String activedetail_created_at) {
            this.activedetail_created_at = activedetail_created_at;
        }

        public String getActivedetail_update_at() {
            return activedetail_update_at;
        }

        public void setActivedetail_update_at(String activedetail_update_at) {
            this.activedetail_update_at = activedetail_update_at;
        }

        public String getActivedetail_created_by() {
            return activedetail_created_by;
        }

        public void setActivedetail_created_by(String activedetail_created_by) {
            this.activedetail_created_by = activedetail_created_by;
        }

        public String getActivedetail_updated_by() {
            return activedetail_updated_by;
        }

        public void setActivedetail_updated_by(String activedetail_updated_by) {
            this.activedetail_updated_by = activedetail_updated_by;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
