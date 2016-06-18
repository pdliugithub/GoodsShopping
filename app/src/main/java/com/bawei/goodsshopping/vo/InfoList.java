package com.bawei.goodsshopping.vo;

/**
 * Created by Administrator on 2016/3/17.
 */
public class InfoList {

    private String title;
    private String url; //WebView httpUrl
    private String image;   //ImageUrl

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "InfoList{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
