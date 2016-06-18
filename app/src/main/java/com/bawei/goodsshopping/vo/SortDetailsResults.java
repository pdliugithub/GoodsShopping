package com.bawei.goodsshopping.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SortDetailsResults implements Serializable{

    private int product_id; //
    private String code;    //
    private String name;    //商品的名称
    private int market_price;   //参考价格
    private int sale_price;     //销售价格
    private String status;  //状态
    private String if_can_use_card; //
    private String time_to_market;  //
    private int brand_id;   //
    private int category_id;    //
    private String main_color;  //主要颜色
    private String is_outlets;  //
    private int is_import;
    private int is_collect;
    private String import_logo;
    private String explain;
    private String left_icon_url;
    private String right_icon_url;

    private String brand_name;  //商品品牌名称
    private int gender_id;
    private String gender_name;

    private SortDetailPhotos photos;    //商品详情的photos
    private String list_image;  //图片
    private String category_name;   //

    private String notes;   //注释

    @Override
    public String toString() {
        return "SortDetailsResults{" +
                "product_id=" + product_id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", market_price=" + market_price +
                ", sale_price=" + sale_price +
                ", status='" + status + '\'' +
                ", if_can_use_card='" + if_can_use_card + '\'' +
                ", time_to_market='" + time_to_market + '\'' +
                ", brand_id=" + brand_id +
                ", category_id=" + category_id +
                ", main_color='" + main_color + '\'' +
                ", is_outlets='" + is_outlets + '\'' +
                ", is_import=" + is_import +
                ", is_collect=" + is_collect +
                ", import_logo='" + import_logo + '\'' +
                ", explain='" + explain + '\'' +
                ", left_icon_url='" + left_icon_url + '\'' +
                ", right_icon_url='" + right_icon_url + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", gender_id=" + gender_id +
                ", gender_name='" + gender_name + '\'' +
                ", photos=" + photos +
                ", list_image='" + list_image + '\'' +
                ", category_name='" + category_name + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarket_price() {
        return market_price;
    }

    public void setMarket_price(int market_price) {
        this.market_price = market_price;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIf_can_use_card() {
        return if_can_use_card;
    }

    public void setIf_can_use_card(String if_can_use_card) {
        this.if_can_use_card = if_can_use_card;
    }

    public String getTime_to_market() {
        return time_to_market;
    }

    public void setTime_to_market(String time_to_market) {
        this.time_to_market = time_to_market;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getMain_color() {
        return main_color;
    }

    public void setMain_color(String main_color) {
        this.main_color = main_color;
    }

    public String getIs_outlets() {
        return is_outlets;
    }

    public void setIs_outlets(String is_outlets) {
        this.is_outlets = is_outlets;
    }

    public int getIs_import() {
        return is_import;
    }

    public void setIs_import(int is_import) {
        this.is_import = is_import;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public String getImport_logo() {
        return import_logo;
    }

    public void setImport_logo(String import_logo) {
        this.import_logo = import_logo;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getLeft_icon_url() {
        return left_icon_url;
    }

    public void setLeft_icon_url(String left_icon_url) {
        this.left_icon_url = left_icon_url;
    }

    public String getRight_icon_url() {
        return right_icon_url;
    }

    public void setRight_icon_url(String right_icon_url) {
        this.right_icon_url = right_icon_url;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public SortDetailPhotos getPhotos() {
        return photos;
    }

    public void setPhotos(SortDetailPhotos photos) {
        this.photos = photos;
    }

    public String getList_image() {
        return list_image;
    }

    public void setList_image(String list_image) {
        this.list_image = list_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
