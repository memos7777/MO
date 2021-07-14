package com.mialab.healthbutler.manager.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mialab.healthbutler.manager.domain.system.MobileSupportCity;
import com.mialab.healthbutler.manager.persistence.system.MobileSupportCityMapper;
import com.mialab.healthbutler.manager.service.system.MobileSupportCityService;

@Service("mobileSupportCityService")
public class MobileSupportCityImpl implements MobileSupportCityService {

	@Autowired
	private MobileSupportCityMapper mobileSupportCityMapper;

    @Override
    public List<MobileSupportCity> citys() {
        return mobileSupportCityMapper.listCity();
    }

    @Override
    public List<MobileSupportCity> citys(int userCityId) {
        return mobileSupportCityMapper.listCityById(userCityId);
    }
    
    @Override
    public List<MobileSupportCity> listAppCitys(int userCityId) {
        return mobileSupportCityMapper.listAppCitys(userCityId);
    }


}
