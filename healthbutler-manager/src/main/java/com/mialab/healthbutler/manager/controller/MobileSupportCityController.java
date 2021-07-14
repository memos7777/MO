package com.mialab.healthbutler.manager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mialab.healthbutler.manager.domain.system.MobileSupportCity;
import com.mialab.healthbutler.manager.service.system.MobileSupportCityService;
import com.mialab.healthbutler.manager.util.RequestUtil;

@Controller
@RequestMapping("/cityservice/mobileSupportCityController")
public class MobileSupportCityController {
	 static Logger logger = Logger.getLogger(MobileSupportCityController.class.getName());
	    
	    @Autowired
	    private MobileSupportCityService mobileSupportCityService;

	    @RequestMapping(params = "citys")
	    @ResponseBody
	    public List<MobileSupportCity> citys(HttpSession session) {
	        int userCityId = RequestUtil.getLoginAccountCityId(session);
	        List<MobileSupportCity> citys = mobileSupportCityService.citys(userCityId);
	        return citys;
	    }
	    
	    @RequestMapping(params = "appcitys")
	    @ResponseBody
	    public List<MobileSupportCity> appcitys(HttpSession session) {
	        int userCityId = RequestUtil.getLoginAccountCityId(session);
	        List<MobileSupportCity> citys = mobileSupportCityService.listAppCitys(userCityId);
	        return citys;
	    }

	    @RequestMapping(params = "commoncitys")
	    @ResponseBody
	    public List<MobileSupportCity> commoncitys(HttpSession session) {
	        int userCityId = RequestUtil.getLoginAccountCityId(session);
	        List<MobileSupportCity> citys = mobileSupportCityService.citys(userCityId);

	        List<MobileSupportCity> commoncitys = new ArrayList<MobileSupportCity>(
	                Arrays.asList(new MobileSupportCity[citys.size()]));
	        Collections.copy(commoncitys, citys);
	        
	        if(userCityId==0){
	            MobileSupportCity city = new MobileSupportCity();
	            city.setCityId(0);
	            city.setName("全国");
	            commoncitys.add(city);
	        }
	        
	        return commoncitys;
	    }


}
