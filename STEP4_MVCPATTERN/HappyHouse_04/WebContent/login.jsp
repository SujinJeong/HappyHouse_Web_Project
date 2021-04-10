<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
String msg = (String) request.getAttribute("msg");
Cookie cookies[] = request.getCookies();
String idck = "";
String saveid = "";
if(cookies != null) {
	for(Cookie cookie : cookies) {
		if(cookie.getName().equals("save_id")) {
			saveid = cookie.getValue();
			idck = " checked=\"checked\"";
			break;
		}
	}
}
%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY-로그인</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function login() {
	if(document.getElementById("userid").value == "") {
		alert("아이디 입력!!!");
		return;
	} else if(document.getElementById("userpwd").value == "") {
		alert("비밀번호 입력!!!");
		return;
	} else {
		document.getElementById("loginform").action = "<%=root%>/user";
		document.getElementById("loginform").submit();
	}
}
	 
function moveJoin() {
	document.location.href = "<%=root%>/user?act=mvsignUp";
}	
</script>
<style>
.err {
	color: red;
}
</style>
</head>
<body>

<div class="container" align="center">
	<div class="col-lg-6 mt-3" align="center">
		<form id="loginform" method="post" action="">
			<input type="hidden" name="act" value="login">
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="userid" name="userid" placeholder="" value="<%= saveid %>">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="userpwd" name="userpwd" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}">
			</div>
			<div class="form-group form-check" align="right">
			    <label class="form-check-label">
			      <input class="form-check-input" type="checkbox" id="idsave" name="idsave" value="saveok"<%= idck %>> 아이디저장
			    </label>
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-warning" onclick="javascript:login();">로그인</button>
				<button type="button" class="btn btn-primary" onclick="javascript:moveJoin();">회원가입</button>
			</div>
		</form>
		<span class="err"><%= msg == null ? "" : msg %></span>
	</div>
</div>