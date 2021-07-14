package com.mialab.healthbutler.manager.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mialab.healthbutler.manager.domain.system.ModuleInfo;
import com.mialab.healthbutler.manager.httpmodel.DataGridModel;
import com.mialab.healthbutler.manager.httpmodel.EasyuiDataGridJson;
import com.mialab.healthbutler.manager.httpmodel.Json;
import com.mialab.healthbutler.manager.service.system.ModuleService;
import com.mialab.healthbutler.manager.util.RequestUtil;

@Controller
@RequestMapping("/healthservice/moduleinfoController")
public class ModuleInfoController {

    static Logger logger = Logger.getLogger(ModuleInfoController.class.getName());

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(params = "moduleinfo")
    public String render() {
        return "/module/moduleInfo";
    }

    @RequestMapping(params = "datagrid")
    @ResponseBody
    public EasyuiDataGridJson datagrid(DataGridModel dg, ModuleInfo bean, HttpSession session) {
        return moduleService.datagrid(dg, bean);
    }

    @RequestMapping(params = "add")
    @ResponseBody
    public int add(ModuleInfo bean, HttpSession session) {
        RequestUtil.saveAccessLog(session, "ModuleInfoController.add" + bean.toString());
        return moduleService.add(bean);
    }

    @RequestMapping(params = "edit")
    @ResponseBody
    public int edit(ModuleInfo bean, HttpSession session) {
        RequestUtil.saveAccessLog(session, "ModuleInfoController.edit" + bean.toString());
        return moduleService.edit(bean);
    }

    @RequestMapping(params = "del")
    @ResponseBody
    public Json del(String ids, HttpSession session) {
        RequestUtil.saveAccessLog(session, "ModuleInfoController.del" + ids);
        Json json = new Json();
        moduleService.remove(ids);
        json.setSuccess(true);
        return json;
    }
}
