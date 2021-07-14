package com.mialab.healthbutler.manager.persistence.system;

import java.util.List;
import java.util.Map;

import com.mialab.healthbutler.manager.domain.system.ModuleInfo;

public interface ModuleInfoMapper {
    
    int save(ModuleInfo record);

    int update(ModuleInfo record);

    int remove(Integer id);

    List<ModuleInfo> find(Map<String, Object> para);
    
    String findCount(Map<String, Object> para);
}