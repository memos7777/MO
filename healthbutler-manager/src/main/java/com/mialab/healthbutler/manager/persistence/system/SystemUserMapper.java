package com.mialab.healthbutler.manager.persistence.system;

import java.util.List;
import java.util.Map;

import com.mialab.healthbutler.manager.domain.system.SystemUser;

public interface SystemUserMapper {
    int save(SystemUser record);

    int update(SystemUser record);

    int remove(Integer id);

    List<SystemUser> find(Map<String, Object> para);

    String findCount(Map<String, Object> para);
    
    SystemUser findByName(String accountName);

    int saveAccountGroup(SystemUser bean);

    int updateAccountGroup(SystemUser bean);

    void removeAccountGroup(Integer valueOf);

}