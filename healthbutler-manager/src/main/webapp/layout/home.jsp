<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include/meta.jsp"></jsp:include>
<jsp:include page="/include/easyui.jsp"></jsp:include>
<jsp:include page="/include/easyui-portal.jsp"></jsp:include>
<script type="text/javascript">
	var portal;
	$(function() {
		portal = $('#portal').portal({
			border : false,
			fit : true
		});
		var ifr = [ {
			title : '关于健康管家管理平台',
			src : sy.bp() + '/portal/about.jsp',
			height : 100,
			closable : true,
			collapsible : true
		}, {
			title : '组织',
			src : sy.bp() + '/portal/ad.jsp',
			height : 160,
			closable : false,
			collapsible : true
		}, {
			title : '群',
			src : sy.bp() + '/portal/qun.jsp',
			height : 100,
			closable : false,
			collapsible : true
		}, {
			title : '文档',
			src : sy.bp() + '/portal/source.jsp',
			height : 60,
			closable : false,
			collapsible : true
		} ];
		for ( var i = 0; i < ifr.length; i++) {
			var p = $('<div style="overflow:hidden;"/>').appendTo('body').panel({
				title : ifr[i].title,
				content : '<iframe src="' + ifr[i].src + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
				height : ifr[i].height,
				closable : ifr[i].closable,
				collapsible : ifr[i].collapsible
			});
			portal.portal('add', {
				panel : p,
				columnIndex : i % 2
			});
		}
		portal.portal('resize');
	});
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" style="overflow: hidden;" border="false">
		<div id="portal" style="position:relative;">
			<div style="width:50%;"></div>
			<div style="width:50%;"></div>
		</div>
	</div>
</body>
</html>