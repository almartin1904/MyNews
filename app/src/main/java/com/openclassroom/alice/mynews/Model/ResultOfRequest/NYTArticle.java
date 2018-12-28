package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.Multimedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alice on 10 December 2018.
 */
public class NYTArticle {

    private static final String TAG = NYTArticle.class.getSimpleName();
    
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("print_page")
    @Expose
    private String printPage;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private Integer wordCount;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia = null;
    @SerializedName("media")
    @Expose
    private List<Media> media = null;
    @SerializedName("subsection")
    @Expose
    private String subsection="";
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("abstract")
    @Expose
    private String _abstract;

    public static final String API_TOP_STORIES= "TopStories";
    public static final String API_MOST_POPULAR= "MostPopular";
    public static final String API_ARTICLE_SEARCH= "ArticleSearch";

    public NYTArticle(String API, String title, String URL, String publishedDate, String section, String subsection, String imageURL){
        switch (API){
            case API_TOP_STORIES:
                this.title = title;
                this.url = URL;
                this.publishedDate=publishedDate;
                this.section=section;
                this.subsection=subsection;
                this.multimedia = new ArrayList<>();
                this.multimedia.add(new Multimedia(imageURL));
                break;
            case API_MOST_POPULAR:
                this.title = title;
                this.url = URL;
                this.publishedDate=publishedDate;
                this.section=section;
                this.media = new ArrayList<>();
                this.media.add(new Media(imageURL));
            case API_ARTICLE_SEARCH:
                this.snippet = title;
                this.webUrl = URL;
                this.pubDate=publishedDate;
                this.section=section;
                this.media = new ArrayList<>();
                this.media.add(new Media(imageURL));
            default:
        }
    }

    public String getTitle() {
        if (snippet!=null){
            return snippet;
        } else {
            return title;
        }
    }

    public String getUrl() {
        if (webUrl!=null){
            return webUrl;
        } else {
            return url;
        }
    }

    public String getPublishedDate() {
        String date;
        if (pubDate!=null){
            date=pubDate;
        }else {
            date=publishedDate;
        }
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);
        return day + "/" + month + "/" + year;
    }

    public String getSection() {
        return section;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public List<Media> getMedia() {
        return media;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public String getAbstract() {
        return _abstract;
    }

    public String getPrintPage() {
        return printPage;
    }

    public String getSource() {
        return source;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getId() {
        return id;
    }

    public String getSectionAndSubsection(){
        String result=section;
        if (sectionName!=null) {
            result=sectionName;
        }
        if (subsection!=null && !subsection.equals("")){
            result=result+">"+subsection;
        }
        return result;
    }

    public String getImageURL () {
        if (media!=null){
            return media.get(0).getMediaMetadata().get(0).getUrl();
        } else
        {
            if (multimedia!=null){
                String url=multimedia.get(0).getUrl();
                if (!url.substring(0,4).equals("http"))
                {
                    url = "https://static01.nyt.com/"+url;
                }
                return url;
            } else
            {
                return "";
            }
        }
    }
}
