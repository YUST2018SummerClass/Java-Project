package jdbc.cecom.yust.edu;

import java.sql.*;
class JDBCExample1 {
    public static void main(String args[]) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
            		"jdbc:mysql://114.70.21.41:3306/JavaClass", "root", "cir@817");
            System.out.println("데이터베이스에 접속했습니다.");
            conn.close();
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("해당 클래스를 찾을 수 없습니다." + 
                               cnfe.getMessage());
        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
