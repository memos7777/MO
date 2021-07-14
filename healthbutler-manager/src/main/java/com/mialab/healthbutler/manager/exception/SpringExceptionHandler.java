package com.mialab.healthbutler.manager.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class SpringExceptionHandler implements HandlerExceptionResolver{
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        System.out.println("SpringExceptionHandler exception class is ——————————————————> "+ ex.getClass());
        if (ex instanceof org.springframework.dao.DuplicateKeyException) {
            //org.springframework.dao.DuplicateKeyException exception = (org.springframework.dao.DuplicateKeyException)ex;
            return new ModelAndView("redirect:/error/dup");
        }
        return null;
    }
    
}
