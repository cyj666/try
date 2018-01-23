<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<link rel="stylesheet" href="${ctx}/static/css/login.css">
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<link rel="icon" href="${ctx}/static/ico/bitbug_favicon.ico" type="image/x-icon">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
</head>
<body>
<!-- 代码 开始 -->
<div id="login">
    <div class="wrapper">
        <div class="login">
            <form action="login" method="post" class="container offset1 loginform">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad">
                <h5 class="text-danger">${message}</h5>
                    <input type="hidden" name="_csrf" value="9IAtUxV2CatyxHiK2LxzOsT6wtBE6h8BpzOmk=">
                    <div class="control-group">
                        <div class="controls">
                            <label for="account" class="control-label fa fa-user"></label>
                            <input id="account" type="text" name="account" placeholder="账号" tabindex="1" autofocus="autofocus" class="form-control input-medium" required>
                        </div>
                    </div>
                    <!--  <div class="control-group hidden">
                        <div class="controls">
                            <label for="email" class="control-label fa fa-envelope"></label>
                            <input id="email" type="email" name="email" placeholder="Email" tabindex="1" autofocus="autofocus" class="form-control input-medium" required>
                        </div>
                    </div>-->
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label fa fa-lock"></label>
                            <input id="password" type="password" name="password" placeholder="密码" tabindex="2" class="form-control input-medium" required>
                        </div>
                    </div>
                  <!--  <div class="control-group hidden">
                        <div class="controls">
                            <label for="captcha" class="control-label fa fa-lock" hidden></label>
                            <input id="captcha" type="text" name="captcha" placeholder="验证码" tabindex="2" class="form-control input-medium">
                        </div>
                    </div>-->
                </div>
                <div class="form-actions">
                <a href="#" tabindex="5" class="btn pull-left btn-link text-muted">忘记密码?</a>
                <a href="register" tabindex="6" class="btn btn-link text-muted">注册</a>
                    <button type="submit" tabindex="4" class="btn btn-primary">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 代码 结束 -->
</body>
<script type="text/javascript">
$(function() {

    $('#login #password').focus(function() {
        $('#owl-login').addClass('password');
    }).blur(function() {
        $('#owl-login').removeClass('password');
    });
});
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