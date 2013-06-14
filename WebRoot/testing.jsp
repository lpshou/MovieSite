<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.movie.domain.*" %>
<%@ page import="com.movie.dao.*" %>
<%@ page import="com.movie.ceph.*" %>
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
    This is my JSP page. <br>
    <%
    	/*Movie movie=new Movie();
		movie.setName("test");
		movie.setPublished_year("test");
		movie.setType("test");
		movie.setDetails("test");
		movie.setVedioUrl("test");
		movie.setVedioSum(Integer.valueOf(1));
		movie.setImageUrl("test");
		movie.setImageSum(Integer.valueOf(1));
		//movieDAO.insertMovie(movie);
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(movie);
		movies.add(movie);
		movieDAO.insertMovies(movies);*/
		/*List<Movie>movies = movieDAO.getMovieByMovieKeyword("Love");
		System.out.println("movieName:"+movies.size());
		System.out.println("mvoiefirst: "+movies.get(0).getName());*/
		/*MoviePreference mp = new MoviePreference();
		moviePreferenceDAO.updateOrInsertRating(1,1193,5);
		mp=moviePreferenceDAO.getMoviePreference(1,1193);
		System.out.println("评分： "+mp.getPreference());*/
		
		/*String userID = request.getParameter("userID");
		String movieID = request.getParameter("movieID");
		String preference = request.getParameter("preference");
		System.out.println("userid: "+userID);
		System.out.println("movieid: "+movieID);
		System.out.println("preference: "+preference);
		moviePreferenceDAO.updateOrInsertRating(Integer.parseInt(userID),Integer.parseInt(movieID),Integer.parseInt(preference));
		PrintWriter writer = response.getWriter();
		writer.print("{msg:'success'}");*/
		
		/*List<MovieImage> mis = movieImageDAO.getMovieImageByMovieID(String.valueOf(1));
		for(int i=0 ;i<mis.size();i++){
		
		System.out.println("hah:  "+mis.get(i).getImageID());
		}

		System.out.println("mis: "+mis.size());*/
		
		cephDAO.imagesToDataBase();
		
		
		
     %>
     结sd束，，，，
  </body>
</html>
