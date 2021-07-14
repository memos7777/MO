<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/include/meta.jsp"></jsp:include>
<jsp:include page="/include/easyui.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Robots" content="noarchive" />
<meta http-equiv="Cache-Control" content="public" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="description" content="健康管家 首页 健康管家是一款健康类安卓客户端应用，具体包含运动计步、睡眠管理、饮食贴士、询医生、找医院、查疾病、社区资讯、健康测评、自我激励、个人中心等多个模块，其他还加入了天气助手，任务规划，任务奖励的辅助功能，以及AR扫码、第三方社会化分享、实时路线绘制和历史运动查看等功能。" />
<meta name="keywords"
	content="健康管家 首页 健康管家下载,客户端下载,运动计步、睡眠管理、饮食贴士、询医生、找医院、查疾病、社区资讯、健康测评、自我激励、个人中心" />
<meta name="author" content="苏大移动互联网应用研发中心" />
<meta name="build" content="2016-2-18" />
<meta name="copyright" content="苏大移动互联网应用研发中心" />

<title>健康管家登录</title>
<link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
	
	function update() {
		var np = $("#newAccountPwd").val();
		var ap = $("#againPassWord").val();
		if (np == null || np == "" || ap == null || ap == "") {
			alert("密码不能为空");
			return false;
		}
		if ($("#newAccountPwd").val() != $("#againPassWord").val()) {
			alert("两次密码输入不一致，请重新输入");
			return false;
		}
	}
</script>
</head>
<body style="margin:0px auto; text-align:center;">
<br />
    <br />
    <br />
    <br />
    <br />

<div style="margin: 0 auto;">

<form id="userLoginForm" action="welcome?changepassword" method="post">
	<table width="620" border="0" align="center" cellpadding="0" cellspacing="0">
		<tbody>
            <tr>
                <td width="620">
                    <img height="11" src="<%=request.getContextPath()%>/resources/images/login_p_img02.gif" width="650" alt="健康管家" />
                </td>
            </tr>
            <tr>
                <td align="center" style="background-image: url('<%=request.getContextPath()%>/resources/images/login_p_img03.gif')">
                    <br />
                    <table width="570" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table cellspacing="0" cellpadding="0" width="570" border="0">
                                    <tbody>
                                        <tr>
                                            <td width="245" height="80" align="center" valign="top">
                                                <img height="67" src="<%=request.getContextPath()%>/resources/images/member_t04.jpg" width="245" alt="健康管家" />
                                            </td>
                                            <td align="right" valign="top">
                                                <br />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;<span id="lblGUID" style="display: none">d6255dfc-7340-4bc6-8501-dfb6392357bf</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="<%=request.getContextPath()%>/resources/images/a_te01.gif" width="570" height="3" alt="健康管家" />
                            </td>
                        </tr>
                        <tr>
                            <td align="center" style="background-image: url('<%=request.getContextPath()%>/resources/images/a_te02.gif')">
                                <table width="520" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="123" height="150">
                                            <img height="95" src="<%=request.getContextPath()%>/resources/images/pwdchange05.png" width="123" border="0" alt="健康管家" />
                                        </td>
                                        <td align="center">
                                            <table cellspacing="0" cellpadding="0" border="0">
                                                <tr>
                                                    <td width="210" height="25" valign="top"> 用户名：&nbsp;&nbsp;&nbsp;
                                                        <input name="accountName" type="text" id="accountName" class="nemo01" tabindex="1" maxlength="22" size="22"   />
                                                    </td>
                                                    <td width="80" rowspan="4" align="right" valign="middle">
                                                        <input type="image" name="btnLogin" id="btnLogin" onclick="return update();" src="<%=request.getContextPath()%>/resources/images/pwdchange.png" style="border-width:0px;" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="210" height="25" valign="top"> 原密码：&nbsp;&nbsp;&nbsp;
                                                        <input name="accountPwd" type="password" id="txtPass" class="nemo01" tabindex="1" size="22" maxlength="22"  />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="210" height="25" valign="top"> 新密码：&nbsp;&nbsp;&nbsp;
                                                        <input id="newAccountPwd" name="newAccountPwd" type="password" id="txtPass" class="nemo01" tabindex="1" size="22" maxlength="22"  />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="210" height="25" valign="top"> 确认密码：
                                                        <input id="againPassWord" name="againPassWord" type="password" id="txtPass" class="nemo01" tabindex="1" size="22" maxlength="22"  />
                                                    </td>
                                                </tr>
                                            </table>
                                            <br />
                                            <p style="color: red; font-size: 12px" >${message}</p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td height="70" align="center">
                                            Copyright(C) 2016-2017 HEALTHBUTLER@MIALAB All Rights Reserved.
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <img height="11" src="<%=request.getContextPath()%>/resources/images/login_p_img04.gif" width="650" alt="健康管家" />
                </td>
            </tr>
            
		</tbody>
	</table>
</form>
</div>
</body>
</html>