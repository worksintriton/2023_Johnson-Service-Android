
package com.triton.johnson_tap_app.data.form3submit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobDetail {

    @SerializedName("s_no")
    @Expose
    private Integer sNo;
    @SerializedName("job_no")
    @Expose
    private String jobNo;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("contract_no")
    @Expose
    private String contractNo;
    @SerializedName("pay_type")
    @Expose
    private String payType;
    @SerializedName("frm")
    @Expose
    private String frm;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("pay_amount")
    @Expose
    private Integer payAmount;

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

}
