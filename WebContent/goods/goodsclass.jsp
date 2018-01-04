<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:if test="${!(empty errmsg)}">
	<script type="text/javascript">
		alert("${errmsg}");
	</script>
</c:if> 
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
		$("[value=添加]").click(
				function() {
					var dalei = $("[name=dalei]").val();
					var zhonglei = $("[name=zhonglei]").val();
					var xiaolei = $("[name=xiaolei]").val();
					var leiname = $("[name=leiname]").val();
					document.location.href = 'Goods.do?action=addClass&dalei='
							+ dalei + '&zhonglei=' + zhonglei + '&xiaolei=' + xiaolei + '&leiname=' + leiname;
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
			<li id="active"><a href="Goods.do?pageIndex=1">商品管理</a></li>
			<li><a href="Users.do?pageIndex=1">用户管理</a></li>
			<li><a href=emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a>	</li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>商品管理页面</span>
		</div>
		<div class="search">
			<span>大类：</span> <input name="dalei" type="text" placeholder="请输商品的大类" />
			<span>中类：</span> <input name="zhonglei" type="text" placeholder="请输商品的中类" /><br>
			<span>小类：</span> <input name="xiaolei" type="text" placeholder="请输商品的小类" />
			<span>名称：</span> <input name="leiname" type="text" placeholder="请输商品的类名" />
			<input type="button" value="添加" />
		</div>	<br><br><br>
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="20%">大类</th>
				<th width="20%">中类</th>
				<th width="20%">小类</th>
				<th width="20%">操作</th>
			</tr>
		</table>
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<c:forEach var="gclass" items="${classList}">
				<tr>
					
					<td width="60%">
						<c:choose>
							<c:when test="${gclass.type==1}">
								<p align="left">${gclass.daid}  ${gclass.name}</p>
							</c:when>
							<c:when test="${gclass.type==2}">
								<p align="center">${gclass.zhongid}  ${gclass.name}</p>
							</c:when>
							<c:when test="${gclass.type==3}">
								<p align="right">${gclass.xiaoid}  ${gclass.name}</p>
							</c:when>
						</c:choose>
					</td>
					
					
					<td width="20%">
						<a href="Goods.do?action=removeClass&dalei=${gclass.daid}&zhonglei=${gclass.zhongid}&xiaolei=${gclass.xiaoid}" class="removeProduct">
							<img src="img/schu.png" alt="删除" title="删除" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</section>
	
	<footer class="footer"> 版权归北大青鸟 </footer>

	<script src="js/jquery.js"></script>
	<script src="js/js.js"></script>
	<script src="js/time.js"></script>

</body>
</html>
