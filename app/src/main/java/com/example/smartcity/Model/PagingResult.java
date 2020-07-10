package com.example.smartcity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PagingResult<T> {

    @SerializedName("paging")
    private List<T> result;
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("pageIndex")
    private int pageIndex;


    public PagingResult() {}

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
