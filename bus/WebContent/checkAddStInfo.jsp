<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
String addStid = request.getParameter("stname") == null ? "" : request.getParameter("stname");
String busNum = request.getParameter("selectBusNum") == null ? "" : request.getParameter("selectBusNum");
String beginStid = request.getParameter("beginSt") == null ? "" : request.getParameter("beginSt");
String endStid = request.getParameter("endSt") == null ? "" : request.getParameter("endSt");
String stCount = request.getParameter("stCount") == null ? "" : request.getParameter("stCount");
ModifyBusNum modifyBusNum = new ModifyBusNum();
boolean success = modifyBusNum.addStInfo(addStid,busNum,beginStid,endStid,stCount); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>
<script type="text/javascript">
<%
if (success) {%>
	alert("添加站点成功！");
	window.location.href = "addStInfo.jsp";
<%} else {%>
	alert("添加站点失败！");
	window.location.href = "addStInfo.jsp";
<%}
%>
</script>
<body>

</body>
</html>
