<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Health Butler Manger</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<jsp:include page="/include/easyui.jsp"></jsp:include>
</head>
<body id="indexLayout" class="easyui-layout" fit="true">
	<div region="north" href="layout/north.jsp" style="height:71px;overflow: hidden;background:#2626FF url('resources/images/top.gif') repeat-y;"></div>
	<div region="west" href="layout/west.jsp" title="导航" split="false" style="width:235px;overflow: hidden;"></div>
	<div region="center" href="layout/center.jsp" title="欢迎使用：健康管家管理平台" style="overflow: hidden;"></div>
	<div region="south" href="layout/south.jsp" style="height:20px;overflow: hidden;"></div>
<%-- 	<jsp:include page="user/loginAndReg.jsp"></jsp:include> --%>
</body>
</html>