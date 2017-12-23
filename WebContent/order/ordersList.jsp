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
		$(".addorder").click(function() {
		    document.location.href = 'Orders.do?action=addView';
		});
		$(".removeBill").click(function() {
			var oid = $(this).parent("td").siblings(".oid").text();
			//将要删除的订单oid保存到cookie
			document.cookie = "oid=" + oid;
		});
	
		$("[value=查询]").click(
				function() {
					var txt = $("[value=查询]").prevAll("input").val();
					var sname = $("[name=tigong]").val();
					var opaid = $("[name=fukuan]").val();
					document.location.href = 'Orders.do?action=search&gname='
							+ txt + '&sname=' + sname + '&opaid=' + opaid;
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
			<li id="active"><a href="Orders.do?pageIndex=1">账单管理</a></li>
			<li><a href="Suppliers.do?pageIndex=1">供应商管理</a></li>
			<li><a href="Goods.do?pageIndex=1">商品管理</a></li>
			<li><a href="Users.do?pageIndex=1">用户管理</a></li>
			<li><a href="emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a>	</li>
		</ul>
		</nav>
	</div>
	<div class="right">
		<div class="location">
			<strong>你现在所在的位置是:</strong> <span>账单管理页面</span>
		</div>
		<div class="search">
			<span>供应商：</span>
			<select name="tigong">
				<option value="">--请选择--</option>
				<c:forEach var="supplier" items="${suppliersList}">
					<option value="${supplier.name}">${supplier.name}</option>
				</c:forEach>
			</select> 
			<span>审核结果：</span> 
			<select name="fukuan">
				<option value="">--请选择--</option>
				<option value="未审核">未审核</option>
				<option value="已通过">已通过</option>
				<option value="未通过">未通过</option>
			</select> 
			<input type="button" value="查询" />
			<a class="addorder">添加订单</a>
		</div>
		<!--账单表格 样式和供应商公用-->
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">账单编码</th>
				<th width="20%">供应商</th>
				<th width="10%">账单金额</th>
				<th width="10%">审核结果</th>
				<th width="20%">创建时间</th>
				<th width="30%">操作</th>
			</tr>
			<%--  !!!items里一定要记得写${ } --%>
			<c:choose>
				<c:when test="${ordersList.size()==0}">
					<td colspan="7" ><span id="null">没有匹配的数据</span></td>
				</c:when>
				<c:when test="${ordersList.size()!=0}">
					<c:forEach var="order" items="${ordersList}">
						<tr>
							<td class="oid">${order.id }</td>
							<td>${order.sname }</td>
							<td>${order.cost}</td>
							<td>${order.checkresult}</td>
							<td>${order.ordertime}</td>
							<td>
								<a href="Orders.do?action=details&id=${order.id}">
									<img src="img/read.png" alt="查看" title="查看" /> 
								</a> 
								<c:set var="bl" value="${order.checkresult}" scope="request"/>
								<% if("未审核".equals(request.getAttribute("bl"))){ %>
								<a href="Orders.do?action=update&id=${order.id}">
									<img src="img/xiugai.png" alt="修改" title="修改" /> 
								</a> 
								<c:choose>
									<c:when test="${param.pageIndex==null}">
										<a href="Orders.do#" class="removeBill">
											<img src="img/schu.png" alt="删除" title="删除" />
										</a>
									</c:when>
									<c:when test="${param.pageIndex!=null}">
										<a href="Orders.do?pageIndex=${pages.currPageNo}#" class="removeBill">
											<img src="img/schu.png" alt="删除" title="删除" /> 
										</a>
									</c:when>
								</c:choose>
								<a href="Orders.do?action=checkView&id=${order.id}">
									<img src="img/shenhe.gif" alt="审核" title="审核" /> 
								</a> 
								<%} %>
							</td>

						</tr>
					</c:forEach>
				</c:when>
			</c:choose>

		</table>
		<table width="800">
			<tr>
				<c:if test="${pages.currPageNo>1}">
					<td><a href="Orders.do?pageIndex=1">首页</a>
					</td>
					<td><a href="Orders.do?pageIndex=${pages.currPageNo - 1}">上一页</a>
					</td>
				</c:if>
				<c:if test="${pages.currPageNo<pages.totalPageCount}">
					<td><a href="Orders.do?pageIndex=${pages.currPageNo + 1}">下一页</a>
					</td>
					<td><a href="Orders.do?pageIndex=${pages.totalPageCount}">末页</a>
					</td>
				</c:if>
				<c:if test="${param.action!='search'}">
					<td class="total" align="right" ><span class=total style="text-align:right">${pages.currPageNo }/${pages.totalPageCount}页</span>
					</td>
					</c:if>
			</tr>
		</table>
	</div>
	</section>

	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeBi">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该订单吗？</p>
				<a href="Orders.do?action=del" id="yes">确定</a>
				<c:choose>
					<c:when test="${param.pageIndex==null}">
						<a href="Orders.do#" id="no">取消</a>
					</c:when>
					<c:when test="${param.pageIndex!=null}">
						<a href="Orders.do?pageIndex=${pages.currPageNo}#" id="no">取消</a>
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
