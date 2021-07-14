package com.mialab.healthbutler.manager.service.system;

import java.util.List;

import com.mialab.healthbutler.manager.domain.system.SystemResource;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;

public interface SystemResourceService {
    
    List<EasyuiTreeNode> tree(String id, Integer integer);

    List<SystemResource> treegrid(String id);

    int add(SystemResource resources);

    int del(String id);

    int edit(SystemResource resources);

    SystemResource getResourcesByRequestPath(String requestPath);

    boolean checkRoleResources(int groupId, String requestPath);
}
