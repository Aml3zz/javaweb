<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%if (session.getAttribute("userName")==null || session.getAttribute("userName")=="" || session.getAttribute("userPassword") == null || session.getAttribute("userPassword") == ""){
	response.sendRedirect("userLogin.jsp");
} %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>网友留言</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.3059" name=GENERATOR></HEAD>
<link href="css/my_domain/main.css" type=text/css rel=stylesheet>
 <script type="text/javascript" src="scripts/function.js"></script>

<BODY><form action="checkAddMessage.jsp" name = "form1" method = "post">
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD width="100%"><img height=67 src="images/leaf.gif" 
      width=753><BR>
      <BR></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD width="9%"></TD>
    <TD width="80%">
      <TABLE borderColor=#bed09d borderColorDark=#aec585 cellPadding=3 
      width="100%" borderColorLight=#d6e1bf border=3>
        <TBODY>
        <TR>
          <TD width="100%">　
            <P align=center><STRONG><SPAN style="FONT-SIZE: 10.4pt"><FONT 
            color=#ff8000>留言板</FONT></SPAN></STRONG></P>
            <TABLE cellSpacing=0 cellPadding=6 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width="100%" bgColor=#e9efde><table width="100%"  border="0">
                  <tr>
                    <td>留言人:&nbsp;&nbsp;&nbsp;<%=(String) session.getAttribute("userName") %></td>
                  </tr>
                  <tr>
                    <td>E-mail:&nbsp;&nbsp;
                      <input type="text" name="email"></td>
                  </tr>
                  <tr>
                    <td>留言主题:
                      <input type="text" name="topic"></td>
                  </tr>
                  <tr>
                    <td>留言内容:</td>
                  </tr>
                  <tr>
                    <td><textarea name="messagetext" cols="50" rows="10"></textarea> </td>
                  </tr>
                </table>
				
                   </TD>
              </TR></TBODY></TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width="30%">
                  <P align=center><IMG height=100 
                  src="images/175.jpg" width=87></P></TD>
                <TD vAlign=top width="70%"><SPAN style="FONT-SIZE: 9pt"><FONT 
                  color=#400040><BR>
                </FONT><FONT 
                  color=#400040><BR>
                <BR></FONT></SPAN>
                  <input type="submit" name="Submit" value="留言" onClick = "return mescheck()">&nbsp;&nbsp;&nbsp; 
</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>
    <TD width="11%"></TD></TR></TBODY></TABLE></form></BODY></HTML>
