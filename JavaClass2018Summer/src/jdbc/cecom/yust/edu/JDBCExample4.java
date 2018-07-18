package jdbc.cecom.yust.edu;

import java.sql.*;
class JDBCExample4 {
    public static void main(String args[]) {
        if (args.length != 4) {
            System.out.println(
                "Usage: java JDBCExample4 상품코드 상품명 가격 제조사");
            return;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
            		"jdbc:mysql://114.70.21.41:3306/JavaClass", "root", "cir@817");
            stmt = conn.createStatement();
            int rowNum = stmt.executeUpdate(
                "insert into `order` (tableNumber, orderedMenu, price, orderedNumber) values('" +
                    toLatin1(args[0]) + "', '" + 
                    toLatin1(args[1]) + "', " + 
                    toLatin1(args[2]) + ", '" +  
                    toLatin1(args[3]) + "');");
            System.out.println(rowNum + "행이 추가되었습니다.");
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("해당 클래스를 찾을 수 없습니다." +
                cnfe.getMessage());
        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        finally {
            try {
                stmt.close();
            }
            catch (Exception ignored) {
            }
            try {
                conn.close();
            }
            catch (Exception ignored) {
            }
        }
    }
    private static String toLatin1(String str) {    
        try {
            byte[] b = str.getBytes();
            return new String(b, "ISO-8859-1");
        }
        catch (java.io.UnsupportedEncodingException uee) {
            System.out.println(uee.getMessage());
            return null;
        }
    }
}
