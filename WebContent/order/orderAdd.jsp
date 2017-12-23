<%@ page language="java" import="java.util.*,com.psm.model.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  <title>星星采购管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$("[value=手动生成]").click(function() {
			now = new Date();
			var year = now.getFullYear();
		    var mon = now.getMonth()+1;
		    var day = now.getDate();
		    var h = now.getHours();
		    var m = now.getMinutes();
		    var s = now.getSeconds();
		    var ms = now.getMilliseconds();
		    var sid = $("[name=sid]").val();
		    var eid = ${sessionScope.id};
		    ordertime = year +"-"+totwo(mon)+"-"+totwo(day)+' '+totwo(h)+":"+totwo(m)+":"+totwo(s)+"."+ms;
		    document.location.href = 'Orders.do?action=add&ordertime=' + ordertime + '&sid=' + sid + '&eid=' + eid;
		});
		$("[value=自动生成]").click(function() {
			now = new Date();
			var year = now.getFullYear();
		    var mon = now.getMonth()+1;
		    var day = now.getDate();
		    var h = now.getHours();
		    var m = now.getMinutes();
		    var s = now.getSeconds();
		    var ms = now.getMilliseconds();
		    var eid = ${sessionScope.id};
		    ordertime = year +"-"+totwo(mon)+"-"+totwo(day)+' '+totwo(h)+":"+totwo(m)+":"+totwo(s)+"."+ms;
		    document.location.href = 'Orders.do?action=autoadd&ordertime=' + ordertime + '&eid=' + eid;
		});
		$("[value=返回]").click(function(){
				history.back(-1);
		});
		$("[value=查询]").click(function() {
			var sname = $("[name=tigong]").val();
			var choice = $("[name=fukuan]").val();
			document.location.href = 'Orders.do?action=shaixuan&sname=' + sname + '&choice=' + choice;
		});
		$(".removeOgoods").click(function() {
			var id = $(this).parent("td").siblings(".id").text();
			document.location.href = 'Orders.do?action=delgfora&id=' + id;
		});
	});
</script>
<%session.setAttribute("goodsList", request.getAttribute("goodsList")); %>
  </head>
  
 <body>
<!--头部-->
<header class="publicHeader">
    <h1>星星采购管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> ${sessionScope.name}</span> , 欢迎你！</p>
        <a href="loginOut.do">退出</a>
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
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li id="active"><a href="Orders.do">账单管理</a></li>
                <li><a href="Suppliers.do">供应商管理</a></li>
                <li><a href="Goods.do">商品管理</a></li>
                <li><a href="Users.do">用户管理</a></li>
                <li><a href="emp/password.jsp">密码修改</a></li>
                <li><a href="loginOut.do">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面 >> 订单添加页面</span>
        </div>
        <div class="providerAdd">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <label>供应商名称：</label>
				<select name="sid">
					<c:forEach var="supplier" items="${suppliersminiList}">
						<option value="${supplier.id}">${supplier.name}</option>
					</c:forEach>
				</select>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="button" value="手动生成"/> 
                    <input type="button" value="返回"/> 
                </div>
        </div>
        <div class="providerAdd">
	        <div class="search">
				供应商：
				<select name="tigong">
					<option value="">--请选择--</option>
					<c:forEach var="supplier" items="${suppliersminiList}">
						<option value="${supplier.name}">${supplier.name}</option>
					</c:forEach>
				</select> 
				筛选条件：
				<select name="fukuan">
					<option value=">0">--请选择--</option>
					<option value="<10"><10</option>
					<option value="<5"><5</option>
				</select> 
				<input type="button" value="查询" />
			</div>
			<table class="providerTable" cellpadding="0" cellspacing="0">
				<tr class="firstTr">
					<th width="10%">商品编号</th>
					<th width="20%">商品名称</th>
					<th width="10%">商品进价</th>
					<th width="10%">供应商编码</th>
					<th width="10%">商品库存</th>
					<th width="10%">商品单位</th>
					<th width="20%">操作</th>
				</tr>
				<c:forEach var="good" items="${goodsList}">
					<tr>
						<td class="id">${good.id}</td>
						<td>${good.name }</td>
						<td>${good.price}</td>
						<td>${good.sid}</td>
						<td>${good.num}</td>
						<td>${good.spec}</td>
						<td>
							<a href="Goods.do?action=details&id=${good.id}">
								<img src="img/read.png" alt="查看" title="查看" />
							</a>
							<a class="removeOgoods">
								<img src="img/schu.png" alt="删除" title="删除" /> 
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="button" value="自动生成"/> 
                    <input type="button" value="返回"/> 
                </div>
		</div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>

</body>
</html>
