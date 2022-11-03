package com.triton.johnson_tap_app.requestpojo;

public class CheckLocationRequest {


    /**
     * job_id : 61f222396667ac391fc85c55
     * job_long : 80.2235346
     * job_lat : 12.9831482
     */

    private String job_id;
    private double job_long;
    private double job_lat;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public double getJob_long() {
        return job_long;
    }

    public void setJob_long(double job_long) {
        this.job_long = job_long;
    }

    public double getJob_lat() {
        return job_lat;
    }

    public void setJob_lat(double job_lat) {
        this.job_lat = job_lat;
    }
}
