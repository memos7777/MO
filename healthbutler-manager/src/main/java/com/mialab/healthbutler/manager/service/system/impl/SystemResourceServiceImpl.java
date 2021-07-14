package com.mialab.healthbutler.manager.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mialab.healthbutler.manager.domain.system.SystemResource;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;
import com.mialab.healthbutler.manager.persistence.system.SystemResourceMapper;
import com.mialab.healthbutler.manager.service.system.SystemResourceService;

@Service("resourcesService")
public class SystemResourceServiceImpl implements SystemResourceService {

    private static final Logger logger = Logger.getLogger(SystemMenuServiceImpl.class);
    
    @Autowired
    private SystemResourceMapper resourcesMapper;

    @Override
    public List<EasyuiTreeNode> tree(String id, Integer groupId) {
        logger.debug("start method tree...\tparameter id=" + id + ", groupId=" + groupId);
        
        List<SystemResource> resourceList = null;
        if (groupId == 1) {
            resourceList = resourcesMapper.getSubTree(id);
        } else {
            Map<String, String> para = new HashMap<String, String>();
            para.put("groupId", String.valueOf(groupId));
            para.put("pid", id);
            resourceList = resourcesMapper.findRoleResource(para);
        }
        
        List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
        for (SystemResource res : resourceList) {
            tree.add(tree(res, false, groupId));
        }
        return tree;
    }

    public EasyuiTreeNode tree(SystemResource res, boolean recursive, Integer groupId) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(res.getId());
        node.setText(res.getText());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("src", res.getSrc());
        node.setAttributes(attributes);
        
        List<SystemResource> subResourceList = null;
        if (groupId == 1) {
            subResourceList = resourcesMapper.getSubTree(res.getId());
        } else {
            Map<String, String> para = new HashMap<String, String>();
            para.put("groupId", String.valueOf(groupId));
            para.put("pid", res.getId());
            subResourceList = resourcesMapper.findRoleResource(para);
        }
        
        if (subResourceList != null && subResourceList.size() > 0) {
            node.setState("closed");
            if (recursive) {// 递归查询子节点
//              List<SystemMenu> symenuList = new ArrayList<SystemMenu>(menu.getSymenus());
//              Collections.sort(symenuList, new MenuComparator());// 排序
//              List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
//              for (SystemMenu m : symenuList) {
//                  EasyuiTreeNode t = tree(m, true);
//                  children.add(t);
//              }
//              node.setChildren(children);
            }
        }
        return node;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SystemResource> treegrid(String id) {
        List<SystemResource> treegrid = new ArrayList<SystemResource>();
        
        if("".equals(id) || id==null)
			id = "969696";
        
        List<SystemResource> resources = resourcesMapper.find(id);
        for (SystemResource res : resources) {
            List<SystemResource> subResources = resourcesMapper.find(res.getId());
            if(subResources != null){
                res.setParentId(res.getParentId());
                res.setParentText(res.getParentText());
            }
            
            if(subResources != null && subResources.size() > 0){
                res.setState("closed");
            }
            
            treegrid.add(res);
        }
        
        return treegrid;
    }

    @Override
    public int add(SystemResource resources) {
        return resourcesMapper.save(resources);
    }

    @Override
    public int del(String id) {
        return resourcesMapper.del(id);
    }

    @Override
    public int edit(SystemResource resources) {
        return resourcesMapper.update(resources);
    }

    @Override
    public SystemResource getResourcesByRequestPath(String requestPath) {
        return resourcesMapper.findBySrc(requestPath);
    }

    @Override
    public boolean checkRoleResources(int groupId, String requestPath) {
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("groupId", groupId);
        para.put("src", requestPath);
        SystemResource res = resourcesMapper.findRoleResources(para);
        return res==null? false:true;
    }


}
