package com.triton.johnson_tap_app.requestpojo;


import java.util.List;

public class FormFiveBasedStroeDataRequest {

    private DataBean Data;
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



    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }



    public static class DataBean {
        private String remarks;
        /**
         * part_no : No1
         * material_desc : this is the material for all data 01
         * desc_qty : 10
         * accepts : 10
         * demage : 0
         * shortage : 0
         * excess : 0
         */

        private List<MaterialDetailsBean> material_details;

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public List<MaterialDetailsBean> getMaterial_details() {
            return material_details;
        }

        public void setMaterial_details(List<MaterialDetailsBean> material_details) {
            this.material_details = material_details;
        }

        public static class MaterialDetailsBean {
            private String part_no;
            private String material_desc;
            private int desc_qty;
            private int accepts;
            private int demage;
            private int shortage;
            private int excess;

            public String getPart_no() {
                return part_no;
            }

            public void setPart_no(String part_no) {
                this.part_no = part_no;
            }

            public String getMaterial_desc() {
                return material_desc;
            }

            public void setMaterial_desc(String material_desc) {
                this.material_desc = material_desc;
            }

            public int getDesc_qty() {
                return desc_qty;
            }

            public void setDesc_qty(int desc_qty) {
                this.desc_qty = desc_qty;
            }

            public int getAccepts() {
                return accepts;
            }

            public void setAccepts(int accepts) {
                this.accepts = accepts;
            }

            public int getDemage() {
                return demage;
            }

            public void setDemage(int demage) {
                this.demage = demage;
            }

            public int getShortage() {
                return shortage;
            }

            public void setShortage(int shortage) {
                this.shortage = shortage;
            }

            public int getExcess() {
                return excess;
            }

            public void setExcess(int excess) {
                this.excess = excess;
            }
        }
    }
}
