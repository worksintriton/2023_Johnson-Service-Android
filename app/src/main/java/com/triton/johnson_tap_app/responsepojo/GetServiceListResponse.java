package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class GetServiceListResponse {
    /**
     * Status : Success
     * Message : Functiondetails
     * Data : [{"_id":"61a8b8752d9a15335c1e511f","service_name":"Service one - 1","service_created_at":"12/2/2021, 5:43:41 PM","service_update_at":"","service_created_by":"12/2/2021, 5:43:41 PM","service_updated_by":"","__v":0},{"_id":"61a8b88d2d9a15335c1e5122","service_name":"Service Two - 2","service_created_at":"12/2/2021, 5:44:05 PM","service_update_at":"","service_created_by":"12/2/2021, 5:44:05 PM","service_updated_by":"","__v":0},{"_id":"61a8b8ac2d9a15335c1e5125","service_name":"Service Three - 3","service_created_at":"12/2/2021, 5:44:36 PM","service_update_at":"","service_created_by":"12/2/2021, 5:44:36 PM","service_updated_by":"","__v":0}]
     * Code : 200
     */

    private String Status;
    private String Message;
    private int Code;
    /**
     * _id : 61a8b8752d9a15335c1e511f
     * service_name : Service one - 1
     * service_created_at : 12/2/2021, 5:43:41 PM
     * service_update_at :
     * service_created_by : 12/2/2021, 5:43:41 PM
     * service_updated_by :
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
        private String service_name;
        private String service_created_at;
        private String service_update_at;
        private String service_created_by;
        private String service_updated_by;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getService_created_at() {
            return service_created_at;
        }

        public void setService_created_at(String service_created_at) {
            this.service_created_at = service_created_at;
        }

        public String getService_update_at() {
            return service_update_at;
        }

        public void setService_update_at(String service_update_at) {
            this.service_update_at = service_update_at;
        }

        public String getService_created_by() {
            return service_created_by;
        }

        public void setService_created_by(String service_created_by) {
            this.service_created_by = service_created_by;
        }

        public String getService_updated_by() {
            return service_updated_by;
        }

        public void setService_updated_by(String service_updated_by) {
            this.service_updated_by = service_updated_by;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
