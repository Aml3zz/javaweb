<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.busSystem.util.*" %>
<%
String busNum = request.getParameter("busnum");
ArrayList arrayList = new ArrayList();
ArrayList listBusNum = new ArrayList();
QueryData queryData = new QueryData();
UpdateBusInfo updateBusInfo = new UpdateBusInfo();
updateBusInfo.countBusNumData(busNum);
int count = updateBusInfo.getCount(); //总记录数
int pageCount = updateBusInfo.getPageCount(); //总页数
int currentPage = 1;
int pages; //上一页或下一页的页数
if (request.getParameter("pages") == null || request.getParameter("pages").equals("")) {
	pages = 1;
} else {
	pages = new Integer(request.getParameter("pages")).intValue(); // 将字符型转换为整型
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
<title>站点查询信息显示</title>
<link href="css/my_domain/main.css" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div> 
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">
						<table width="768" border="1" align="center" cellpadding="1" cellspacing="0" bordercolordark="#ffffff" bordercolor="#ffffff" bordercolorlight="#0082BF">
						  <tr  height="20">
						    <td align="center">车号</td>
						    <td align="center">票价</td>
						    <td align="center">车辆等级</td>
						    <td align="center">票价等级</td>
						    <td align="center">站点名称</td>
						    <td align="center">站点次序</td>
						  </tr>
						  <%if (!arrayList.isEmpty()) {
						  	for (int i = 0;i < arrayList.size();i++) {
						  		HashMap hashMap = (HashMap) arrayList.get(i); //从arrayList中返回的值给haspMap(一个haspMap放的是表中一条记录,arrayList放得N个haspMap)
						  %>
						  <tr height="50">
						    <td align="center"><%=hashMap.get("BusNum") %></td>
						    <td align="center"><%=hashMap.get("TicketNote") %></td>
						    <td align="center"><%=hashMap.get("BusLevel") %></td>
						    <td align="center"><%=hashMap.get("Note") %></td>
						    <td align="center"><%=hashMap.get("StName") %></td>
						    <td align="center"><%=hashMap.get("StOrder") %></td>
						  </tr>
						  <%}%>
							  <tr height="50">
							  	<td colspan = "6">总记录数：<%=count %>&nbsp;&nbsp;总页数：<%=pageCount %>&nbsp;&nbsp;
							  	当前页：<%=currentPage%>&nbsp;&nbsp;<%if (pages == 1) {%>首页<%}else {%><a href="queryBusNum.jsp?pages=1&busnum=<%=busNum%>">首页</a><%}%>
							  	&nbsp;&nbsp;<%if (currentPage > 1) {%><a href="queryBusNum.jsp?pages=<%=currentPage-1%>&busnum=<%=busNum%>">上一页</a><%}
							  	else {%>上一页<%}%>&nbsp;&nbsp;<%if (currentPage < pageCount) { %><a href="queryBusNum.jsp?pages=<%=currentPage+1%>&busnum=<%=busNum%>">下一页</a><%} else{%>
							  	下一页<%}%>&nbsp;&nbsp;<%if (currentPage == pageCount || currentPage > pageCount) {%>末页<%} else {%><a href="queryBusNum.jsp?pages=<%=pageCount%>&busnum=<%=busNum%>">末页</a><%}%>
							  	</td>
							  </tr>
							  <%} else {%>
							  <tr height="50">
							    <td colspan="6" align="center"><font color="#FF0000">没有此信息内容!</font></td>
							  </tr>
							  <%}%>
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
