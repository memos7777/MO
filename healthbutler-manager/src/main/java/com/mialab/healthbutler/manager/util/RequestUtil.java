package com.mialab.healthbutler.manager.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mialab.healthbutler.manager.domain.system.SystemUser;

public class RequestUtil {
static Logger logger = Logger.getLogger(RequestUtil.class.getName());
    
    /**
     * 获得请求路径
     * 
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI() + "?" + request.getQueryString();
        if (requestPath.indexOf("&") > -1) {// 去掉其他参数
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }
        requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
        return requestPath;
    }

    public static Integer getLoginAccountGroupId(HttpSession session){
        SystemUser account = (SystemUser) session.getAttribute(Constants.USER_INFO_SESSION);
        if(account != null){
            return account.getGroupId();
        }
        return -1;
    }
    
    public static Integer getLoginAccountCityId(HttpSession session){
        SystemUser account = (SystemUser) session.getAttribute(Constants.USER_INFO_SESSION);
        //if(account != null && account.getCityId() != null){
            //return account.getCityId();
        //}
        return 0;
    }
    
    public static String getLoginAccountUserName(HttpSession session){
        SystemUser account = (SystemUser) session.getAttribute(Constants.USER_INFO_SESSION);
        if(account != null && account.getAccountName() != null){
            return account.getAccountName();
        }
        return "";
    }
    
    public static void saveAccessLog(HttpServletRequest request, String url){
        SystemUser account = (SystemUser) request.getSession().getAttribute(Constants.USER_INFO_SESSION);
        if(account != null && account.getAccountName() != null){
            logger.info(account.getAccountName() + " access data " + url);
        }
        logger.error("account is null.");
    }
    
    public static void saveAccessLog(HttpSession session, String url){
        SystemUser account = (SystemUser) session.getAttribute(Constants.USER_INFO_SESSION);
        if(account != null && account.getAccountName() != null){
            logger.info(account.getAccountName() + " access data " + url);
            return;
        }
        logger.error("account is null.");
    }
}
