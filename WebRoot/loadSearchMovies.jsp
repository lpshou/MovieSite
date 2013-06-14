<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loadSearchMovies.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="style/styles.css" rel="stylesheet" type='text/css' />

  </head>
  
  <body>
    <div class="div_first">
					<struts:iterator value="#request['movies']" id="movie">
						<div class="movie_one">
							<img src="<struts:property value="#movie.miniImageUrl"/>" class="img" width="131" height="83"><br />
							电影名：<struts:property value="#movie.name" /><br />
							发行日期：<struts:property value="#movie.published_year" /><br />
							类型：<struts:property value="#movie.type" /><br />
							<div>
								<form action="movieDetails.action" method="post">
									<input type="hidden" name="movieID" value="<struts:property value="#movie.id"/>"/>
									<input type="submit" value="详情"/>
								</form>
							</div>
						</div>
					</struts:iterator>
				</div>
  </body>
</html>
