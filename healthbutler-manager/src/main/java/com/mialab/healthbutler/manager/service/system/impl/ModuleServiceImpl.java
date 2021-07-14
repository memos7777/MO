package com.mialab.healthbutler.manager.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mialab.common.util.TextUtils;
import com.mialab.healthbutler.manager.domain.system.ModuleInfo;
import com.mialab.healthbutler.manager.httpmodel.DataGridModel;
import com.mialab.healthbutler.manager.httpmodel.EasyuiDataGridJson;
import com.mialab.healthbutler.manager.persistence.system.ModuleInfoMapper;
import com.mialab.healthbutler.manager.service.system.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	static Logger logger = Logger.getLogger(ModuleServiceImpl.class.getName());

	@Autowired
	private ModuleInfoMapper moduleInfoMapper;

	@Override
	public EasyuiDataGridJson datagrid(DataGridModel dg, ModuleInfo record) {
		EasyuiDataGridJson grid = new EasyuiDataGridJson();

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("dg", dg);
		para.put("record", record);
		List<ModuleInfo> list = moduleInfoMapper.find(para);
		String count = moduleInfoMapper.findCount(para);
		grid.setTotal(TextUtils.parseLong(count));
		grid.setRows(list);
		return grid;
	}

	@Override
	public int add(ModuleInfo bean) {
		return moduleInfoMapper.save(bean);
	}

	@Override
	public int edit(ModuleInfo bean) {
		return moduleInfoMapper.update(bean);
	}

	@Override
	public int remove(String ids) {
		for (String id : ids.split(",")) {
			moduleInfoMapper.remove(Integer.valueOf(id));
		}

		return 1;
	}
}
