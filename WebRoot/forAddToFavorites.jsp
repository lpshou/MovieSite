<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "com.movie.dao.movieFavoriteDAO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'forAddToFavorites.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	String userID = request.getParameter("userID");
		String movieID = request.getParameter("movieID");
		System.out.println("userid: "+userID);
		System.out.println("movieid: "+movieID);
		movieFavoriteDAO.addToMovieFavorite(Integer.valueOf(userID), Integer.valueOf(movieID));
     %>
  </body>
</html>
