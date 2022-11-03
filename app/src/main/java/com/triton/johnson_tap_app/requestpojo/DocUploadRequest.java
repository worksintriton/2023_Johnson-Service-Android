package com.triton.johnson_tap_app.requestpojo;

import java.io.Serializable;
import java.util.List;

public class DocUploadRequest implements Serializable {


    /**
     * user_id : 5fb22773e70b0d3cc5b2c19d
     * dr_title : Dr
     * dr_name : mohammed
     * clinic_name : Mohammed Clinic
     * clinic_loc : Muthamil nager Kodugaiyur
     * clinic_lat : 18.12
     * clinic_long : 80.0987
     * education_details : [{"education":"10 th pass","year":"2020"},{"education":"10 th pass","year":"2020"}]
     * experience_details : [{"company":"triton it slotuions","from":"22-10-2020","to":"22-10-2021"},{"company":"triton it slotuions","from":"22-10-2020","to":"22-10-2021"},{"company":"triton it slotuions","from":"22-10-2020","to":"22-10-2021"}]
     * specialization : [{"specialization":"Spec - 1"},{"specialization":"Spec - 1"},{"specialization":"Spec - 1"}]
     * pet_handled : [{"pet_handled":"Pet - 1"},{"pet_handled":"Pet - 1"}]
     * clinic_pic : [{"clinic_pic":""},{"clinic_pic":""}]
     * certificate_pic : [{"certificate_pic":""},{"certificate_pic":""}]
     * govt_id_pic : [{"govt_id_pic":""},{"govt_id_pic":""}]
     * photo_id_pic : [{"photo_id_pic":""},{"photo_id_pic":""}]
     * profile_status : 0
     * profile_verification_status : Not verified
     * date_and_time : 23-10-2020 11:10 AM
     * mobile_type : Android
     * communication_type  : 1
     * consultancy_fees
     * city_name : Chennai
     */



    public DocUploadRequest() {

    }

    /**
     * education : 10 th pass
     * year : 2020
     */

    private List<EducationDetailsBean> education_details;
    /**
     * company : triton it slotuions
     * from : 22-10-2020
     * to : 22-10-2021
     */

    private List<ExperienceDetailsBean> experience_details;
    /**
     * specialization : Spec - 1
     */

    public DocUploadRequest(List<EducationDetailsBean> education_details, List<ExperienceDetailsBean> experience_details) {

        this.education_details = education_details;
        this.experience_details = experience_details;
    }

    public List<EducationDetailsBean> getEducation_details() {
        return education_details;
    }

    public void setEducation_details(List<EducationDetailsBean> education_details) {
        this.education_details = education_details;
    }

    public List<ExperienceDetailsBean> getExperience_details() {
        return experience_details;
    }

    public void setExperience_details(List<ExperienceDetailsBean> experience_details) {
        this.experience_details = experience_details;
    }

    public static class EducationDetailsBean implements Serializable {
        private String education;
        private String year;

        public EducationDetailsBean(String education, String year) {
            this.education = education;
            this.year = year;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }

    public static class ExperienceDetailsBean implements Serializable {
        private String company;
        private String from;
        private String to;
        private int yearsofexperience;

        public int getYearsofexperience() {
            return yearsofexperience;
        }

        public void setYearsofexperience(int yearsofexperience) {
            this.yearsofexperience = yearsofexperience;
        }

        public ExperienceDetailsBean(String company, String from, String to, int yearsofexperience) {
            this.company = company;
            this.from = from;
            this.to = to;
            this.yearsofexperience = yearsofexperience;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

}
