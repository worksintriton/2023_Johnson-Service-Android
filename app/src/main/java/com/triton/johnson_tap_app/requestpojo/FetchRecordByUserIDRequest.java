package com.triton.johnson_tap_app.requestpojo;

public class FetchRecordByUserIDRequest {
    private String user_id;
    private String activity_id;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
