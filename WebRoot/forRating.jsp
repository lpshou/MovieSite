<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.movie.domain.MoviePreference" %>
<%@ page import="com.movie.dao.moviePreferenceDAO" %>
<%@ page import="java.io.PrintWriter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'testing.jsp' starting page</title>
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
		String preference = request.getParameter("preference");
		System.out.println("userid: "+userID);
		System.out.println("movieid: "+movieID);
		System.out.println("preference: "+preference);
		moviePreferenceDAO.updateOrInsertRating(Integer.parseInt(userID),Integer.parseInt(movieID),Integer.parseInt(preference));
		//PrintWriter writer = response.getWriter();
		//writer.print("{msg:'success'}");	
     %>
  </body>
</html>
