package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alice on 10 December 2018.
 */
public class MediaMetadata {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;

    public MediaMetadata(String URL){
        this.url=URL;
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
