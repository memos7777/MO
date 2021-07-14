package com.mialab.healthbutler.manager.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mialab.common.util.FunctionUtil;
import com.mialab.common.util.TextUtils;
import com.mialab.healthbutler.manager.domain.system.SystemUser;
import com.mialab.healthbutler.manager.service.system.SystemResourceService;
import com.mialab.healthbutler.manager.service.system.SystemUserService;
import com.mialab.healthbutler.manager.util.Constants;
import com.mialab.healthbutler.manager.util.RequestUtil;

public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	static Logger logger = Logger.getLogger(AuthorizeInterceptor.class.getName());

	@Autowired
	private SystemUserService userService;

	@Autowired
	private SystemResourceService resourcesService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String url = request.getRequestURI();
		logger.info("preHandle =" + url);

		// 与老后台的集成
		checkFromOldSystemManager(request);

		// 与外部的接口
		checkFromExternalSystem(request);

		// url without intercept
		if (url.endsWith("welcome")) {
			logger.debug("from welcome =" + url);
			return true;
		}
		if (url.endsWith("regist")) {
			logger.debug("from regist =" + url);
			return true;
		}

		// check whether user was login.
		SystemUser account = (SystemUser) request.getSession().getAttribute(Constants.USER_INFO_SESSION);
		// 没有登录系统，或登录超时
		if (account == null) {
			logger.info("您没有登录或登录超时，请重新登录！");
			forward("您没有登录或登录超时，请重新登录！", request, response);
			return false;
		}
		logger.info("account login:" + account.toString());
		logger.info("用户已登录：" + url);

		int groupId = account.getGroupId();
		logger.info("用户角色id：" + groupId);

		String requestPath = RequestUtil.getRequestPath(request);
		logger.info("user " + account.getAccountName() + " access resource " + requestPath);

		// 只有超级管理员可以重置密码
		if (url.contains("resetpwd") && groupId != 1) {
			logger.info("try to reset password" + url);
			return false;
		}

		// 超级管理员/管理员角色不需要验证权限
		return true;

	}

	private void checkFromOldSystemManager(HttpServletRequest request) {
		String user = request.getParameter("account");
		String date = request.getParameter("date");
		String clientToken = request.getParameter("token");

		if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(clientToken)) {
			logger.info("Check From Old System Manager: user=" + user + ", date=" + date + ", clientToken=" + clientToken);
			String serverToken = FunctionUtil.EncoderByMd5(user + date + "INTERNAL-RAIYI-88900976-001");
			logger.debug("serverToken=" + serverToken);

			if (serverToken.equals(clientToken)) {
				logger.info("client token is valid:" + clientToken);
				SystemUser systemUser = userService.getAccountByName(user);
				request.getSession().setAttribute(Constants.USER_INFO_SESSION, systemUser);
			}
		}
	}

	/**
	 * TODO NOT Security.
	 * 
	 * @param request
	 */
	private void checkFromExternalSystem(HttpServletRequest request) {
		String user = request.getParameter("act");
		String password = request.getParameter("pwd");

		if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
			logger.info("Check From External System: user=" + user);
			SystemUser systemUser = userService.getAccountByName(user);
			if (password.equals(systemUser.getAccountPwd())) {
				request.getSession().setAttribute(Constants.USER_INFO_SESSION, systemUser);
			}
		}
	}

	private void forward(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward(msg, request, response, 0);
	}

	private void forward(String msg, HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		logger.info(msg);

		// request from ajax method
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			response.setHeader("sessionstatus", "timeout");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(msg);

			out.flush();
			out.close();
			return;
		}

		if (type == 550) { // no permission
			response.sendRedirect(request.getContextPath() + "/error/nopermission.jsp");
			return;
		}

		// request.getRequestDispatcher("/login.jsp").forward(request,
		// response);
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}
