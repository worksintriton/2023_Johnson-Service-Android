package com.triton.johnson_tap_app.requestpojo;

public class SubGroupDetailManagementRequest {


    /**
     * group_id : 61c1e5e09934282617679543
     * sub_group_id
     * search_string : ""
     */

    private String group_id;
    private String sub_group_id;
    private String search_string;

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
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
}
