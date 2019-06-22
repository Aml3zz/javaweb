<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8" %>
<%@ page import = "com.busSystem.core.*" %>
<% 
String username = request.getParameter("username") == null ? "" : request.getParameter("username");
String userpassword = request.getParameter("userpassword") == null ? "" : request.getParameter("userpassword");
String mail = request.getParameter("mail") == null ? "" : request.getParameter("mail");
String idnum = request.getParameter("idnum") == null ? "" : request.getParameter("idnum");
String address = request.getParameter("address") == null ? "" : request.getParameter("address");
String age = request.getParameter("age") == null ? "" : request.getParameter("age");
String rand = (String) session.getAttribute("syscode");
String input = request.getParameter("vaild");
String info = "";
Register register = new Register();
if (input.equals(rand)) {
	session.removeAttribute("rand");
	info = register.getRegisterInfo(username,userpassword,age,address,mail,idnum);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册信息</title>
<script type="text/javascript">
	<%if (info.equals("用户注册成功！")) {%>alert("<%=info%>");window.location.href = "userLogin.jsp";<%} %>
	<%if (info.equals("用户注册不成功！")) {%>alert("<%=info%>");window.location.href = "userRegister.jsp";<%}%>	
</script>
</head>

<body>

</body>
</html>
