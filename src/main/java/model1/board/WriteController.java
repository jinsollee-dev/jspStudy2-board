package model1.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/board/write.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO dao = new BoardDAO();
        BoardDTO dto = new BoardDTO();
        HttpSession session =req.getSession();

        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        dto.setId((String)session.getAttribute("userId"));
        int result=dao.insertWrite(dto);
        if(result==1){
            resp.sendRedirect("/board/list.do");
        }else{
            req.setAttribute("msg", "입력 오류");
            req.getRequestDispatcher("/board/write.do").forward(req, resp);
        }


    }
}
