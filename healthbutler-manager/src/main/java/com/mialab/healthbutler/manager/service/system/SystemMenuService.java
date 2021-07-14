package com.mialab.healthbutler.manager.service.system;

import java.util.List;

import com.mialab.healthbutler.manager.domain.system.SystemMenu;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;

public interface SystemMenuService {

	List<EasyuiTreeNode> tree(String id, Integer groupId);

	List<SystemMenu> treegrid(String id);

	int add(SystemMenu menu);

	int del(SystemMenu menu);

	int edit(SystemMenu menu);
	
	//=========================
	List<EasyuiTreeNode> tree();
}
