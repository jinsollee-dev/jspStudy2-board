package model1.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/board/list.do")
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BoardDAO dao = new BoardDAO();
        Map<String, Object> map = new HashMap<>();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");


        if (searchWord != null) {
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        } else{
            searchField="";
            searchWord="";
        }

        int totalCount = dao.selectCount(map);

        //페이징 처리//
        int pageSize = 10; //페이지당 레코드 수
        int blockSize = 5; //블록당 페이지 수
        int totalPage = (int) Math.ceil((double) totalCount / pageSize); //전체페이지 수

        //현재 페이지
        int pageNum = 1;
        String pageTemp = req.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals("")) {
            pageNum = Integer.parseInt(pageTemp);
        }

        // 목록에 출력할 게시물 범위 계산
        //int start = ((pageNum)-1)*pageSize+1; oracle 적용
        //int end = pageNum * pageSize; //oracle 적용
        int start = ((pageNum) - 1) * pageSize;
        map.put("start", start);
        map.put("pageSize", pageSize);
        List<BoardDTO> boardDTOList = dao.selectPagingList(map);
        dao.close();
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("boardList", boardDTOList);
        req.setAttribute("map",map);
        req.getRequestDispatcher("/board/sList.jsp").forward(req,resp);


    }
}
