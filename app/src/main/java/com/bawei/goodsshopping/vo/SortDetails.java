package com.bawei.goodsshopping.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SortDetails implements Serializable {


    private int status; //请求状态
    private SortDetailsResults results; //详情对象

    @Override
    public String toString() {
        return "SortDetails{" +
                "results=" + results +
                ", status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SortDetailsResults getResults() {
        return results;
    }

    public void setResults(SortDetailsResults results) {
        this.results = results;
    }
}
