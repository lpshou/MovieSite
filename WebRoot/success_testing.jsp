<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.movie.domain.*" %>
<%@ page import="com.movie.dao.*" %>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="com.opensymphony.xwork2.ActionSupport"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stccess_testing.jsp' starting page</title>
    
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
   <h1>这里算是通过了，继续加油!!!</h1>
	 <%
   		Movie movie =(Movie)session.getAttribute("movie");
   		if(movie!=null){
   				movieDAO.insertMovie(movie);
   			}
   		System.out.println("缩略图: "+movie.getMiniImageUrl());
    %>
    <%
    /*
    	MovieList movieList = moviePreferenceDAO.getMoviesByPreference();
		Map<Movie ,Float>movies = movieList.getMovies();
		System.out.println(movies.size());
	*/
     %>


  </body>
</html>
