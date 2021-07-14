<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript" charset="UTF-8">	
	function displayDate() {
		document.getElementById("demo").innerHTML = Date();
	}
</script>

<html>
<body>
	<h2>This is simple test page!</h2>
	<hr>
	<p>
		request.getContextPath()的值为：<%=request.getContextPath()%>
	</p>

	<p id="demo">这是一个测试</p>

	<button type="button" onclick="displayDate()">显示日期</button>

</body>
</html>
