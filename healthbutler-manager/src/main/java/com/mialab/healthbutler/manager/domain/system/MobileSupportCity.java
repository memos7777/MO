package com.mialab.healthbutler.manager.domain.system;

public class MobileSupportCity {
    private Integer cityId;

    private Short index;

    private Integer parentId;

    private String name;

    private Byte level;

    private Short backgroundVersion;

    private String background;

    private Byte tag;

    private Integer productId;

    private Integer cellStationId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Short getIndex() {
        return index;
    }

    public void setIndex(Short index) {
        this.index = index;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Short getBackgroundVersion() {
        return backgroundVersion;
    }

    public void setBackgroundVersion(Short backgroundVersion) {
        this.backgroundVersion = backgroundVersion;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    public Byte getTag() {
        return tag;
    }

    public void setTag(Byte tag) {
        this.tag = tag;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCellStationId() {
        return cellStationId;
    }

    public void setCellStationId(Integer cellStationId) {
        this.cellStationId = cellStationId;
    }
}