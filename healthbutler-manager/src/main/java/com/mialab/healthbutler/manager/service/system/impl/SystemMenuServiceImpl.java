package com.mialab.healthbutler.manager.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mialab.healthbutler.manager.domain.system.SystemMenu;
import com.mialab.healthbutler.manager.httpmodel.EasyuiTreeNode;
import com.mialab.healthbutler.manager.persistence.system.SystemMenuMapper;
import com.mialab.healthbutler.manager.service.system.SystemMenuService;

@Service("menuService")
public class SystemMenuServiceImpl implements SystemMenuService {

	private static final Logger logger = Logger.getLogger(SystemMenuServiceImpl.class);

	@Autowired
	private SystemMenuMapper menuMapper;

	@Transactional(readOnly = true)
	public List<EasyuiTreeNode> tree(String id, Integer groupId) {
		logger.debug("start method tree...\tparameter id=" + id + ", groupId=" + groupId);

		if ("".equals(id) || id == null)
			id = "999999";

		List<SystemMenu> menuList = null;
		if (groupId == 1) {
			menuList = menuMapper.getSubTree(id);
		} else {
			Map<String, String> para = new HashMap<String, String>();
			para.put("groupId", String.valueOf(groupId));
			para.put("pid", id);
			menuList = menuMapper.findRoleMenu(para);
		}

		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
		for (SystemMenu menu : menuList) {
			tree.add(tree(menu, false, groupId));
		}
		return tree;
	}

