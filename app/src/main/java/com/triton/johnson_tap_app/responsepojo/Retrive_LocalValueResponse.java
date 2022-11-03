package com.triton.johnson_tap_app.responsepojo;

import android.provider.ContactsContract;

import java.util.List;

public class Retrive_LocalValueResponse {

    private String Status;

    private String Message;

    private Data Data;

    private Integer Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Retrive_LocalValueResponse.Data getData() {
        return Data;
    }

    public void setData(Retrive_LocalValueResponse.Data data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }


    public class Data {


        private String SMU_SCH_COMPNO;

        private String SMU_SCH_SERTYPE;

        private String eng_signature;

        private String jobId;

        private List<MrDatum> mr_data = null;

        private String user_mobile_no;

        private String SMU_SCQH_QUOTENO;

        private String _id;

        private String customerAcknowledgement;

        private String customerName;

        private String customerNo;

        private String remarks;

        private String serviceType;

        private String techSignature;

        private String userId;

        private String SMU_ACK_COMPNO;

        public String getSMU_SCH_COMPNO() {
            return SMU_SCH_COMPNO;
        }

        public void setSMU_SCH_COMPNO(String SMU_SCH_COMPNO) {
            this.SMU_SCH_COMPNO = SMU_SCH_COMPNO;
        }

        public String getSMU_SCH_SERTYPE() {
            return SMU_SCH_SERTYPE;
        }

        public void setSMU_SCH_SERTYPE(String SMU_SCH_SERTYPE) {
            this.SMU_SCH_SERTYPE = SMU_SCH_SERTYPE;
        }

        public String getEng_signature() {
            return eng_signature;
        }

        public void setEng_signature(String eng_signature) {
            this.eng_signature = eng_signature;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public List<MrDatum> getMr_data() {
            return mr_data;
        }

        public void setMr_data(List<MrDatum> mr_data) {
            this.mr_data = mr_data;
        }

        public String getUser_mobile_no() {
            return user_mobile_no;
        }

        public void setUser_mobile_no(String user_mobile_no) {
            this.user_mobile_no = user_mobile_no;
        }

        public String getSMU_SCQH_QUOTENO() {
            return SMU_SCQH_QUOTENO;
        }

        public void setSMU_SCQH_QUOTENO(String SMU_SCQH_QUOTENO) {
            this.SMU_SCQH_QUOTENO = SMU_SCQH_QUOTENO;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCustomerAcknowledgement() {
            return customerAcknowledgement;
        }

        public void setCustomerAcknowledgement(String customerAcknowledgement) {
            this.customerAcknowledgement = customerAcknowledgement;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerNo() {
            return customerNo;
        }

        public void setCustomerNo(String customerNo) {
            this.customerNo = customerNo;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getTechSignature() {
            return techSignature;
        }

        public void setTechSignature(String techSignature) {
            this.techSignature = techSignature;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSMU_ACK_COMPNO() {
            return SMU_ACK_COMPNO;
        }

        public void setSMU_ACK_COMPNO(String SMU_ACK_COMPNO) {
            this.SMU_ACK_COMPNO = SMU_ACK_COMPNO;
        }

        public class MrDatum {

            private String partno;
            private String partname;
            private String req;
            private String title;
            private String value;

            public String getPartno() {
                return partno;
            }

            public void setPartno(String partno) {
                this.partno = partno;
            }

            public String getReq() {
                return req;
            }

            public void setReq(String req) {
                this.req = req;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getPartname() {
                return partname;
            }

            public void setPartname(String partname) {
                this.partname = partname;
            }
        }
    }


}
