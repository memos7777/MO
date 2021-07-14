package com.mialab.healthbutler.manager.domain.system;

import java.math.BigDecimal;

public class SystemResource {
    private String id;

    private String pid;

    private String text;

    private BigDecimal seq;

    private String src;

    private String descript;

    private String onoff;

    private String parentId;

    private String parentText;

    private String state;

    private String checked;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public String getOnoff() {
        return onoff;
    }

    public void setOnoff(String onoff) {
        this.onoff = onoff == null ? null : onoff.trim();
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

    @Override
    public String toString() {
        return "Resources [id=" + id + ", pid=" + pid + ", text=" + text + ", seq=" + seq + ", src=" + src
                + ", descript=" + descript + ", onoff=" + onoff + ", parentId=" + parentId + ", parentText="
                + parentText + ", state=" + state + ", checked=" + checked + "]";
    }


}