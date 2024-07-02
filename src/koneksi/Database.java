/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author G512
 */
public class Database {
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        
        try{
            String url = "jdbc:mysql://localhost:3306/eramartbaru";
            String user = "root";
            String password = "";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = DriverManager.getConnection(url,user,password);
            System.out.println("koneksi berhasil");

        }catch(Exception e){
            System.out.println("koneksi gagal");
        }
        return koneksi;
    }
    
    public static int insertQueryGetId(String query) {
        getKoneksi();
        int num = 0;
        int result = -1;

        try {
            Statement stmt = koneksi.createStatement();
            num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public static boolean executeQuery(String query) {
        getKoneksi();
        boolean result = false;

        try {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query);

            result = true;

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ResultSet selectQuery(String query) {
        getKoneksi();
        ResultSet rs = null;

        try {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    //NEW
    
    public static ResultSet read(String sql) throws SQLException{
        ResultSet rs = null;
        
        try {
            koneksi = (Connection) getKoneksi();
            Statement st = koneksi.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
 
        return rs;
    }
    
    public static void closeDB() throws SQLException{
        try {
            koneksi.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public static boolean exec(String sql) throws SQLException{
        try {
            koneksi = (Connection) getKoneksi();
            Statement st = koneksi.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        return false;
    } 
}
