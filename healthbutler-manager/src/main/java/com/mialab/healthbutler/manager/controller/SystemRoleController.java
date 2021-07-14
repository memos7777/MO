package com.mialab.healthbutler.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mialab.common.util.TextUtils;
import com.mialab.healthbutler.manager.domain.system.SystemRole;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;
import com.mialab.healthbutler.manager.httpmodel.Json;
import com.mialab.healthbutler.manager.service.system.SystemRoleService;
import com.mialab.healthbutler.manager.util.RequestUtil;


@Controller
@RequestMapping("/system/role")
public class SystemRoleController{

    @Autowired
    private SystemRoleService roleService;

    @RequestMapping(params = "role")
    public String user() {
        return "/admin/role";
    }

    @RequestMapping(params = "tree")
    @ResponseBody
    public List<EasyuiTreeNode> tree(String id) {
        return roleService.tree(id);
    }
    
    @RequestMapping(params = "treegrid")
    @ResponseBody
    public List<SystemRole> treegrid(String id) {
        return roleService.treegrid(id);
    }
    
    @RequestMapping(params = "add")
    @ResponseBody
    public Json add(SystemRole bean, HttpSession session) {
        RequestUtil.saveAccessLog(session, "role.add " + bean.toString());
        if(TextUtils.isNotEmpty(bean.getParentId())){
            bean.setPid(bean.getParentId());
        }
        
        Json j = new Json();
        roleService.add(bean);
        j.setSuccess(true);
        j.setMsg("添加成功!");
        return j;
        
        
        // add role_menu
        
        // add role_resource
        
    }

    @RequestMapping(params = "edit")
    @ResponseBody
    public Json edit(SystemRole bean, HttpSession session) {
        RequestUtil.saveAccessLog(session, "role.edit " + bean.toString());
        if(!TextUtils.isEmpty(bean.getParentId())){
            bean.setPid(bean.getParentId());
        }
        Json j = new Json();
        roleService.edit(bean);
        j.setSuccess(true);
        j.setMsg("编辑成功!");
        return j;
    }
    
    @RequestMapping(params = "del")
    @ResponseBody
    public Json del(SystemRole role, HttpSession session) {
        RequestUtil.saveAccessLog(session, "role.del " + role.toString());
        Json j = new Json();
        roleService.delete(role);
        j.setSuccess(true);
        j.setMsg("删除成功!");
        return j;
    }
    
    @RequestMapping(params = "roles")
    @ResponseBody
    public List<SystemRole> roles() {
        return roleService.listRoles();
    }
}
