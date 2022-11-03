package com.triton.johnson_tap_app.requestpojo;

import java.util.List;

public class RowBasedStroeDataRequest {

    /**
     * noofbracket : 1
     * dimx_one : 230
     * dimx_two : 202
     * dimx_three : 202
     * dimx_four : 202
     * dimy_one : 230
     * dimy_two : 202
     * remarks : 202
     */

    private List<DataBean> Data;
    private String user_id;
    private String activity_id ;
    private String job_id ;
    private String group_id ;
    private String sub_group_id;
    private String start_time;
    private String pause_time;
    private String stop_time;
    private String storage_status;
    private String date_of_create;
    private String date_of_update;
    private String created_by;
    private String updated_by;
    private String update_reason;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String noofbracket;
        private String dimx_one;
        private String dimx_two;
        private String dimx_three;
        private String dimx_four;
        private String dimy_one;
        private String dimy_two;
        private String remarks;

        public String getNoofbracket() {
            return noofbracket;
        }

        public void setNoofbracket(String noofbracket) {
            this.noofbracket = noofbracket;
        }

        public String getDimx_one() {
            return dimx_one;
        }

        public void setDimx_one(String dimx_one) {
            this.dimx_one = dimx_one;
        }

        public String getDimx_two() {
            return dimx_two;
        }

        public void setDimx_two(String dimx_two) {
            this.dimx_two = dimx_two;
        }

        public String getDimx_three() {
            return dimx_three;
        }

        public void setDimx_three(String dimx_three) {
            this.dimx_three = dimx_three;
        }

        public String getDimx_four() {
            return dimx_four;
        }

        public void setDimx_four(String dimx_four) {
            this.dimx_four = dimx_four;
        }

        public String getDimy_one() {
            return dimy_one;
        }

        public void setDimy_one(String dimy_one) {
            this.dimy_one = dimy_one;
        }

        public String getDimy_two() {
            return dimy_two;
        }

        public void setDimy_two(String dimy_two) {
            this.dimy_two = dimy_two;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
