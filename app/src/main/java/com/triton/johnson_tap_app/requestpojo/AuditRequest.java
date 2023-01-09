package com.triton.johnson_tap_app.requestpojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditRequest {


    private List<MrDatum> mrData = null;

    private List<FieldValueDatum> fieldValueData = null;

    private String customerSignature;

    private String jobId;

    private String job_id;

    private String user_mobile_no;

    private String OM_OSA_COMPNO;

    private String omOsaCompno;

    private String userMobileNo;

    private String service_type;

    private int PageNumber;

    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public List<MrDatum> getMrData() {
        return mrData;
    }


    public void setMrData(List<MrDatum> mrData) {
        this.mrData = mrData;
    }


    public List<FieldValueDatum> getFieldValueData() {
        return fieldValueData;
    }


    public void setFieldValueData(List<FieldValueDatum> fieldValueData) {
        this.fieldValueData = fieldValueData;
    }


    public String getCustomerSignature() {
        return customerSignature;
    }


    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }


    public String getJobId() {
        return jobId;
    }


    public void setJobId(String jobId) {
        this.jobId = jobId;
    }


    public String getOmOsaCompno() {
        return omOsaCompno;
    }


    public void setOmOsaCompno(String omOsaCompno) {
        this.omOsaCompno = omOsaCompno;
    }


    public String getUserMobileNo() {
        return userMobileNo;
    }


    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    public String getOM_OSA_COMPNO() {
        return OM_OSA_COMPNO;
    }

    public void setOM_OSA_COMPNO(String OM_OSA_COMPNO) {
        this.OM_OSA_COMPNO = OM_OSA_COMPNO;
    }

//
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }

    public static class MrDatum {


        private String partno;

        private String req;

        private String title;

        private String value;

        private String partname;

       // private Map<String, Object> additionalProperties = new HashMap<String, Object>();


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


//        public Map<String, Object> getAdditionalProperties() {
//            return this.additionalProperties;
//        }
//
//
//        public void setAdditionalProperty(String name, Object value) {
//            this.additionalProperties.put(name, value);
//        }
    }

    public static class FieldValueDatum {


        private String fieldCatId;

        private String fieldComments;

        private String fieldGroupId;

        private String fieldName;

        private String fieldValue;

        private String fieldRemarks;

       // private Map<String, Object> additionalProperties = new HashMap<String, Object>();


        public String getFieldCatId() {
            return fieldCatId;
        }


        public void setFieldCatId(String fieldCatId) {
            this.fieldCatId = fieldCatId;
        }


        public String getFieldComments() {
            return fieldComments;
        }


        public void setFieldComments(String fieldComments) {
            this.fieldComments = fieldComments;
        }


        public String getFieldGroupId() {
            return fieldGroupId;
        }


        public void setFieldGroupId(String fieldGroupId) {
            this.fieldGroupId = fieldGroupId;
        }


        public String getFieldName() {
            return fieldName;
        }


        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }


        public String getFieldValue() {
            return fieldValue;
        }


        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }


        public String getFieldRemarks() {
            return fieldRemarks;
        }


        public void setFieldRemarks(String fieldRemarks) {
            this.fieldRemarks = fieldRemarks;
        }


//        public Map<String, Object> getAdditionalProperties() {
//            return this.additionalProperties;
//        }
//
//
//        public void setAdditionalProperty(String name, Object value) {
//            this.additionalProperties.put(name, value);
//        }
    }
}
