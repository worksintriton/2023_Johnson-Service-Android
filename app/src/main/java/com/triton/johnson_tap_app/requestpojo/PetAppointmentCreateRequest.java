package com.triton.johnson_tap_app.requestpojo;

import java.io.Serializable;
import java.util.List;

public class PetAppointmentCreateRequest implements Serializable {

    /**
     * doctor_id : 5fb62a1924583828f10f8731
     * booking_date : 19/11/2020
     * booking_time : 12:22 pm
     * booking_date_time : 19/11/2020 12:22 pm
     * communication_type :
     * video_id : http://vidoe.com
     * user_id : 5fb6162a211fce241eaf53a9
     * pet_id : 5fb38ea334f6014ea9013d30
     * problem_info : problem info
     * doc_attched : [{"file":"http://google.pdf"}]
     * doc_feedback : doc feedback
     * doc_rate : 5
     * user_feedback : user feedback
     * user_rate : 4.5
     * display_date : 19/11/2020 01:00 PM
     * server_date_time : 09/12/2020 03:00 PM
     * payment_id : 1234567890
     * payment_method : Card
     * appointment_types : Normal
     * allergies : this is
     * amount : 400
     * location_id,
     * visit_type
     * original_price : 100
     * discount_price : 10
     * total_price : 90
     * coupon_status : String,
     *  coupon_code : String,
     */

    private String doctor_id;
    private String booking_date;
    private String booking_time;
    private String booking_date_time;
    private String communication_type;
    private String video_id;
    private String user_id;
    private String family_id;
    private String problem_info;
    private String doc_feedback;
    private int doc_rate;
    private String user_feedback;
    private double user_rate;
    private String display_date;
    private String server_date_time;
    private String payment_id;
    private String payment_method;
    private String appointment_types;
    private String allergies;
    private int amount;
    private List<DocAttchedBean> doc_attched;
    private String mobile_type;
    private String service_name;
    private String service_amount;
    private String date_and_time;

    private String location_id;
    private String visit_type;

    private String health_issue_title;



    private int original_price;
    private int discount_price;
    private int total_price;
    private String coupon_status;
    private String coupon_code;

    private List<PetImgBean> current_img ;
    public List<PetImgBean> getPet_img() {
        return current_img;
    }
    public void setPet_img(List<PetImgBean> pet_img) {
        this.current_img = pet_img;

    }

    public static class PetImgBean implements Serializable {
        /**
         * pet_img : http://54.212.108.156:3000/api/uploads/Pic_empty.jpg
         */

        private String pet_img;

        public PetImgBean(String pet_img) {

            this.pet_img = pet_img;
        }

        public PetImgBean() {

        }


        public String getPet_img() {
            return pet_img;
        }

        public void setPet_img(String pet_img) {
            this.pet_img = pet_img;

        }
    }


    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public int getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getCoupon_status() {
        return coupon_status;
    }

    public void setCoupon_status(String coupon_status) {
        this.coupon_status = coupon_status;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getHealth_issue_title() {
        return health_issue_title;
    }

    public void setHealth_issue_title(String health_issue_title) {
        this.health_issue_title = health_issue_title;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getVisit_type() {
        return visit_type;
    }

    public void setVisit_type(String visit_type) {
        this.visit_type = visit_type;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_amount() {
        return service_amount;
    }

    public void setService_amount(String service_amount) {
        this.service_amount = service_amount;
    }

    public String getMobile_type() {
        return mobile_type;
    }

    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;
    }


    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;

    }


    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;

    }


    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;

    }


    public String getBooking_date_time() {
        return booking_date_time;
    }

    public void setBooking_date_time(String booking_date_time) {
        this.booking_date_time = booking_date_time;

    }


    public String getCommunication_type() {
        return communication_type;
    }

    public void setCommunication_type(String communication_type) {
        this.communication_type = communication_type;

    }


    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;

    }


    public String getProblem_info() {
        return problem_info;
    }

    public void setProblem_info(String problem_info) {
        this.problem_info = problem_info;

    }


    public String getDoc_feedback() {
        return doc_feedback;
    }

    public void setDoc_feedback(String doc_feedback) {
        this.doc_feedback = doc_feedback;

    }


    public int getDoc_rate() {
        return doc_rate;
    }

    public void setDoc_rate(int doc_rate) {
        this.doc_rate = doc_rate;

    }


    public String getUser_feedback() {
        return user_feedback;
    }

    public void setUser_feedback(String user_feedback) {
        this.user_feedback = user_feedback;

    }


    public double getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(double user_rate) {
        this.user_rate = user_rate;

    }

    public String getDisplay_date() {
        return display_date;
    }

    public void setDisplay_date(String display_date) {
        this.display_date = display_date;

    }


    public String getServer_date_time() {
        return server_date_time;
    }

    public void setServer_date_time(String server_date_time) {
        this.server_date_time = server_date_time;

    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;

    }


    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;

    }


    public String getAppointment_types() {
        return appointment_types;
    }

    public void setAppointment_types(String appointment_types) {
        this.appointment_types = appointment_types;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;

    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;

    }


    public List<DocAttchedBean> getDoc_attched() {
        return doc_attched;
    }

    public void setDoc_attched(List<DocAttchedBean> doc_attched) {
        this.doc_attched = doc_attched;

    }

    public static class DocAttchedBean {
        /**
         * file : http://google.pdf
         */

        private String file;


        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;

        }
    }
}
