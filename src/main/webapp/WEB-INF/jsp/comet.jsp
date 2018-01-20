<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ctx = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=ctx%>/static/js/comet4j.js"></script>

</head>
<body>
	Time：
	<span id="kb">...</span>
	聊天内容<p style="border:solid black 1px;">...</p>
	<input type="text"><button class="submit">发送</button>
	<button class="start">开始</button>
	<button class="end">结束</button>
</body>
<script type="text/javascript">
	function init() {
		var kbDom = document.getElementById('kb');
		// 监听后台某个频道
		JS.Engine.on({
			// 对应服务端 “频道1”(也就是频道名hello) 的值 result1
			hello : function(kb) {//侦听一个channel
				kbDom.innerHTML = kb;
			}
			/*start : function(cId, channelList, engine){
		      alert('连接已建立，连接ID为：' + cId);
		    },
		    stop : function(cause, cId, url, engine){
		      alert('连接已断开，连接ID为：' + cId + ',断开原因：' + cause + ',断开的连接地址：'+ url);
		    }*/
		});
		 // 建立连接，conn 即web.xml中 CometServlet的<url-pattern>
		JS.Engine.start('conn');
		//JS.Engine.stop('主动断开');
	}
	$(function(){
		//init();
	});
	$(".start").click(function(){
		//JS.Engine.start('conn');
		init();
	});
	$(".end").click(function(){
		JS.Engine.stop('主动断开');
	});
	$('.submit').click(function(){
		//alert($(':text').val());
		$.ajax({
		    url:'test?value='+$(':text').val(),
		    type:'GET', //GET
		    async:true,    //或false,是否异步
		    //data:{value=$(':text').val()},
		    processData : false, //告诉jQuery不要处理发送的数据
			contentType : false, //告诉jQuery去设置Content-Type请求头
		    timeout:5000,    //超时时间
		   // dataType:'json'    //返回的数据格式：json/xml/html/script/jsonp/text  
		})
	});
</script>
</html>