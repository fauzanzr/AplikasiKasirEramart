/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import koneksi.Database;
import java.sql.*;
import frontend.Transaksi;

import static frontend.Transaksi.jTextField_kembali;
import static frontend.Transaksi.jTextField_subtotal;
import static frontend.Transaksi.jTextField_bayar;
import static frontend.Transaksi.jTextField_hargadiskon;
import javax.swing.JOptionPane;
/**
 *
 * @author G512
 */
public class Customer implements IPembayaran{

    @Override
    public void kembaliMember(){
        double hargadiskon = Double.parseDouble(jTextField_hargadiskon.getText());
        double bayar = Double.parseDouble(jTextField_bayar.getText());
        double kembalian = bayar - hargadiskon;
        String kembali = Double.toString(kembalian);
        jTextField_kembali.setText(kembali);
    }
    
    @Override
    public void kembaliNonMember(){
        double harga = Double.parseDouble(jTextField_subtotal.getText());
        double bayar = Double.parseDouble(jTextField_bayar.getText());
        double kembalian = bayar - harga;
        String kembali = Double.toString(kembalian);
        jTextField_kembali.setText(kembali);
    }
    
    @Override
    public void member() {
        double subtotal = Double.parseDouble(jTextField_subtotal.getText());
        double grandtotal;
        
        if((subtotal >= 20000) && (subtotal < 50000) ){
            grandtotal = subtotal - (0.1 * subtotal);
            String total = Double.toString(grandtotal);
            jTextField_hargadiskon.setText(total);
            JOptionPane.showMessageDialog(null, "Mendapat Diskon 10%");
            
        }else if((subtotal >= 50000) && (subtotal < 100000) ){
            grandtotal = subtotal - (0.2 * subtotal);
            String total = Double.toString(grandtotal);
            jTextField_hargadiskon.setText(total);
            JOptionPane.showMessageDialog(null, "Mendapat Diskon 20%");
            
        }else if(subtotal >= 100000){
            grandtotal = subtotal - (0.3 * subtotal);
            String total = Double.toString(grandtotal);
            jTextField_hargadiskon.setText(total);
            JOptionPane.showMessageDialog(null, "Mendapat Diskon 30%");
            
        }else{
            grandtotal = subtotal;
            String total = Double.toString(grandtotal);
            jTextField_hargadiskon.setText(total);
            JOptionPane.showMessageDialog(null, "Tidak Mendapat Diskon");
        }
        kembaliMember();
    }

    @Override
    public void nonmember() {
        jTextField_hargadiskon.setText("Tidak Mendapat Diskon");
        kembaliNonMember();
    }
    
}
