<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
 <script type="text/javascript" src="js/checkform.js"></script>
<script type="text/javascript">

	$(function() {
		$("[value=保存]").click(function() {
		if(checkinput()){
			$("form").submit();
			}
		});
	});
</script>
 </head>
  
  <body>
<!--头部-->
<header class="publicHeader">
    <h1>星星采购管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b">${sessionScope.name}</span> , 欢迎你！</p>
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
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="Orders.do?pageIndex=1">账单管理</a></li>
			<li id="active"><a href="Suppliers.do?pageIndex=1">供应商管理</a></li>
			<li><a href="Goods.do?pageIndex=1">商品管理</a></li>
			<li><a href="Users.do?pageIndex=1">用户管理</a></li>
			<li><a href="emp/password.jsp">密码修改</a></li>
			<li><a href="loginOut.do">退出系统</a></li></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span><a href="Suppliers.do?pageIndex=1">供应商管理页面</a>>> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="Suppliers.do?action=add" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="id">供应商编码：</label>
                    <input type="text" name="id" id="id"/>
                    <span>*请输入供应商编码</span>
                </div>
                <div>
                    <label for="name">供应商名称：</label>
                    <input type="text" name="name" id="name"/>
                    <span >*请输入供应商名称</span>
                </div>
                <div>
                    <label for="agent">联系人：</label>
                    <input type="text" name="agent" id="agent"/>
                    <span>*请输入联系人</span>

                </div>
                <div>
                    <label for="phone">联系电话：</label>
                    <input type="text" name="phone" id="phone"  maxLength="11" />
                    <span>*请输入联系电话</span>
                </div>
                <div>
                    <label for="address">联系地址：</label>
                    <input type="text" name="address" id="address"/>
                    <span></span>
                </div>
                <div>
                    <label for="postcode">邮政编码：</label>
                    <input type="text" name="postcode" id="postcode"/>
                    <span></span>
                </div>
                <div>
                    <label for="bank">银行：</label>
                    <input type="text" name="bank" id="bank"/>
                </div>
                <div>
                    <label for="account">账号：</label>
                    <input type="text" name="account" id="account"/>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="providerList.html">返回</a>-->
                    <input type="button" value="保存" />
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