package com.bawei.goodsshopping.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
public class DataList {

    private List<InfoList> list;



    public List<InfoList> getList() {
        return list;
    }

    public void setList(List<InfoList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "list=" + list +
                '}';
    }
}
