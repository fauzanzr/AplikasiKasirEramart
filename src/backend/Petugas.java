/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.*;
import koneksi.Database;
import frontend.*;

import javax.swing.JOptionPane;
import static frontend.LoginPetugas.jTextField_username;
import static frontend.LoginPetugas.jTextField_password;
/**
 *
 * @author G512
 */
public class Petugas extends Pegawai{

    @Override
    public void login() {
        try{
            Connection connect = Database.getKoneksi();
            Statement sttmnt = connect.createStatement();
            String query = "SELECT * FROM petugas "
                    + "WHERE username = '"+jTextField_username.getText()+"' "
                    + "&& password = '"+jTextField_password.getText()+"' ";
            
            ResultSet go = sttmnt.executeQuery(query);
            
            int row = 0;
            while(go.next()){
                row = go.getRow();
            }
            if(row == 1){
                JOptionPane.showMessageDialog(null, "Login Berhasil, Selamat Datang !");
                new MenuPetugas().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Login Gagal, Selamat Tinggal !");
                jTextField_username.setText(null);
                jTextField_password.setText(null);
            }
            
        }catch(SQLException e ){
            System.out.println(e);
        }
    }

    @Override
    public void logout() {
        new MenuLogin().setVisible(true);
        JOptionPane.showMessageDialog(null, "Goodbye Petugas !");
    }
    
}
