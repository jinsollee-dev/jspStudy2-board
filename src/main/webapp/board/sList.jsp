<%@ page import="utils.BoardPage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../common/link.jsp"/>
<h2>게시글 목록보기</h2>
<h4>게시글 수 : ${totalCount}</h4>

<form method="get">
    <table>
        <tr>
            <td align="center">
                <select name = "searchField">
                    <c:choose>
                        <c:when test ="${searchField=='content'}">
                            <option value="title" >제목</option>
                            <option value="content" selected>내용</option>
                        </c:when>
                         <c:otherwise>
                            <option value="title" selected>제목</option>
                            <option value="content">내용</option>
                        </c:otherwise>
                    </c:choose>
                </select>
                <input type="text" name="searchWord" value="${searchWord}">
                <input type ="submit" name="검색">
            </td>
        </tr>
    </table>
</form>
<table border="1" width="80%">
    <tr>
        <th width="10%">번호</th>
        <th width="50%">제목</th>
        <th width="15%">작성자</th>
        <th width="10%">조회</th>
        <th width="15%">작성일</th>
    </tr>
    <c:choose>
        <c:when test="${empty boardList}">
            <tr><td colspan="5" align="center">등록된 게시글이 없습니다.</td></tr>
        </c:when>
         <c:otherwise>
            <c:forEach var ="dto" items="${boardList}">
                <tr>
                    <td>${dto.num}</td>
                    <td><a href="/board/view.do?num=${dto.num}">${dto.title}</a></td>
                    <td>${dto.id}</td>
                    <td>${dto.visitcount}</td>
                    <td>${dto.postdate}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
<table border="1" width="80%">
    <tr>
        <td align="center">
<%--            <%=BoardPage.pagingStr(totalCount, pageSize, blockSize,pageNum, request.getRequestURI())%>--%>
        </td>
        <td align="right">
            <button type = "button" onclick="location.href='/board/write.do'">글쓰기</button>
        </td>
    </tr>
</table>
</body>
</html>
