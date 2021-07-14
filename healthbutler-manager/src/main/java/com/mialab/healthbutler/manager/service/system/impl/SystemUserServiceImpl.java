package com.mialab.healthbutler.manager.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mialab.common.util.FunctionUtil;
import com.mialab.common.util.TextUtils;
import com.mialab.healthbutler.manager.domain.system.SystemUser;
import com.mialab.healthbutler.manager.httpmodel.DataGridModel;
import com.mialab.healthbutler.manager.httpmodel.EasyuiDataGridJson;
import com.mialab.healthbutler.manager.persistence.system.SystemUserMapper;
import com.mialab.healthbutler.manager.service.system.SystemUserService;

@Service("accountService")
public class SystemUserServiceImpl implements SystemUserService {
	
	@Autowired
    private SystemUserMapper userMapper;

    @Override
    public EasyuiDataGridJson datagrid(DataGridModel dg, SystemUser record) {
        EasyuiDataGridJson grid = new EasyuiDataGridJson();

        Map<String, Object> para = new HashMap<String, Object>();
        para.put("dg", dg);
        para.put("record", record);
        List<SystemUser> list = userMapper.find(para);
        String count = userMapper.findCount(para);
        grid.setTotal(TextUtils.parseLong(count));
        grid.setRows(list);
        return grid;
    }

    @Override
    @Transactional
    public int add(SystemUser bean) {
        userMapper.save(bean);
        if (bean.getGroupId() == null) {
            bean.setGroupId(0);
        }
        System.out.println(bean);
        return userMapper.saveAccountGroup(bean);
    }

    @Override
    @Transactional
    public int edit(SystemUser bean) {
        if (bean.getGroupId() == null) {
            bean.setGroupId(0);
        }
        userMapper.updateAccountGroup(bean);
        return userMapper.update(bean);
    }

    @Override
    @Transactional
    public int resetpwd(String accountId) {
        SystemUser user = new SystemUser();
        user.setAccountId(Integer.valueOf(accountId));
        String pwd = FunctionUtil.md5hashString("111111", 3, false);
        String accountPwd = pwd.substring(5, 13);
        String pwdb = pwd.substring(16, 24);
        user.setAccountPwd(accountPwd);
        user.setPwdb(pwdb);
        return userMapper.update(user);
    }

    @Override
    @Transactional
    public int remove(String ids) {
        for (String id : ids.split(",")) {
            userMapper.remove(Integer.valueOf(id));
            userMapper.removeAccountGroup(Integer.valueOf(id));
        }

        return 1;
    }

    @Override
    public SystemUser getAccountByName(String accountName) {
        return userMapper.findByName(accountName);
    }

    @Override
    public int updatePassword(SystemUser bean) {
        return userMapper.update(bean);
    }
}
