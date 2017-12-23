<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		$("[value=保存]").click(function() {
			$("form").submit();
		});
	});
</script>
</head>

<body>
	=
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
			<li id="active"><a href="Goods.do?pageIndex=1">商品管理</a></li>
			<li><a href="Users.do?pageIndex=1">用户管理</a></li>
			<li><a href="emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a>	</li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>商品管理页面 >商品修改页</span>
		</div>
		<div class="providerAdd">
			<form action="Goods.do?action=save" method="post">
				<!--div的class 为error是验证错误，ok是验证成功-->
				<div class="">
					<label for="id">商品编号：</label> <input type="text"
						name="id" id="id" value="${good.id }"
						readonly="readonly" /> <span>*商品编号不可修改</span>
				</div>
				<div>
					<label for="name">商品名称：</label> <input type="text"
						name="name" id="name" value="${good.name }"/>
				</div>
				<div>
					<label for="spec">单位：</label> <input type="text" name="spec"
						id="spec" value="${good.spec }" /> <span></span>
				</div>
				<div>
					<label for="model">型号：</label> <input type="text" name="model"
						id="model" value="${good.model }" /> <span></span>
				</div>
				<div>
					<label for="size">尺寸：</label> <input type="text" name="size"
						id="size" value="${good.size }" /> <span></span>
				</div>
				<div>
					<label for="sid">供应商编号：</label>
					<select name="sid">
						<c:forEach var="sid" items="${sidList}">
							<c:if test="${sid!=''}">
								<c:if test="${sid==good.sid}">
									<option value="${sid}" selected="selected">${sid}</option>
								</c:if>
								<c:if test="${sid!=good.sid}">
									<option value="${sid}">${sid}</option>
								</c:if>
							</c:if>							
						</c:forEach>
					</select> 
					<span></span>
				</div>
				<div>
					<label for="price">进价：</label> <input type="text" name="price"
						id="price" value="${good.price }" /> <span></span>
				</div>
				<div>
					<label for="saleprice">售价：</label> <input type="text" name="saleprice"
						id="saleprice" value="${good.saleprice }" /> <span></span>
				</div>
				<div>
					<label for="vipprice">VIP价：</label> <input type="text" name="vipprice"
						id="vipprice" value="${good.vipprice }" /> <span></span>
				</div>
				<div>
					<label for="num">库存：</label> <input type="text" name="num"
						id="num" value="${good.num }" /> <span></span>
				</div>
				<div class="providerAddBtn">
					<!--<a href="#">保存</a>-->
					<!--<a href="providerList.html">返回</a>-->
					<input type="button" value="保存"  /> <input type="button"
						value="返回" onclick="history.back(-1)" />
				</div>
			</form>
		</div>

	</div>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
	<script src="js/time.js"></script>

</body>
</html>