<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!(empty errmsg)}">
	<script type="text/javascript">
		alert("${errmsg}");
	</script>
</c:if> 
<html>
  <head>
    
       <title>星星采购管理系统</title>
    <link rel="stylesheet" href="css/style.css"/>
	<style type="text/css">
	.err{
	margin:500px,100px;
	padding:500px,100px;
	color:red;
	}
	</style>
  </head>
  
  <body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>星星采购管理系统</h1>
        </header>
        <section class="loginCont">
            <form class="loginForm" action="login.do" method="post">
                <div class="inputbox">
                    <label for="user">用户名：</label>
                    <input id="user" type="text" name="empid" placeholder="请输入用户名" required/>
                </div>
                <div class="inputbox">
                    <label for="mima">密码：</label>
                    <input id="mima" type="password" name="password" placeholder="请输入密码" required/>
                </div>
                <div class="subBtn">
                    <input type="submit" value="登录" />
                    <input type="reset" value="重置"/>
                </div>

            </form>
        </section>
    </section>

</body>
</html>