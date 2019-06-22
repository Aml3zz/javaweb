<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<% 
String stid = request.getParameter("stid") == null ? "" : request.getParameter("stid");
String busnum = request.getParameter("busnum") == null ? "" : request.getParameter("busnum");
String storder = request.getParameter("storder") == null ? "" : request.getParameter("storder");
String selectStid = request.getParameter("selectStname") == null ? "" : request.getParameter("selectStname");
QueryData queryData = new QueryData();
ArrayList arrayList = new ArrayList();
arrayList = queryData.querySt();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站点更新</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
<script type="text/javascript" language = "javascript">
function doSubmit() {
	document.form1.doFlag.value = "update";
	document.form1.submit();
}
</script>
</head>
<% 
String doFlag = request.getParameter("doFlag") == null ? "" : request.getParameter("doFlag"); //获得页面参数("update"这个字符串)
if (doFlag != null && doFlag.trim().equals("update")) {
	UpdateBusInfo updateBusInfo = new UpdateBusInfo();
	boolean success = updateBusInfo.updateBusst(busnum,selectStid,storder);
	if (success) {%>
	<script language = "javascript">
		alert("更新成功！");
		window.close();
	</script>
	<%} else {%>
	<script language = "javascript">
		alert("更新失败！");
		window.close();
	</script>
	<%}
}
%>
<body><form name="form1" action="" method="post">
<input type = "hidden" name = "doFlag">
<table width="300" border="1" cellpadding="1" cellspacing="0" align="center" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#0082BF">
<tr>
	<td>站点名称 ：</td>
	<td><select name="selectStname">
	<%if (!arrayList.isEmpty() && arrayList.size() > 0) {
		for (int i = 0;i < arrayList.size();i++) {
			HashMap hashMap = new HashMap();
			hashMap = (HashMap) arrayList.get(i);%>
		<option value = "<%=hashMap.get("stid")%>"<%if (stid.equals(hashMap.get("stid"))) {%>selected<%} %>><%=hashMap.get("stname") %></option>
		<%}
	} %>
	</select></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" name="Submit" value="提交" onClick = "doSubmit()">&nbsp;<input type="button" value="关闭" onClick="javascript:window.close()"></td>
</tr>
</table></form>
</body>
</html>
