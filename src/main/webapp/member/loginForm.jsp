<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h2>로그인페이지 </h2>
<span style="color:red; font-size:1.2em">
    <%=request.getAttribute("loginErrMsg")==null? "": request.getAttribute("loginErrMsg")%>
</span>
<body>
<%
    if(session.getAttribute("userId")==null) {
    //로그아웃 상태
%>
<script>
    function validateForm(form){
        if(!form.user_id.value){
            alert("아이디를 입력하세요");
            return false;
        }
        if (!form.user_pw.value){
            alert("패스워드를 입력하세요")
            return false;
        }
    }

</script>
<form action="loginProcess.jsp" method="post" name="loginFrm"
      onsubmit="return validateForm(this)">
    아이디 : <input type = "text" name="user_id"><br>
    패스워드 : <input type ="password" name ="user_pw"><br>
    <input type="submit" value = "로그인하기">
</form>

<%
    }else{
%>

<%=session.getAttribute("userName")%>회원님, 로그인하셨습니다.<br>
<a href = "logout.jsp">[로그아웃]</a>
<%
    }
%>
</body>
</html>
