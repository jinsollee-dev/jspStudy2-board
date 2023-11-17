package utils;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize,
                                   int blockPage, int pageNum, String reqUrl) {
        String pagingStr = "";
        //전체 페이지 수
        int totalPages = (int)(Math.ceil(((double) totalCount/pageSize)));

        // 이전 페이지 블록 바로가기 출력
            //어떤 블록의 첫페이지
        int pageTemp = (((pageNum-1)/blockPage)*blockPage)+1;
        if(pageTemp !=1){
            pagingStr+="<a href='"+reqUrl+"?pageNum=1'>[첫 페이지]</a>";
            pagingStr+="&nbsp;";
            pagingStr+="<a href='"+reqUrl+"?pageNum="+(pageTemp-1)+"'>[이전블록]</a>";
        }
        //페이지 번호 출력
        int blockCount = 1;
        while (blockCount<=blockPage && pageTemp <=totalPages){
            if(pageTemp ==pageNum){
                pagingStr +="&nbsp;"+pageTemp+"&nbsp;";
            }else{
                pagingStr +="&nbsp;<a href = '"+reqUrl+"?pageNum="+pageTemp+"'>"+pageTemp+"</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }


        // 다음 블록 바로가기 마지막 페이지  출력
        if(pageTemp <=totalPages){
            pagingStr += "<a href ='"+reqUrl+"?pageNum="+pageTemp+"'>[다음블록]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href= '"+reqUrl+"?pageNum="+totalPages+"'>[마지막 페이지]</a>";

        }
        return pagingStr;

    }
}
