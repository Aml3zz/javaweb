<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page  import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0040)http://bjcgi.163.net/cgi-bin/tom_reg.cgi -->
<HTML><HEAD><TITLE>用户注册</TITLE>
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
    <script type="text/javascript" src="scripts/function.js"></script>
    <!--  Social tags      -->
    <meta name="keywords" content="">
    <meta name="description" content="">
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="用户注册.files/register.css" type=text/css rel=stylesheet>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #000000
}
TD {
	FONT-SIZE: 12px; COLOR: #000000
}
A:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
A:active {
	COLOR: #000000; TEXT-DECORATION: none
}
A:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
.glow {
	PADDING-RIGHT: 0px; PADDING-LEFT: 12px; FILTER: Glow(Color=#ffffff, Strength=4); PADDING-BOTTOM: 1px; PADDING-TOP: 0px
}
P {
	LINE-HEIGHT: 130%
}
.px14 {
	FONT-SIZE: 14px
}
.px16 {
	FONT-SIZE: 16px
}
.px5 {
	FONT-SIZE: 5px
}
.hx {
	BORDER-BOTTOM: #ade0ff 1px solid; BACKGROUND-COLOR: #f7fbff
}
.input {
	BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BACKGROUND: #ffffff; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid
}
.a5 {
	COLOR: #0059a5
}
A.a5:link {
	COLOR: #0059a5; TEXT-DECORATION: underline
}
A.a5:visited {
	COLOR: #0059a5; TEXT-DECORATION: underline
}
A.a5:hover {
	COLOR: #ff0000; TEXT-DECORATION: none
}
.bm {
	COLOR: #0000ff
}
A.bm:link {
	COLOR: #0000ff; TEXT-DECORATION: underline
}
A.bm:visited {
	COLOR: #0000ff; TEXT-DECORATION: underline
}
A.bm:hover {
	COLOR: #ff0000; TEXT-DECORATION: none
}
.00 {
	COLOR: #0000ff
}
A.00:link {
	COLOR: #0000ff; TEXT-DECORATION: underline
}
A.00:visited {
	COLOR: #0000ff; TEXT-DECORATION: underline
}
A.00:active {
	COLOR: #0000ff; TEXT-DECORATION: underline
}
A.00:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
.bl {
	COLOR: #0000ff
}
A.bl:link {
	COLOR: #0000ff; TEXT-DECORATION: none
}
A.bl:visited {
	COLOR: #0000ff; TEXT-DECORATION: none
}
A.bl:hover {
	COLOR: #ff0000; TEXT-DECORATION: none
}
.ff {
	COLOR: #ffffff
}
A.ff:link {
	COLOR: #ffffff; TEXT-DECORATION: none
}
A.ff:visited {
	COLOR: #ffffff; TEXT-DECORATION: none
}
A.ff:hover {
	COLOR: #ff0000; TEXT-DECORATION: none
}
.STYLE2 {
	FONT-WEIGHT: bold; FONT-SIZE: 11pt; COLOR: #666666; FONT-FAMILY: "宋体"
}
.STYLE4 {
	FONT-WEIGHT: bold; FONT-SIZE: 11pt; COLOR: #019867; FONT-FAMILY: "宋体"
}
.STYLE5 {
	COLOR: #666666
}
.STYLE6 {
	COLOR: #ff0000
}
</STYLE>

<script type="text/javascript" src="scripts/function.js"></script>
<META content="MSHTML 6.00.2900.3059" name=GENERATOR></HEAD>
<BODY >
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(assets/img/login.jpg)"></div> 
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">
                	<div>
								<form name="form1" method="post" action="userRegisterInfo.jsp" onSubmit="return usercheck()"><CENTER>
								<TABLE cellSpacing=0 cellPadding=0 width=580 border=0 bgColor=#eafce6>
								  <TBODY>
								  <TR>
								    <TD width=165><IMG height=43 src="images/318_logo.gif" 
								width=165></TD>
								    </TR>
								    </TBODY>
								</TABLE>
								
								<TABLE width=580 border=0 bgColor=#eafce6>
								        <TBODY>
								        <TR>
								          <TD>&nbsp;</TD>
								          <TD align=right><EM>
								          <FONT  color=#ff0000>*号为必填项，请认真填写</FONT></EM>
								          </TD>
								          </TR>
								          </TBODY>
								</TABLE>
								
							    <TABLE    cellSpacing=0 cellPadding=4 width=580 border=0>
								        <TBODY>
								        
								        <TR bgColor=#eafce6>
								          <TD height=37><SPAN class=STYLE4>基本选项</SPAN></TD>
								       </TR>
								       
								        <TR bgColor=#eafce6>
								          <TD bgColor=#eafce6 height=36>
								          <FONT color=#cc0000>*</FONT> 
								          用 户 名：  <INPUT maxLength=20 size=18 name="username">
											<INPUT onclick="checkuser()" tabIndex=50 type=button value="是否可用" name=image> 
								            <SPAN class=STYLE5>请使用英文和数字， 并以英文开头。</SPAN>
								          </TD>
								          </TR>
								          
								       	 <TR bgColor=#eafce6>
								          <TD height=38>
								          <table cellspacing=0 cellpadding=0 width="100%" border=0>
								            <tbody>
								              <tr>
								                <td width=180><font color=#cc0000>*</font> 密　　码：
								                    <input type=password maxlength=12 size=12 name="userpassword"></td>
								                <td>
								                  <div class=STYLE5 id=div1>请使用5--12位</div>
								               </td>
								             </tr>
								            </tbody>
								          </table>
								         </TD>
								        </TR>
								        
								        <TR bgColor=#eafce6>
								          <TD height=36>
								            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
								              <TBODY>
								              <TR bgColor=#eafce6>
								                <TD width=180>
								                <FONT color=#cc0000>*</FONT> 
								                确认密码： <INPUT type=password maxLength=12 size=12 name="confirm_password">
								                </TD>
								                <TD>
								                  <DIV class=STYLE5 id=div2>请再输入一遍您上面填写的密码。</DIV>
								                </TD>
								                </TR>
								                </TBODY>
								              </TABLE>
								             </TD>
								           </TR>
								           
								        <TR bgColor=#eafce6>    
										<TD height=36><FONT color=#cc0000>*</FONT> 
										地　　址： <INPUT maxLength=20 size=18 name="address"> 
								        </TD>
								        </TR>
										<TR bgColor=#eafce6>
								          <TD bgColor=#eafce6 height=36>&nbsp; 
								          年　　龄：<INPUT maxLength=20 size=18 name="age">
								          </TD>
								          </TR>
								          
								          </TBODY>
								</TABLE>
								
								<TABLE height=10 width=580 border=0>
								        <TBODY>
								        <TR bgColor=#eafce6>
								          <TD></TD>
								        </TR>
								        </TBODY>
							  </TABLE>
							  
							  <TABLE cellSpacing=0 cellPadding=4 width=580 border=0>     
								        <TBODY>
								        
								        <TR bgColor=#eafce6>
								          <TD width=556 height=36>
								          <SPAN class=STYLE2>安全设置 </SPAN>　　　　
								          <SPAN class=STYLE6></SPAN>
								          </TD>
								          </TR>
								          
								        <TR>
								          <TD bgColor=#f3f3f3 height=4></TD>
								        </TR>
								        
								        <TR bgColor=#eafce6>
								          <TD height=36>
								            
								            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
								              <TBODY>
								              <TR>
								                <TD width="43%">
								                <FONT color=#cc0000>1.</FONT> 
								           邮箱： <INPUT maxLength=60 size=18 name="mail">
								              </TD>
								              <TD width="57%">
								              <SPAN class=STYLE5>（如:limin@qq.com）</SPAN>
								              </TD>
								              </TR>
								             </TBODY>
								           </TABLE>
								           
								           </TD>
								           </TR>
								           
								           <TR>
								          	<TD bgColor=#f3f3f3 height=4></TD>
								           </TR>
								           <TR bgColor=#eafce6>
								            <TD>
								            <TABLE height=36 cellSpacing=0 cellPadding=0 width="100%"  border=0>
								              <TBODY>
												<TR bgColor=#eafce6>
								                <TD width="43%"><FONT color=#cc0000>2.</FONT>手机号码： <INPUT maxLength=60 size=18 name=idnum> </TD>
								                </TR>
								               </TBODY>
								            </TABLE>
								           </TD>
								          </TR>
								          
								          </TBODY>
								  </TABLE>
								  
							<TABLE height=10 width=580 border=0>
								<TBODY>
								        <TR>
								          <TD></TD>
								          </TR>
								 </TBODY>
							</TABLE>
							<TABLE cellSpacing=0 cellPadding=4 width=580 border=0>
								   <TBODY>
								     <TR bgColor=#eafce6>
								       <TD height=36><FONT color=#cc0000>*</FONT> 
								       验 证 码： <INPUT size=18 name="vaild"> 请输入右边的验证码：
								            <img name = "randImage" id = "randImage" src = "usernum?<%=new Date().getTime() %>" onclick="change(this)" width="60" height="20" border="1" align="absmiddle">
								             &nbsp; <font class=pt95>看不清点图片</font>
								         </TD>
								      </TR>
								      
								      <TR align=middle bgColor=#eafce6>
								        <TD><INPUT type=submit value="确定" border=0 height="27" width="91"> 
								            　 <INPUT name="重置" type=reset value="重置" width="91" height="27" border=0> 
								          </TD>
								       </TR>
								       </TBODY>
						</TABLE>
						
						
								</center></form>
								</div>
						</div>
            </div>
        </div> 
    </div>
</BODY>
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
</HTML>