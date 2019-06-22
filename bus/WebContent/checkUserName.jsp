<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%
ArrayList arrayList = new ArrayList();
QueryData queryData = new QueryData();
String username = request.getParameter("username") == null ? "" :request.getParameter("username");
arrayList = queryData.checkUserName(username);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户名存在查询</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
</head>
<script type="text/javascript" src="scripts/function.js"></script>
<body>
<table width="300" border="1" cellpadding="1" cellspacing="0" align="center" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#0082BF">
  <tr>
    
    <td><font color="#FF0000">用户名称</font></td>
  </tr>
  <%if (!arrayList.isEmpty()) {
  	for (int i = 0;i < arrayList.size();i++) {
  		HashMap hashMap = new HashMap();
  		hashMap = (HashMap) arrayList.get(i);%>
  	<tr>
    <td ><a href = "javascript:convertValue('<%=hashMap.get("username")%>')"><%=hashMap.get("username") %></a></td>
  	</tr>
  	<tr><td>对不起,此用户已注册!</td></tr>
  	<%}
  } 
  else {
  %><tr><td>此用户名没被注册!</td></tr><%
  }
  %>
  <tr>
   <td> <input type="button" value="关闭窗口" onClick="javascript:window.close()"></td>
  </tr>
</table>
</body>
</html>
