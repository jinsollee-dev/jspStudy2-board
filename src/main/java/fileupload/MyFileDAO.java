package fileupload;

import common.MySQLConnectPool;

public class MyFileDAO extends MySQLConnectPool {

    public MyFileDAO(){
        super();
    }

    public int insertFile(MyFileDTO dto){
        int result = 0;
        String sql = "insert into myfile(title, cate, ofile, sfile) values(?,?,?,?)";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getCate());
            pstmt.setString(3, dto.getOfile());
            pstmt.setString(4, dto.getSfile());
            result = pstmt.executeUpdate();

        } catch (Exception ex){
            ex.printStackTrace();
        }

        return result;

    }

}
