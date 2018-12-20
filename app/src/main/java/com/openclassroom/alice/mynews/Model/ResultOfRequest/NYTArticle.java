package com.openclassroom.alice.mynews.Model.ResultOfRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.openclassroom.alice.mynews.Model.ResultOfRequest.Multimedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alice on 10 December 2018.
 */
public class NYTArticle {
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

    private static final String API_TOP_STORIES= "TopStories";
    private static final String API_MOST_POPULAR= "MostPopular";
    private static final String API_ARTICLE_SEARCH= "ArticleSearch";

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
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        if (webUrl!=null){
            return webUrl;
        } else {
            return url;
        }
    }
    public void setUrl(String url) {
        this.url = url;
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
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public List<Multimedia> getMultimedia() {
        return multimedia;
    }
    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
    public List<Media> getMedia() {
        return media;
    }
    public void setMedia(List<Media> media) {
        this.media = media;
    }
    public String getSubsection() {
        return subsection;
    }
    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }
    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPrintPage() {
        return printPage;
    }

    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionAndSubsection(){
        if (sectionName!=null) {
            section=sectionName;
        }
        if (!subsection.equals("")){
            //Log.d(TAG, "getSectionAndSubsection: "+subsection+"\"");
            return section + ">" + subsection;
        } else
            return section;
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
