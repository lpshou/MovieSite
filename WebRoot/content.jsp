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
    
    <title>My JSP 'content.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--  <meta http-equiv="refresh" content="0;url=loadHotMovies.action">-->
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="script/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="style/styles.css">
	<script type="text/javascript">
		function a()
		{
			alert("haha");
		}s
		function b()
		{
			$.ajax({
			type:'POST',
			url:'loadHotMovies.action',
			data:'',
			success:function(){
				alert("success");
				},
			error:function(){
				alert("fail");
				}
			
			});
		}
	</script>
  </head>
  
  <body  onload= "b()" style="background-color:grey">
  	<h4>Testing......请先登录<br></br>测试帐号：test1@gmail.com <br/>无密码</h4>
  	 <div><h1>一些电影</h1></div>
		<div class="div_first">
   			<struts:iterator value="#request['hotmovies']" id="movie">
   				<div class="movie_one">
   					<img src="<struts:property value="#movie.key.miniImageUrl"/>"  width="144" height="98"><br/>
   					电影名：<struts:property value="#movie.key.name"/><br/>
   					出版日期：<struts:property value="#movie.key.published_year"/><br/>
   					类型：<struts:property value="#movie.key.type"/><br/>
   					评分：<struts:property value="#movie.value"/><br/>
   					<div>
						<form action="movieDetails.action" method="post">
							<input type="hidden" name="movieID" value="<struts:property value="#movie.key.id"/>"/>
							<input type="submit" value="详情"/>
						</form>
					</div>
   				</div>		
   			</struts:iterator>
   		</div>

    <hr/>
  </body>
</html>
