<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<title>combobox</title>
</head>
<body>
	<!-- 方式三：使用插件来动态创建datagrid -->
	<table id="mytable">

	</table>
	<script type="text/javascript">
		$(function() {
			var myIndex = -1;//全局变量，值为正在编辑的索引
			//页面加载完成后，创建数据表格datagrid
			$("#mytable")
					.datagrid(
							{
								//定义标题行所有的列
								columns : [ [ {
									title : '编号',
									field : 'id'
								}, {
									width : 150,
									title : '姓名',
									field : 'name',
									editor : {
										type : 'vaildatebox',
										options : {}
									}
								}, {
									width : 150,
									title : '年龄',
									field : 'age',
									editor : {
										type : 'numberbox',
										options : {}
									}
								}, {
									width : 150,
									title : '日期',
									field : 'address',
									editor : {
										type : 'datebox',
										options : {}
									}
								} ] ],
								url : '${pageContext.request.contextPath }/json/datagrid_data.json',
								rownumbers : true,
								//定义工具栏
								toolbar : [ {
									text : '添加',
									iconCls : 'icon-add',
									handler : function() {
										$("#mytable").datagrid("insertRow", {
											index : 0,
											row : {}
										});
	                                    $("#mytable").datagrid("beginEdit", 0);
	                                    myIndex = 0;
									}
								}, {
									text : '删除',
									iconCls : 'icon-delete',
									heandler : function() {
										//获得选择的行对象
                                        var rows = $("#mytable").datagrid("getSelections");
                                        if(rows.length == 1){
                                            var row = rows[0];
                                            //获取对象索引
                                            myIndex = $("#mytable").datagrid("getRowIndex",row);
                                        }
                                        $("#mytable").datagrid("deleteRow", myIndex);
                                    }
								}, {
									text : '修改',
									iconCls : 'icon-edit',
									heandler : function() {
										//获得选择的行对象
										var rows = $("#mytable").datagrid("getSelections");
										if(rows.length == 1){
											var row = rows[0];
											//获取对象索引
											myIndex = $("#mytable").datagrid("getRowIndex",row);
										}
										$("#mytable").datagrid("beginEdit", myIndex);
									}
								}, {
									text : '保存',
									iconCls : 'icon-save',
									handler : function() {
										$("#mytable").datagrid("endEdit", myIndex);
									}
								} ],
								//显示分页条
								pagination : true,
								//监听结束编辑事件
								onAfterEdit:function(index,data,changes){
									$.post();
								}
							});
		});
	</script>
</body>
</html>