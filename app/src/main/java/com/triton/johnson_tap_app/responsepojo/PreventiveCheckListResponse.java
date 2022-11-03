package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class PreventiveCheckListResponse {

    private String Status;

    private String Message;

    private List<Datum> Data = null;

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

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public List<Datum> getData() {
        return Data;
    }

    public void setData(List<Datum> data) {
        Data = data;
    }


    private class Datum {


        private List<String> drop_down = null;

        private List<Object> lift_list = null;

        private String _id;

        private String cat_id;

        private String group_id;

        private String sub_group_id;

        private String field_name;

        private String field_type;

        private String field_value;

        private String field_length;

        private String field_comments;

        private String field_update_reason;

        private String date_of_create;

        private String date_of_update;

        private String created_by;

        private String updated_by;

        private Integer __v;

        private Boolean delete_status;

        public List<String> getDrop_down() {
            return drop_down;
        }

        public void setDrop_down(List<String> drop_down) {
            this.drop_down = drop_down;
        }

        public List<Object> getLift_list() {
            return lift_list;
        }

        public void setLift_list(List<Object> lift_list) {
            this.lift_list = lift_list;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getSub_group_id() {
            return sub_group_id;
        }

        public void setSub_group_id(String sub_group_id) {
            this.sub_group_id = sub_group_id;
        }

        public String getField_name() {
            return field_name;
        }

        public void setField_name(String field_name) {
            this.field_name = field_name;
        }

        public String getField_type() {
            return field_type;
        }

        public void setField_type(String field_type) {
            this.field_type = field_type;
        }

        public String getField_value() {
            return field_value;
        }

        public void setField_value(String field_value) {
            this.field_value = field_value;
        }

        public String getField_length() {
            return field_length;
        }

        public void setField_length(String field_length) {
            this.field_length = field_length;
        }

        public String getField_comments() {
            return field_comments;
        }

        public void setField_comments(String field_comments) {
            this.field_comments = field_comments;
        }

        public String getField_update_reason() {
            return field_update_reason;
        }

        public void setField_update_reason(String field_update_reason) {
            this.field_update_reason = field_update_reason;
        }

        public String getDate_of_create() {
            return date_of_create;
        }

        public void setDate_of_create(String date_of_create) {
            this.date_of_create = date_of_create;
        }

        public String getDate_of_update() {
            return date_of_update;
        }

        public void setDate_of_update(String date_of_update) {
            this.date_of_update = date_of_update;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getUpdated_by() {
            return updated_by;
        }

        public void setUpdated_by(String updated_by) {
            this.updated_by = updated_by;
        }

        public Integer get__v() {
            return __v;
        }

        public void set__v(Integer __v) {
            this.__v = __v;
        }

        public Boolean getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(Boolean delete_status) {
            this.delete_status = delete_status;
        }
    }
}
