package com.mialab.healthbutler.manager.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class CommonsMultipartResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver{

    static Logger logger = Logger.getLogger(CommonsMultipartResolver.class.getName());
    
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        logger.info("request.getRequestURI()==" + request.getRequestURI());
        if (request.getRequestURI().indexOf("fileUploadConfigController") != -1) {
            return false;
        } else{
            return super.isMultipart(request);
        }
    }

}
