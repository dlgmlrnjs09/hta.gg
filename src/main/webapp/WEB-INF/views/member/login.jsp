<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/login.jsp</title>
<script type="text/javascript" src="/lol/resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
    var key = getCookie("key");
    $("#userId").val(key); 
     
    if($("#userId").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
            setCookie("key", $("#userId").val(), 7); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("key");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("#userId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            setCookie("key", $("#userId").val(), 7); // 7일 동안 쿠키 보관
        }
    });
});
 
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

</script>
</head>
<body>
<div id="main_home">
	<h1>회원로그인</h1>
	<!-- action="/lol/login" ==> 고정값임!! rootcontext/login -->
	<form:form method="post" action="/lol/login">
		아이디<br>
		<input type="text" name="username" id="userId" style="width: 300px;"><br>
		비밀번호<br>
		<input type="password" name="password" style="width: 300px;"><br>
		<input type="submit" value="로그인" style="width: 300px;"><br>
		<input type="checkbox" id="idSaveCheck">아이디 저장&nbsp;&nbsp;&nbsp;
		<input type="checkbox" id="remember-me" name="remember-me">자동 로그인<br>
		<input type="button" value="아이디 찾기" id="seachId" onclick="location.href =  '/lol/member/id';">&nbsp;
		<input type="button" value="비밀번호 찾기" id="seachPwd" onclick="location.href =  '/lol/member/pwd';">&nbsp;
		<input type="button" value="회원가입" id="join" onclick="location.href =  '/lol/member/join';">
	</form:form>
</div>
</body>
</html>