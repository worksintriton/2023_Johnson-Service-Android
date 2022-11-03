
package com.triton.johnson_tap_app.data.form3submit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadedFile {

    @SerializedName("image")
    @Expose
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
