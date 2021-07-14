package com.mialab.healthbutler.manager.httpmodel;

public class DataGridModel implements java.io.Serializable {

    private static final long serialVersionUID = 7232798260610351343L;

    private int page; // 当前页,名字必须为page

    private int rows; // 每页大小,名字必须为rows

    private String sort; // 排序字段

    private String order; // 排序规则

    private int beginOffset;

    private int endOffset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getBeginOffset() {
        this.beginOffset = (page - 1) * rows;
        return this.beginOffset;
    }

    public void setBeginOffset(int beginOffset) {
        this.beginOffset = beginOffset;
    }

    public int getEndOffset() {
        this.endOffset = page * rows;
        return this.endOffset;
    }

    public void setEndOffset(int endOffset) {
        this.endOffset = endOffset;
    }

    @Override
    public String toString() {
        return "DataGridModel [page=" + page + ", rows=" + rows + ", sort=" + sort + ", order=" + order
                + ", beginOffset=" + beginOffset + ", endOffset=" + endOffset + "]";
    }

}
