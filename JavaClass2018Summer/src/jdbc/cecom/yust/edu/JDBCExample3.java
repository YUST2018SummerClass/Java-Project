package jdbc.cecom.yust.edu;

import java.sql.*;
class JDBCExample3 {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Usage: java JDBCExample3 상품명");
            return;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
            		"jdbc:mysql://114.70.21.41:3306/JavaClass", "root", "cir@817");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
            		"select tableNumber, orderedMenu, price, orderedNumber from `order` where tableNumber =" +
                Integer.parseInt(args[0]) );
            System.out.println("테이블번호 메뉴 \t\t 가격 개수");
            System.out.println(
                "----------------------------------------------");
            while (rs.next()) {
                String code = rs.getString("tableNumber");
                String name = rs.getString("orderedMenu");
                int price = rs.getInt("price");
                String maker = rs.getString("orderedNumber");
                System.out.printf("%8s %s \t%12d %s%n", code, 
                    toUnicode(name), price, toUnicode(maker));
            }
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." + 
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
