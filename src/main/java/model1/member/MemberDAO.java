package model1.member;

import common.JDBCConnect;
import common.MySQLConnectPool;

public class MemberDAO extends MySQLConnectPool {
    public MemberDAO() {
        super();
    }

    public MemberDTO getMemberDTO(String uid, String upass) {
        MemberDTO dto = new MemberDTO();
        String query = "select * from member where id=? and pass=?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, uid);
            pstmt.setString(2, upass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getString("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString("name"));
                dto.setRegidate(rs.getDate("regidate"));

            }
        } catch (Exception ex) {

        }
        return dto;

    }

}
