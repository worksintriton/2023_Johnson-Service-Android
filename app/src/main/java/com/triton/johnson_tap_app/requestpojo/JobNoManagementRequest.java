package com.triton.johnson_tap_app.requestpojo;

public class JobNoManagementRequest {


    /**
     * activedetail__id : 61c1e43497057923644090bd
     * search_string
     * request_type
     */

    private String activedetail__id;
    private String search_string;
    private String request_type;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getActivedetail__id() {
        return activedetail__id;
    }

    public void setActivedetail__id(String activedetail__id) {
        this.activedetail__id = activedetail__id;
    }


    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }
}
