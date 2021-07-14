package com.mialab.healthbutler.manager.domain.system;

import java.math.BigDecimal;

public class SystemMenu implements java.io.Serializable {

	private static final long serialVersionUID = -4032244612835599935L;
	private String id;

    private String pid;

    private String text;

    private String iconcls;

    private String src;

    private BigDecimal seq;

    private String parentId;

    private String parentText;

    private String state;

    private String checked;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
        return "SystemMenu [id=" + id + ", pid=" + pid + ", text=" + text + ", iconcls=" + iconcls + ", src=" + src
                + ", seq=" + seq + ", parentId=" + parentId + ", parentText=" + parentText + ", state=" + state
                + ", checked=" + checked + "]";
    }

}