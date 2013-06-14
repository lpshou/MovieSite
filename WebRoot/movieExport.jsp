<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page language="java" import="com.movie.dao.*"  %>
<%@page language="java" import="com.movie.domain.*" %>
<%@page language="java" import="java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'movieExport.jsp' starting page</title>
    
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
  	  /*  //导出电影信息 
  	   movieDAO md = new movieDAO();
		List<Movie>movies = new ArrayList<Movie>();
		movies=md.getAllMovies();
		FileWriter out1 = new FileWriter("/ttserver/movies.dat");
		for(Movie movie:movies){
			String ip="192.168.0.19";
			String line ="";
			line=movie.getId()+"\t"+movie.getName()+"\t"+movie.getPublished_year()
				+"\t"+ip+"\t"+movie.getVedioUrl()
				+"\t"+movie.getDetails()+"\t"+movie.getMiniImageUrl()+"\n";
			System.out.println(line);
			out1.append(line);
			out1.flush();//全部冲到文件中
		}
		//导出用户信息
		userDAO ud = new userDAO();
		List<User>users = new ArrayList<User>();
		users=ud.getAllUsers();
		FileWriter out2 = new FileWriter("/ttserver/users.dat");
		for(User user:users){
			String line2 = "";
			line2 = user.getId()+"\t"+user.getName()+"\t"+user.getEmail()+"\n";
			System.out.println(line2);
			out2.append(line2);
			out2.flush();
		}
		//导出评分信息
		moviePreferenceDAO mpd = new moviePreferenceDAO();
		List<MoviePreference> mps= new ArrayList<MoviePreference>();
		mps=mpd.getAllMoviePreferences();
		FileWriter out3 = new FileWriter("/ttserver/preferences.dat");
		for(MoviePreference mp: mps){
			String line3 = "";
			line3=mp.getUserID()+"\t"+mp.getMovieID()+"\t"+mp.getPreference()+"\t"+mp.getTimestamp()+"\n";
			System.out.println(line3);
			out3.append(line3);
			out3.flush();
		}*/
		//导出电影图片信息
		movieImageDAO mid = new movieImageDAO();
		List<MovieImage> mis = new ArrayList<MovieImage>();
		mis = mid.getAllMovieImage();
		FileWriter out4 = new FileWriter("/ttserver/movieimages.dat");
		for(MovieImage mi:mis){
			String line4 ="";
			line4=mi.getMovieID()+"\t"+mi.getImageID()+"\t"+mi.getImageName()+"\t"+mi.getImageDetails()+"\n";
			System.out.println(line4);
			out4.append(line4);
			out4.flush();
		}
		
		
		
    %>
  </body>
</html>
