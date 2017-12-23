<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$("[value=保存]").click(function() {
			now = new Date();
			var year = now.getFullYear();
		    var mon = now.getMonth()+1;
		    var day = now.getDate();
		    var h = now.getHours();
		    var m = now.getMinutes();
		    var s = now.getSeconds();
		    var ms = now.getMilliseconds();
		    submittime = year +"-"+totwo(mon)+"-"+totwo(day)+' '+totwo(h)+":"+totwo(m)+":"+totwo(s)+"."+ms;
		    document.location.href = 'Orders.do?action=save&submittime=' + submittime+'&id='+${order.id}+'&eid='+${sessionScope.id};
		});
		$(".removeOgoods").click(function() {
			var oid = ${order.id};
			var gid = $(this).parent("td").siblings(".gid").text();
			document.location.href = 'Orders.do?action=delgoods&gid='
				+ gid +'&oid='+oid;
		});

		$("[value=添加]").click(
			function() {
				var gid = $("[name=shangpin]").val();
				var oid = ${order.id};
				document.location.href = 'Orders.do?action=addgoods&gid='
						+ gid +'&oid='+oid;
		});
	});
	function cop(){
		var num = event.target.value;
		var gid = event.target.parentNode.parentNode.firstElementChild.innerHTML;
		document.location.href = 'Orders.do?action=updatenum&oid='+${order.id}+'&gid='+gid+'&num='+num;
	}
</script>
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
            <form action="Orders.do?action=save" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="search">
                    <label for="providerId">订单编码：</label>
                    <input type="text" name="billId" id="providerId" value="${order.id }" readonly="readonly"/>
                    <span>*不可修改</span>
                    <label for="fax">供应商：</label>
                    <input type="text" name="sname" id="address" value="${order.sname}" readonly="readonly" />
                    <span>*不可修改</span>
                </div>
                <div class="search">
                    <label>商品名称：</label>
					<select name="shangpin">
						<option value="">--请选择--</option>
						<c:forEach var="good" items="${goodsminiList}">
							<option value="${good.id}">${good.name}</option>
						</c:forEach>
					</select>
                    <input type="button" value="添加" />
                </div>
		        <table class="providerTable" cellpadding="0" cellspacing="0">
					<tr class="firstTr">
						<th width="10%">商品编号</th>
						<th width="20%">商品名称</th>
						<th width="10%">商品进格</th>
						<th width="10%">数量</th>
						<th width="10%">商品单位</th>
						<th width="10%">商品金额</th>
						<th width="20%">操作</th>
					</tr>
					<c:forEach var="good" items="${goodsList}">
						<tr>
							<td class="gid">${good.id}</td>
							<td>${good.name }</td>
							<td>${good.price}</td>
							<td><input type="text" onchange="cop()" value="${good.num}"/></td>
							<td>${good.spec}</td>
							<td>${good.price*good.num}</td>
							<td><a href="Orders.do?action=gooddetails&id=${good.id}">
									<img src="img/read.png" alt="查看" title="查看" /> 
								</a>
								<a class="removeOgoods">
									<img src="img/schu.png" alt="删除" title="删除" /> 
								</a>
							</td>
						</tr>
					</c:forEach>					
				</table>
				<table class="providerTable" cellpadding="0" cellspacing="0">
               		<tr>
               			<td width="80%"/>
               			<td width="10%">总金额：</td>
               			<td width="10%">${order.cost}</td>
               		</tr>
                </table>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="button" value="保存"  /> 
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>

</body>
</html>
