<%@ page import="model1.board.BoardDTO" %>
<%@ page import="model1.board.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./isLoggedIn.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    String title = request.getParameter("title");
    String content=request.getParameter("content");
    BoardDTO dto = new BoardDTO();
    dto.setNum(num);
    dto.setTitle(title);
    dto.setContent(content);
    BoardDAO dao = new BoardDAO();
    int iResult = dao.updateEdit(dto);
    dao.close();
    if(iResult==1){
        response.sendRedirect("view.jsp?num="+num);
    } else{
        JSFunction.alertBack("수정하기 실패", out);
    }

%>
</body>
</html>
