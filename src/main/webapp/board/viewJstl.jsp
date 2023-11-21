<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script>
        function deletePost() {
            var confirmed = confirm("정말 삭제하시겠습니까?")
            if(confirmed){
                var form=document.viewFrm;
                form.method="post";
                form.action="/board/deleteProcess.do";
                form.submit();
            }
        }
    </script>
</head>
<body>
<jsp:include page="../common/link.jsp"/>
<h2>회원제 게시판 상세보기</h2>

<form name="viewFrm">
    <input type="hidden" name ="num" value="${dto.num}">
    <table>
        <tr>
            <td>번호</td>
            <td>${dto.num}</td>
        </tr>
        <tr>
            <td>제목</td>
            <td>${dto.title}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td>${dto.content}</td>
        </tr>
        <tr>
            <td>작성자</td>
            <td>${dto.id}</td>
        </tr>
        <tr>
            <td>작성일</td>
            <td>${dto.getPostdate()}</td>
        </tr>
        <tr>
            <td>조회수</td>
            <td>${dto.getVisitcount()}</td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test ="${not empty userId && userId==dto.id}">
                <button type="button" onclick="location.href='/board/edit.do?num=${dto.num}'">수정하기</button>
                <button type="button" onclick="deletePost();">삭제하기</button>
                </c:if>
                <button type="button" onclick="location.href='list.do'">목록보기</button>
            </td>
        </tr>

    </table>

</form>
</body>
</html>
