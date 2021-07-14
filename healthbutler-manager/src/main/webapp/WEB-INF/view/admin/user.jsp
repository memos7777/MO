<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include/meta.jsp"></jsp:include>
<jsp:include page="/include/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	var roleTree;
	var passwordInput;
	$(function() {

		userForm = $('#userForm').form();

		passwordInput = userForm.find('[name=password]').validatebox({
			required : true
		});

		roleTree = $('[name=roleId]').combotree({
			url : sy.bp() + '/system/role?tree',
			animate : false,
			lines : !sy.isLessThanIe8(),
			checkbox : true,
			multiple : true,
			onLoadSuccess : function(node, data) {
				var t = $(this);
				if (data) {
					$(data).each(function(index, d) {
						if (this.state == 'closed') {
							t.tree('expandAll');
						}
					});
				}
			}
		});

		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '用户信息',
			buttons : [ {
				text : '确定',
				handler : function() {
					if (userForm.find('[name=accountId]').val() != '') {
						userForm.form('submit', {
							url : sy.bp() + '/system/user?edit',
							onSubmit: function () {
								var flag = $(this).form('validate');
								if (flag) {
									showProcess(true, '温馨提示', '正在提交数据...');
								}
								return flag;
							},	
							success : function(data) {
								showProcess(false);
								userDialog.dialog('close');
								$.messager.show({
									msg : '用户编辑成功！',
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
						userForm.form('submit', {
							url : sy.bp() + '/system/user?add',
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
										userDialog.dialog('close');
										$.messager.show({
											msg : '用户创建成功！',
											title : '提示'
										});
										datagrid.datagrid('reload');
									}
								} catch (e) {
									$.messager.show({
										msg : '用户名称已存在！',
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
			url : sy.bp() + '/system/user?datagrid',
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					userDialog.dialog('open');
					passwordInput.validatebox({
						required : true
					});
					userForm.find('[name=name]').removeAttr('readonly');
					userForm.form('clear');
					$("#pwdtr").show();
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
							msg : '请选择一个用户进行编辑！',
							title : '提示'
						});
						datagrid.datagrid('unselectAll');
					} else {
						passwordInput.validatebox({
							required : false
						});
						userForm.find('[name=name]').attr('readonly', 'readonly');
						userDialog.dialog('open');
						userForm.form('clear');
						userForm.form('load', {
							accountId : rows[0].accountId,
							accountName : rows[0].accountName,
							accountPwd : rows[0].accountPwd,
							trueName : rows[0].trueName,
							mobile : rows[0].mobile,
							email : rows[0].email,
							cityId : rows[0].cityId,
							groupId : rows[0].groupId
						});
						$("#pwdtr").hide();
					}
				}
			}, '-', {
				text : '重置密码',
				iconCls : 'icon-edit',
				handler : function() {
					var rows = datagrid.datagrid('getSelections');
					if (rows.length != 1) {
						$.messager.show({
							msg : '请选择一个用户进行重置密码！',
							title : '提示'
						});
						datagrid.datagrid('unselectAll');
					} else {
						resetPassword();
					}
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('unselectAll');
				}
			}, '-' ],
			onLoadSuccess:function(){
				$('#datagrid').datagrid('clearSelections'); 
			},
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'accountId',
			frozenColumns : [ [ {
				title : 'accountId',
				field : 'accountId',
				width : 50,
				checkbox : true
			}, {
				field : 'accountName',
				title : '用户名称',
				width : 100
			} ] ],
			columns : [ [ {
				field : 'trueName',
				title : '姓名',
				width : 100
			}, {
				field : 'accountPwd',
				title : '密码',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					return '******';
				}
			}, {
				field : 'mobile',
				title : '电话',
				width : 150
			}, {
				field : 'email',
				title : '邮件地址',
				width : 150
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150
			}, {
				field : 'groupName',
				title : '角色',
				width : 200
			}, {
				field : 'groupId',
				title : '角色',
				width : 200,
				hidden : true
			}, {
				field : 'cityName',
				title : '城市',
				width : 200
			}, {
				field : 'cityId',
				title : '城市',
				width : 200,
				hidden : true
			} ] ]
		});

		$('#roleCombo').combobox({  
			url: sy.bp() + "/system/role?roles",
			fitColumns: true,
			striped: true,
			editable:false,
			valueField:'groupId',
		    textField:'groupName'
		});
		
		$('#cityCombo').combobox({  
			url: sy.bp() + "/cityservice/mobileSupportCityController?commoncitys",
			fitColumns: true,
			striped: true,
			editable:false,
			valueField:'cityId',
		    textField:'name'
		});
	});

	function resetPassword() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length = 1) {
			var accountId = rows[0].accountId;
			var accountName = rows[0].accountName;
			$.messager.confirm('请确认', '您要重置' + accountName + '的密码？', function(r) {
				if (r) {
					$.ajax({
						url : sy.bp() + '/system/user?resetpwd',
						data : {
							ids : accountId
						},
						cache : false,
						dataType : "json",
						success : function(response) {
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '重置密码成功！'
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要重置密码的记录！', 'error');
		}
	}
	
	function remove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].accountId);
					}
					$.ajax({
						url : sy.bp() + '/system/user?del',
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
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow: hidden; width:600px;height:400px;">
		<form id="userForm" method="post">
			<table class="tableForm">
				<tr>
					<th>用户ID</th>
					<td><input name="accountId" readonly="readonly" style="background-color:#C0C0C0; width:100px;" /></td>
				</tr>
				<tr>
					<th>登录名称</th>
					<td><input name="accountName" class="easyui-validatebox" required="true" style=" width:455px;" /></td>
				</tr>
				<tr  id="pwdtr">
					<th>登录密码</th>
					<td><input name="accountPwd"  type="password" class="easyui-validatebox" required="true" style=" width:455px;" /></td>
				</tr>
				<tr>
					<th>姓名</th>
					<td><input name="trueName" class="easyui-validatebox" required="true" style=" width:455px;" /></td>
				</tr>
				<tr>
					<th>电话</th>
					<td><input name="mobile" class="easyui-validatebox" style=" width:455px;" /></td>
				</tr>
				<tr>
					<th>邮件地址</th>
					<td><input name="email" class="easyui-validatebox" style=" width:455px;" /></td>
				</tr>
				<tr>
					<th>所属角色</th>
					<td><select id="roleCombo" name="groupId" required="true"  style="width: 156px;"></select></td>
				</tr>
				<tr>
					<th>所属城市</th>
					<td><select id="cityCombo" name="cityId" required="true" style="width: 156px;"></select></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>