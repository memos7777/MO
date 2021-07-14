package com.mialab.healthbutler.manager.persistence.system;

import java.util.List;

import com.mialab.healthbutler.manager.domain.system.MobileSupportCity;

public interface MobileSupportCityMapper {

    List<MobileSupportCity> listCity();

    List<MobileSupportCity> listCityById(int userCityId);
    
    List<MobileSupportCity> listAppCitys(int userCityId);

}
