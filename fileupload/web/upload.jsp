<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
$(function(){
	var i = 2;
	
	$("#addfile").click(function(){
		$(this).parent().parent().before("<tr class='file'><td>file"
				+ i + ":</td><td><input type='file' name='file"
				+ i +"'/></td></tr>"	
				+ "<tr class='desc'><td>desc"
				+ i +":</td><td><input type='text' name='desc"
				+ i +"'/><button id='delete"
				+ i +"'>删除</button></td></tr>");
		
			i++;
			
			//获取新添加的删除按钮
			$("#delete" + (i-1)).click(function(){
				var $tr = $(this).parent().parent();//button 所在的行
				$tr.prev("tr").remove();//button所在行的前一个同胞元素tr
				$tr.remove();
				
				//对 i 重写排序
				$(".file").each(function(index){
					var n = index + 1;		//index从0开始计数，为当前的节点索引值
					$(this).find("td:first").text("file"+n);//第一列td
					$(this).find("td:last input").attr("name","file" + n);					
				});
				
				$(".desc").each(function(index){
					var n =index +1;
					$(this).find("td:first").text("desc"+n);
					$(this).find("td:last input").attr("name","desc"+n);
				});
				
				i = i-1;
			
			});
			
			return false;
	});
});

	
</script>

</head>
<body>
		
				
			<h3>file upload</h3>
			<h4>提交的文件类型可支持：pptx,docx,doc ，提交其他类型文件将上传失败</h4>
			<h4>每个文件的大小限制在1M，文件过大将不能上传</h4>
			<font color="red">${msg }</font>
			<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
				<table>
					<tr class="file">
						<td>file1:</td>
						<td><input type="file" name="file1"/></td>
					</tr>
					<tr class="desc">
						<td>desc1:</td>
						<td><input type="text" name="desc1"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交"/></td>
						<td><button id="addfile">新增一个附件</button></td>
					</tr>
				</table>
				
					
					
					
					
					
			</form>
		
</body>
</html>
