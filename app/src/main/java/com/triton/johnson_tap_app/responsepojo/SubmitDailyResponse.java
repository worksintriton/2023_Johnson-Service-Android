package com.triton.johnson_tap_app.responsepojo;

import com.triton.johnson_tap_app.data.form3submit.JobDetail;

import java.util.ArrayList;
import java.util.List;

public class SubmitDailyResponse {

    /**
     * Status : Success
     * Message : Location Added successfully
     * Data : {"_id":"5fba28013f872724bbaacf79","user_id":"5fb63307b223363ad0039b0e","location_state":"Tamil Nadu","location_country":"India","location_city":"Tiruchirappalli","location_pin":"620018","location_address":"Tiruchchirappalli Junction, Railway Junction, Bharathiyar Salai, Sangillyandapuram, Tiruchirappalli, Tamil Nadu 620001, India","location_lat":10.820872970525246,"location_long":78.6857356131077,"location_title":"Home","location_nickname":"DK HOME","default_status":true,"date_and_time":"22-11-2020 02:26 PM","__v":0}
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

        private String collection_type;
        private String current_date;
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
        private String total;
        private String remarks;
        private String created_by;
        private String collection_value;
        private List<JobDetail> jobDetails;

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
            this.ded_other_two_value = ded_other_two_value;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
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

        public String getCollection_value() {
            return collection_value;
        }

        public void setCollection_value(String collection_value) {
            this.collection_value = collection_value;
        }

        public List<JobDetail> getJobDetails() {
            return jobDetails;
        }

        public void setJobDetails(List<JobDetail> jobDetails) {
            this.jobDetails = jobDetails;
        }


        public static class JobDetail  {
            /**
             * s_no : 1
             * job_no : L-0011
             * customer_name : Moahmmed
             * contract_no : 098654321
             * pay_type : ADVANCE
             * frm : 23-May-2022
             * to : 23-May-2022
             * pay_amount : 10000
             */

            private String s_no;
            private String job_no;
            private String customer_name;
            private String contract_no;
            private String pay_type;
            private String frm;
            private String to;
            private String pay_amount;

            public String getS_no() {
                return s_no;
            }

            public void setS_no(String s_no) {
                this.s_no = s_no;

            }

            public String getJob_no() {
                return job_no;
            }

            public void setJob_no(String job_no) {
                this.job_no = job_no;

            }

            public String getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(String customer_name) {
                this.customer_name = customer_name;

            }

            public String getContract_no() {
                return contract_no;
            }

            public void setContract_no(String contract_no) {
                this.contract_no = contract_no;

            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;

            }

            public String getFrm() {
                return frm;
            }

            public void setFrm(String frm) {
                this.frm = frm;

            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;

            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;

            }

        }

    }
}
