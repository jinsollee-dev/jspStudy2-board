
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td align="center">
            <% if(session.getAttribute("userId")==null){%>
            <a href="../member/loginForm.jsp">로그인</a>
            <%}else {%>
            <a href="../member/logout.jsp">로그아웃</a>
            <% }%>
            &nbsp;&nbsp;
            <a href="../board/list.jsp">게시판 페이징(x)</a>
            &nbsp;&nbsp;
            <a href="../board/pagingList.jsp">게시판 페이징(o)</a>
        </td>
    </tr>
</table>
</body>
</html>