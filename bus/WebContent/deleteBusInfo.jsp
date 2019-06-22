<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.busSystem.util.*" %>
<%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {
	response.sendRedirect("adminLogin.jsp");
} %>
<%
ArrayList arrayList = new ArrayList();
QueryData queryData = new QueryData();
queryData.countBusInfo("");
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
//arrayList = queryData.queryBusNumDetail("");
arrayList = queryData.statisticsByBusInfo("",currentPage);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除车次信息</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
</head>

<body>
<table width = "100%" border = "0" cellpadding = "0" cellspacing = "0">
	<tr>
		<td width = "100" height = "25" align = "center" valign="middle" bgcolor="#0082BF"><strong>删除车次</strong></td>
    	<td width="4" bgcolor="#F0F7FD"></td>
		<td width = "100" height = "25" align = "center" valign="middle"><a href = "deleteStInfo.jsp"><strong>删除站点</strong></a></td>
		<td width="4" bgcolor="#F0F7FD"></td>
    	<td align="center" valign="middle" bgcolor="#F0F7FD"></td>
	</tr>
	<tr>
   		<td height="5" bgcolor="#0082BF" colspan="11"></td>
	</tr>
</table>
<table width="768" border="1" align="center" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#0082BF">
  <tr bgcolor="#DBECF8">
    <td align="center">车号</td>
    <td align="center">起点站</td>
    <td align="center">终点站</td>
    <td align="center">票价</td>
    <td align="center">车辆等级</td>
    <td align="center">票价等级</td>
    <td align="center">操作</td>
  </tr>
  <%if (!arrayList.isEmpty() && arrayList.size() > 0) {
  	for (int i = 0;i < arrayList.size();i++) {
  		HashMap hashMap = new HashMap();
  		hashMap = (HashMap) arrayList.get(i);%>
  <tr>
    <td align="center"><%=hashMap.get("BusNum") %></td>
    <td align="center"><%=hashMap.get("BeginSt") %></td>
    <td align="center"><%=hashMap.get("EndSt") %></td>
    <td align="center"><%=hashMap.get("TicketNote") %></td>
    <td align="center"><%=hashMap.get("BusLevel") %></td>
    <td align="center"><%=hashMap.get("Note") %></td>
    <td align="center"><a href="checkDeleteBusInfo.jsp?busNum=<%=hashMap.get("BusNum")%>">删除</a></td>
  </tr>
  	<%}
  } %>
  <tr>
  	<td colspan = "7">总记录数：<%=count %>&nbsp;&nbsp;总页数：<%=pageCount %>&nbsp;&nbsp;
  	当前页：<%=currentPage%>&nbsp;&nbsp;<%if (pages == 1) {%>首页<%}else {%><a href="deleteBusInfo.jsp?pages=1">首页</a><%}%>
  	&nbsp;&nbsp;<%if (currentPage > 1) {%><a href="deleteBusInfo.jsp?pages=<%=currentPage-1%>">上一页</a><%}
  	else {%>上一页<%}%>&nbsp;&nbsp;<%if (currentPage < pageCount) { %><a href="deleteBusInfo.jsp?pages=<%=currentPage+1%>">下一页</a><%} else{%>
  	下一页<%}%>&nbsp;&nbsp;<%if (currentPage == pageCount || currentPage > pageCount) {%>末页<%} else {%><a href="deleteBusInfo.jsp?pages=<%=pageCount%>">末页</a><%}%>
  	</td>
  </tr>
</table>
</body>
</html>
