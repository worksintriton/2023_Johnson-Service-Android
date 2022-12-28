package com.triton.johnson_tap_app.responsepojo;

public class GetPopupImageResponse {

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

    public Data getData() {
        return Data;
    }

    public void setData(Data data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public class Data {

        private String _id;
        private String user_mobile_no;
        private String code;
        private String status;
        private String image_path;
        private String date_of_create;
        private Integer __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_mobile_no() {
            return user_mobile_no;
        }

        public void setUser_mobile_no(String user_mobile_no) {
            this.user_mobile_no = user_mobile_no;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getDate_of_create() {
            return date_of_create;
        }

        public void setDate_of_create(String date_of_create) {
            this.date_of_create = date_of_create;
        }

        public Integer get__v() {
            return __v;
        }

        public void set__v(Integer __v) {
            this.__v = __v;
        }
    }
}
