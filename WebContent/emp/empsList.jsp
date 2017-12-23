<%@ page language="java" import="com.psm.model.EmpView" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>星星采购管理系统</title>
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$(".removeUser").click(function() {
			var uid = $(this).parent("td").siblings(".uid").text();
			//保存到cookie
			document.cookie = "uid=" + uid;
		});
		$("[value=查询]").click(function(){
			var txt=$("[value=查询]").prev().val();
		   document.location.href='Users.do?action=search&name='+txt;
		});
	
	});
</script>

</head>

<body>
	<!--头部-->
	<header class="publicHeader">
	<h1>星星采购管理系统</h1>
	<div class="publicHeaderR">
		<p>
			<span>下午好！</span><span style="color: #fff21b">
				${sessionScope.name}</span> , 欢迎你！
		</p>
		<a href="loginOut.do">退出</a>
	</div>
	</header>
	<!--时间-->
	<section class="publicTime"> <span id="time">2015年1月1日
		11:11 星期一</span> <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a> </section>
	<!--主体内容-->
	<section class="publicMian ">
	<div class="left">
		<h2 class="leftH2">
			<span class="span1"></span>功能列表 <span></span>
		</h2>
		<nav>
		<ul class="list">
			<li><a href="Orders.do?pageIndex=1">账单管理</a></li>
			<li><a href="Suppliers.do?pageIndex=1">供应商管理</a></li>
			<li><a href="Goods.do?pageIndex=1">商品管理</a></li>
			<li id="active"><a href="Users.do?pageIndex=1">用户管理</a></li>
			<li><a href="emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a></li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>用户管理页面</span>
		</div>
		<div class="search">
			<span>用户名：</span> <input type="text" placeholder="请输入用户名" /> <input
				type="button" value="查询"  /> <a href="emp/empAdd.jsp">添加用户</a>
		</div>
		<!--用户-->
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">用户编码</th>
				<th width="20%">用户名称</th>
				<th width="10%">性别</th>
				<th width="10%">年龄</th>
				<th width="10%">电话</th>
				<th width="10%">用户类型</th>
				<th width="30%">操作</th>
			</tr>
			<!-- 开始遍历 -->
			<c:forEach var="emp" items="${empsList}">
				<tr>
					<td class="uid">${emp.id}</td>
					<td>${emp.name}</td>
					<td>${emp.gender}</td>
					<td><script type="text/javascript">
				var year = new Date().getFullYear()-${emp.year};
				document.write(year);
			</script></td>
					<td>${emp.phone}</td>
					<td>${emp.position}</td>
					<td><a href="Users.do?action=details&id=${emp.id}"><img
							src="img/read.png" alt="查看" title="查看" /> 
						</a>
						<a
						href="Users.do?action=update&id=${emp.id}"><img
							src="img/xiugai.png" alt="修改" title="修改" /> 
						</a> 
						 <%
						 	if (request.getParameter("pageIndex") == null) {
						 %>
						 <img class="removeUser" src="img/schu.png" alt="删除" title="删除" /> 
						 <%
						 	} else {
						 %>
						 <img class="removeUser" src="img/schu.png" alt="删除" title="删除" /> 
						 <%
						 	}
						 %>
					</td>
				</tr>
			</c:forEach>
			<!-- 遍历结束 -->
		</table>

		<table>
			<tr>
				<c:if test="${pages.currPageNo>1}">
					<td><a href="Users.do?pageIndex=1">首页</a></td>
					<td>
						<a href="Users.do?pageIndex=${pages.currPageNo - 1}">上一页</a>
					</td>
				</c:if>
				<c:if test="${pages.currPageNo<pages.totalPageCount}">
					<td>
						<a href="Users.do?pageIndex=${pages.currPageNo + 1}">下一页</a>
					</td>
					<td>
						<a href="Users.do?pageIndex=${pages.totalPageCount}">末页</a>
					</td>
				</c:if>
			</tr>
		</table>
	</div>
	</section>

	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeUse">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该用户吗？</p>
				<a href="Users.do?action=del" id="yes">确定</a>
				<%
					if (request.getParameter("pageIndex") == null) {
				%>
				<a href="Users.do#" id="no">取消</a>
				<%
					} else {
				%>
				<a href="Users.do?pageIndex=${pages.currPageNo}#" id="no">取消</a>
				<%
					}
				%>
			</div>
		</div>
	</div>

	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/jquery.js"></script>
	<script src="js/js.js"></script>
	<script src="js/time.js"></script>
</body>
</html>