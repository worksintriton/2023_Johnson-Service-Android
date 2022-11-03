package com.triton.johnson_tap_app.requestpojo;

public class StartWorkRequest {
    private String user_id;
    private String activity_id ;
    private String job_id ;
    private String start_time;
    private String date_of_create;
    private double job_lat;
    private double job_long;

    public double getJob_lat() {
        return job_lat;
    }

    public void setJob_lat(double job_lat) {
        this.job_lat = job_lat;
    }

    public double getJob_long() {
        return job_long;
    }

    public void setJob_long(double job_long) {
        this.job_long = job_long;
    }

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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDate_of_create() {
        return date_of_create;
    }

    public void setDate_of_create(String date_of_create) {
        this.date_of_create = date_of_create;
    }
}
