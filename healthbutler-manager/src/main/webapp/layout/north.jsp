<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8">
	function logout(b) {
		 window.location.href="welcome?logout";
// 		$.post('system/user?logout', function() {
// 			if (b) {
// 				if (sy.isLessThanIe8()) {
// 					loginAndRegDialog.dialog('open');
// 				} else {
// 					location.replace(sy.bp());
// 				}
// 			} else {
// 				loginAndRegDialog.dialog('open');
// 			}
// 		});
	}
</script>
<div style="position: absolute; right: 0px; bottom: 0px; ">
<!-- 	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a> -->
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 100px;">
			<div onclick="">default</div>
			<div onclick="">gray</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
<!-- 	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div> -->
<!-- 	<div class="menu-sep"></div> -->
<!-- 	<div onclick="logout();">重新登录</div> -->
<!-- 	<div onclick="logout(true);">退出系统</div> -->
		<div onclick="logout();">退出系统</div>
</div>