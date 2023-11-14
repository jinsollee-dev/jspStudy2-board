package model1.board;

import common.JDBCConnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDAO extends JDBCConnect {

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
        String sql = "insert into board(id, title, content) values(?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,dto.getId());
            pstmt.setString(2, dto.getTitle());
            pstmt.setString(3, dto.getContent());
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



}
