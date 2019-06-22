<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%
if (session.getAttribute("adminname") != null && !session.getAttribute("adminname").equals("") && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("")) {
} else
if (session.getAttribute("userName")==null || session.getAttribute("userName")=="" || session.getAttribute("userPassword") == null || session.getAttribute("userPassword") == ""){
	response.sendRedirect("userLogin.jsp");
}
%>
<% 
ArrayList listBusNum = new ArrayList();
QueryData queryData = new QueryData();
listBusNum = queryData.queryBusNum();
ArrayList listStInfo = new ArrayList();
listStInfo = queryData.querySt();
ArrayList listPlace = new ArrayList();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
    <meta charset="UTF-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/now-ui-kit.css?v=1.1.0" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="assets/css/demo.css" rel="stylesheet" />
    <!-- Canonical SEO -->
    <link rel="canonical" href="" />
    <!--  Social tags      -->
    <meta name="keywords" content="">
    <meta name="description" content="">
<link href="css/my_domain/main.css" type="text/css" rel="stylesheet">
</head>
 <script type="text/javascript" src="scripts/function.js"></script>
<body>
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div> 
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">
	
					<table width="800" border="1" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#DBECF8">
					  <tr>
					    <td bgcolor="#F4F8FF"><font color="#007ED4"><strong>线路查询</strong></font></td>
					  </tr>
					  <tr>
					    <td><form name="form1" method="post" action="queryBusNum.jsp" onSubmit = "return checkBusNum()">
					      <table width="100%"  border="0" cellpadding="0" cellspacing="0">
					        <tr>
					          <td width="30%" align="right"><font color="#007ED4" size="2">线路名称</font>&nbsp;</td>
					          <td width="70%"><input type="text" name="busnum">
					          <input type="submit" name="Submit" value="线路查询">&nbsp;&nbsp;(如：1路)</td>
					        </tr>
					        <tr>
					          <td width="30%" align="right"><font color="#007ED4" size="2">选择输入&nbsp;</font></td>
					          <td width="70%"><select name="selectBusNum" id = "selectBusNum" onChange="loadBusNum()">
							  <option value="">--请选择--</option>
							  <% 
							  if (!listBusNum.isEmpty()) { //存入数据库中读出来的值(用hashmap代替javabean)
							  	for (int i = 0;i < listBusNum.size();i++) {%>
							  	<option value = <%=listBusNum.get(i) %>><%=listBusNum.get(i)%>路</option>
							  	<%}
							  }
							  %>
					          </select></td>
					        </tr>
					      </table>
					    </form></td>
					  </tr>
					</table><br>
					<br><br>
					<table width="800" border="1" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#D1EAD1">
					  <tr>
					    <td bgcolor="#EEFCEE"><font color="#46A539"><strong>站点查询</strong></font></td>
					  </tr>
					  <tr>
					    <td><form name="form2" method="post" action="queryStInfo.jsp" onSubmit="return checkStInfo()">
					      <table width="100%"  border="0" cellpadding="0" cellspacing="0">
					        <tr>
					          <td width="30%" align="right"><font color="#46A539" size="2">站点名称</font>&nbsp;</td>
					          <td width="70%"><input type="text" name="st">
					          <input type="submit" name="Submit" value="站点查询">&nbsp;&nbsp;(如：二号门)</td>
					        </tr>
					        <tr>
					          <td width="30%" align="right"><font color="#46A539" size="2">选择输入</font>&nbsp;</td>
					          <td width="70%"><select name="selectSt" id = "selectSt" onChange="loadStInfo(this.options.selectedIndex)">
							  <option value="">--请选择--</option>
							  <%
							  if (!listStInfo.isEmpty()) {
							  	for (int i = 0;i < listStInfo.size();i++) {
							  		HashMap hashMap = new HashMap();
							  		hashMap = (HashMap) listStInfo.get(i);%>
							  		<option value = "<%=hashMap.get("stid") %>"><%=hashMap.get("stname") %></option>
							  	<%}
							  }
							  %>
					          </select></td>
					        </tr>
					      </table>
					    </form></td>
					  </tr>
					</table><br>
					
					<br><br>
					<table width="800" border="1" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#FCEEEE">
					  <tr>
					    <td bgcolor="#EFDADA"><font color="#DB4918"><strong>站点之间查询</strong></font></td>
					  </tr>
					  <tr>
					    <td><form name="form3" method="post" action="queryBestSt.jsp" onSubmit="return checkStInfo2()">
					      <table width="100%"  border="0" cellpadding="0" cellspacing="0">
					        <tr>
					          <td width="30%" align="right"><font color="#DB4918" size="2">起点名称</font>&nbsp;</td>
					          <td width="70%"><input type="text" name="beginSt">&nbsp;&nbsp;(如：二号门)</td>
					        </tr>
					        <tr>
					          <td width="30%" align="right"><font color="#DB4918" size="2">选择输入</font>&nbsp;</td>
					          <td width="70%"><select name="selectBeginSt" id = "selectBeginSt" onChange = "loadBeginSt(this.options.selectedIndex)">
							  <option value="">--请选择--</option>
							  <%
							  if (!listStInfo.isEmpty()) {
							  	for (int i = 0;i < listStInfo.size();i++) {
							  		HashMap hashMap = new HashMap();
							  		hashMap = (HashMap) listStInfo.get(i);%>
							  		<option value = "<%=hashMap.get("stid") %>"><%=hashMap.get("stname") %></option>
							  	<%}
							  }
							  %>
					          </select></td>
					        </tr>
					        <tr>
					          <td width="30%" align="right"><font color="#DB4918" size="2">终点名称</font>&nbsp;</td>
					          <td width="70%"><input type="text" name="endSt">
					            <input type="submit" name="Submit2" value="站站查询">&nbsp;&nbsp;(如：中心图书馆)</td>
					        </tr>
					        <tr>
					          <td width="30%" align="right"><font color="#DB4918" size="2">选择输入</font>&nbsp;</td>
					          <td width="70%"><select name="selectEndSt" id = "selectEndSt" onChange = "loadEndSt(this.options.selectedIndex)">
							  <option value="">--请选择--</option>
							  <%
							  if (!listStInfo.isEmpty()) {
							  	for (int i = 0;i < listStInfo.size();i++) {
							  		HashMap hashMap = new HashMap();
							  		hashMap = (HashMap) listStInfo.get(i);%>
							  		<option value = "<%=hashMap.get("stid") %>"><%=hashMap.get("stname") %></option>
							  	<%}
							  }
							  %>
					          </select></td>
					        </tr>
					      </table>
					    </form></td>
					  </tr>
					</table>
					</div>
            </div>
        </div> 
    </div>
</body>
</html>
