package com.triton.johnson_tap_app.requestpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HelperAttendanceSubmitRequest {
    @SerializedName("ATTENDANCE_DETAILS")
    @Expose
    private ArrayList<AttendanceDetail> attendanceDetails = new ArrayList<>();

    public HelperAttendanceSubmitRequest() {
    }

    public HelperAttendanceSubmitRequest(ArrayList<AttendanceDetail> attendanceDetails) {
        this.attendanceDetails = attendanceDetails;
    }

    public ArrayList<AttendanceDetail> getAttendanceDetails() {
        return attendanceDetails;
    }

    public void setAttendanceDetails(ArrayList<AttendanceDetail> attendanceDetails) {
        this.attendanceDetails = attendanceDetails;
    }

    public static class AttendanceDetail {

        @SerializedName("JLS_HA_ENGGCODE")
        @Expose
        private String jlsHaEnggcode;
        @SerializedName("JLS_HA_ENGGNAME")
        @Expose
        private String jlsHaEnggname;
        @SerializedName("JLS_HA_HELPERCODE")
        @Expose
        private String jlsHaHelpercode;
        @SerializedName("JLS_HA_HELPERNAME")
        @Expose
        private String jlsHaHelpername;
        @SerializedName("JLS_HA_ROUTECD")
        @Expose
        private String jlsHaRoutecd;
        @SerializedName("JLS_HA_ATTDATE")
        @Expose
        private String jlsHaAttdate;
        @SerializedName("JLS_HA_STATUS")
        @Expose
        private String jlsHaStatus;
        @SerializedName("JLS_HA_FROMTIME")
        @Expose
        private String jlsHaFromtime;
        @SerializedName("JLS_HA_TOTIME")
        @Expose
        private String jlsHaTotime;
        @SerializedName("JLS_HA_SUBMITDATE")
        @Expose
        private String jlsHaSubmitdate;
        @SerializedName("JLS_HA_REMARKS")
        @Expose
        private String jlsHaRemarks;
        @SerializedName("JLS_HA_VALUE")
        @Expose
        private String jlsHaValue;

        public AttendanceDetail(String jlsHaEnggcode, String jlsHaEnggname, String jlsHaHelpercode, String jlsHaHelpername, String jlsHaRoutecd, String jlsHaAttdate, String jlsHaStatus, String jlsHaFromtime, String jlsHaTotime, String jlsHaSubmitdate, String jlsHaRemarks, String jlsHaValue) {
            this.jlsHaEnggcode = jlsHaEnggcode;
            this.jlsHaEnggname = jlsHaEnggname;
            this.jlsHaHelpercode = jlsHaHelpercode;
            this.jlsHaHelpername = jlsHaHelpername;
            this.jlsHaRoutecd = jlsHaRoutecd;
            this.jlsHaAttdate = jlsHaAttdate;
            this.jlsHaStatus = jlsHaStatus;
            this.jlsHaFromtime = jlsHaFromtime;
            this.jlsHaTotime = jlsHaTotime;
            this.jlsHaSubmitdate = jlsHaSubmitdate;
            this.jlsHaRemarks = jlsHaRemarks;
            this.jlsHaValue = jlsHaValue;
        }

        public String getJlsHaValue() {
            return jlsHaValue;
        }

        public void setJlsHaValue(String jlsHaValue) {
            this.jlsHaValue = jlsHaValue;
        }

        public String getJlsHaEnggcode() {
            return jlsHaEnggcode;
        }

        public void setJlsHaEnggcode(String jlsHaEnggcode) {
            this.jlsHaEnggcode = jlsHaEnggcode;
        }

        public String getJlsHaEnggname() {
            return jlsHaEnggname;
        }

        public void setJlsHaEnggname(String jlsHaEnggname) {
            this.jlsHaEnggname = jlsHaEnggname;
        }

        public String getJlsHaHelpercode() {
            return jlsHaHelpercode;
        }

        public void setJlsHaHelpercode(String jlsHaHelpercode) {
            this.jlsHaHelpercode = jlsHaHelpercode;
        }

        public String getJlsHaHelpername() {
            return jlsHaHelpername;
        }

        public void setJlsHaHelpername(String jlsHaHelpername) {
            this.jlsHaHelpername = jlsHaHelpername;
        }

        public String getJlsHaRoutecd() {
            return jlsHaRoutecd;
        }

        public void setJlsHaRoutecd(String jlsHaRoutecd) {
            this.jlsHaRoutecd = jlsHaRoutecd;
        }

        public String getJlsHaAttdate() {
            return jlsHaAttdate;
        }

        public void setJlsHaAttdate(String jlsHaAttdate) {
            this.jlsHaAttdate = jlsHaAttdate;
        }

        public String getJlsHaStatus() {
            return jlsHaStatus;
        }

        public void setJlsHaStatus(String jlsHaStatus) {
            this.jlsHaStatus = jlsHaStatus;
        }

        public String getJlsHaFromtime() {
            return jlsHaFromtime;
        }

        public void setJlsHaFromtime(String jlsHaFromtime) {
            this.jlsHaFromtime = jlsHaFromtime;
        }

        public String getJlsHaTotime() {
            return jlsHaTotime;
        }

        public void setJlsHaTotime(String jlsHaTotime) {
            this.jlsHaTotime = jlsHaTotime;
        }

        public String getJlsHaSubmitdate() {
            return jlsHaSubmitdate;
        }

        public void setJlsHaSubmitdate(String jlsHaSubmitdate) {
            this.jlsHaSubmitdate = jlsHaSubmitdate;
        }

        public String getJlsHaRemarks() {
            return jlsHaRemarks;
        }

        public void setJlsHaRemarks(String jlsHaRemarks) {
            this.jlsHaRemarks = jlsHaRemarks;
        }

    }
}
