// JavaScript Document

//addBusInfo2.jsp
function adbus2check() {
	var tBusNum = document.form1.busNum.value;
	var len = tBusNum.length;
	var t="\\d{"+len+"}";
	var RG=new RegExp(t);
	var result=RG.exec(tBusNum);
	if (tBusNum == "") {
		alert("车号不能为空!");
		return false;
	}
	if(result == null ||"null"==typeof(result)){
			alert("输入只能是0-9之间的数字");
			return false;
	}
	var tBeginSt = document.form1.beginSt.value;
	if (tBeginSt == "") {
		alert("起始站名称不能为空!");
		return false;
	}
	var tEndSt = document.form1.endSt.value;
	if (tEndSt == "") {
		alert("终点站名称不能为空!");
		return false;
	}
}

//addMessage
function mescheck() {
	var paraTopic = document.form1.topic.value;
	if (paraTopic == "") {
		alert("留言的主题不能为空！");
		return false;
	}
	var paraMessagetext = document.form1.messagetext.value;
	if (paraMessagetext == "") {
		alert("留言的内容不能为空！");
		return false;
	}
}

//query
function checkBusNum() {
	var temp = document.all.busnum.value; //获得文本框的里的值
	var len = temp.length; //将值的长度赋值给len
	/*用正则表达式来判断是否为数字*/
	var t="\\d{"+len+"}"; 
	var RG=new RegExp(t);
	var result=RG.exec(temp);
	if(result == null ||"null"==typeof(result)){ //typeof类型转换
			alert("输入只能是0-9之间的数字");
			return false;
	}
	if (temp == "") {
		alert("请输入查询的车号！");
		return false;
	}
}
function checkStInfo() {
	var temp = document.all.st.value; //获得文本框的里的值
	if (temp == "") {
		alert("请输入要查询站点名称!");
		return false;
	}
}
function checkStInfo2() {
	var temp = document.all.beginSt.value; //获得文本框的里的值
	var temp2 = document.all.endSt.value; //获得文本框的里的值
	if (temp == "") {
		alert("请输入起点名称!");
		return false;
	}
	if (temp2 == "") {
		alert("请输入终点名称!");
		return false;
	}
}
function loadPlace(x) {
	var temp = document.all.selectPlace.options[x].text;;
	document.all.placesid.value = temp;
}
function loadBusNum() { //将下拉框里的值传给文本框
	var temp = document.all.selectBusNum.value;
	document.all.busnum.value = temp;
}
function loadStInfo(x) { //将下拉框里的值传给文本框
	var temp = document.all.selectSt.options[x].text;
	document.all.st.value = temp;
}
function loadBeginSt(x) {
	var temp = document.all.selectBeginSt.options[x].text;
	document.all.beginSt.value = temp;
}
function loadEndSt(x) {
	var temp = document.all.selectEndSt.options[x].text;
	document.all.endSt.value = temp;
}

//adminLogin
window.parent.contents.location.reload(); //刷新left.jsp
function admincheck() {
	var name = document.form1.adminname.value;
	var svaild = document.form1.vaild.value;
	if (name == "") {
		alert("管理员名不能为空!");
		return false;
	}
	if (svaild == "") {
		alert("验证码不能为空!");
		return false;
	}
}
function change(img){
	img.src="usernum?"+new Date().getTime();
}

//userLogin
function usercheckForm()
{
	var temp = document.loginDialog.username.value;
	if (temp == "") {
		alert("用户名不能为空，请输入用户名!");
		return false;
	}
}

//userRegister
function checkuser(){
    var username=document.form1.username.value;
    if (username == "") {
    	alert("用户名不能为空！");
    	return false;
    } else {
    	window.open("checkUserName.jsp?username="+username,"检查用户名", 'menubar=0,width=350,height=240,left=230,top=200,resizable=0,scrollbars=auto');
    }
}
function usercheck() {
	var username = document.form1.username.value;
	if (username == "") {
		alert("用户名不能为空！");
		return false;
	}
	var userpassword = document.form1.userpassword.value;
	if (userpassword == "") {
		alert("密码不能为空！");
		return false;
	}
	var confirm_password = document.form1.confirm_password.value;
	if (confirm_password == "") {
		alert("确认密码不能为空！");
		return false;
	}
	var vaild = document.form1.vaild.value;
	if (vaild == "") {
		alert("验证码不能为空！");
		return false;
	}
	var address = document.form1.address.value;
	if (address == "") {
		alert("地址不能为空！");
		return false;
	}
	var confirm_password = document.form1.confirm_password.value;
	if (confirm_password == "") {
		alert("确认密码不能为空！");
		return false;
	} else if (userpassword != confirm_password) {
		alert("确认密码与第一次密码不同！");
		return false;
	}
}

