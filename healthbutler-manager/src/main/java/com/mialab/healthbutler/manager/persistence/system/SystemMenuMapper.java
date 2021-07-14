package com.mialab.healthbutler.manager.persistence.system;

import java.util.List;
import java.util.Map;

import com.mialab.healthbutler.manager.domain.system.SystemMenu;

public interface SystemMenuMapper {

    List<SystemMenu> find(String id);

    int save(SystemMenu menu);

    int update(SystemMenu menu);

    int del(String id);

    List<SystemMenu> getTree(String id);

	List<SystemMenu> getSubTree(String id);
	
	List<SystemMenu> findRoleMenu(Map<String, String> para);

    List<SystemMenu> findMenusByRole(String id);

}
