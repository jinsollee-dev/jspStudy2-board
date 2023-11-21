package model1.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        BoardDAO dao = new BoardDAO();
        dao.updateVisitCount(num);
        BoardDTO dto = dao.selectView(num);

        req.setAttribute("dto",dto);
        req.getRequestDispatcher("/board/viewJstl.jsp").forward(req,resp);

    }
}
