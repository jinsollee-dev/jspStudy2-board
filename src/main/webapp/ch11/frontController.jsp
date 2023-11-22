
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>한번의 매핑으로 여러가지 요청처리</h2>
${resultValue}<br>
<ol>
    <li>URI : ${uri}</li>
    <li>요청명 : ${commandStr}</li>
</ol>
<ol>
    <li><a href="/ch11/regist.one">회원가입</a></li>
    <li><a href="/ch11/login.one">로그인</a></li>
    <li><a href="/ch11/freeboard.one">자유게시판</a></li>

</ol>
<form action="/ch11/post1.one">
    <input type="submit" value="post형식 요청">
</form>
</body>
</html>
