package com.bawei.goodsshopping.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SortThird implements Serializable{


    private String id;
    private String virtual_name;    //标记名称
    private String virtual_icon;    //标记icon
    private String brand_id;    //标记ID
    private String category_id; //分类ID
    private String keyword; //
    private String topic_id;    //
    private String type;
    private String category_name;   //分类名称
    private String brand_english_name;  //分类英语名称



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVirtual_name() {
        return virtual_name;
    }

    public void setVirtual_name(String virtual_name) {
        this.virtual_name = virtual_name;
    }

    public String getVirtual_icon() {
        return virtual_icon;
    }

    public void setVirtual_icon(String virtual_icon) {
        this.virtual_icon = virtual_icon;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBrand_english_name() {
        return brand_english_name;
    }

    public void setBrand_english_name(String brand_english_name) {
        this.brand_english_name = brand_english_name;
    }

    @Override
    public String toString() {
        return "SortThird{" +
                "id='" + id + '\'' +
                ", virtual_name='" + virtual_name + '\'' +
                ", virtual_icon='" + virtual_icon + '\'' +
                ", brand_id='" + brand_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", keyword='" + keyword + '\'' +
                ", topic_id='" + topic_id + '\'' +
                ", type='" + type + '\'' +
                ", category_name='" + category_name + '\'' +
                ", brand_english_name='" + brand_english_name + '\'' +
                '}';
    }
}
