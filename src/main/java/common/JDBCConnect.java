package common;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;

import java.sql.*;


public class JDBCConnect {
    public Connection conn; //db 연결 할 때

    public Statement stmt; //완성형 쿼리 작성하여 전송

    public PreparedStatement pstmt; //파라메터형 쿼리 작성하여 전송

    public ResultSet rs; //Select문 쿼리 결과를 리턴 받을 때 사용

    public JDBCConnect() {
        try {
            //db driver 로드 oracle.jdbc.OracleDraviver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //DB 연결
            String url = "jdbc:mysql://localhost:3306/boarddb?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
            String userid = "ljs";
            String userpw = "1234";
            conn = DriverManager.getConnection(url, userid, userpw);
            System.out.println("DB connection success(default constructor!!!!!)");


        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public JDBCConnect(String driver, String url, String id, String pwd) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, pwd);
            System.out.println("JDBC2 connect!!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JDBCConnect(ServletContext applicaton){
        try{
            String driver= applicaton.getInitParameter("MySQLDriver");
            Class.forName(driver);
            String url = applicaton.getInitParameter("MySQLURL");
            String id = applicaton.getInitParameter("MySQLId");
            String pwd = applicaton.getInitParameter("MySQLPwd");
            conn = DriverManager.getConnection(url, id, pwd);
            System.out.println("JDBC3 Success!!!!!!!");
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();

        } catch (Exception ex) {
        }

    }

}


