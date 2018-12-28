package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alice on 10 December 2018.
 */
public class Media {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("approved_for_syndication")
    @Expose
    private Integer approvedForSyndication;
    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadata> mediaMetadata = null;

    public Media(String URL){
        this.mediaMetadata=new ArrayList<>();
        this.mediaMetadata.add(new MediaMetadata(URL));
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public Integer getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public List<MediaMetadata> getMediaMetadata() {
        return mediaMetadata;
    }

}
