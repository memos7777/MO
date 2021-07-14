package com.mialab.healthbutler.manager.domain.system;

import java.math.BigDecimal;

public class SystemRole implements java.io.Serializable {

    private static final long serialVersionUID = 6448138772115137382L;
    
    private Integer rid;
    
    private String id;

    private String pid;
    
    private String groupId;
    
    private String groupName;
    
    private String text;

    private BigDecimal seq;

    private String descript;

    private String parentId;

    private String parentText;

    private String state;

    private String checked;

    private String resourcesId;

    private String resourcesText;
    
    private String menusId;
    
    private String menusText;
    
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getResourcesText() {
        return resourcesText;
    }

    public void setResourcesText(String resourcesText) {
        this.resourcesText = resourcesText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentText() {
        return parentText;
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMenusId() {
        return menusId;
    }

    public void setMenusId(String menusId) {
        this.menusId = menusId;
    }

    public String getMenusText() {
        return menusText;
    }

    public void setMenusText(String menusText) {
        this.menusText = menusText;
    }

    @Override
    public String toString() {
        return "SystemRole [id=" + id + ", pid=" + pid + ", groupId=" + groupId + ", groupName=" + groupName
                + ", text=" + text + ", seq=" + seq + ", descript=" + descript + ", parentId=" + parentId
                + ", parentText=" + parentText + ", state=" + state + ", checked=" + checked + ", resourcesId="
                + resourcesId + ", resourcesText=" + resourcesText + ", menusId=" + menusId + ", menusText="
                + menusText + "]";
    }

}