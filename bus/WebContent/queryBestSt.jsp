<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.busSystem.util.*" %>
<% 
String beginSt = request.getParameter("beginSt") == null ? "" : request.getParameter("beginSt");
String endSt = request.getParameter("endSt") == null ? "" : request.getParameter("endSt");
ArrayList arrayList = new ArrayList();
ArrayList arrayListBusNum = new ArrayList();
QueryData queryData = new QueryData();
arrayList = queryData.queryShortestPath(beginSt,endSt);
arrayListBusNum = queryData.getArrayListBusNum();
String travelLine = "";
String travelLine2 = "";
/*将查出的最短路径显示到页面上*/
if (!arrayList.isEmpty()) { //最短路径中所有的站点名称
	for (int i = 0;i < arrayList.size();i++) {
		if (i == (arrayList.size() - 1)) { //判断是否为最短路径中的最后一个站点(终点)
			travelLine = travelLine + arrayList.get(i);
		} else {
			travelLine = travelLine + arrayList.get(i).toString() + "-->";
		}
	}
	for (int i = 0;i < arrayList.size();i++) {
		if (i == (arrayList.size() - 1)) {
			Vector vector = (Vector) arrayListBusNum.get(i);
			String temp = "";
			if (!vector.isEmpty()) {
				for (int j = 0;j < vector.size();j++) {
					if (j == (vector.size() - 1)) {
						temp = temp + vector.get(j);
					} else {
						temp = temp + vector.get(j) + "、";
					}
				}
			}
			temp = temp + "路";
			travelLine2 = travelLine2 + arrayList.get(i) + "(<font color=#DB4918><strong>"+temp+"</strong></font>)";
		} else {
			Vector vector = (Vector) arrayListBusNum.get(i);
			String temp = "";
			if (!vector.isEmpty()) {
				for (int j = 0;j < vector.size();j++) {
					if (j == (vector.size() - 1)) {
						temp = temp + vector.get(j);
					} else {
						temp = temp + vector.get(j) + "、";
					}
				}
			}
			temp = temp + "路";
			travelLine2 = travelLine2 + arrayList.get(i) + "(<font color=#DB4918><strong>"+temp+"</strong></font>)" + "-->";
		}
	}
} else {
	travelLine = "没有可达的线路!";
	travelLine2 = "没有可达的线路!";
}
%>
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
<title>站点之间查询</title>
<link href = "css/my_domain/main.css" type="text/css" rel="stylesheet">
</head>

<body>
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div> 
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">		
	
			<table width="800" border="1"  cellpadding="1" cellspacing="0" align="center" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#FF3300">
				  <tr  height="70">
				    <td><font ><strong>站站查询</strong></font>&nbsp;&nbsp;&nbsp;<font ><strong>站点:<%=new String(beginSt.getBytes("ISO8859-1"))%>&nbsp;至&nbsp;<%=new String(endSt.getBytes("ISO8859-1"))%></strong></font></td>
				  </tr>
				  <tr height="200">
				    <td>最短乘车线路：<%=travelLine%><br><br>相应的车次：<%=travelLine2 %></td>
				  </tr>
				</table>

			</div>
            </div>
        </div> 
    </div>
</body>
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
