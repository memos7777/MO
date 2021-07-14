package com.mialab.healthbutler.manager.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mialab.common.util.TextUtils;
import com.mialab.healthbutler.manager.domain.system.SystemMenu;
import com.mialab.healthbutler.manager.domain.system.SystemResource;
import com.mialab.healthbutler.manager.domain.system.SystemRole;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;
import com.mialab.healthbutler.manager.persistence.system.SystemMenuMapper;
import com.mialab.healthbutler.manager.persistence.system.SystemResourceMapper;
import com.mialab.healthbutler.manager.persistence.system.SystemRoleMapper;
import com.mialab.healthbutler.manager.service.system.SystemRoleService;

@Service("groupService")
public class SystemRoleServiceImpl implements SystemRoleService {
    private static final Logger logger = Logger.getLogger(SystemRoleServiceImpl.class);

    @Autowired
    private SystemRoleMapper roleMapper;

    @Autowired
    private SystemResourceMapper resourcesMapper;
    
    @Autowired
    private SystemMenuMapper menuMapper;

    @Transactional(readOnly = true)
    public List<EasyuiTreeNode> tree(String id) {
        logger.debug("start method tree...\tparameter id=" + id);

        List<SystemRole> roleList = null;
        roleList = roleMapper.getSubTree(id);

        List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
        for (SystemRole role : roleList) {
            tree.add(tree(role, false));
        }
        return tree;
    }

    public EasyuiTreeNode tree(SystemRole role, boolean recursive) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        node.setId(role.getId());
        node.setText(role.getText());
        Map<String, Object> attributes = new HashMap<String, Object>();
        node.setAttributes(attributes);

        List<SystemRole> subRoleList = null;
        subRoleList = roleMapper.getSubTree(role.getId());

        if (subRoleList != null && subRoleList.size() > 0) {
            node.setState("closed");
            if (recursive) {// 递归查询子节点
            // List<SystemMenu> symenuList = new ArrayList<SystemMenu>(menu.getSymenus());
            // Collections.sort(symenuList, new MenuComparator());// 排序
            // List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
            // for (SystemMenu m : symenuList) {
            // EasyuiTreeNode t = tree(m, true);
            // children.add(t);
            // }
            // node.setChildren(children);
            }
        }
        return node;
    }
    
    @Transactional(readOnly = true)
    public List<SystemRole> treegrid(String id) {
        List<SystemRole> treegrid = new ArrayList<SystemRole>();
        
        if("".equals(id) || id==null)
			id = "989898";

        List<SystemRole> roles = roleMapper.find(id);

        for (SystemRole role : roles) {
            List<SystemRole> subRoles = roleMapper.find(role.getId());
            if (subRoles != null) {
                role.setParentId(role.getParentId());
                role.setParentText(role.getParentText());
            }

            if (subRoles != null && subRoles.size() > 0) {
                role.setState("closed");
            }

            List<SystemResource> resources = resourcesMapper.findResourcesByRole(role.getId());
            if (resources != null && resources.size() > 0) {
                String resourcesTextList = "";
                String resourcesIdList = "";
                boolean b = false;
                for (SystemResource res : resources) {
                    if (!b) {
                        b = true;
                    } else {
                        resourcesTextList += ",";
                        resourcesIdList += ",";
                    }
                    resourcesTextList += res.getText();
                    resourcesIdList += res.getId();
                }
                role.setResourcesId(resourcesIdList);
                role.setResourcesText(resourcesTextList);
            }
            
            List<SystemMenu> menus = menuMapper.findMenusByRole(role.getId());
            if (menus != null && menus.size() > 0) {
                String menuTextList = "";
                String menuIdList = "";
                boolean b = false;
                for (SystemMenu menu : menus) {
                    if (!b) {
                        b = true;
                    } else {
                        menuTextList += ",";
                        menuIdList += ",";
                    }
                    menuTextList += menu.getText();
                    menuIdList += menu.getId();
                }
                role.setMenusId(menuIdList);
                role.setMenusText(menuTextList);
            }
            
            treegrid.add(role);
        }
        return treegrid;
    }

    @Override
    public int add(SystemRole bean) {
        return roleMapper.save(bean);
    }

    @Override
    @Transactional
    public int edit(SystemRole role) {
        String roleId = role.getId();
        String resourceId = role.getResourcesId();
        String menuId = role.getMenusId();
        
        roleMapper.deleteRoleResources(roleId);
        roleMapper.deleteRoleMenus(roleId);
        
        if(!TextUtils.isEmpty(resourceId)){ // 角色和资源的关系
            String[] resourceIds = resourceId.split(",");
            for (String resId : resourceIds) {
                Map<String, String> roleResources = new HashMap<String, String>();
                roleResources.put("roleId", roleId);
                roleResources.put("resourceId", resId);
                roleMapper.saveRoleResources(roleResources);
            }
        }
        
        if(!TextUtils.isEmpty(menuId)){ // 角色和菜单的关系
            String[] menuIds = menuId.split(",");
            for (String menId : menuIds) {
                Map<String, String> roleMenus = new HashMap<String, String>();
                roleMenus.put("roleId", roleId);
                roleMenus.put("menuId", menId);
                roleMapper.saveRoleMenus(roleMenus);
            }
        }
        
        return roleMapper.update(role);
    }

    @Transactional
    public void delete(SystemRole role) {
        String roleId = role.getId();
        roleMapper.deleteRoleResources(roleId);
        roleMapper.deleteRoleMenus(roleId);
        roleMapper.del(roleId);
    }

    @Override
    public List<SystemRole> listRoles() {
        return roleMapper.listRoles();
    }

}
