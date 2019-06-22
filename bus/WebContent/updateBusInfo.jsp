<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8" %>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {
	response.sendRedirect("adminLogin.jsp");
} %>
<%
String busNum = request.getParameter("selectBusNum") == null ? "" : request.getParameter("selectBusNum");
ArrayList arrayList = new ArrayList();
ArrayList listBusNum = new ArrayList();
QueryData queryData = new QueryData();
UpdateBusInfo updateBusInfo = new UpdateBusInfo();
updateBusInfo.countBusNumData(busNum);
int count = updateBusInfo.getCount();
int pageCount = updateBusInfo.getPageCount();
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
arrayList = updateBusInfo.queryBusNumData(busNum,currentPage);
listBusNum = queryData.queryBusNum();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新车次</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<script language="javascript">
	function checkSt_Windows(busnumPara,stidPara,storderPara) {
		//var temp = document.form1.stname.value;
		window.open('checkSt3_Windows.jsp?busnum='+busnumPara+'&stid='+stidPara+'&storder='+storderPara,'更新站点名称','height=300,width=300,top=50,left=250,scrollbars=yes');
	}
</script>
<body>
<table width = "100%" border = "0" cellpadding = "0" cellspacing = "0">
	<tr>
		<td width = "100" height = "25" align = "center" valign="middle" bgcolor="#0082BF"><strong>更新站点</strong></td>
    	<td width="4" bgcolor="#F0F7FD"></td>
		<td width = "100" height = "25" align = "center" valign="middle"><a href = "updateBusInfo2.jsp"><strong>更新车次</strong></a></td>
		<td width="4" bgcolor="#F0F7FD"></td>
    	<td align="center" valign="middle" bgcolor="#F0F7FD"></td>
	</tr>
	<tr>
   		<td height="5" bgcolor="#0082BF" colspan="11"></td>
	</tr>
</table>
<table width="768" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td>
		  <form name="form1" method="post" action="updateBusInfo.jsp">选择车号：
		    <select name="selectBusNum">
			<option value="">--请选择--</option>
			<%if (!listBusNum.isEmpty() && listBusNum.size() > 0) {
				for (int i = 0;i < listBusNum.size();i++) {%>
				<option value = "<%=listBusNum.get(i) %>" <%if (busNum.equals(listBusNum.get(i))) {%>selected = "true"<%}%>><%=listBusNum.get(i) %>路</option>
				<%}
			} %>
	        </select>
            <input type="submit" name="Submit" value="提交">
          </form></td>
	</tr>
</table>
<table width="768" border="1" align="center" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#0082BF">
  <tr bgcolor="#DBECF8">
    <td align="center">车号</td>
    <td align="center">票价</td>
    <td align="center">车辆等级</td>
    <td align="center">票价等级</td>
    <td align="center">站点名称</td>
    <td align="center">站点次序</td>
	<td align="center">操作</td>
  </tr>
  <%if (!arrayList.isEmpty()) {
  	for (int i = 0;i < arrayList.size();i++) {
  		HashMap hashMap = (HashMap) arrayList.get(i);
  %>
  <tr>
    <td align="center"><%=hashMap.get("BusNum") %></td>
    <td align="center"><%=hashMap.get("TicketNote") %></td>
    <td align="center"><%=hashMap.get("BusLevel") %></td>
    <td align="center"><%=hashMap.get("Note") %></td>
    <td align="center"><%=hashMap.get("StName") %></td>
    <td align="center"><%=hashMap.get("StOrder") %></td>
	<td align="center"><a href="javascript:checkSt_Windows('<%=hashMap.get("BusNum") %>','<%=hashMap.get("StId")%>','<%=hashMap.get("StOrder") %>')">更新</a></td>
  </tr>
  <%}%>
  <tr>
  	<td colspan = "7">总记录数：<%=count %>&nbsp;&nbsp;总页数：<%=pageCount %>&nbsp;&nbsp;
  	当前页：<%=currentPage%>&nbsp;&nbsp;<%if (pages == 1) {%>首页<%}else {%><a href="updateBusInfo.jsp?pages=1&selectBusNum=<%=busNum%>">首页</a><%}%>
  	&nbsp;&nbsp;<%if (currentPage > 1) {%><a href="updateBusInfo.jsp?pages=<%=currentPage-1%>&selectBusNum=<%=busNum%>">上一页</a><%}
  	else {%>上一页<%}%>&nbsp;&nbsp;<%if (currentPage < pageCount) { %><a href="updateBusInfo.jsp?pages=<%=currentPage+1%>&selectBusNum=<%=busNum%>">下一页</a><%} else{%>
  	下一页<%}%>&nbsp;&nbsp;<%if (currentPage == pageCount || currentPage > pageCount) {%>末页<%} else {%><a href="updateBusInfo.jsp?pages=<%=pageCount%>&selectBusNum=<%=busNum%>">末页</a><%}%>
  	</td>
  </tr>
  <%} else {%>
  <tr>
    <td colspan="7" align="center"><font color="#FF0000">没有此信息内容!</font></td>
  </tr>
  <%}%>
</table>
</body>
</html>
