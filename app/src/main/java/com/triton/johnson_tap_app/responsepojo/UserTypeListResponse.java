package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class UserTypeListResponse {

    /**
     * Status : Success
     * Message : User type Details
     * Data : {"usertypedata":[{"_id":"5fad517c625e495678343783","user_type_title":"Pet Lover","user_type_value":1,"user_type_img":"","date_and_time":"23/10/2019 12:12:00","__v":0},{"_id":"5fad5186625e495678343784","user_type_title":"Service Provider","user_type_value":2,"user_type_img":"","date_and_time":"23/10/2019 12:12:00","__v":0},{"_id":"5fad518d625e495678343785","user_type_title":"Vendor","user_type_value":3,"user_type_img":"","date_and_time":"23/10/2019 12:12:00","__v":0},{"_id":"5fad5197625e495678343786","user_type_title":"Doctor","user_type_value":4,"user_type_img":"","date_and_time":"23/10/2019 12:12:00","__v":0}]}
     * Code : 200
     */

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
        private List<UsertypedataBean> usertypedata;

        public List<UsertypedataBean> getUsertypedata() {
            return usertypedata;
        }

        public void setUsertypedata(List<UsertypedataBean> usertypedata) {
            this.usertypedata = usertypedata;
        }

        public static class UsertypedataBean {
            /**
             * _id : 5fad517c625e495678343783
             * user_type_title : Pet Lover
             * user_type_value : 1
             * user_type_img :
             * date_and_time : 23/10/2019 12:12:00
             * __v : 0
             */

            private String _id;
            private String user_type_title;
            private int user_type_value;
            private String user_type_img;
            private String date_and_time;
            private int __v;

            private boolean isSelected ;
            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getUser_type_title() {
                return user_type_title;
            }

            public void setUser_type_title(String user_type_title) {
                this.user_type_title = user_type_title;
            }

            public int getUser_type_value() {
                return user_type_value;
            }

            public void setUser_type_value(int user_type_value) {
                this.user_type_value = user_type_value;
            }

            public String getUser_type_img() {
                return user_type_img;
            }

            public void setUser_type_img(String user_type_img) {
                this.user_type_img = user_type_img;
            }

            public String getDate_and_time() {
                return date_and_time;
            }

            public void setDate_and_time(String date_and_time) {
                this.date_and_time = date_and_time;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }
    }
}
