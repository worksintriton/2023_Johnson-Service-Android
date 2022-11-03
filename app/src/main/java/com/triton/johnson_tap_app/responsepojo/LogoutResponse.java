package com.triton.johnson_tap_app.responsepojo;

public class LogoutResponse {


    /**
     * Status : Success
     * Message : Appointment Updated
     * Data : {"doc_attched":[],"doc_business_info":[{"education_details":[{"education":"MBBS","year":"2014"}],"experience_details":[{"company":"Apollo hospitals","from":"2016","to":"2020"}],"specialization":[{"specialization":"special - 3"}],"pet_handled":[{"pet_handled":"Dog"},{"pet_handled":"Parrot"},{"pet_handled":"Cat"},{"pet_handled":"Cow"},{"pet_handled":"Sheep"}],"clinic_pic":[{"clinic_pic":"http://52.25.163.13:3000/api/uploads/5fd2fdceb8f10b159576fe9311-12-2020 10:34 AMPetfolio1.jpg"}],"certificate_pic":[{"certificate_pic":"http://52.25.163.13:3000/api/uploads/5fd2fdceb8f10b159576fe9311-12-2020 10:34 AM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"govt_id_pic":[{"govt_id_pic":"http://52.25.163.13:3000/api/uploads/5fd2fdceb8f10b159576fe9311-12-2020 10:34 AM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"photo_id_pic":[{"photo_id_pic":"http://52.25.163.13:3000/api/uploads/5fd2fdceb8f10b159576fe9311-12-2020 10:34 AM1606464394712_4893XXXXXXXXXX79_13-11-2020.PDF"}],"_id":"5fd2fe531978e618628c9667","user_id":"5fd2fdceb8f10b159576fe93","dr_title":"Dr","dr_name":"DINESH","clinic_name":"APOLLO HOSPITAL","clinic_loc":"4/3 Marriyamman Kovil street","clinic_lat":11.0558215,"clinic_long":78.6323567,"profile_status":true,"profile_verification_status":"Verified","date_and_time":"11/12/2020 10:36:26","mobile_type":"Android","delete_status":false,"__v":0}],"_id":"5fd30a701978e618628c966c","doctor_id":"5fd2fdceb8f10b159576fe93","appointment_UID":"PET-1607666288166","booking_date":"11-12-2020","booking_time":"01:00 PM","booking_date_time":"11-12-2020 01:00 PM","communication_type":"","msg_id":"PET-1607666288166","video_id":"","user_id":"5fd2281180791a71361baad4","pet_id":"5fd22a2080791a71361baad7","problem_info":"this is the task pending from react side can you text me when your ","appoinment_status":"Completed","start_appointment_status":"Not Started","end_appointment_status":"Not End","doc_feedback":"","doc_rate":"0","user_feedback":"","user_rate":"0","display_date":"","server_date_time":"","payment_method":"","prescription_details":"","vaccination_details":"","appointment_types":"Emergency","allergies":"this is the task pending from react side can you ","payment_id":"","amount":"0","service_name":"","service_amount":"","mobile_type":"Android","delete_status":false,"__v":0,"completed_at":"11/12/2020 12:58 PM"}
     * Code : 200
     */

    private String Status;
    private String Message;
    private DataBean Data;
    private int Code;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;

    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;

    }


    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }


    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
    }
}
