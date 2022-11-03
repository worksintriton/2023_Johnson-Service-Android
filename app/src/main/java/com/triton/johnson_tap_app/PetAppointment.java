package com.triton.johnson_tap_app;

import java.io.Serializable;
import java.util.List;

public class PetAppointment implements Serializable {

    /**
     * collection_type : Chq
     * current_date : 23-10-2022
     * uploaded_file : [{"http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png","http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png","http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png","http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png"}]
     * Agent_code : AG-0001
     * cheq_no : UI000999
     * rtgs_no : ""
     * cheq_amount : 100
     * cheq_date : 23-May-2022
     * bank_name : Indian bank
     * ifsc_code : ""
     * third_party_chq : Y
     * ded_it : 1999
     * ded_gst : 200
     * ded_other_one_type : Type 1
     * ded_other_one_value : 100
     * ded_other_two_type : Type 2
     * ded_other_two_value : 1000
     * remarks : asdfasdf
     * created_by : 98877654321
     * job_details : [{"http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png","http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png","http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png","http://smart.johnsonliftsltd.com:3000/api/uploads/sign.png"}]

     */

    private String collection_type;
    private String current_date;
    private List<uploaded_fileBean> uploaded_file;
    private String Agent_code;
    private String cheq_no;
    private String rtgs_no;
    private String cheq_amount;
    private String cheq_date;
    private String bank_name;
    private String ifsc_code;
    private String third_party_chq;
    private String ded_it;
    private String ded_gst;
    private String ded_other_one_type;
    private String ded_other_one_value;
    private String ded_other_two_type;
    private String ded_other_two_value;
    private String remarks;
    private String created_by;

//    private List<PetImgBean> current_img ;
//    public List<PetImgBean> getPet_img() {
//        return current_img;
//    }
//    public void setPet_img(List<PetImgBean> pet_img) {
//        this.current_img = pet_img;
//
//    }
//
//    public static class PetImgBean implements Serializable {
//        /**
//         * pet_img : http://54.212.108.156:3000/api/uploads/Pic_empty.jpg
//         */
//
//        private String pet_img;
//
//        public PetImgBean(String pet_img) {
//
//            this.pet_img = pet_img;
//        }
//
//        public PetImgBean() {
//
//        }
//
//
//        public String getPet_img() {
//            return pet_img;
//        }
//
//        public void setPet_img(String pet_img) {
//            this.pet_img = pet_img;
//
//        }
//    }




    public String getCollection_type() {
        return collection_type;
    }

    public void setCollection_type(String collection_type) {
        this.collection_type = collection_type;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getAgent_code() {
        return Agent_code;
    }

    public void setAgent_code(String Agent_code) {
        this.Agent_code = Agent_code;
    }

    public String getCheq_no() {
        return cheq_no;
    }

    public void setCheq_no(String cheq_no) {
        this.cheq_no = cheq_no;
    }

    public String getRtgs_no() {
        return rtgs_no;
    }

    public void setRtgs_no(String rtgs_no) {
        this.rtgs_no = rtgs_no;
    }

    public String getCheq_amount() {
        return cheq_amount;
    }

    public void setCheq_amount(String cheq_amount) {
        this.cheq_amount = cheq_amount;
    }

    public String getCheq_date() {
        return cheq_date;
    }

    public void setCheq_date(String cheq_date) {
        this.cheq_date = cheq_date;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getThird_party_chq() {
        return third_party_chq;
    }

    public void setThird_party_chq(String third_party_chq) {
        this.third_party_chq = third_party_chq;

    }

    public String getDed_it() {
        return ded_it;
    }

    public void setDed_it(String ded_it) {
        this.ded_it = ded_it;

    }

    public String getDed_gst() {
        return ded_gst;
    }

    public void setDed_gst(String ded_gst) {
        this.ded_gst = ded_gst;

    }

    public String getDed_other_one_type() {
        return ded_other_one_type;
    }

    public void setDed_other_one_type(String ded_other_one_type) {
        this.ded_other_one_type = ded_other_one_type;

    }

    public String getDed_other_one_value() {
        return ded_other_one_value;
    }

    public void setDed_other_one_value(String ded_other_one_value) {
        this.ded_other_one_value = ded_other_one_value;

    }

    public String getDed_other_two_type() {
        return ded_other_two_type;
    }

    public void setDed_other_two_type(String ded_other_two_type) {
        this.ded_other_two_type = ded_other_two_type;

    }

    public String getDed_other_two_value() {
        return ded_other_two_value;
    }

    public void setDed_other_two_value(String ded_other_two_value) {
        this.ded_other_two_value  = ded_other_two_value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;

    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;

    }


    public List<uploaded_fileBean> getUploaded_file() {
        return uploaded_file;
    }

    public void setUploaded_file(List<uploaded_fileBean> uploaded_file) {
        this.uploaded_file = uploaded_file;

    }

    public static class uploaded_fileBean {
        /**
         * file : http://google.pdf
         */

        private String image;


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;

        }
    }
}
