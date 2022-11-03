package com.triton.johnson_tap_app.requestpojo;

public class GroupDetailManagementRequest {


    /**
     * user_id : 61c1e5e09934282617679543
     * search_string
     */

    private String user_id;
    private String search_string;
    private String job_detail_id;

    public String getJob_detail_id() {
        return job_detail_id;
    }

    public void setJob_detail_id(String job_detail_id) {
        this.job_detail_id = job_detail_id;
    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
