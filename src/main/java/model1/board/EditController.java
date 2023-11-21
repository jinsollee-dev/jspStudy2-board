package model1.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

import java.io.IOException;

@WebServlet("/board/edit.do")
public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/board/write.jsp").forward(req,resp);
        HttpSession session =req.getSession();

        int num = Integer.parseInt(req.getParameter("num"));
        BoardDAO dao = new BoardDAO();
        BoardDTO dto = dao.selectView(num);
        String sessionId = session.getAttribute("userId").toString();
//        if(!sessionId.equals(dto.getId())){
//            JSFunction.alertBack("작성자만 수정할 수 있음",out);
//            return;
//        }
        dao.close();

        req.setAttribute("dto",dto);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int num = Integer.parseInt(req.getParameter("num"));
        String title = req.getParameter("title");
        String content=req.getParameter("content");
        BoardDTO dto = new BoardDTO();
        dto.setNum(num);
        dto.setTitle(title);
        dto.setContent(content);
        BoardDAO dao = new BoardDAO();
        int iResult = dao.updateEdit(dto);
        dao.close();
        if(iResult==1){
            resp.sendRedirect("viewJstl.jsp?num="+num);
        } else{
            //JSFunction.alertBack("수정하기 실패", out);

        }


    }
}
