package com.triton.johnson_tap_app.responsepojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AttendanceHelperListResponse {

    @Expose
    @SerializedName("Code")
    private Integer code;
    @Expose
    @SerializedName("Data")
    private ArrayList<Data> data;
    @Expose
    @SerializedName("Message")
    private String message;
    @Expose
    @SerializedName("Status")
    private String status;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Data {
        @Expose
        @SerializedName("JLS_HA_REMARKS")
        private String jlsHaRemarks;
        @Expose
        @SerializedName("JLS_HA_SUBMITDATE")
        private String jlsHaSubmitdate;
        @Expose
        @SerializedName("JLS_HA_TOTIME")
        private String jlsHaTotime;
        @Expose
        @SerializedName("JLS_HA_FROMTIME")
        private String jlsHaFromtime;
        @Expose
        @SerializedName("JLS_HA_STATUS")
        private String jlsHaStatus;
        @Expose
        @SerializedName("JLS_HA_ATTDATE")
        private String jlsHaAttdate;
        @Expose
        @SerializedName("JLS_HA_ROUTECD")
        private String jlsHaRoutecd;
        @Expose
        @SerializedName("JLS_HA_HELPERNAME")
        private String jlsHaHelpername;
        @Expose
        @SerializedName("JLS_HA_HELPERCODE")
        private String jlsHaHelpercode;
        @Expose
        @SerializedName("JLS_HA_ENGGNAME")
        private String jlsHaEnggname;
        @Expose
        @SerializedName("JLS_HA_ENGGCODE")
        private String jlsHaEnggcode;
        @Expose
        @SerializedName("JLS_HA_VALUE")
        private String jlsHaValue;
        @Expose
        @SerializedName("STATUS_DISPLAY")
        private String statusDisplay;

        public String getJlsHaValue() {
            return jlsHaValue;
        }

        public void setJlsHaValue(String jlsHaValue) {
            this.jlsHaValue = jlsHaValue;
        }

        public String getJlsHaRemarks() {
            return jlsHaRemarks;
        }

        public void setJlsHaRemarks(String jlsHaRemarks) {
            this.jlsHaRemarks = jlsHaRemarks;
        }

        public String getJlsHaSubmitdate() {
            return jlsHaSubmitdate;
        }

        public void setJlsHaSubmitdate(String jlsHaSubmitdate) {
            this.jlsHaSubmitdate = jlsHaSubmitdate;
        }

        public String getJlsHaTotime() {
            return jlsHaTotime;
        }

        public void setJlsHaTotime(String jlsHaTotime) {
            this.jlsHaTotime = jlsHaTotime;
        }

        public String getJlsHaFromtime() {
            return jlsHaFromtime;
        }

        public void setJlsHaFromtime(String jlsHaFromtime) {
            this.jlsHaFromtime = jlsHaFromtime;
        }

        public String getJlsHaStatus() {
            return jlsHaStatus;
        }

        public void setJlsHaStatus(String jlsHaStatus) {
            this.jlsHaStatus = jlsHaStatus;
        }

        public String getJlsHaAttdate() {
            return jlsHaAttdate;
        }

        public void setJlsHaAttdate(String jlsHaAttdate) {
            this.jlsHaAttdate = jlsHaAttdate;
        }

        public String getJlsHaRoutecd() {
            return jlsHaRoutecd;
        }

        public void setJlsHaRoutecd(String jlsHaRoutecd) {
            this.jlsHaRoutecd = jlsHaRoutecd;
        }

        public String getJlsHaHelpername() {
            return jlsHaHelpername;
        }

        public void setJlsHaHelpername(String jlsHaHelpername) {
            this.jlsHaHelpername = jlsHaHelpername;
        }

        public String getJlsHaHelpercode() {
            return jlsHaHelpercode;
        }

        public void setJlsHaHelpercode(String jlsHaHelpercode) {
            this.jlsHaHelpercode = jlsHaHelpercode;
        }

        public String getJlsHaEnggname() {
            return jlsHaEnggname;
        }

        public void setJlsHaEnggname(String jlsHaEnggname) {
            this.jlsHaEnggname = jlsHaEnggname;
        }

        public String getJlsHaEnggcode() {
            return jlsHaEnggcode;
        }

        public void setJlsHaEnggcode(String jlsHaEnggcode) {
            this.jlsHaEnggcode = jlsHaEnggcode;
        }

        public String getStatusDisplay() {
            return statusDisplay;
        }

        public void setStatusDisplay(String statusDisplay) {
            this.statusDisplay = statusDisplay;
        }
    }
}
