<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	$(function(){
		$("[value=保存]").click(function(){
			$("form").submit();
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
			<span>下午好！</span><span style="color: #fff21b">${sessionScope.name}</span>
			, 欢迎你！
		</p>
		<a href="login.html">退出</a>
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
			<strong>你现在所在的位置是:</strong> <span>用户管理页面  >> 用户修改页面</span>
		</div>
		<div class="providerAdd">
			<form action="Users.do?action=save&id=${emp.id}" method="post">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<div>
					<label for="userName">用户名称：</label> <input type="text"
						name="userName" id="userName" value="${emp.name}"
						readonly="readonly" /> <span>*</span>
				</div>

				<div>
					<label>用户性别：</label>
					<c:if test="${emp.gender=='男'}">
						<input name="sex" type="radio" checked value="男">男
                    <input name="sex" type="radio" value="女">女
                    </c:if>
					<c:if test="${emp.gender=='女'}">
						<input name="sex" type="radio" value="男">男
                    <input name="sex" type="radio" checked value="女">女
                    </c:if>
				</div>
				<div>
					<label for="data">出生日期：</label> <INPUT class="sang_Calender"
						class=test type=text name="houseDate" value="${emp.birth}">
					<script type="text/javascript" src="js/datetime.js"></script>
					<span>*</span>
				</div>
				<div>
					<label for="userphone">用户电话：</label> <input type="text"
						name="userphone" id="userphone" value="${emp.phone}" /> <span>*</span>
				</div>
				<div>
					<label for="userAddress">用户地址：</label> <input type="text"
						name="userAddress" id="userAddress" value="${emp.addr}" />
				</div>
				<div>
					<label>用户类别：</label>
					<c:choose>
						<c:when test="${emp.position=='系统管理员'}">
							<input type="radio" name="userlei" checked value="系统管理员" />系统管理员
                    <input type="radio" name="userlei" value="经理" />经理
                    <input type="radio" name="userlei" value="采购员" />采购员
                    </c:when>
						<c:when test="${emp.position=='经理'}">
							<input type="radio" name="userlei" checked value="系统管理员" />系统管理员
                    <input type="radio" name="userlei" value="经理" />经理
                    <input type="radio" name="userlei" value="采购员" />采购员
                    </c:when>
						<c:when test="${emp.position=='采购员'}">
							<input type="radio" name="userlei" checked value="系统管理员" />系统管理员
                    <input type="radio" name="userlei" value="经理" />经理
                    <input type="radio" name="userlei" value="采购员" />采购员
                    </c:when>
					</c:choose>
				</div>
				<div class="providerAddBtn">
					<!--<a href="#">保存</a>-->
					<!--<a href="userList.html">返回</a>-->
					<input type="button" value="保存" /> 
					<input type="button" value="返回 " onclick="history.back(-1)" />
				</div>
			</form>
		</div>

	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>

</body>
</html>