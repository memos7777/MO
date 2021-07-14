package com.mialab.healthbutler.manager.persistence.system;

import java.util.List;
import java.util.Map;

import com.mialab.healthbutler.manager.domain.system.SystemRole;

public interface SystemRoleMapper {
    int save(SystemRole record);

    int update(SystemRole record);

    List<SystemRole> find(Map<String, Object> para);

    String findCount(Map<String, Object> para);

    List<SystemRole> listRoles();

    String findByGroupId(Integer groupId);

    int saveRoleMenus(Map<String, String> para);

    List<SystemRole> find(String id);

    List<SystemRole> getSubTree(String id);

    void del(String id);

    void deleteRoleResources(String roleId);

    void saveRoleResources(Map<String, String> roleResources);

    void deleteRoleMenus(String menuId);
}