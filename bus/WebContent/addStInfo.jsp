<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%
ArrayList arrayListBusNum = new ArrayList(); 
QueryData queryData = new QueryData();
arrayListBusNum = queryData.queryBusNum();
%>
<%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {
	response.sendRedirect("adminLogin.jsp");
} %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公交车站点添加页面</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
</head>
<script language="javascript">
	function checkSt_Windows() {
		var temp = document.form1.stname.value;
		window.open('checkSt_Windows.jsp?stname='+temp,'站点名称查重','height=300,width=300,top=50,left=250,scrollbars=yes');
	}
	function getValueByCheckStWindows(para,stid) {
		document.form1.stname.value = para;
		document.form1.stid.value = stid;
	}
</script>

<body>
<table width = "100%" border = "0" cellpadding = "0" cellspacing = "0">
	<tr>
		<td width = "100" height = "25" align = "center" valign="middle"><a href ="addBusInfo.jsp"><strong>添加车次</strong></a></td>
    	<td width="4" bgcolor="#F0F7FD"></td>
		<td width = "100" height = "25" align = "center" valign="middle" bgcolor="#0082BF"><a href = "addStInfo.jsp"><strong>添加站点</strong></a></td>
		<td width="4" bgcolor="#F0F7FD"></td>
    	<td align="center" valign="middle" bgcolor="#F0F7FD"></td>
	</tr>
	<tr>
   		<td height="5" bgcolor="#0082BF" colspan="11"></td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr><td>&nbsp;</td></tr>
	<tr>
		<td><form name="form1" method="post" action="checkAddStInfo.jsp">
		  <table width="100%"  border="1" cellpadding="1" cellspacing="0" align="center" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#0082BF">
            <tr>
              <td align="right">添加站点：</td>
              <td><input type="text" name="stname">&nbsp;&nbsp;<a href="javascript:checkSt_Windows()">站点查重</a>
			  <input type="hidden" name="stid" readonly>
			  </td>
            </tr>
            <tr>
              <td align="right">选择车次：</td>
              <td><select name="selectBusNum" id="selectBusNum" onChange="select()">
			  <option>--请选择--</option>
			  <%if (!arrayListBusNum.isEmpty()) {
			  	for (int i = 0;i < arrayListBusNum.size();i++) {%>
			  	<option value=<%=arrayListBusNum.get(i) %>><%=arrayListBusNum.get(i) %>路</option>
			  	<%}
			  } %>
              </select></td>
            </tr>
			<tr>
			  <td align="right">起始站点：</td>
			  <td align="left"><input type="text" name="beginSt" id="beginSt" readonly ></td>
			</tr>
			<tr>
			  <td align="right">终点站：</td>
			  <td align="left"><input type="text" name="endSt" id="endSt" readonly ></td>
			</tr>
			<tr>
			  <td align="right">已有的站点总数：</td>
			  <td align="left"><input type="text" name="stCount" id="stCount" readonly></td>
			</tr>
			<tr>
			  <td align="center" colspan="2"><input type="submit" value="确定">&nbsp;<input type="reset" value="重置"></td>
			</tr>
          </table>
	    </form></td>
	</tr>
	<iframe id = "selectByBusNum" name = "selectByBusNum" style="width:0;height:0" src="selectByBusNum.jsp"></iframe>
</table>
</body>
<script type="text/javascript">
	function select() {
		var para = document.all.selectBusNum.value;
		selectByBusNum.location="selectByBusNum.jsp?busnum="+para;
	}
</script>
</html>
