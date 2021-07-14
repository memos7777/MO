package com.mialab.healthbutler.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mialab.healthbutler.manager.domain.system.SystemResource;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;
import com.mialab.healthbutler.manager.httpmodel.Json;
import com.mialab.healthbutler.manager.service.system.SystemResourceService;
import com.mialab.healthbutler.manager.util.RequestUtil;

/**
 * 资源控制器
 * 
 */
@Controller
@RequestMapping("/system/resource")
public class SystemResourceController{

    private static final Logger logger = Logger.getLogger(SystemResourceController.class);

    @Autowired
    private SystemResourceService resourcesService;

    @RequestMapping(params = "resource")
    public String resources() {
        return "/admin/resource";
    }

    @RequestMapping(params = "tree")
    @ResponseBody
    public List<EasyuiTreeNode> tree(String id, HttpSession session) {
        return resourcesService.tree(id, RequestUtil.getLoginAccountGroupId(session));
    }

    @RequestMapping(params = "treegrid")
    @ResponseBody
    public List<SystemResource> treegrid(String id) {
        return resourcesService.treegrid(id);
    }

    @RequestMapping(params = "add")
    @ResponseBody
    public Json add(SystemResource resources, HttpSession session) {
        RequestUtil.saveAccessLog(session, "resource.add " + resources.toString());
        Json j = new Json();
        resourcesService.add(resources);
        j.setSuccess(true);
        j.setMsg("添加成功!");
        return j;
    }

    @RequestMapping(params = "del")
    @ResponseBody
    public Json del(String id, HttpSession session) {
        RequestUtil.saveAccessLog(session, "resource.del " + id);
        Json j = new Json();
        resourcesService.del(id);
        j.setSuccess(true);
        j.setMsg("删除成功!");
        logger.info("删除成功!");
        return j;
    }

    @RequestMapping(params = "edit")
    @ResponseBody
    public Json edit(SystemResource resources, HttpSession session) {
        RequestUtil.saveAccessLog(session, "resource.edit " + resources.toString());
        resources.setPid(resources.getParentId());
        Json j = new Json();
        resourcesService.edit(resources);
        j.setSuccess(true);
        j.setMsg("编辑成功!");
        return j;
    }

}
