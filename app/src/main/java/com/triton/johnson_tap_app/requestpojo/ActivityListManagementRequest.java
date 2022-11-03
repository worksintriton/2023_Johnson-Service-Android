package com.triton.johnson_tap_app.requestpojo;

public class ActivityListManagementRequest {


    /**
     * user_id : 1234456789
     * search_string:""
     * request_type
     */

    private String user_id;
    private String search_string;
    private String request_type ;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }
}
