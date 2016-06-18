package com.bawei.goodsshopping.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SortResult implements Serializable {

    private String id;
    private String virtual_name;    //虚拟名称
    private List<SortBrandCategory> brand_category;
    private List<SortSecond> second;

    @Override
    public String toString() {
        return "SortResult{" +
                "id='" + id + '\'' +
                ", virtual_name='" + virtual_name + '\'' +
                ", brand_category=" + brand_category +
                ", second=" + second +
                '}';
    }

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

    public List<SortBrandCategory> getBrand_category() {
        return brand_category;
    }

    public void setBrand_category(List<SortBrandCategory> brand_category) {
        this.brand_category = brand_category;
    }

    public List<SortSecond> getSecond() {
        return second;
    }

    public void setSecond(List<SortSecond> second) {
        this.second = second;
    }
}
