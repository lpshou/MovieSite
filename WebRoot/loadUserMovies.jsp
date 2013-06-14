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
		<title>loadUserMovies</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
	 <script type="text/javascript" src="script/jquery.js"></script>
     <link href="style/tabbedContent.css" rel="stylesheet" type='text/css' />
     <link href="style/styles.css" rel="stylesheet" type='text/css' />
     <script src="script/tabbedContent.js" type="text/javascript"></script>
     <script type="text/javascript">
     </script>
	</head>

<body class="body2">
<div class='tabbed_content'>
    <div class='tabs'>
        <div class='moving_bg'> </div>
        <span class='tab_item'> 评分的电影</span>
        <span class='tab_item'>收藏的电影</span>
        <span class='tab_item'> 上传的电影</span>
        <span class='tab_item'> 个人信息</span>
    </div>
    <div class='slide_content'>
        <div class='tabslider'>
            <!-- content goes here -->
            <ul><li>
				<div class="div_first">
					<struts:iterator value="#request['movies']" id="movie">
						<div class="movie_one">
							<img src="<struts:property value="#movie.key.miniImageUrl"/>" class="img" width="131" height="83"><br />

							电影名：<struts:property value="#movie.key.name" /><br />
							出版日期：<struts:property value="#movie.key.published_year" /><br />
							类型：<struts:property value="#movie.key.type" /><br />
							评分：<struts:property value="#movie.value" /><br />
							<div>
								<form action="movieDetails.action" method="post">
									<input type="hidden" name="movieID" value="<struts:property value="#movie.key.id"/>"/>
									<input type="submit" value="详情"/>
								</form>
							</div>
						</div>
					</struts:iterator>
				</div>
            </li></ul>
            
			<ul><li> 
					<div class="div_first">
					<struts:iterator value="#request['favoriteMovies']" id="movie">
						<div class="movie_one">
							<img src="<struts:property value="#movie.miniImageUrl"/>" class="img" width="131" height="83"><br />
							电影名：<struts:property value="#movie.name" /><br />
							出版日期：<struts:property value="#movie.published_year" /><br />
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
			 </li></ul>
			 
			<ul><li> 待完善... </li></ul>
			
			<ul><li>
				<div>
					<struts:iterator value="#request['user']" id="user">
						<div id="loadUserMovies_head_left">
							<img src="images/head_icon.jpg" class="img" width="131" height="100"/>
						</div>
						<div id="loadUserMovies_head_right">
							<b>用 户 名:</b><struts:property value="#user.name"/><br/>
							<b>注册邮箱：</b><struts:property value="#user.email"/><br/>
							<b>其    他：</b>for Testing...<br/>
						
						</div>
					</struts:iterator>
				</div>
			</li></ul>
			
        </div>
    </div>
</div>
	</body>
</html>
