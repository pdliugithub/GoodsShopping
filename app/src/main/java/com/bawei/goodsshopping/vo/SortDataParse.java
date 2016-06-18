package com.bawei.goodsshopping.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SortDataParse implements Serializable {

    private String status;
    private String reason;
    private List<SortResult> results;

    @Override
    public String toString() {
        return "SortDataParse{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", results=" + results +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<SortResult> getResults() {
        return results;
    }

    public void setResults(List<SortResult> results) {
        this.results = results;
    }
}
