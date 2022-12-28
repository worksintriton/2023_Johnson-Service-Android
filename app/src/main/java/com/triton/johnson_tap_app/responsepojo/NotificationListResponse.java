package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class NotificationListResponse {

    private String Status;
    private String Message;
    private List<NotificationData> Data = null;
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

    public List<NotificationData> getData() {
        return Data;
    }

    public void setData(List<NotificationData> data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public class NotificationData {

        private String _id;
        private String notification_title;
        private String notification_desc;
        private String user_mobile_no;
        private String date_and_time;
        private String read_status;
        private String date_value;
        private Integer __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getNotification_title() {
            return notification_title;
        }

        public void setNotification_title(String notification_title) {
            this.notification_title = notification_title;
        }

        public String getNotification_desc() {
            return notification_desc;
        }

        public void setNotification_desc(String notification_desc) {
            this.notification_desc = notification_desc;
        }

        public String getUser_mobile_no() {
            return user_mobile_no;
        }

        public void setUser_mobile_no(String user_mobile_no) {
            this.user_mobile_no = user_mobile_no;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public String getRead_status() {
            return read_status;
        }

        public void setRead_status(String read_status) {
            this.read_status = read_status;
        }

        public String getDate_value() {
            return date_value;
        }

        public void setDate_value(String date_value) {
            this.date_value = date_value;
        }

        public Integer get__v() {
            return __v;
        }

        public void set__v(Integer __v) {
            this.__v = __v;
        }
    }
}
