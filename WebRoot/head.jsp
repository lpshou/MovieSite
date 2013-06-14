<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>

    <style type="text/css">

    </style>
    <link href="style/styles.css" rel="stylesheet" />	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="script/my.js"></script>
	<link href="style/styles.css" rel="stylesheet" />
  </head>
  
  <body >
 <div id="head">
		<div id="head_first">
			<div id="head_img">
				<img src="image/Umovie.jpg" width="40px" height="40px" />
			</div>
			<div id="head_title">
				<h4>电影推荐</h4>
			</div>		
		</div>
	</div>
  </body>
</html>
