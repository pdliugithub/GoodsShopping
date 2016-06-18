package com.bawei.goodsshopping.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SortSubResults implements Serializable{

    private List<SortSubResultData> results;

    @Override
    public String toString() {
        return "SortSubResults{" +
                "results=" + results +
                '}';
    }

    public List<SortSubResultData> getResults() {
        return results;
    }

    public void setResults(List<SortSubResultData> results) {
        this.results = results;
    }
}
