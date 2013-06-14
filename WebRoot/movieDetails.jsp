<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'movieDetails.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<script type="text/javascript" src="script/jquery.js"></script>
		<script type="text/javascript" src="script/jquery.raty.js"></script>	
		<script type="text/javascript" src="script/jquery.bxslider.min.js"></script>

		<link href="style/jquery.bxslider.css" rel="stylesheet" />	
		<link href="style/styles.css" rel="stylesheet" />	
		<script type="text/javascript">	
			$(function() {
					$('div#click').raty({
						hintList:['1分', '2分', '3分', '4分', '5分'],  // A hint information for default 5 stars.
			 			path:"images",  
						onClick: function(score) {
							//alert('score: ' + score);
							$("#hid").val(score);
							rating();
						}
					});
				});
			$(document).ready(function(){
 				 $('.bxslider').bxSlider();
				});
					
			function rating(){
				var preference = $.trim($("#hid").val());
				//alert(preference);
				var userId="<%=session.getAttribute("userID")%>"; 
   				//alert(userid); 
   				var movieId = "<%=session.getAttribute("movieID")%>";
   				//alert(movieId);
   				$.ajax({
   					url:'forRating.jsp',
   					type:'POST',
   					data:{userID:userId,movieID:movieId,preference:preference},
   					dataType:'json',
   					onSuccess:alert("成功评分,你的评分对推荐优化有着重要作用！")
   					});		
			}
			
			function addToFavorites(){
				var userId="<%=session.getAttribute("userID")%>"; 
   				//alert(userid); 
   				var movieId = "<%=session.getAttribute("movieID")%>";
   				//alert(movieId);
				$.ajax({
					url:'forAddToFavorites.jsp',
					type:'POST',
					data:{userID:userId,movieID:movieId},
					dataType:'json',
					onSuccess:alert("成功加入到个人收藏夹，您的收藏对推荐系统优化有着重要作用！")
					});
			}
		</script>
</head>
	<body>
		<div>
			<div id="movieDetails_img" >
				<h2>电影剧照展示</h2>
				<ul class="bxslider">
	   		 		<struts:iterator value="#request['imageUrls']" id="imageurl">
	   		 			<li><div class="image_movieDetails"><img src="<struts:property value="#imageurl"/>" width="100%" height="100%" ></div></li>
	   				 </struts:iterator>
				</ul>
			</div>
			<div class="div_left">
				电影名：<struts:property value="#request['movieDetails'].name" /><br />
				出版日期：<struts:property value="#request['movieDetails'].published_year" /><br />
				类型：<struts:property value="#request['movieDetails'].type" /><br />
				详细介绍：<struts:property value="#request['movieDetails'].details" /><br />
				<a href="<struts:property value="#request['vedioUrl']" />">下载影片</a>
			</div>
			<div class="div_right">
				<div class="session">评分：</div>
				<struts:hidden name="hidden" id="hid"></struts:hidden>
				<div id="click"></div>
				<div>
					<a href="javascript:void(0)" onclick="addToFavorites()">加入收藏</a>
				</div>
			</div>	
		</div>	
	</body>
</html>
