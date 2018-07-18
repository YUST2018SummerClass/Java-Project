package jdbc.cecom.yust.edu;

import java.sql.*;
class JDBCExample2 {
    public static void main(String args[]) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
            		"jdbc:mysql://114.70.21.41:3306/JavaClass", "root", "cir@817");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select tableNumber, orderedMenu, price, orderedNumber from `order` ");
            System.out.println("테이블번호 메뉴 \t\t 가격 개수");
            System.out.println(
                "----------------------------------------------");
            while (rs.next()) {
                int tableNum = rs.getInt("tableNumber");
                String menu = rs.getString("orderedMenu");
                int price = rs.getInt("price");
                int orderedNum = rs.getInt("orderedNumber");
                System.out.printf("%8s %s \t%12d %s%n", tableNum, 
                    toUnicode(menu), price, orderedNum);
            }
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
    private static String toUnicode(String str) {    
        try {
            byte[] b = str.getBytes("ISO-8859-1");
            return new String(b);
        }
        catch (java.io.UnsupportedEncodingException uee) {
            System.out.println(uee.getMessage());
            return null;
        }
    }
}
