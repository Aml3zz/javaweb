<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <meta charset="UTF-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>My JSP 'index.jsp' starting page</title>
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
  </head>
  
  <body>

    <div class="page-header" filter-color="orange">
        <!-- <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div>  -->
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">
                
<TABLE  cellSpacing=0 cellPadding=0  border=0 >
  <TBODY>
  <TR>
    <TD vAlign=top >
      <TABLE cellSpacing=0 cellPadding=0 width= align=center bgColor=#ffffff 
      border=0>
        <TBODY>
        <TR>
          <TD vAlign=top align=left colSpan=3 height><img height
            src="images/my_domain/menu_01.gif" width=170></TD>
        </TR>
        <TR>
          <TD width=8 background=images/my_domain/menu_03.gif><IMG height=4 
            src="images/my_domain/menu_03.gif" width=8></TD>
          <TD vAlign=top align=middle width>
            <TABLE class=font_green02 cellSpacing=0 cellPadding=0 width=0 
            border=0>
              <TBODY>
              <TR>
                <TD>　</TD></TR></TBODY></TABLE>
			
            <TABLE cellSpacing=0 cellPadding=0 width=0 border=0>
              <TBODY>
              <TR>
                <TD>
                  <TABLE height=22 cellSpacing=0 cellPadding=0 width=130 
                  background=images/my_domain/menu_bg.gif border=0>
                    <TBODY>
                    <TR>
                      <TD vAlign=center align=middle width=40><IMG height=15 
                        src="images/my_domain/01.gif" width=16></TD>
                      <TD style="CURSOR: hand" 
                      onclick="document.all.menu0.style.display=document.all.menu0.style.display=='none'?'':'none';return false;" 
                      align=left width=90>操作</TD>
                    </TR></TBODY></TABLE></TD></TR>
              <TR id=menu0>
                <TD class=font_green02>
                  <TABLE class=font_hui02 cellSpacing=0 cellPadding=0 width=130 
                  border=0>
                    <TBODY>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="query.jsp" 
                        target=main>站点和车次查询</A></TD>
                    </TR>
                    <%if (session.getAttribute("adminname") != null && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("") && !session.getAttribute("adminname").equals("")) {%>
					<TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="addBusInfo.jsp" 
                        target=main>站点和车次添加</A></TD></TR>
					<%}%>
					<%if (session.getAttribute("adminname") != null && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("") && !session.getAttribute("adminname").equals("")) {%>
					<TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="deleteBusInfo.jsp" 
                        target=main>站点和车次删除</A></TD></TR>
					<%} %>
					<%if (session.getAttribute("adminname") != null && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("") && !session.getAttribute("adminname").equals("")) {%>
					<TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="updateBusInfo.jsp" 
                        target=main>站点和车次更新</A></TD></TR>
					<%} %>
                    </TBODY></TABLE></TD></TR></TBODY></TABLE>      
            <TABLE cellSpacing=0 cellPadding=0 width=0 border=0>
              <TBODY>
              <TR>
                <TD>
                  <TABLE height=22 cellSpacing=0 cellPadding=0 width=130 
                  background=images/my_domain/menu_bg.gif border=0>
                    <TBODY>
                    <TR>
                      <TD vAlign=center align=middle width=40><IMG height=15 
                        src="images/my_domain/02.gif" width=16></TD>
                      <TD style="CURSOR: hand" 
                      onclick="document.all.menu1.style.display=document.all.menu1.style.display=='none'?'':'none';return false;" 
                      align=left width=90>用户管理</TD>
                    </TR></TBODY></TABLE></TD></TR>
              <TR id=menu1 style="DISPLAY">
                <TD class=font_green02>
                  <TABLE class=font_hui02 cellSpacing=0 cellPadding=0 width=96 
                  border=0>
                    <TBODY>
                    <%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {%>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left width=84><A class=font_hui05 
                        href="userLogin.jsp" 
                        target=main>用户登录</A></TD></TR>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="userInfo.jsp" 
					    target=main>用户信息</A></TD></TR>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left width=84><A class=font_hui05 
                        href="userLoginOut.jsp" 
                        target=main>用户注销</A></TD></TR><%}%>
                    <%if (session.getAttribute("adminname") != null && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("") && !session.getAttribute("adminname").equals("")) {%>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left width=84><A class=font_hui05 
                        href="deleteUserInfo.jsp" 
                        target=main>删除用户</A></TD></TR><%}%></TBODY></TABLE></TD></TR></TBODY></TABLE>
            <%if (session.getAttribute("adminname") != null && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("") && !session.getAttribute("adminname").equals("")) {%>
            <TABLE cellSpacing=0 cellPadding=0 width=0 border=0>
              <TBODY>
              <TR>
                <TD>
                  <TABLE height=22 cellSpacing=0 cellPadding=0 width=130 
                  background=images/my_domain/menu_bg.gif border=0>
                    <TBODY>
                    <TR>
                      <TD vAlign=center align=middle width=40><IMG height=15 
                        src="images/my_domain/02.gif" width=16></TD>
                      <TD style="CURSOR: hand" 
                      onclick="document.all.menu12.style.display=document.all.menu12.style.display=='none'?'':'none';return false;" 
                      align=left width=90>管理员信息</TD>
                    </TR></TBODY></TABLE></TD></TR>
              <TR id=menu12 style="DISPLAY">
                <TD class=font_green02>
                  <TABLE class=font_hui02 cellSpacing=0 cellPadding=0 width=96 
                  border=0>
                    <TBODY>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="adminLogin.jsp" 
                        target="main">管理员登录</A></TD></TR>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="adminLoginOut.jsp" 
                        target=main>管理员注销</A></TD></tr>
                    
                    </TBODY></TABLE></TD></TR></TBODY></TABLE><%} %>
					<TABLE cellSpacing=0 cellPadding=0 width=0 border=0>
              <TBODY>
              <TR>
                <TD>
                  <TABLE height=22 cellSpacing=0 cellPadding=0 width=130 
                  background=images/my_domain/menu_bg.gif border=0>
                    <TBODY>
                    <TR>
                      <TD vAlign=center align=middle width=40><IMG height=15 
                        src="images/my_domain/02.gif" width=16></TD>
                      <TD style="CURSOR: hand" 
                      onclick="document.all.menu13.style.display=document.all.menu13.style.display=='none'?'':'none';return false;" 
                      align=left width=90>留言板</TD>
                    </TR></TBODY></TABLE></TD></TR>
              <TR id=menu13 style="DISPLAY">
                <TD class=font_green02>
                  <TABLE class=font_hui02 cellSpacing=0 cellPadding=0 width=96 
                  border=0>
                    <TBODY>
                    <%if (session.getAttribute("adminname") == null || session.getAttribute("adminname") == "" || session.getAttribute("adminpassword") == null || session.getAttribute("adminpassword") == "") {%>
            
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="addMessage.jsp" 
                        target="main">留言</A></TD></TR>
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="queryMessage.jsp" 
						target=main>查看留言</A></TD></TR><%} %>
					<%if (session.getAttribute("adminname") != null && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("") && !session.getAttribute("adminname").equals("")) {%>
           			
                    <TR>
                      <TD align=middle width=22 height=22><img src="images/my_domain/pic_item.gif"></TD>
                      <TD align=left><A class=font_hui05 
                        href="deleteMessage.jsp" 
                        target=main>删除留言</A></TD></TR><%} %></TBODY></TABLE></TD></TR></TBODY></TABLE>
     	  </TD>
		  <TD width=8 background=images/my_domain/menu_04.gif><IMG height=4 
            src="images/my_domain/menu_04.gif" width=8></TD></TR>
        <TR>
          <TD vAlign=bottom align=left colSpan=3 height=7><IMG height=15 
            src="images/my_domain/menu_02.gif" 
  width=170></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
  
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