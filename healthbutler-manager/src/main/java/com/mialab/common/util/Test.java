package com.mialab.common.util;


public class Test {

	public static void main(String[] args) {
		String loginPassword = "123456";
		String pwd = FunctionUtil.md5hashString(loginPassword, 3, false);
		String accountPwd = pwd.substring(5, 13);
		String pwdb = pwd.substring(16, 24);

		System.out.println(accountPwd);
		System.out.println(pwdb);

	}

}
