package com.mialab.healthbutler.manager.domain.system;

public class ModuleInfo {
    private Short moduleId;

    private String moduleVersion;

    private String moduleName;

    private String moduleKey;

    private Integer options;

    private Integer iconVersion;

    private String iconUrl;

    private Short moduleType;

    private Short moduleLevel;

    private String moduleUrl;

    private String displayClientVersion;

    private String iphoneDisplayClientVersion;

    private String runClientVersion;

    private String iphoneRunClientVersion;

    private String description;

    private Short tag;

    public Short getModuleId() {
        return moduleId;
    }

    public void setModuleId(Short moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion == null ? null : moduleVersion.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey == null ? null : moduleKey.trim();
    }

    public Integer getOptions() {
        return options;
    }

    public void setOptions(Integer options) {
        this.options = options;
    }

    public Integer getIconVersion() {
        return iconVersion;
    }

    public void setIconVersion(Integer iconVersion) {
        this.iconVersion = iconVersion;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public Short getModuleType() {
        return moduleType;
    }

    public void setModuleType(Short moduleType) {
        this.moduleType = moduleType;
    }

    public Short getModuleLevel() {
        return moduleLevel;
    }

    public void setModuleLevel(Short moduleLevel) {
        this.moduleLevel = moduleLevel;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl == null ? null : moduleUrl.trim();
    }

    public String getDisplayClientVersion() {
        return displayClientVersion;
    }

    public void setDisplayClientVersion(String displayClientVersion) {
        this.displayClientVersion = displayClientVersion == null ? null : displayClientVersion.trim();
    }

    public String getIphoneDisplayClientVersion() {
        return iphoneDisplayClientVersion;
    }

    public void setIphoneDisplayClientVersion(String iphoneDisplayClientVersion) {
        this.iphoneDisplayClientVersion = iphoneDisplayClientVersion == null ? null : iphoneDisplayClientVersion.trim();
    }

    public String getRunClientVersion() {
        return runClientVersion;
    }

    public void setRunClientVersion(String runClientVersion) {
        this.runClientVersion = runClientVersion == null ? null : runClientVersion.trim();
    }

    public String getIphoneRunClientVersion() {
        return iphoneRunClientVersion;
    }

    public void setIphoneRunClientVersion(String iphoneRunClientVersion) {
        this.iphoneRunClientVersion = iphoneRunClientVersion == null ? null : iphoneRunClientVersion.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getTag() {
        return tag;
    }

    public void setTag(Short tag) {
        this.tag = tag;
    }
}