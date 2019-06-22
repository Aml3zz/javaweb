<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%
ArrayList arrayList = new ArrayList();
QueryData queryData = new QueryData();
String stname = request.getParameter("stname") == null ? "" :request.getParameter("stname");
arrayList = queryData.checkSt(stname);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站点库</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
</head>
    <script language="javascript">
	function convertValue(para,stid) {
		window.opener.getValueByCheckStWindows(para,stid);
		window.close();
	}
</script>
<body>
<table width="300" border="1" cellpadding="1" cellspacing="0" align="center" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#0082BF">
  <tr>
    <td align="center"><font color="#FF0000">站点ID号</font></td>
    <td align="center"><font color="#FF0000">站点名称</font></td>
  </tr>
  <%if (!arrayList.isEmpty()) {
  	for (int i = 0;i < arrayList.size();i++) {
  		HashMap hashMap = new HashMap();
  		hashMap = (HashMap) arrayList.get(i);%>
  	<tr>
    <td align="center"><%=hashMap.get("stid") %></td>
    <td align="center"><a href = "javascript:convertValue('<%=hashMap.get("stname")%>','<%=hashMap.get("stid")%>')"><%=hashMap.get("stname") %></a></td>
  	</tr>
  	<%}
  } %>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
