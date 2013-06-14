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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="style/styles.css" rel="stylesheet" />
  </head>
  
<body ">
	<div id="login_first">
    	<h2>用户登录</h2><hr/>
		<struts:form action="loginAction" method="post">
			<struts:textfield name="email" label="帐号"></struts:textfield>
			<struts:password name="password" label="密码"></struts:password>
			<struts:submit value="登录"></struts:submit>

		</struts:form>
	</div>
</body>
</html>