	public EasyuiTreeNode tree(SystemMenu menu, boolean recursive, Integer groupId) {
		EasyuiTreeNode node = new EasyuiTreeNode();
		node.setId(menu.getId());
		node.setText(menu.getText());
		node.setIconCls(menu.getIconcls());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("src", menu.getSrc());
		node.setAttributes(attributes);

		List<SystemMenu> subMenuList = null;
		if (groupId == 1) {
			subMenuList = menuMapper.getSubTree(menu.getId());
		} else {
			Map<String, String> para = new HashMap<String, String>();
			para.put("groupId", String.valueOf(groupId));
			para.put("pid", menu.getId());
			subMenuList = menuMapper.findRoleMenu(para);
		}

		if (subMenuList != null && subMenuList.size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				// List<SystemMenu> symenuList = new
				// ArrayList<SystemMenu>(menu.getSymenus());
				// Collections.sort(symenuList, new MenuComparator());// 排序
				// List<EasyuiTreeNode> children = new
				// ArrayList<EasyuiTreeNode>();
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
	public List<SystemMenu> treegrid(String id) {
		List<SystemMenu> treegrid = new ArrayList<SystemMenu>();

		if ("".equals(id) || id == null)
			id = "999999";

		List<SystemMenu> menus = menuMapper.find(id);
		for (SystemMenu menu : menus) {
			List<SystemMenu> subMenus = menuMapper.find(menu.getId());
			if (subMenus != null) {
				menu.setParentId(menu.getParentId());
				menu.setParentText(menu.getParentText());
			}

			if (subMenus != null && subMenus.size() > 0) {
				menu.setState("closed");
			}

			treegrid.add(menu);
		}

		return treegrid;
	}

	public int add(SystemMenu menu) {
		return menuMapper.save(menu);
	}

	public int del(SystemMenu menu) {
		return menuMapper.del(menu.getId());
	}

	public int edit(SystemMenu menu) {
		return menuMapper.update(menu);
	}

	// ==================================================
	@Transactional(readOnly = true)
	public List<EasyuiTreeNode> tree() {

		EasyuiTreeNode e0 = new EasyuiTreeNode("0", "模块");

		EasyuiTreeNode e01 = new EasyuiTreeNode("01", "模块管理");
		e01.addAttributes("src", "/healthservice/moduleinfoController?moduleinfo");

		EasyuiTreeNode e02 = new EasyuiTreeNode("02", "模块配置激活");
		// e02.addAttributes("src","/healthservice/moduleController?modulefiles");
		
		List<EasyuiTreeNode> e0Tree = new ArrayList<EasyuiTreeNode>();
		e0Tree.add(e01);
		e0Tree.add(e02);

		e0.setChildren(e0Tree);

		// =======================

		EasyuiTreeNode e1 = new EasyuiTreeNode("1", "运动计步");

		EasyuiTreeNode e11 = new EasyuiTreeNode("11", "运动计步数据信息");
		// e11.addAttributes("src","/healthservice/sportsController?paceinfo");		

		EasyuiTreeNode e12 = new EasyuiTreeNode("12", "运动计步广告管理");
		
		List<EasyuiTreeNode> e1Tree = new ArrayList<EasyuiTreeNode>();
		e1Tree.add(e11);
		e1Tree.add(e12);

		e1.setChildren(e1Tree);

		// =======================

		EasyuiTreeNode e2 = new EasyuiTreeNode("2", "睡眠管理");
		EasyuiTreeNode e21 = new EasyuiTreeNode("21", "助眠音乐分类");
		EasyuiTreeNode e22 = new EasyuiTreeNode("22", "助眠音乐信息");
		EasyuiTreeNode e23 = new EasyuiTreeNode("23", "用户睡眠数据");

		List<EasyuiTreeNode> e2Tree = new ArrayList<EasyuiTreeNode>();
		e2Tree.add(e21);
		e2Tree.add(e22);
		e2Tree.add(e23);

		e2.setChildren(e2Tree);

		// =======================

		EasyuiTreeNode e3 = new EasyuiTreeNode("3", "健康档案");

		EasyuiTreeNode e31 = new EasyuiTreeNode("31", "个人档案");

		EasyuiTreeNode e32 = new EasyuiTreeNode("32", "健康测评");
		
		List<EasyuiTreeNode> e3Tree = new ArrayList<EasyuiTreeNode>();
		e3Tree.add(e31);
		e3Tree.add(e32);

		e3.setChildren(e3Tree);

		// =======================

		EasyuiTreeNode e4 = new EasyuiTreeNode("4", "寻医生");

		EasyuiTreeNode e41 = new EasyuiTreeNode("41", "医生分类信息");
		// e41.addAttributes("src", "/doctor/doctorCategoryController?catinfo");
		EasyuiTreeNode e42 = new EasyuiTreeNode("42", "医生个人信息");
		// e42.addAttributes("src", "/doctor/doctorController?dinfo");

		List<EasyuiTreeNode> e4Tree = new ArrayList<EasyuiTreeNode>();
		e4Tree.add(e41);
		e4Tree.add(e42);

		e4.setChildren(e4Tree);

		// =======================
		
		EasyuiTreeNode e5 = new EasyuiTreeNode("5", "找医院");
		EasyuiTreeNode e51 = new EasyuiTreeNode("51", "医院类别管理");		
		EasyuiTreeNode e52 = new EasyuiTreeNode("52", "科室信息管理");
		EasyuiTreeNode e53 = new EasyuiTreeNode("53", "就诊预约信息管理");
		
		List<EasyuiTreeNode> e5Tree = new ArrayList<EasyuiTreeNode>();
		e5Tree.add(e51);
		e5Tree.add(e52);
		e5Tree.add(e53);
		e5.setChildren(e5Tree);
		// ========================================
		EasyuiTreeNode e6 = new EasyuiTreeNode("6", "查疾病");
		EasyuiTreeNode e61 = new EasyuiTreeNode("61", "疾病类别管理");		
		EasyuiTreeNode e62 = new EasyuiTreeNode("62", "疾病信息管理");		

		List<EasyuiTreeNode> e6Tree = new ArrayList<EasyuiTreeNode>();
		e6Tree.add(e61);
		e6Tree.add(e62);
		e6.setChildren(e6Tree);

		// =======================
		EasyuiTreeNode e7 = new EasyuiTreeNode("7", "个人中心");

		EasyuiTreeNode e71 = new EasyuiTreeNode("71", "个人信息管理");
		EasyuiTreeNode e72 = new EasyuiTreeNode("72", "推送设置管理");
		List<EasyuiTreeNode> e7Tree = new ArrayList<EasyuiTreeNode>();

		e7Tree.add(e71);
		e7Tree.add(e72);
		e7.setChildren(e7Tree);
		// ========================================
		EasyuiTreeNode e8 = new EasyuiTreeNode("8", "饮食小贴士");
		EasyuiTreeNode e81 = new EasyuiTreeNode("81", "食物类别管理");
		EasyuiTreeNode e82 = new EasyuiTreeNode("82", "食物信息管理");
		List<EasyuiTreeNode> e8Tree = new ArrayList<EasyuiTreeNode>();

		e8Tree.add(e81);
		e8Tree.add(e82);
		e8.setChildren(e8Tree);
		// ==============================================
		EasyuiTreeNode e9 = new EasyuiTreeNode("9", "社区咨询");
		EasyuiTreeNode e91 = new EasyuiTreeNode("91", "活动类别管理");
		EasyuiTreeNode e92 = new EasyuiTreeNode("92", "活动信息管理");
		List<EasyuiTreeNode> e9Tree = new ArrayList<EasyuiTreeNode>();

		e9Tree.add(e91);
		e9Tree.add(e92);
		e9.setChildren(e9Tree);
		// ==============================================		

		List<EasyuiTreeNode> root = new ArrayList<EasyuiTreeNode>();
		root.add(e0);
		root.add(e1);
		root.add(e2);
		root.add(e3);
		root.add(e4);
		root.add(e5);
		root.add(e6);
		root.add(e7);
		root.add(e8);
		root.add(e9);

		return root;
	}

}
