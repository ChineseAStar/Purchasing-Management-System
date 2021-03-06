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
	$(function() {
		$(".removeProduct").click(function() {
			var gid = $(this).parent("td").siblings(".id").text();
			//保存到cookie
			document.cookie = "id=" + gid;
		});

		$("[value=查询]").click(function(){
			var txt=$("[value=查询]").prev().val();
		   document.location.href='Goods.do?action=search&name='+txt;
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
			<li><a href="emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a>	</li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>商品管理页面</span>
		</div>
		<div class="search">
			<span>商品名称：</span> <input type="text" placeholder="请输商品的名称" />
			<input type="button" value="查询" />
			<a href="Goods.do?action=addView">添加商品</a>
			<a href="Goods.do?action=classManage&num=0">类别管理</a>
		</div>
		<!--商品操作表格-->
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">商品编号</th>
				<th width="20%">商品名称</th>
				<th width="10%">商品进价</th>
				<th width="10%">商品售价</th>
				<th width="10%">VIP价格</th>
				<th width="10%">商品单位</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach var="good" items="${goodsList}">
				<tr>
					<td class="id">${good.id}</td>
					<td>${good.name }</td>
					<td>${good.price}</td>
					<td>${good.saleprice}</td>
					<td>${good.vipprice}</td>
					<td>${good.spec}</td>
					<td>
						<a href="Goods.do?action=details&id=${good.id}">
							<img src="img/read.png" alt="查看" title="查看" />
						</a>
						<a href="Goods.do?action=update&id=${good.id}">
							<img src="img/xiugai.png" alt="修改" title="修改" />
						</a>
						<c:choose>
							<c:when test="${param.pageIndex==null}">
								<a href="Goods.do#" class="removeProduct">
									<img src="img/schu.png" alt="删除" title="删除" />
								</a>
							</c:when>
							<c:when test="${param.pageIndex!=null}">
								<a href="Goods.do?pageIndex=${pages.currPageNo}#" class="removeProduct">
									<img src="img/schu.png" alt="删除" title="删除" /> 
								</a>
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<c:if test="${pages.currPageNo>1}">
					<td><a href="Goods.do?pageIndex=1">首页</a></td>
					<td>
						<a href="Goods.do?pageIndex=${pages.currPageNo - 1}">上一页</a>
					</td>
				</c:if>
				<c:if test="${pages.currPageNo<pages.totalPageCount}">
					<td>
						<a href="Goods.do?pageIndex=${pages.currPageNo + 1}">下一页</a>
					</td>
					<td>
						<a href="Goods.do?pageIndex=${pages.totalPageCount}">末页</a>
					</td>
				</c:if>
			</tr>
		</table>
	</div>
	</section>

	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeProv">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删该除商品吗？</p>
				<a href="Goods.do?action=del" id="yes">确定</a>
				<c:choose>
					<c:when test="${param.pageIndex==null}">
						<a href="Goods.do#" id="no">取消</a>
					</c:when>
					<c:when test="${param.pageIndex!=null}">
						<a href="Goods.do?pageIndex=${pages.currPageNo}#" id="no">取消</a>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>


	<footer class="footer"> 版权归北大青鸟 </footer>

	<script src="js/jquery.js"></script>
	<script src="js/js.js"></script>
	<script src="js/time.js"></script>

</body>
</html>
