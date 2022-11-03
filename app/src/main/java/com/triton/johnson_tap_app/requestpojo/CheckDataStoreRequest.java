package com.triton.johnson_tap_app.requestpojo;

public class CheckDataStoreRequest {

    /**
     * job_id : 61c561a83c5cfc7d49dfa1fc
     * activity_id : 61c55f858bc953743afdaa52
     * user_id : 61a8cc5a77cefc37a966657b
     */

    private String job_id;
    private String activity_id;
    private String user_id;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

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
