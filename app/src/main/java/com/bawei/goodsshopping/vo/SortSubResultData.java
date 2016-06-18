package com.bawei.goodsshopping.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SortSubResultData implements Serializable{

    private String discount;//打折数
    private String product_id;  //商品ID
    private String name;//商品名称
    private String market_price;//参考价格
    private String sale_price;//销售价格
    private String sale_num;//销售数量
    private String brand_name;//品牌名称
    private String list_image;//图片URL
    private String left_icon_url;//左边图片URL
    private String right_icon_url;//右边图片URL

    @Override
    public String toString() {
        return "SortSubResultData{" +
                "discount='" + discount + '\'' +
                ", name='" + name + '\'' +
                ", market_price='" + market_price + '\'' +
                ", sale_price='" + sale_price + '\'' +
                ", sale_num='" + sale_num + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", list_image='" + list_image + '\'' +
                ", left_icon_url='" + left_icon_url + '\'' +
                ", right_icon_url='" + right_icon_url + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getSale_num() {
        return sale_num;
    }

    public void setSale_num(String sale_num) {
        this.sale_num = sale_num;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getList_image() {
        return list_image;
    }

    public void setList_image(String list_image) {
        this.list_image = list_image;
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
}
