package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtile {

    public static String uploadFile(HttpServletRequest req, String sDirectory)
            throws ServletException, IOException {
        //자카르타에서 Part api로 파일 업로드 처리함
        Part part =req.getPart("ofile"); //part가 하나 생성
        String partHeader = part.getHeader("content-disposition");
        System.out.println("partHeader="+partHeader);
        String[] phArr = partHeader.split("filename="); //filename 부분에서 나누어서 리스트로 저장
        String originalFileName=phArr[1].trim().replace("\"","");
        if(!originalFileName.isEmpty()){
            part.write(sDirectory+ File.separator+originalFileName);
        }
        return originalFileName;
    }

    public static String renameFile(String sDirectory, String filename){
        String ext=filename.substring(filename.lastIndexOf(".")); //확장자 분리
        String now =new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        //파일 중복되는 경우를 대비해 제목에 날짜정보 추가해서 새로운 이름 주기
        String newFileName = now+ext;
        File oldFile=new File(sDirectory+File.separator+filename);
        File newFile=new File(sDirectory+File.separator+newFileName);
        oldFile.renameTo(newFile);
        return newFileName;


    }

}
