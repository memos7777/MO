package com.mialab.healthbutler.manager.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mialab.common.util.FunctionUtil;
import com.mialab.healthbutler.manager.domain.system.SystemUser;
import com.mialab.healthbutler.manager.httpmodel.DataGridModel;
import com.mialab.healthbutler.manager.httpmodel.EasyuiDataGridJson;
import com.mialab.healthbutler.manager.httpmodel.Json;
import com.mialab.healthbutler.manager.service.system.SystemUserService;
import com.mialab.healthbutler.manager.util.RequestUtil;

@Controller
@RequestMapping("/system/user")
public class SystemUserController {
	@Autowired
    private SystemUserService userService;

    @RequestMapping(params = "user")
    public String user() {
        return "/admin/user";
    }

    @RequestMapping(params = "datagrid")
    @ResponseBody
    public EasyuiDataGridJson datagrid(DataGridModel dg, SystemUser bean) {
        return userService.datagrid(dg, bean);
    }

    @RequestMapping(params = "add")
    @ResponseBody
    public int add(SystemUser bean, HttpSession session) {
        RequestUtil.saveAccessLog(session, "user.add " + bean.toString());

        bean.setCreateTime(Calendar.getInstance().getTime());
        bean.setEntryNumber(0);

        String pwd = FunctionUtil.md5hashString(bean.getAccountPwd(), 3, false);
        String accountPwd = pwd.substring(5, 13);
        String pwdb = pwd.substring(16, 24);
        bean.setAccountPwd(accountPwd);
        bean.setPwdb(pwdb);
        return userService.add(bean);
    }

    @RequestMapping(params = "edit")
    @ResponseBody
    public int edit(SystemUser bean, HttpSession session) {
        RequestUtil.saveAccessLog(session, "user.edit " + bean.toString());
        // NOT allow to edit password
        bean.setAccountPwd(null);
        bean.setPwdb(null);
        return userService.edit(bean);
    }

    @RequestMapping(params = "resetpwd")
    @ResponseBody
    public Json resetpwd(String ids, HttpSession session) {
        RequestUtil.saveAccessLog(session, "user.resetpwd " + ids);
        Json json = new Json();
        int retu = userService.resetpwd(ids);
        json.setSuccess(retu == 1 ? true : false);
        return json;
    }

    @RequestMapping(params = "del")
    @ResponseBody
    public Json del(String ids, HttpSession session) {
        RequestUtil.saveAccessLog(session, "user.del " + ids);
        Json json = new Json();
        userService.remove(ids);
        json.setSuccess(true);
        return json;
    }

}
