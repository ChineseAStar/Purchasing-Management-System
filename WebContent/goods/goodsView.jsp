<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	</head>
<body>
	<!--头部-->
<header class="publicHeader">
    <h1>星星采购管理系统</h1>

    <div class="publicHeaderR">
        <p>
			<span>下午好！</span><span style="color: #fff21b"> ${sessionScope.name }</span> , 欢迎你！</p>
        <a href="login.html">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2">
			<span class="span1"></span>功能列表 <span></span>
		</h2>
        <nav>
            <ul class="list">
             <li><a href="Orders.do">账单管理</a></li>
                <li><a href="Suppliers.do">供应商管理</a></li>
                <li id="active"><a href="Goods.do">商品管理</a></li>
                <li><a href="Users.do">用户管理</a></li>
                <li><a href="emp/password.jsp">密码修改</a></li>
                <li><a href="loginOut.do">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>商品管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p>
				<strong>商品编号：</strong><span>${good.id}</span>
			</p>
            <p>
				<strong>商品名称：</strong><span>${good.name }</span>
			</p>
            <p>
				<strong>单位：</strong><span>${good.spec}</span>
			</p>
            <p>
				<strong>型号：</strong><span>${good.model}</span>
			</p>
            <p>
				<strong>尺寸：</strong><span>${good.size}</span>
			</p>
            <p>
				<strong>供应商编号：</strong><span>${good.sid}</span>
			</p>
            <p>
				<strong>进价：</strong><span>${good.price }</span>
			</p>
            <p>
				<strong>售价：</strong><span>${good.saleprice }</span>
			</p>
            <p>
				<strong>VIP价：</strong><span>${good.vipprice }</span>
			</p>
            <p>
				<strong>库存：</strong><span>${good.num}</span>
			</p>

            <a href="javascript:void(0);" onclick="history.back(-1)">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>
</body>
</html>
