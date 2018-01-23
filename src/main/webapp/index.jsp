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
<title>index</title>
</head>
<body>

<video width="320" height="240" controls="controls">
  <source src="http://www.w3school.com.cn/i/movie.ogg" type="video/ogg">
  <source src="http://www.w3school.com.cn/i/movie.mp4
     " type="video/mp4">
Your browser does not support the video tag.
</video>
<shiro:guest>
欢迎游客访问，请<a href="${ctx}/login" >登录</a>
</shiro:guest>
	<h3>Welcome to here! ${user.username}</h3>
	<form action="getUserById" method="GET">
	ID:<input type="text" name="userId"> <br/>
	<input type="submit" value="提交">
	</form>
</body>

</html>