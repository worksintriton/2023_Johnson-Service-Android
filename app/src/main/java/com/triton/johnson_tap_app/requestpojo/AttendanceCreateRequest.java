package com.triton.johnson_tap_app.requestpojo;

public class AttendanceCreateRequest {

    /*{
            "user_id": "6209f7cb967cef205b87110c",
            "attendance_name": "Mohammed imthiyas",
            "attendance_start_date": "23-10-2022",
            "attendance_start_date_time": "23-10-2022 11:00 AM",
            "attendance_start_lat": 18.009090,
            "attendance_start_long": 79.990090,
            "attendance_created_at": "23-10-2022 11:00 AM"
}*/

    private String user_id;
    private String attendance_name;
    private String attendance_start_date;
    private String attendance_start_date_time;
    private double attendance_start_lat;
    private double attendance_start_long;
    private String attendance_created_at;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAttendance_name() {
        return attendance_name;
    }

    public void setAttendance_name(String attendance_name) {
        this.attendance_name = attendance_name;
    }

    public String getAttendance_start_date() {
        return attendance_start_date;
    }

    public void setAttendance_start_date(String attendance_start_date) {
        this.attendance_start_date = attendance_start_date;
    }

    public String getAttendance_start_date_time() {
        return attendance_start_date_time;
    }

    public void setAttendance_start_date_time(String attendance_start_date_time) {
        this.attendance_start_date_time = attendance_start_date_time;
    }

    public double getAttendance_start_lat() {
        return attendance_start_lat;
    }

    public void setAttendance_start_lat(double attendance_start_lat) {
        this.attendance_start_lat = attendance_start_lat;
    }

    public double getAttendance_start_long() {
        return attendance_start_long;
    }

    public void setAttendance_start_long(double attendance_start_long) {
        this.attendance_start_long = attendance_start_long;
    }

    public String getAttendance_created_at() {
        return attendance_created_at;
    }

    public void setAttendance_created_at(String attendance_created_at) {
        this.attendance_created_at = attendance_created_at;
    }
}
