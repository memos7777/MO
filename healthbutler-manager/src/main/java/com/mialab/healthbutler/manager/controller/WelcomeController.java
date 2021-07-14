package com.mialab.healthbutler.manager.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mialab.common.util.FunctionUtil;
import com.mialab.healthbutler.manager.domain.system.SystemUser;
import com.mialab.healthbutler.manager.service.system.SystemUserService;
import com.mialab.healthbutler.manager.util.Constants;
import com.mialab.healthbutler.manager.util.RequestUtil;

/*
 * 不需要实现任何接口，也不需要继承任何的类，也不需要任何 Servlet API
 */
@Controller
@RequestMapping("/welcome")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class WelcomeController {
	static Logger logger = Logger.getLogger(WelcomeController.class.getName());

	@Autowired
	private SystemUserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String login(SystemUser user, Model model, HttpSession session) throws Exception {
		String sessionKey = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String validCode = user.getValidCode();
		logger.info("user logining..." + user.toString());
		if (sessionKey == null || validCode == null) {
			logger.error("sessionKey=" + sessionKey + ", validCode=" + validCode);
			return "relogin";
		}

		if (!sessionKey.equals(validCode)) {
			logger.error("验证码不正确. sessionKey=" + sessionKey + ", validCode=" + validCode);
			model.addAttribute("message", "验证码不正确");
			return "relogin";
		}

		SystemUser user1 = userService.getAccountByName(user.getAccountName());
		if (user1 == null) {
			logger.error("用户不存在. sessionKey=" + sessionKey + ", validCode=" + validCode);
			model.addAttribute("message", "用户不存在");
			return "relogin";
		}

		String loginPassword = user.getAccountPwd();
		String pwd = FunctionUtil.md5hashString(loginPassword, 3, false);
		String accountPwd = pwd.substring(5, 13);
		String pwdb = pwd.substring(16, 24);

		if (user == null || !user1.getAccountPwd().equals(accountPwd) || !user1.getPwdb().equals(pwdb)) {
			logger.error("密码错误. sessionKey=" + sessionKey + ", validCode=" + validCode);
			model.addAttribute("message", "密码错误");
			return "relogin";
		}

		logger.info("user logined:" + user1.toString());
		model.addAttribute(Constants.USER_INFO_SESSION, user1); // 名为Constants.USER_INFO_SESSION的属性放到Session属性列表中

		return "welcome";
	}

	@RequestMapping(params = "logout")
	public String logout(HttpSession session) {
		RequestUtil.saveAccessLog(session, "logout");
		if (session != null) {
			session.invalidate();
		}
		return "login";
	}

	@RequestMapping(params = "changepassword")
	public String changepassword(SystemUser user, Model model, HttpSession session) {
		RequestUtil.saveAccessLog(session, "changepassword");
		SystemUser user1 = userService.getAccountByName(user.getAccountName());
		if (user1 == null) {
			model.addAttribute("message", "用户不存在");
			return "passwordchange";
		}

		logger.info("user1 login:" + user1.toString());
		String loginPassword = user.getAccountPwd();
		String pwd = FunctionUtil.md5hashString(loginPassword, 3, false);
		String accountPwd = pwd.substring(5, 13);
		String pwdb = pwd.substring(16, 24);

		if (user == null || !user1.getAccountPwd().equals(accountPwd) || !user1.getPwdb().equals(pwdb)) {
			model.addAttribute("message", "密码错误");
			return "passwordchange";
		}

		logger.info("user login:" + user.toString());
		String newPassword = user.getNewAccountPwd();
		String newHashPassword = FunctionUtil.md5hashString(newPassword, 3, false);
		user1.setAccountPwd(newHashPassword.substring(5, 13));
		user1.setPwdb(newHashPassword.substring(16, 24));
		int restu = userService.updatePassword(user1);
		if (restu == 1) {
			model.addAttribute("message", "密码修改成功");
			return "login";
		}

		model.addAttribute("message", "密码修改失败");
		return "passwordchange";
	}

}
