<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function save() {
		now = new Date();
		var year = now.getFullYear();
		var mon = now.getMonth()+1;
		var day = now.getDate();
		var h = now.getHours();
		var m = now.getMinutes();
		var s = now.getSeconds();
		var ms = now.getMilliseconds();
		var obj = document.getElementById('checkresult');
		var index = obj.selectedIndex;
		var checkresult = obj.options[index].value;
		var checksuggest = document.getElementById('checksuggest').value;
		var cid = "${sessionScope.id}";
		var cname = "${sessionScope.name}";
		checktime = year +"-"+totwo(mon)+"-"+totwo(day)+' '+totwo(h)+":"+totwo(m)+":"+totwo(s)+"."+ms;
		document.location.href = 'Orders.do?action=check&id='+${order.id} + '&checkresult=' + checkresult + '&checksuggest=' 
				+ checksuggest + '&checktime=' + checktime + '&cid=' + cid + '&cname=' + cname;
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
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            
            <table class="providerTable" cellpadding="0" cellspacing="0">
            	<tr class="firstTr">
            		<th width="10%">订单编码：</th>
            		<th width="10%">${order.id}</th>
            		<th width="10%">订货时间：</th>
            		<th width="20%">${order.ordertime}</th>
            		<th width="10%">采购员：</th>
            		<th width="10%">${order.ename}</th>
            	</tr>            	
            </table>
            <table class="providerTable" cellpadding="0" cellspacing="0">
            	<tr class="firstTr">
            		<th width="15%">供应商编码：</th>
            		<th width="20%">${order.sid}</th>
            		<th width="15%">供应商名字：</th>
            		<th width="30%">${order.sname}</th>
            	</tr>
            </table>
            <table class="providerTable" cellpadding="0" cellspacing="0">
            	<tr class="firstTr">
            		<th width="15%">单位地址：</th>
            		<th width="40%">${order.saddr}</th>
            		<th width="15%">邮政编码：</th>
            		<th width="10%">${order.spostcode}</th>
            	</tr>
            </table>
            <table class="providerTable" cellpadding="0" cellspacing="0">
				<tr class="firstTr">
					<th width="10%">商品编码</th>
					<th width="20%">商品名称</th>
					<th width="15%">商品单位</th>
					<th width="15%">商品型号</th>
					<th width="10%">商品数量</th>
					<th width="10%">商品价格</th>
				</tr>
		        <c:forEach var="good" items="${goodsList}">
					<tr>
						<td>${good.id }</td>
						<td>${good.name }</td>
						<td>${good.spec}</td>
						<td>${good.model}</td>
						<td>${good.num}</td>
						<td>${good.price}</td>
					</tr>
				</c:forEach>
			</table>
            <table class="providerTable" cellpadding="0" cellspacing="0">
            	<tr class="firstTr">
            		<th width="10%">提交时间：</th>
            		<th width="20%">${order.submittime}</th>
            		<th width="40%"/>
            		<th width="10%">总金额：</th>
            		<th width="10%">${order.cost}元</th>
            	</tr>
            </table>
	        <div class="search">
			    <label>审核结果：</label>
				<select id="checkresult" name="checkresult">
					<option value="未审核">--未审核--</option>
					<option value="不通过">不通过</option>
					<option value="已通过">已通过</option>
				</select>
		        <p>审核意见：</p>
		        <textarea id="checksuggest" name="checksuggest" rows="7" cols="100" ></textarea>
	            </div>
	            
	            <br><br><br><br><br><br><br>
            <a onclick="save()">保存</a><br><br>
            <a href="javascript:void(0);" onclick="history.back(-1)">返回</a>
            <br><br><br><br><br><br><br>
        </div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="js/time.js"></script>

</body>
</html>