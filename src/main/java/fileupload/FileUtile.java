package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
        System.out.println(originalFileName);
        if(!originalFileName.isEmpty()){
            part.write(sDirectory+File.separator+originalFileName);
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

    public static void download(HttpServletRequest req, HttpServletResponse resp,
               String sFileName, String oFileName){

        String sDirectory=req.getServletContext().getRealPath("/uploads");
         try{
            File file = new File(sDirectory, sFileName);
            InputStream inputStream = new FileInputStream(file);
            //한글 파일명 깨짐 방지, 브라우저마다 인코딩 방식이 달라서 if문 사용
            String client =req.getHeader("User-Agent");
            //WOW64-> internet explorer
            if(client.indexOf("WOW64")==-1){
                oFileName=new String(oFileName.getBytes("UTF-8"),
                        "ISO-8859-1");
            } else{
                oFileName=new String(oFileName.getBytes("KSC5601"),
                        "ISO-8859-1");
            }
            //파일 다운로드용 응답 헤드설정
            resp.reset();
            resp.setContentType("application/octet-stream"); //stream형식으로 만듦
            resp.setHeader("Content-Disposition",
                    "attachment;filename=\""+oFileName); //파일 다운로드시 origianlname사용
            resp.setHeader("Content-Lenght", ""+file.length());

            //response에 다운로드 실어서 보냄
            OutputStream outputStream = resp.getOutputStream(); //resposne의 outputstream!
            byte[] b = new byte[(int)file.length()]; //이미지 같은 파일도 받을 수 있게 byte타입
            int readBuffer=0;
            while((readBuffer=inputStream.read(b)) >0){
                outputStream.write(b,0,readBuffer);
            }
            inputStream.close();
            outputStream.close();

        }catch (FileNotFoundException fe){
            System.out.println("파일을 찾을 수 없습니다.");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public static ArrayList<String> multipleFile(HttpServletRequest req, String saveDirectory)
    throws ServletException,IOException {
        ArrayList<String> listFileName=new ArrayList<String>();
        Collection<Part> parts=req.getParts();
        for(Part part : parts){
            if(!part.getName().equals("ofile")){
                continue;
            }
            String partHeader=part.getHeader("content-disposition");
            System.out.println("partHeader="+partHeader);
            String[] phArr=partHeader.split("filename=");
            String originalFileName=phArr[1].trim().replace("\"", "");
            if(!originalFileName.isEmpty()){
                part.write(saveDirectory+File.separator+originalFileName);
            }
            listFileName.add(originalFileName);
        }
        return listFileName;
    }
}
