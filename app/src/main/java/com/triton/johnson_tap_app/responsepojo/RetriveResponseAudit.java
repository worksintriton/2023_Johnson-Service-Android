package com.triton.johnson_tap_app.responsepojo;

import java.util.List;

public class RetriveResponseAudit {

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

    public RetriveResponseAudit.Data getData() {
        return Data;
    }

    public void setData(RetriveResponseAudit.Data data) {
        Data = data;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public class Data {

        private String customerSignature;
        private List<FieldValueDatum> fieldValueData = null;
        private String jobId;
        private String service_type;
        private List<MrDatum> mrData = null;
        private String omOsaCompno;
        private String userMobileNo;
        private int PageNumber;

        public String getCustomerSignature() {
            return customerSignature;
        }

        public void setCustomerSignature(String customerSignature) {
            this.customerSignature = customerSignature;
        }

        public List<FieldValueDatum> getFieldValueData() {
            return fieldValueData;
        }

        public void setFieldValueData(List<FieldValueDatum> fieldValueData) {
            this.fieldValueData = fieldValueData;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public List<MrDatum> getMrData() {
            return mrData;
        }

        public void setMrData(List<MrDatum> mrData) {
            this.mrData = mrData;
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

        public class FieldValueDatum {

            private String fieldCatId;
            private String fieldComments;
            private String fieldGroupId;
            private String fieldName;
            private String fieldRemarks;
            private String fieldValue;

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

            public String getFieldRemarks() {
                return fieldRemarks;
            }

            public void setFieldRemarks(String fieldRemarks) {
                this.fieldRemarks = fieldRemarks;
            }

            public String getFieldValue() {
                return fieldValue;
            }

            public void setFieldValue(String fieldValue) {
                this.fieldValue = fieldValue;
            }
        }

        public class MrDatum {
            private String partname;
            private String partno;
            private String req;
            private String value;

            public String getPartname() {
                return partname;
            }

            public void setPartname(String partname) {
                this.partname = partname;
            }

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
