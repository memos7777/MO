package com.mialab.healthbutler.manager.service.system;

import com.mialab.healthbutler.manager.domain.system.SystemUser;
import com.mialab.healthbutler.manager.httpmodel.DataGridModel;
import com.mialab.healthbutler.manager.httpmodel.EasyuiDataGridJson;

public interface SystemUserService {
    
    EasyuiDataGridJson datagrid(DataGridModel dg, SystemUser user);

    int add(SystemUser user);

    int edit(SystemUser user);

    int remove(String ids);

    SystemUser getAccountByName(String accountName);

    int updatePassword(SystemUser user);

    int resetpwd(String ids);

}
