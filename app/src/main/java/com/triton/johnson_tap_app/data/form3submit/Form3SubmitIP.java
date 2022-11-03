
package com.triton.johnson_tap_app.data.form3submit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Form3SubmitIP {

    @SerializedName("collection_type")
    @Expose
    private String collectionType;
    @SerializedName("current_date")
    @Expose
    private String currentDate;
    @SerializedName("uploaded_file")
    @Expose
    private List<UploadedFile> uploadedFile = new ArrayList<UploadedFile>();
    @SerializedName("Agent_code")
    @Expose
    private String agentCode;
    @SerializedName("cheq_no")
    @Expose
    private String cheqNo;
    @SerializedName("rtgs_no")
    @Expose
    private String rtgsNo;
    @SerializedName("cheq_amount")
    @Expose
    private Integer cheqAmount;
    @SerializedName("cheq_date")
    @Expose
    private String cheqDate;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("third_party_chq")
    @Expose
    private String thirdPartyChq;
    @SerializedName("ded_it")
    @Expose
    private Integer dedIt;
    @SerializedName("ded_gst")
    @Expose
    private Integer dedGst;
    @SerializedName("ded_other_one_type")
    @Expose
    private String dedOtherOneType;
    @SerializedName("ded_other_one_value")
    @Expose
    private Integer dedOtherOneValue;
    @SerializedName("ded_other_two_type")
    @Expose
    private String dedOtherTwoType;
    @SerializedName("ded_other_two_value")
    @Expose
    private Integer dedOtherTwoValue;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("collection_value")
    @Expose
    private String collection_value;
    @SerializedName("job_details")
    @Expose
    private List<JobDetail> jobDetails = new ArrayList<JobDetail>();

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<UploadedFile> getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(List<UploadedFile> uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getCheqNo() {
        return cheqNo;
    }

    public void setCheqNo(String cheqNo) {
        this.cheqNo = cheqNo;
    }

    public String getRtgsNo() {
        return rtgsNo;
    }

    public void setRtgsNo(String rtgsNo) {
        this.rtgsNo = rtgsNo;
    }

    public Integer getCheqAmount() {
        return cheqAmount;
    }

    public void setCheqAmount(Integer cheqAmount) {
        this.cheqAmount = cheqAmount;
    }

    public String getCheqDate() {
        return cheqDate;
    }

    public void setCheqDate(String cheqDate) {
        this.cheqDate = cheqDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getThirdPartyChq() {
        return thirdPartyChq;
    }

    public void setThirdPartyChq(String thirdPartyChq) {
        this.thirdPartyChq = thirdPartyChq;
    }

    public Integer getDedIt() {
        return dedIt;
    }

    public void setDedIt(Integer dedIt) {
        this.dedIt = dedIt;
    }

    public Integer getDedGst() {
        return dedGst;
    }

    public void setDedGst(Integer dedGst) {
        this.dedGst = dedGst;
    }

    public String getDedOtherOneType() {
        return dedOtherOneType;
    }

    public void setDedOtherOneType(String dedOtherOneType) {
        this.dedOtherOneType = dedOtherOneType;
    }

    public Integer getDedOtherOneValue() {
        return dedOtherOneValue;
    }

    public void setDedOtherOneValue(Integer dedOtherOneValue) {
        this.dedOtherOneValue = dedOtherOneValue;
    }

    public String getDedOtherTwoType() {
        return dedOtherTwoType;
    }

    public void setDedOtherTwoType(String dedOtherTwoType) {
        this.dedOtherTwoType = dedOtherTwoType;
    }

    public Integer getDedOtherTwoValue() {
        return dedOtherTwoValue;
    }

    public void setDedOtherTwoValue(Integer dedOtherTwoValue) {
        this.dedOtherTwoValue = dedOtherTwoValue;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

}
