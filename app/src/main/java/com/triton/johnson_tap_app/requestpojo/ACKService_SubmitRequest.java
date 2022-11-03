package com.triton.johnson_tap_app.requestpojo;

public class ACKService_SubmitRequest {

    private String jobId;
    private String userId;
    private String serviceType;
    private String techSignature;
    private String customerAcknowledgement;
    private String SMU_ACK_COMPNO;
    private String _id;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCustomerAcknowledgement() {
        return customerAcknowledgement;
    }

    public void setCustomerAcknowledgement(String customerAcknowledgement) {
        this.customerAcknowledgement = customerAcknowledgement;
    }

    public String getSMU_ACK_COMPNO() {
        return SMU_ACK_COMPNO;
    }

    public void setSMU_ACK_COMPNO(String SMU_ACK_COMPNO) {
        this.SMU_ACK_COMPNO = SMU_ACK_COMPNO;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
