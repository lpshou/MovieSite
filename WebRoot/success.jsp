<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
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
  	<h2>欢迎登录</h2><br/>
       用户：<struts:property value="email"/>
       <a href="./login.jsp">注销</a><br/>
       <hr/>
       <div id="success_login">
       	<form action="searchByKeyWord.action" method="post" target = "destination">
       		关键字:<struts:textfield name="keyword"></struts:textfield>
       		<struts:submit value="检索"></struts:submit>
       	</form>
       	<a href="#" target= "destination"></a>
    	 <a href="loadUserMovies.action" target="destination">我的电影</a><br/>
    	 <a href="loadRecommendMovies.action" target="destination">推荐电影</a><br/>
    	 <a href="loadHotMovies.action" target="destination">评分较高电影推荐</a><br/>
    	 <a href="upLoadMovie.jsp" target="destination">上传影片</a>
       </div>
       
  </body>
</html>
