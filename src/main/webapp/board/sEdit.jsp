
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function validateForm(form){
            if(form.title.value == ""){
                alert("제목을 입력하세요");
                form.title.focus();
                return false;
            }
            if (form.content.value == ""){
                alert("내용을 입력하세요");
                form.content.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<jsp:include page="../common/link.jsp"/>
<h2>게시글 수정 폼</h2>
<form name="editFrm" method="post" action="/board/edit.do"
    onsubmit = "return validateForm(this)">
    <input type ="hidden" name="num" value="${dto.num}">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${dto.title}"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="content" style="width: 80%;">${dto.content}</textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">게시글 등록</button>
                <button type="reset">새로고침</button>
                <button type="button" onclick="location.href='/board/list.do'">목록보기</button>

            </td>
        </tr>
    </table>
</form>
</body>
</html>
