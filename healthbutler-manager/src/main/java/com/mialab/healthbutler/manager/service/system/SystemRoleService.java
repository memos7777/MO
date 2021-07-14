package com.mialab.healthbutler.manager.service.system;

import java.util.List;

import com.mialab.healthbutler.manager.domain.system.SystemRole;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;

public interface SystemRoleService {
	int add(SystemRole user);

    int edit(SystemRole user);

    List<SystemRole> listRoles();

    List<SystemRole> treegrid(String id);

    List<EasyuiTreeNode> tree(String id);

    void delete(SystemRole role);

}
