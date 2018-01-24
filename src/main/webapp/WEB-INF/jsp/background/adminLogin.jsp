<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<link rel="shortcut icon" href="${ctx }/static/ico/bitbug_favicon.ico">
<style type="text/css">
body {
	background: url(static/img/background.jpg) no-repeat;
	background-size: cover;
	font-size: 16px;
}

.form {
	background: rgba(255, 255, 255, 0.6);
	width: 400px;
	margin: 100px auto;
}

#login_form {
	display: block;
}

#register_form {
	display: none;
}

.fa {
	display: inline-block;
	top: 27px;
	left: 6px;
	position: relative;
	color: #ccc;
}

input[type="text"], input[type="password"] {
	padding-left: 26px;
}

.checkbox {
	padding-left: 21px;
}
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台管理</title>
</head>
<body>
<shiro:user>
欢迎<shiro:principal>登录</shiro:principal>
</shiro:user>
<shiro:authenticated>用户<shiro:principal/>通过验证</shiro:authenticated>
<shiro:notAuthenticated>未通过验证</shiro:notAuthenticated>
<shiro:hasRole name="admin"> 拥有角色admin</shiro:hasRole> 
<shiro:lacksRole name="admin"> 没有角色admin </shiro:lacksRole>
<shiro:hasPermission name="insert"> 拥有权限inert  </shiro:hasPermission>
<shiro:lacksPermission name="insert"> 没有权限insert  </shiro:lacksPermission>

	<div class="container">
		<div class="form row">
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3"
				id="login_form" action="/login" method="POST">
				<h3 class="form-title">Login to your account</h3>
				<small><span class="text-danger">${message}</span></small>
				<span class="text-warning">当前在线人数：${sesessionCount}</span>
				<span class="text-warning">最近登录时间：${lastTime}</span>
				<h1 class="text_danger">${user}</h1>
				<div class="col-sm-9 col-md-9">
					<div class="form-group">
						<i class="fa fa-user fa-lg"><span
							class="glyphicon glyphicon-user"></span></i> <input
							class="form-control required" type="text" placeholder="账号"
							name="account" autofocus="autofocus" maxlength="20" required />
					</div>
					<div class="form-group">
						<i class="fa fa-lock fa-lg"><span
							class="glyphicon glyphicon-lock"></span></i> <input
							class="form-control required" type="password" placeholder="密码"
							name="password" maxlength="8" required />
					</div>	
					<div class="form-group">					
						<input type="submit" class="btn btn-success"
							value="登录 " />
							<input type="reset" class="btn btn-success "
							value="重置 " />
					</div>
				</div>
			</form>
		</div>
</body>
<script type="text/javascript">
$('#captchaImage').click(function() 
		{
		  $('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
		  
		});
$(function(){
	var href=location.href;  
	   if(href.indexOf("kickout")>0){  
	       alert("您的账号在另一台设备上登录，您被挤下线，若不是您本人操作，请立即修改密码！");  
	   }   
});
   
</script>
</html>