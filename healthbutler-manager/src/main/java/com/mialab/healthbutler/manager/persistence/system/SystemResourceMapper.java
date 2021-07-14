package com.mialab.healthbutler.manager.persistence.system;

import java.util.List;
import java.util.Map;

import com.mialab.healthbutler.manager.domain.system.SystemResource;

public interface SystemResourceMapper {

    List<SystemResource> find(String id);

    int save(SystemResource resources);

    int update(SystemResource resources);

    List<SystemResource> getSubTree(String id);

    int del(String id);

    SystemResource findBySrc(String requestPath);

    SystemResource findRoleResources(Map<String, Object> para);

    List<SystemResource> findRoleResource(Map<String, String> para);

    List<SystemResource> findResourcesByRole(String id);
}