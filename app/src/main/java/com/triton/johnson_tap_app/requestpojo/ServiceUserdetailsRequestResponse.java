package com.triton.johnson_tap_app.requestpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceUserdetailsRequestResponse {

    @Expose
    private String jobId;
    @SerializedName("user_mobile_no")
    @Expose
    private String userMobileNo;
    @SerializedName("eng_signature")
    @Expose
    private String engSignature;

    private String SMU_SCH_COMPNO;
    private String SMU_SCH_SERTYPE;
    private String SMU_SCQH_QUOTENO;
    @SerializedName("mr_data")
    @Expose
    private List<MrDatum> mrData = null;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public String getEngSignature() {
        return engSignature;
    }

    public void setEngSignature(String engSignature) {
        this.engSignature = engSignature;
    }

    public List<MrDatum> getMrData() {
        return mrData;
    }

    public void setMrData(List<MrDatum> mrData) {
        this.mrData = mrData;
    }

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

    public String getSMU_SCQH_QUOTENO() {
        return SMU_SCQH_QUOTENO;
    }

    public void setSMU_SCQH_QUOTENO(String SMU_SCQH_QUOTENO) {
        this.SMU_SCQH_QUOTENO = SMU_SCQH_QUOTENO;
    }

    public static class MrDatum {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("partno")
        @Expose
        private String partno;

        private String partname;

        @SerializedName("req")
        @Expose
        private String req;

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

        public String getPartname() {
            return partname;
        }

        public void setPartname(String partname) {
            this.partname = partname;
        }
    }
}
