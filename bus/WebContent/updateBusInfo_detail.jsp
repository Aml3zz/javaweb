<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%
ArrayList arrayList = new ArrayList();
QueryData queryData = new QueryData();
String busNum = request.getParameter("busNum") == null ? "" : request.getParameter("busNum");
arrayList = queryData.queryBusNumDetail(busNum);
HashMap hashMap = new HashMap();
if (!arrayList.isEmpty()) {
	hashMap = (HashMap) arrayList.get(0); //得到第一条记录
} 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车次更新</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
<script type="text/javascript" src="scripts/function.js"></script>
</head>

<body>

					                
					<form name="form1" method="post" action="checkUpdateBusInfo.jsp">
					  <table width="100%"  border="1"  cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#FF3300">
					    <tr>
					      <td><font >车号：</font></td>
					      <td><input type="text" name="BusNum" value = "<%=hashMap.get("BusNum") %>" readonly></td>
					      <td><font >起点站：</font><input type = "hidden" name = "storder" value = "<%=request.getParameter("storder") %>"></td>
					      <td><input type="text" name="beginSt" value = "<%=hashMap.get("BeginSt") %>" readonly><input type = "hidden" name = "beginStid">&nbsp;<a href="javascript:checkSt_Windows()"><font>查看站点</font></a>&nbsp;<font>(从站点库中添加站点)</font><font color="red">*</font></td>
					    </tr>
					    <tr>
					      <td><font >终点站：</font></td>
					      <td><input type="text" name="endSt" value = "<%=hashMap.get("EndSt") %>" readonly><input type = "hidden" name = "endStid">&nbsp;<a href="javascript:checkSt_Windows2()"><font >查看站点</font></a>&nbsp;<font >(从站点库中添加站点)</font><font color="red">*</font></td>
					      <td><font>票价：</font></td>
					      <td><select name="selectPrice">
						  <option>--请选择--</option>
						  <option <%if(hashMap.get("TicketNote").equals("1元")){%>selected = "true"<%}%>value="1元"><font >1元</font></option>
						  <option <%if (hashMap.get("TicketNote").equals("2元")) {%>selected = "true"<%}%>value="2元"><font>2元</font></option>
					      </select></td>
					    </tr>
					    <tr>
					      <td><font >车子等级：</font></td>
					      <td><select name="selectLevel">
						  <option value=""><font >--请选择--</font></option>
						  <option <%if (hashMap.get("BusLevel").equals("普通")) {%>selected = "true"<%} %>value="普通"><font>普通</font></option>
						  <option <%if (hashMap.get("BusLevel").equals("高档")) {%>selected = "true"<%} %>value="高档"><font>高级</font></option>
					      </select></td>
					      <td>票价档次：</font></td>
					      <td><select name="selectNote">
						  <option>--请选择--</option>
						  <option <%if (hashMap.get("Note").equals("普通票价")) {%>selected = "true"<%} %>value="普通票价">普通票价</option>
					      </select></td>
					    </tr>
					    <tr>
					      <td colspan="4" align="center"><input type="submit" name="Submit" value="提交">&nbsp;
					      <input type="reset" name="Submit2" value="重置"></td>
					    </tr>
					  </table>
					</form>
					

</body>

</html>
