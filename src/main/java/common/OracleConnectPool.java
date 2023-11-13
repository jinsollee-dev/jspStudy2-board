package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleConnectPool {
    public Connection conn;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;

    public OracleConnectPool() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("dbcp_oracle");
            conn = ds.getConnection();
            System.out.println("Oracle DB connect pool success");

        } catch (Exception ex) {
            System.out.println("Oracle DB connect pool fail");
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
            System.out.println("Oracle DB connect pool resource release");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
