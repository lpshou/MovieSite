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
    <title>My JSP 'upLoadMovie.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="style/styles.css" type="text/css"></link>
	<script type="text/javascript" src="script/my.js"></script>
	<script type="text/javascript" src="script/jquery.js"></script>
 	<script type="text/javascript">

 	</script>
 	
 </head>
  
  <body>
  	<h3>电影上传</h3><hr/>
  		<div id="upload_all">
  			<struts:form action="upLoadMovie" method="post" enctype="multipart/form-data" theme="simple">
  				<div id="upload_summary">
  					电影名称：<struts:textfield name="movieName"  label="电影名" ></struts:textfield><br/>
    				发行时间：<struts:textfield name="moviePublishedYear"  label="发行时间"></struts:textfield><br/>
    				电影类型：<struts:textfield name="movieType"  label="电影类型"></struts:textfield><br/>
					描述信息：<struts:textarea name="movieDetails"  label="描述信息" cols="17" rows="3" ></struts:textarea><br/>
  				</div>
			
				<hr/>
				<input type="button" class="btn" value="添加图片" onclick="addImage()"/>
				<input type="hidden" name="imagesSum" id="imageSum" value="0"/>
				<div id="upload_imagesUpload" style="overflow-y:scroll"></div>
				
				<hr/>
				<input type="button" class="btn" value="添加影片" onclick="addMovie()"/>
				<input type="hidden" name="moviesSum" id="movieSum" value="0"/>
				<div id="upload_moviesUpload" ></div>
				
				<struts:submit value="上传" onclick="uploading()" ></struts:submit>
  			</struts:form>
  		</div>

  		<div id = "uploading" style="display:none">
  			<h2>文件上传中,不要关闭页面...</h2>
  		</div>
  
  </body>
</html>
