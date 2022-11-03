package com.triton.johnson_tap_app.requestpojo;

public class AttendanceLogoutRequest {

    /*{
            "_id": "621c8149a032672a740353ba",
            "attendance_end_date": "23-10-2022",
            "attendance_end_time": "23-10-2022 11:00 AM",
            "attendance_end_lat": 18.009090,
            "attendance_end_long": 79.990090

}*/


    private String _id;
    private String attendance_end_date;
    private String attendance_end_time;
    private double attendance_end_lat;
    private double attendance_end_long;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAttendance_end_date() {
        return attendance_end_date;
    }

    public void setAttendance_end_date(String attendance_end_date) {
        this.attendance_end_date = attendance_end_date;
    }

    public String getAttendance_end_time() {
        return attendance_end_time;
    }

    public void setAttendance_end_time(String attendance_end_time) {
        this.attendance_end_time = attendance_end_time;
    }

    public double getAttendance_end_lat() {
        return attendance_end_lat;
    }

    public void setAttendance_end_lat(double attendance_end_lat) {
        this.attendance_end_lat = attendance_end_lat;
    }

    public double getAttendance_end_long() {
        return attendance_end_long;
    }

    public void setAttendance_end_long(double attendance_end_long) {
        this.attendance_end_long = attendance_end_long;
    }
}
