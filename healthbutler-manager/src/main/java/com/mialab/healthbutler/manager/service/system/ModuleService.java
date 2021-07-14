package com.mialab.healthbutler.manager.service.system;

import com.mialab.healthbutler.manager.domain.system.ModuleInfo;
import com.mialab.healthbutler.manager.httpmodel.DataGridModel;
import com.mialab.healthbutler.manager.httpmodel.EasyuiDataGridJson;

public interface ModuleService {

    EasyuiDataGridJson datagrid(DataGridModel dg, ModuleInfo bean);

    int add(ModuleInfo bean);

    int edit(ModuleInfo bean);

    int remove(String ids);
}
