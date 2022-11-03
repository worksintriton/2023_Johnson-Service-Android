
package com.triton.johnson_tap_app.breakdown_service_submit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class FeedbackDetail {

    @SerializedName("feedback_group_code")
    @Expose
    private String feedbackGroupCode;
    @SerializedName("feedback_group_title")
    @Expose
    private String feedbackGroupTitle;
    @SerializedName("codes")
    @Expose
    private String codes;
    @SerializedName("title")
    @Expose
    private String title;

    public String getFeedbackGroupCode() {
        return feedbackGroupCode;
    }

    public void setFeedbackGroupCode(String feedbackGroupCode) {
        this.feedbackGroupCode = feedbackGroupCode;
    }

    public String getFeedbackGroupTitle() {
        return feedbackGroupTitle;
    }

    public void setFeedbackGroupTitle(String feedbackGroupTitle) {
        this.feedbackGroupTitle = feedbackGroupTitle;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
