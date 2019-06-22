<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
String busNum = request.getParameter("BusNum") == null ? "" : request.getParameter("BusNum");
String beginSt = request.getParameter("beginSt") == null ? "" : request.getParameter("beginSt");
String endSt = request.getParameter("endSt") == null ? "" : request.getParameter("endSt");
String priceNote = request.getParameter("selectPrice") == null ? "" : request.getParameter("selectPrice");
String busLevel = request.getParameter("selectLevel") == null ? "" : request.getParameter("selectLevel");
String priceLevel = request.getParameter("selectNote") == null ? "" : request.getParameter("selectNote");
//String storder = request.getParameter("storder") == null ? "" : request.getParameter("storder");
UpdateBusInfo updateBusInfo = new UpdateBusInfo();
boolean success = updateBusInfo.updateBusInfo(busNum,beginSt,endSt,priceNote,busLevel,priceLevel);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新车次</title>
</head>
<script type="text/javascript">
<%
if (success) {%>
	alert("更新车次成功！");
	window.location.href = "updateBusInfo2.jsp";
<%}else {%>
	alert("更新车次失败！");
	window.location.href = "updateBusInfo2.jsp";
<%}
%>
</script>
<body>

</body>
</html>
