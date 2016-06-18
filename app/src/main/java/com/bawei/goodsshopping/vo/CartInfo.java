package com.bawei.goodsshopping.vo;

import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/25.
 */
public class CartInfo implements Serializable {

    private int uid;       //自增ID
    private String uname;   //详细名称
    private int product_id;     //生产ID
    private String list_image;  //图片
    private int sale_price;     //售价
    private int market_price;   //标价
    private String category_name;   //商品品牌名称
    private int cart_count;     //商品的数量
    private boolean isChecked; //是否选中



    @Override
    public String toString() {
        return "CartInfo{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", product_id=" + product_id +
                ", list_image='" + list_image + '\'' +
                ", sale_price=" + sale_price +
                ", market_price=" + market_price +
                ", category_name='" + category_name + '\'' +
                ", isChecked=" + isChecked +
                ", cart_count=" + cart_count +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getList_image() {
        return list_image;
    }

    public void setList_image(String list_image) {
        this.list_image = list_image;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public int getMarket_price() {
        return market_price;
    }

    public void setMarket_price(int market_price) {
        this.market_price = market_price;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    public int getCart_count() {
        return cart_count;
    }

    public void setCart_count(int cart_count) {
        this.cart_count = cart_count;
    }
}
