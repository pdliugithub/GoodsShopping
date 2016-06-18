package com.bawei.goodsshopping.vo;

/**
 * Created by Administrator on 2016/3/15.
 */
public class HotRootData {

    private String id;
    private String name;
    private String link;
    private String img;
    private String short_description;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HotRootData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", img='" + img + '\'' +
                ", short_description='" + short_description + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
