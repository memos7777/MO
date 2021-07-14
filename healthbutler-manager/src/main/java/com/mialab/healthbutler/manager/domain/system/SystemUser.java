package com.mialab.healthbutler.manager.domain.system;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.mialab.healthbutler.manager.util.JsonDateSerializer;

public class SystemUser extends SystemCity implements Serializable {	
	
	private Integer accountId;

	private Integer groupId;

	private String groupName;

	private String accountName;

	private String accountPwd;

	private String newAccountPwd;

	private String validCode;

	private String trueName;

	private String sex;

	private Date birthday;

	private String dept;

	private String officePhone;

	private String mobile;

	private String email;

	private Boolean isDel;

	private Integer entryNumber;

	private Date createTime;

	private String pwdb;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd == null ? null : accountPwd.trim();
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName == null ? null : trueName.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept == null ? null : dept.trim();
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone == null ? null : officePhone.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Integer getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(Integer entryNumber) {
		this.entryNumber = entryNumber;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPwdb() {
		return pwdb;
	}

	public void setPwdb(String pwdb) {
		this.pwdb = pwdb == null ? null : pwdb.trim();
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getNewAccountPwd() {
		return newAccountPwd;
	}

	public void setNewAccountPwd(String newAccountPwd) {
		this.newAccountPwd = newAccountPwd;
	}

	@Override
	public String toString() {
		return "SystemUser [accountId=" + accountId + ", groupId=" + groupId + ", groupName=" + groupName + ", accountName=" + accountName;
	}


}
