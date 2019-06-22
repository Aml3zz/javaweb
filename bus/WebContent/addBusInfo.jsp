<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8"%>
<%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {
	response.sendRedirect("adminLogin.jsp");
} %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公交车添加页面</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
</head>
 <script type="text/javascript" src="scripts/function.js"></script>
<script language = "javascript">
	function getValueByCheckStWindows(para,stid) {
		document.form1.beginSt.value = para;
		document.form1.beginStid.value = stid;
	}
	function getValueByCheckStWindows2(para,stid) {
		document.form1.endSt.value = para;
		document.form1.endStid.value = stid;
	}
	function checkSt_Windows() {
		var temp = "";
		window.open('checkSt_Windows.jsp?stname='+temp,'站点名称查重','height=300,width=300,top=50,left=250,scrollbars=yes');
	}
	function checkSt_Windows2() {
		var temp = "";
		window.open('checkSt2_Windows.jsp?stname='+temp,'站点名称查重','height=300,width=300,top=50,left=250,scrollbars=yes');
	}
</script>
<body>
<table width = "100%" border = "0" cellpadding = "0" cellspacing = "0">
	<tr>
		<td width = "100" height = "25" align = "center" valign="middle" bgcolor="#0082BF"><a href ="addBusInfo.jsp"><strong>添加车次</strong></a></td>
    	<td width="4" bgcolor="#F0F7FD"></td>
		<td width = "100" height = "25" align = "center" valign="middle"><a href = "addStInfo.jsp"><strong>添加站点</strong></a></td>
		<td width="4" bgcolor="#F0F7FD"></td>
    	<td align="center" valign="middle" bgcolor="#F0F7FD"></td>
	</tr>
	<tr>
   		<td height="5" bgcolor="#0082BF" colspan="11"></td>
	</tr>
</table>
<table width="100%" border = "0" cellpadding="0" cellspacing="0">
<tr><td>&nbsp;</td></tr>
<tr><td><form name="form1" method="post" action="checkAddBusInfo.jsp" onSubmit="return adbus2check()">
  <table width="100%"  border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#0082BF">
    <tr>
      <td align="right">车号：</td>
      <td><input type="text" name="busNum"></td>
      <td align="right">起点站：</td>
      <td><input type="text" name="beginSt" readonly><input type = "hidden" name = "beginStid">&nbsp;<a href="javascript:checkSt_Windows()">查看站点</a>&nbsp;(从站点库中添加站点)<font color="red">*</font></td>
    </tr>
    <tr>
      <td align="right">终点站：</td>
      <td><input type="text" name="endSt" readonly><input type = "hidden" name = "endStid">&nbsp;<a href="javascript:checkSt_Windows2()">查看站点</a>&nbsp;(从站点库中添加站点)<font color="red">*</font></td>
      <td align="right">票价：</td>
      <td><select name="selectPrice">
	  <option>--请选择--</option>
	  <option value="1元">1元</option>
	  <option value="2元">2元</option>
      </select></td>
    </tr>
    <tr>
      <td align="right">汽车等级：</td>
      <td><select name="selectLevel">
	  <option value="">--请选择--</option>
	  <option value="普通">普通</option>
	  <option value="高档">高档</option>
      </select></td>
      <td align="right">票价类型：</td>
      <td><select name="selectNote">
	  <option>--请选择--</option>
	  <option value="普通票价">普通票价</option>
      </select></td>
    </tr>
	<tr><td colspan="4" align="center"><input type="submit" name="Submit" value="提交">&nbsp;
	  <input type="reset" name="Submit2" value="重置"></td></tr>
  </table>
</form></td></tr>
</table>
</body>
</html>
