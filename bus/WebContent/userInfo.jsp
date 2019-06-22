<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<%if (session.getAttribute("userName")==null || session.getAttribute("userName")=="" || session.getAttribute("userPassword") == null || session.getAttribute("userPassword") == ""){
	response.sendRedirect("userLogin.jsp");
} else {
String username = (String) session.getAttribute("userName");
ArrayList list = new ArrayList(); 
QueryData queryData = new QueryData();
list = queryData.queryUserInfo(username);
HashMap hashMap = new HashMap();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link href = "css/home.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/style.css" rel = "stylesheet" type = "text/css"/>
<link href = "css/theme.css" rel = "stylesheet" type = "text/css" media = "all" title = "Aqua"/>
</head>

<body>
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div> 
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">
				<form name="form1" action="checkModifyUserInfo.jsp?username=<%=username %>" method="post">
				<table width="768" border="1" align="center" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#0082BF">
				  <tr>
				    <td colspan="2" align="center"><strong><font color="ffffff">用户信息</font></strong></td>
				  </tr>
				  <%if (!list.isEmpty() && list.size() > 0) {
				  	for (int i = 0;i < list.size();i++) {
				  		hashMap = (HashMap) list.get(i);
				  	}
				  	
				  }%>
				  <tr>
				    <td><font color="ffffff">用户名：</font></td>
				    <td><font color="ffffff"><%=hashMap.get("username") %></font></td>
				  </tr>
				  <tr>
				    <td><font color="ffffff">用户密码：</font></td>
				    <td><input type="text" name="userpassword" value = "<%=hashMap.get("userpassword") %>"></td>
				  </tr>
				  <tr>
				    <td><font color="ffffff">用户年龄：</font></td>
				    <td><input type="text" name="userage" value = "<%=hashMap.get("userage") %>"></td>
				  </tr>
				  <tr>
				    <td><font color="ffffff">地址：</font></td>
				    <td><input type="text" name="address" value = "<%=hashMap.get("address") %>"></td>
				  </tr>
				  <tr>
				    <td><font color="ffffff">email：</font></td>
				    <td><input type="text" name="email" value = "<%=hashMap.get("email")%>"></td>
				  </tr>
				
				  <tr>
				    <td><font color="ffffff">手机号：</font></td>
				    <td><input type="text" name="idnum" value = "<%=hashMap.get("idnum") %>"></td>
				  </tr>
				  <tr align="center">
				    <td colspan="2"><input type="submit" name="Submit" value="修改"></td>
				    </tr>
				</table>
				</form>
				</div>
            </div>
        </div> 
    </div>
</body>
<!--   Core JS Files   -->
<script src="assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="assets/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="assets/js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- Share Library etc -->
<script src="assets/js/plugins/jquery.sharrre.js" type="text/javascript"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="assets/js/now-ui-kit.js?v=1.1.0" type="text/javascript"></script>
</html>
<%}%>