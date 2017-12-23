<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="css/public.css" type="text/css"></link>
<link rel="stylesheet" href="css/style.css" type="text/css"></link>
</head>

<body>
	<!--头部-->
	<header class="publicHeader">
	<h1>超市账单管理系统</h1>

	<div class="publicHeaderR">
		<p>
			<span>下午好！</span><span style="color: #fff21b">${sessionScope.name}
			</span> , 欢迎你！
		</p>
		<a href="loginOut.do">退出</a>
	</div>
	</header>
	<!--时间-->
	<section class="publicTime"> <span id="time">2015年1月1日
		11:11 星期一</span> <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a> </section>
	<!--主体内容-->
	<section class="publicMian">
	<div class="left">
		<h2 class="leftH2">
			<span class="span1"></span>功能列表 <span></span>
		</h2>
		<nav>
		<ul class="list">
			<li><a href="Orders.do?pageIndex=1">账单管理</a></li>
			<li><a href="Suppliers.do?pageIndex=1">供应商管理</a></li>
			<li><a href="Goods.do?pageIndex=1">商品管理</a></li>
			<li><a href="Users.do?pageIndex=1">用户管理</a></li>
			<li><a href="emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a></li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<img class="wColck" src="img/clock.jpg" alt="" />
		<div class="wFont">
			<h2>${sessionScope.name} ${sessionScope.position}</h2>
			<p>抱歉，您的权限不足!</p>
			<a href="javascript:void(0);" onclick="history.back(-1)">返回</a>
		</div>
	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>
</body>
</html>