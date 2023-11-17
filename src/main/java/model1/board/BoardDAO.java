package model1.board;

import common.JDBCConnect;
import common.OracleJDBCConnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDAO extends OracleJDBCConnect {

    public BoardDAO(){
        super();
    }

    public int selectCount(Map<String, Object> map){
        int totalCount = 0;
        String sql = "select count(*) from board";
        if(map.get("searchWord")!=null){
            sql+=" where " + map.get("searchField")+" ";
            sql+=" like '%"+map.get("searchWord")+"%'";
        }

        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                totalCount = rs.getInt(1);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return totalCount;
    }

    public  List<BoardDTO> selectPagingList(Map<String, Object> map){
        List<BoardDTO> bbs = new ArrayList<BoardDTO>();
        //String query = "select * from board";
        String query = "select * from("
                +" select Tb.*, rownum rNum from("
                +" select * from board";

        if(map.get("searchWord")!=null){
            query+=" where "+map.get("searchField")
                    +" like '%"+map.get("searchWord")+"%'";
        }
        query+=" order by num desc) Tb ) "
                +" where rNum between ? and ?";
//        query+=" order by num desc limit ?, ?";
        try{
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,Integer.parseInt(map.get("start").toString()));
            pstmt.setInt(2, Integer.parseInt(map.get("end").toString()));
            rs=pstmt.executeQuery();
            while(rs.next()){
                BoardDTO dto = new BoardDTO();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setId(rs.getString("id"));
                dto.setVisitcount(rs.getInt("visitcount"));
                bbs.add(dto);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return bbs;
    }

    public List<BoardDTO> selectList(Map<String, Object> map){
        List<BoardDTO> bbs = new ArrayList<BoardDTO>();
        String sql = "select * from board";
        if(map.get("searchWord") !=null){
            sql+=" where " +map.get("searchField")+ " ";
            sql+= " like '%"+map.get("searchWord")+"%'";
        }
        sql+= " order by num DESC";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                BoardDTO dto = new BoardDTO();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setId(rs.getString("id"));
                dto.setVisitcount(rs.getInt("visitcount"));
                bbs.add(dto);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bbs;
    }
    public int insertWrite(BoardDTO dto){
        int iResult =-1;
        String sql1 = "select seq_board_num.nextval from dual";
        String sql = "insert into board(id, title, content,num) values(?,?,?,?)";
        try{
            int num = -1;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql1);
            if(rs.next()){
                num=rs.getInt(1);

            }
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,dto.getId());
            pstmt.setString(2, dto.getTitle());
            pstmt.setString(3, dto.getContent());
            pstmt.setInt(4,num);
            iResult=pstmt.executeUpdate();

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return iResult;

    }

    public void updateVisitCount(int num){
        String sql = "update board set visitcount=visitcount+1 where num=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,num);
            pstmt.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public BoardDTO selectView(int num){
        BoardDTO dto = null;
        String sql = "select * from board where num=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,num);
            rs=pstmt.executeQuery();
            if(rs.next()){
                dto=new BoardDTO();
                dto.setNum(num);
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setId(rs.getString("id"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setVisitcount(rs.getInt("visitcount"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return dto;
    }

    public int updateEdit(BoardDTO dto){
        int iResult =-1;
        String sql = "update board set title=?, content=? where num=?";
        try{
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setInt(3, dto.getNum());
            iResult = pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return iResult;
    }

    public int deletePost(int num){
        int iResult =-1;
        String sql = "delete from board where num=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            iResult=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return iResult;
    }


}
