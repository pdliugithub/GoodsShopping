package com.bawei.goodsshopping.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SortSecond implements Serializable{

    private String id;
    private String virtual_name;    //虚拟名称
    private List<SortThird> third;

    @Override
    public String toString() {
        return "SortSecond{" +
                "third=" + third +
                ", virtual_name='" + virtual_name + '\'' +
                ", id='" + id + '\'' +
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

    public List<SortThird> getThird() {
        return third;
    }

    public void setThird(List<SortThird> third) {
        this.third = third;
    }
}
