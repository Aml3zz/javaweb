<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.busSystem.util.*" %>
<%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {
	response.sendRedirect("adminLogin.jsp");
} %>
<%
String username = (String) session.getAttribute("userName");
ArrayList arrayList = new ArrayList();
QueryData queryData = new QueryData();
queryData.countUserInfo();
int count = queryData.getCount();
int pageCount = queryData.getPageCount();
int currentPage = 1;
int pages;
if (request.getParameter("pages") == null || request.getParameter("pages").equals("")) {
	pages = 1;
} else {
	pages = new Integer(request.getParameter("pages")).intValue();
}
if (currentPage > pageCount || currentPage == 0) {
	currentPage = 1;
} else {
currentPage = pages;
}
arrayList = queryData.statisticsByUserInfo(currentPage);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="css/my_domain/main.css" type="text/css" rel="stylesheet">
</head>

<body>
<table width="768" border="1" align="center" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#0082BF">
  <tr bgcolor="#DBECF8">
    <td align="center">用户名</td>
    <td align="center">用户密码</td>
    <td align="center">用户年龄</td>
    <td align="center">地址</td>
    <td align="center">Email</td>
	<td align="center">手机号码</td>
	<td align="center">操作</td>

  </tr>
   <%if (!arrayList.isEmpty() && arrayList.size() > 0) {
  	for (int i = 0;i < arrayList.size();i++) {
  		HashMap hashMap = new HashMap();
  		hashMap = (HashMap) arrayList.get(i);%>
  <tr>
    <td align="center"><%=hashMap.get("username") %></td>
    <td align="center"><%=hashMap.get("userpassword") %></td>
    <td align="center"><%=hashMap.get("userage") %></td>
    <td align="center"><%=hashMap.get("address") %></td>
    <td align="center"><%=hashMap.get("email") %></td>

	<td align="center"><%=hashMap.get("idnum")%></td>
	<td align="center"><a href="checkDeleteUserInfo.jsp?username=<%=hashMap.get("username")%>">删除</a></td>
  </tr>
  <%}} else {%>
  <tr>
    <td colspan="9" align="center"><font color="#FF0000">没有此信息内容!</font></td>
  </tr>
   <%}%>
  <tr>
    <td colspan = "9">总记录数：<%=count %>&nbsp;总页数：<%=pageCount %>&nbsp;
  	当前页：<%=currentPage%>&nbsp;&nbsp;<%if (pages == 1) {%>首页<%}else {%><a href="deleteUserInfo.jsp?pages=1">首页</a><%}%>
  	&nbsp;&nbsp;<%if (currentPage > 1) {%><a href="deleteMessage.jsp?pages=<%=currentPage-1%>">上一页</a><%}
  	else {%>上一页<%}%>&nbsp;&nbsp;<%if (currentPage < pageCount) { %><a href="deleteUserInfo.jsp?pages=<%=currentPage+1%>">下一页</a><%} else{%>
  	下一页<%}%>&nbsp;&nbsp;<%if (currentPage == pageCount || currentPage > pageCount) {%>末页<%} else {%><a href="deleteUserInfo.jsp?pages=<%=pageCount%>">末页</a><%}%>
  	</td>
  </tr>
</table>
</body>
</html>
