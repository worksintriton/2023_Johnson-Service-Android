package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class FormDataStoreResponse {


    /**
     * Status : Success
     * Message : Added successfully
     * Data : {"data_store":[{"__v":0,"_id":"61aa033385104f58378b3b1d","cat_id":"61a8b8752d9a15335c1e511f","created_by":"Admin","date_of_create":"12/3/2021, 5:14:51 PM","date_of_update":"12/3/2021, 5:14:51 PM","field_comments":"filed Length should be 10 digit","field_length":"10","field_name":"Field 1","field_type":"String","field_update_reason":"","field_value":"","updated_by":"Admin"}],"_id":"61ae0069114e8128075c3035","user_id":"123456","cat_id":"123456","service_id":"123456","job_no":"123456","start_time":"23-10-2021 11:00 AM","pause_time":"23-10-2021 11:00 AM","stop_time":"23-10-2021 11:00 AM","storage_status":"Temp","date_of_create":"23-10-2021 11:00 AM","date_of_update":"","created_by":"123456","updated_by":"","update_reason":"","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * data_store : [{"__v":0,"_id":"61aa033385104f58378b3b1d","cat_id":"61a8b8752d9a15335c1e511f","created_by":"Admin","date_of_create":"12/3/2021, 5:14:51 PM","date_of_update":"12/3/2021, 5:14:51 PM","field_comments":"filed Length should be 10 digit","field_length":"10","field_name":"Field 1","field_type":"String","field_update_reason":"","field_value":"","updated_by":"Admin"}]
     * _id : 61ae0069114e8128075c3035
     * user_id : 123456
     * cat_id : 123456
     * service_id : 123456
     * job_no : 123456
     * start_time : 23-10-2021 11:00 AM
     * pause_time : 23-10-2021 11:00 AM
     * stop_time : 23-10-2021 11:00 AM
     * storage_status : Temp
     * date_of_create : 23-10-2021 11:00 AM
     * date_of_update :
     * created_by : 123456
     * updated_by :
     * update_reason :
     * __v : 0
     */

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
        private String _id;
        private String user_id;
        private String cat_id;
        private String service_id;
        private String job_no;
        private String start_time;
        private String pause_time;
        private String stop_time;
        private String storage_status;
        private String date_of_create;
        private String date_of_update;
        private String created_by;
        private String updated_by;
        private String update_reason;
        private int __v;
        /**
         * __v : 0
         * _id : 61aa033385104f58378b3b1d
         * cat_id : 61a8b8752d9a15335c1e511f
         * created_by : Admin
         * date_of_create : 12/3/2021, 5:14:51 PM
         * date_of_update : 12/3/2021, 5:14:51 PM
         * field_comments : filed Length should be 10 digit
         * field_length : 10
         * field_name : Field 1
         * field_type : String
         * field_update_reason :
         * field_value :
         * updated_by : Admin
         */

        private List<DataStoreBean> data_store;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getJob_no() {
            return job_no;
        }

        public void setJob_no(String job_no) {
            this.job_no = job_no;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getPause_time() {
            return pause_time;
        }

        public void setPause_time(String pause_time) {
            this.pause_time = pause_time;
        }

        public String getStop_time() {
            return stop_time;
        }

        public void setStop_time(String stop_time) {
            this.stop_time = stop_time;
        }

        public String getStorage_status() {
            return storage_status;
        }

        public void setStorage_status(String storage_status) {
            this.storage_status = storage_status;
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

        public String getUpdate_reason() {
            return update_reason;
        }

        public void setUpdate_reason(String update_reason) {
            this.update_reason = update_reason;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public List<DataStoreBean> getData_store() {
            return data_store;
        }

        public void setData_store(List<DataStoreBean> data_store) {
            this.data_store = data_store;
        }

        public static class DataStoreBean {
            private int __v;
            private String _id;
            private String cat_id;
            private String created_by;
            private String date_of_create;
            private String date_of_update;
            private String field_comments;
            private String field_length;
            private String field_name;
            private String field_type;
            private String field_update_reason;
            private String field_value;
            private String updated_by;

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
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

            public String getCreated_by() {
                return created_by;
            }

            public void setCreated_by(String created_by) {
                this.created_by = created_by;
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

            public String getField_comments() {
                return field_comments;
            }

            public void setField_comments(String field_comments) {
                this.field_comments = field_comments;
            }

            public String getField_length() {
                return field_length;
            }

            public void setField_length(String field_length) {
                this.field_length = field_length;
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

            public String getField_update_reason() {
                return field_update_reason;
            }

            public void setField_update_reason(String field_update_reason) {
                this.field_update_reason = field_update_reason;
            }

            public String getField_value() {
                return field_value;
            }

            public void setField_value(String field_value) {
                this.field_value = field_value;
            }

            public String getUpdated_by() {
                return updated_by;
            }

            public void setUpdated_by(String updated_by) {
                this.updated_by = updated_by;
            }
        }
    }
}
