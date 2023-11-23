
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>파일 목록 페이지</h2>
<table border="1">
    <tr>
        <th>No</th>
        <th>제목</th>
        <th>카테고리</th>
        <th>원본파일명</th>
        <th>저장된파일명</th>
        <th>작성일</th>
        <th>-</th>
    </tr>

    <c:forEach var ="dto" items="${fileList}">
        <tr>
            <td>${dto.idx}</td>
            <td>${dto.title}</td>
            <td>${dto.cate}</td>
            <td>${dto.ofile}</td>
            <td>${dto.sfile}</td>
            <td>${dto.postdate}</td>
            <td><a href="/upload/download.do?oName=${dto.ofile}&sName=${dto.sfile}">[다운로드]</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
