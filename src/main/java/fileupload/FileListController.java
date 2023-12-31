package fileupload;

import com.example.boardsystem01.HelloServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/upload/fileList.do")
public class FileListController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        MyFileDAO dao = new MyFileDAO();
        List<MyFileDTO> fileList = dao.selectFileList();
        req.setAttribute("fileList", fileList);
        req.getRequestDispatcher("/ch13/fileList.jsp").forward(req, resp);
    }
}
