package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alice on 10 December 2018.
 */
public class Multimedia {
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("credit")
    @Expose
    private Object credit;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("crop_name")
    @Expose
    private String cropName;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    public Multimedia(String URL){
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

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getCopyright() {
        return copyright;
    }

}
