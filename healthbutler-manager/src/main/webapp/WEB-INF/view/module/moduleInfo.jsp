<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include/meta.jsp"></jsp:include>
<jsp:include page="/include/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var dialog;
	var recordForm;
	
	$(function() {
		recordForm = $('#recordForm').form();

		dialog = $('#dialog').show().dialog({
			modal : true,
			title : '模块信息',
			buttons : [ {
				text : '确定',
				handler : function() {
					
					if ($("#editType").val() == 0) {
						recordForm.form('submit', {
							url : sy.bp() + '/healthservice/moduleinfoController?edit',
							onSubmit: function () {
								showProcess(true, '温馨提示', '正在提交数据...');
							},	
							success : function(data) {
								showProcess(false);
								dialog.dialog('close');
								$.messager.show({
									msg : '编辑成功！',
									title : '提示'
								});
								datagrid.datagrid('reload');
							},
							onLoadError: function () {
								showProcess(false);
								$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
							}
						});
					} else {
						recordForm.form('submit', {
							url : sy.bp() + '/healthservice/moduleinfoController?add',
							onSubmit: function () {
								var flag = $(this).form('validate');
								if (flag) {
									showProcess(true, '温馨提示', '正在提交数据...');
								}
								return flag;
							},	
							success : function(data) {
								showProcess(false);
								try {
									var d = $.parseJSON(data);
									if (d) {
										dialog.dialog('close');
										$.messager.show({
											msg : '创建成功！',
											title : '提示'
										});
										datagrid.datagrid('reload');
									}
								} catch (e) {
									$.messager.show({
										msg : '名称已存在！',
										title : '提示'
									});
								}
							},
							onLoadError: function () {
								showProcess(false);
								$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
							}
						});
					}
				}
			} ]
		}).dialog('close');

		datagrid = $('#datagrid').datagrid({
			url : sy.bp() + '/healthservice/moduleinfoController?datagrid',
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					dialog.dialog('open');
					recordForm.form('clear');
					$("#editType").val("1");
					$("#displayClientVersionId").val("1.3.0");
					$("#iphoneDisplayClientVersionId").val("1.0");
					$("#runClientVersionId").val("1.3.0");
					$("#iphoneRunClientVersionId").val("1.0");
					$("#moduleVersionId").val("1.0");
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var rows = datagrid.datagrid('getSelections');
					if (rows.length != 1) {
						$.messager.show({
							msg : '请选择一条记录进行编辑！',
							title : '提示'
						});
						datagrid.datagrid('unselectAll');
					} else {
// 						recordForm.find('[name=name]').attr('readonly', 'readonly');
						dialog.dialog('open');
						recordForm.form('clear');
						recordForm.form('load', {
							moduleId : rows[0].moduleId,
							moduleVersion : rows[0].moduleVersion,
							moduleName : rows[0].moduleName,
							moduleKey : rows[0].moduleKey,
							moduleUrl : rows[0].moduleUrl,
							options : rows[0].options,
							iconVersion : rows[0].iconVersion,
							iconUrl : rows[0].iconUrl,
							moduleType : rows[0].moduleType,
							moduleLevel : rows[0].moduleLevel,
							displayClientVersion : rows[0].displayClientVersion,
							iphoneDisplayClientVersion : rows[0].iphoneDisplayClientVersion,
							runClientVersion : rows[0].runClientVersion,
							iphoneRunClientVersion : rows[0].iphoneRunClientVersion,
							description : rows[0].description,
							tag : rows[0].tag
						});
						$("#editType").val("0");
					}
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('unselectAll');
					$("input[type='checkbox']").eq(0).attr("checked", false);
				}
			}, '-' ],
			onLoadSuccess:function(){
				$('#datagrid').datagrid('clearSelections'); 
			},
			title : '',
			iconCls : 'icon-save',
			striped: true,
			queryParams:{},
			pagination : true,
			pageSize : 6,
			pageList : [ 6, 12, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : true,
			idField : 'moduleId',
			frozenColumns : [ [ {
				title : 'moduleId',
				field : 'moduleId',
				width : 100
			}, {
				field : 'moduleVersion',
				title : '版本号',
				width : 50
			}, {
				field : 'moduleName',
				title : '模块名称',
				width : 60
			}, {
				field : 'moduleKey',
				title : '模块key',
				width : 60
			} ] ],
			columns : [ [ {
				field : 'options',
				title : '开关',
				width : 40
			}, {
				field : 'iconVersion',
				title : '图标版本',
				width : 54
			}, {
				field : 'iconUrl',
				title : '图标地址',
				width : 60,
				formatter : function(val,rec){
					return "<img src='"+ val+ "' width='35' height='35' border='0'/>";
				}
			}, {
				field : 'moduleType',
				title : '模块类别',
				width : 60,
				formatter:function(val,rec){
					if(val==1){
						return "wap方式";
					}
					if(val==0){
						return "普通方式";
					}
					if(val==2){
						return "apk方式";
					}
            	}
			}, {
				field : 'moduleLevel',
				title : '模块等级',
				width : 150,
				formatter:function(val,rec){
					if(val==1){
						return "有公用数据也有城市数据";
					}
					if(val==0){
						return "与城市无关";
					}
					if(val==2){
						return "必须有城市数据";
					}
					if(val==4){
						return "推广合作方模块";
					}
            	}
			}, {
				field : 'moduleUrl',
				title : '模块地址',
				width : 280
			}, {
				field : 'displayClientVersion',
				title : 'android显示版本',
				width : 100
			}, {
				field : 'iphoneDisplayClientVersion',
				title : 'iphone显示版本',
				width : 100
			}, {
				field : 'runClientVersion',
				title : 'android运行版本',
				width : 100
			}, {
				field : 'iphoneRunClientVersion',
				title : 'iphone运行版本',
				width : 100
			}, {
				field : 'description',
				title : '描述',
				width : 150
			}, {
				field : 'tag',
				title : '是否删除',
				width : 55,
				formatter:function(val,rec){
					if(val==1){
						return "未删除";
					}
					if(val==0){
						return "已删除";
					}
            	}
			}] ]
		});
		
	});

	function remove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].moduleId);
					}
					$.ajax({
						url : sy.bp() + '/healthservice/moduleinfoController?del',
						data : {
							ids : ids.join(',')
						},
						cache : false,
						dataType : "json",
						success : function(response) {
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}
	
	//表格查询
	function searchRecord(){
		var params = $('#datagrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#datagrid').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#classCombo').get(0).selectedIndex=0;
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="north" border="false" class="p-search">  
	    <form id="queryForm" style="margin:10;text-align: left; ">
			<table width="100%">
				<tr>
					<td style="width: 180px;">模块名称：<input name="moduleName" style="width: 100px;" /></td>
					<td style="width: 180px;">模块key：<input name="moduleKey" style="width: 100px;"></td>
					<td style="width: 90px;"><a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>
					<td align="left"><a href="#" onclick="searchRecord();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
				</tr>
			</table>
		</form>
    </div>
      
	<div region="center" border="false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="dialog" style="display: none; width:800px;height:400px;">
		<form id="recordForm" method="post">
			<table class="tableForm">
			<input id="editType" type="hidden" />
				<tr>
					<th style=" width:150px;" >ID</th>
					<td><input name="moduleId" required="true" style="width:100px;" /></td>
				</tr>
				<tr>
					<th style=" width:150px;" >版本号</th>
					<td><input id="moduleVersionId" name="moduleVersion" style="width:100px" /></td>
				</tr>
				<tr>
					<th>模块名称</th>
					<td><input name="moduleName" class="easyui-validatebox" required="true" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>模块key</th>
					<td><input name="moduleKey" class="easyui-validatebox" required="true" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>模块开关</th>
					<td><input name="options" class="easyui-validatebox" required="true" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>图标版本</th>
					<td><input name="iconVersion" class="easyui-validatebox" required="true" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>图标地址</th>
					<td><input name="iconUrl" class="easyui-validatebox" required="true" style=" width:555px;"/></td>
				</tr>
				<tr>
					<th>模块类别</th>
					<td><select name="moduleType" required="true">
						  <option value="0">普通方式</option>
						  <option value="1">wap方式</option>
						  <option value="2">apk方式</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>模块等级</th>
					<td><select name="moduleLevel" required="true">
						  <option value="0">与城市无关</option>
						  <option value="1">有公用数据也有城市数据</option>
						  <option value="2">必须有城市数据</option>
						  <option value="4">推广合作方模块</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>模块地址</th>
					<td>
						<input name="moduleUrl" style="width:555px" required="true"></input>  
					</td>
				</tr>
				<tr>
					<th>android显示版本</th>
					<td><input id="displayClientVersionId" name="displayClientVersion" class="easyui-validatebox" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>iphone显示版本</th>
					<td><input id="iphoneDisplayClientVersionId" name="iphoneDisplayClientVersion" class="easyui-validatebox" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>android运行版本</th>
					<td><input id="runClientVersionId" name="runClientVersion" class="easyui-validatebox" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>iphone运行版本</th>
					<td><input id="iphoneRunClientVersionId" name="iphoneRunClientVersion" class="easyui-validatebox" style=" width:100px;"/></td>
				</tr>
				<tr>
					<th>描述</th>
					<td><input name="description" style=" width:555px;" /></td>
				</tr>
				<tr>
					<th>是否删除</th>
					<td><select id="tagId" name="tag" style=" width:100px;" required="true">
						  <option value="1">未删除</option>
						  <option value="0">已删除</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>