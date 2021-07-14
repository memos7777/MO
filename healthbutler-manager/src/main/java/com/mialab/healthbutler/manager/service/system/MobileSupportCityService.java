package com.mialab.healthbutler.manager.service.system;

import java.util.List;

import com.mialab.healthbutler.manager.domain.system.MobileSupportCity;

public interface MobileSupportCityService {

    List<MobileSupportCity> citys();

    List<MobileSupportCity> citys(int userCityId);
    
    List<MobileSupportCity> listAppCitys(int userCityId);
}
