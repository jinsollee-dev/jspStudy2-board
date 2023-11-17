<%@ page import="model1.member.MemberDAO" %>
<%@ page import="model1.member.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId = request.getParameter("user_id");
    String userPw = request.getParameter("user_pw");

    MemberDAO dao = new MemberDAO();
    MemberDTO memberDTO = dao.getMemberDTO(userId,userPw);
    dao.close();

    if(memberDTO.getId() !=null){
        session.setAttribute("userId",memberDTO.getId());
        session.setAttribute("userName", memberDTO.getName());
        response.sendRedirect("../board/list.jsp");
    } else {
        request.setAttribute("loginErrMsg", "로그인 오류입니다.");
        request.getRequestDispatcher("../board/pagingList.jsp")
                .forward(request, response);
    }

%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
