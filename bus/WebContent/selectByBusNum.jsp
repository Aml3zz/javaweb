<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<%@ page import = "java.util.*" %>
<script type="text/javascript">
<%
ArrayList arrayList = new ArrayList();
ArrayList arrayListStCount = new ArrayList();
String busNum = request.getParameter("busnum") == null ? "" : request.getParameter("busnum");
QueryData queryData = new QueryData();
arrayList = queryData.queryBusNumDetail2(busNum);
arrayListStCount = queryData.queryStCount(busNum);
if (!arrayList.isEmpty() && arrayList.size() > 0) {
	for (int i = 0; i < arrayList.size();i++) {
		HashMap hashMap = new HashMap();
		hashMap = (HashMap) arrayList.get(i); //得到arrayList索引值(一个索引值对应hashmap)
	%>
	parent.form1.beginSt.value = '<%=hashMap.get("BeginSt")%>';
	parent.form1.endSt.value = '<%=hashMap.get("EndSt")%>';
	<%}
} else {%>
	parent.form1.beginSt.value = '';
	parent.form1.endSt.value = '';
<%}
if (!arrayListStCount.isEmpty() && arrayListStCount.size() > 0) {
	for (int i = 0;i < arrayListStCount.size();i++) {%>
	parent.form1.stCount.value = '<%=arrayListStCount.get(i)%>'; //得到总的站点数
	<%}
} else {%>
	parent.form1.stCount.value = '';
<%}
%>
</script>
