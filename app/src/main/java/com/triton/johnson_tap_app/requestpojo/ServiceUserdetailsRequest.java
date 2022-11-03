package com.triton.johnson_tap_app.requestpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceUserdetailsRequest {
    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("user_mobile_no")
    @Expose
    private String userMobileNo;
    private String SMU_SCH_COMPNO;
    private String SMU_SCH_SERTYPE;

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
}
