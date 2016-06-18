package com.bawei.goodsshopping.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SortSubParse implements Serializable{

        private SortSubResults results;

    @Override
    public String
    toString() {
        return "SortSubParse{" +
                "results=" + results +
                '}';
    }

    public SortSubResults getResults() {
        return results;
    }

    public void setResults(SortSubResults results) {
        this.results = results;
    }
}
