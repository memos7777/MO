package com.mialab.healthbutler.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mialab.healthbutler.manager.domain.system.SystemMenu;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;
import com.mialab.healthbutler.manager.httpmodel.Json;
import com.mialab.healthbutler.manager.service.system.SystemMenuService;
import com.mialab.healthbutler.manager.util.RequestUtil;

/**
 * 菜单
 */
@Controller
@RequestMapping("/system/menu")
public class SystemMenuController {
	private static final Logger logger = Logger.getLogger(SystemMenuController.class);

	@Autowired
	private SystemMenuService menuService;

	@RequestMapping(params = "menu")
	public String menu() {
		return "/admin/menu";
	}

	@RequestMapping(params = "tree")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String id, HttpSession session) {
		Integer groupId = RequestUtil.getLoginAccountGroupId(session);
		int groupId_2 = groupId.intValue();
		logger.debug("groupId_2: " + groupId_2);
		logger.debug(groupId_2 == 1);
		
		if (groupId_2 == 1)
			return menuService.tree(id, RequestUtil.getLoginAccountGroupId(session));
		else
			return menuService.tree();
	}

	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<SystemMenu> treegrid(String id) {
		return menuService.treegrid(id);
	}

	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(SystemMenu menu, HttpSession session) {
		RequestUtil.saveAccessLog(session, "menu.add " + menu.toString());
		Json j = new Json();
		menuService.add(menu);
		j.setSuccess(true);
		j.setMsg("添加成功!");
		logger.info("添加成功!");
		return j;
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(SystemMenu menu, HttpSession session) {
		RequestUtil.saveAccessLog(session, "menu.del " + menu.toString());
		Json j = new Json();
		menuService.del(menu);
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(SystemMenu menu, HttpSession session) {
		RequestUtil.saveAccessLog(session, "menu.edit " + menu.toString());
		menu.setPid(menu.getParentId());
		Json j = new Json();
		menuService.edit(menu);
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}


}
