package com.triton.johnson_tap_app.requestpojo;


import com.google.gson.annotations.SerializedName;

public class Breakdown_submitRequest {

    /**
     * Doctor : 0
     * Product : 0
     * address : Unnamed Road, Tamil Nadu 621006, India
     * lat : 11.055715
     * long : 78.632249
     * service : 0
     * user_id : 5fd227ac80791a71361baad3
     * user_type : 1
     */

    private String bd_details;


    public String getBd_details() {
        return bd_details;
    }

    public void setBd_details(String bd_details) {
        this.bd_details = bd_details;
    }

}
