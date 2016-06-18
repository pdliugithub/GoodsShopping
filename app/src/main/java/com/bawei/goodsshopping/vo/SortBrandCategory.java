package com.bawei.goodsshopping.vo;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SortBrandCategory {

    private String brand_id;    //标记的ID
    private String brand_name;  //标记名称
    private String brand_english_name;  //标记英语名称
    private String brand_picture;   //标记图片
    private String category_id; //分类ID
    private String category_name;   //分类名称

    @Override
    public String toString() {
        return "SortBrandCategory{" +
                "brand_id='" + brand_id + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", brand_english_name='" + brand_english_name + '\'' +
                ", brand_picture='" + brand_picture + '\'' +
                ", category_id='" + category_id + '\'' +
                ", category_name='" + category_name + '\'' +
                '}';
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_english_name() {
        return brand_english_name;
    }

    public void setBrand_english_name(String brand_english_name) {
        this.brand_english_name = brand_english_name;
    }

    public String getBrand_picture() {
        return brand_picture;
    }

    public void setBrand_picture(String brand_picture) {
        this.brand_picture = brand_picture;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
