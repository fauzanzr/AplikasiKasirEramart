/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend;

import java.sql.*;
import koneksi.Database;
import backend.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author G512
 */
public class Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Transaksi
     */
    
    DefaultTableModel table = new DefaultTableModel();
    
    public Transaksi() {
        initComponents();
        
        Database.getKoneksi();
        subtotal();
        tanggal();
        
        jTable_keranjang.setModel(table);
        table.addColumn("ID");
        table.addColumn("Nama");
        table.addColumn("Harga");
        table.addColumn("Jumlah");
        table.addColumn("Total");
        
        tampilData();
    }
    
    private void tampilData(){
        //menghapus baris setelah input
        int row = jTable_keranjang.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
        
        String query = "SELECT * FROM keranjang ";
        String procedures = "CALL totalharga()";
        
        try{
            Connection connect = Database.getKoneksi(); //memanggil koneksi
            Statement sttmnt = connect.createStatement(); //membuat statement
            ResultSet rslt = sttmnt.executeQuery(query); //menjalankann query
            
            while (rslt.next()){
                //menampung data sementara
                String kode = rslt.getString("idtransaksi");
                String nama = rslt.getString("namabarang");
                String harga = rslt.getString("harga");
                String jumlah = rslt.getString("jumlah");
                String total = rslt.getString("total");
                    
                //memasukkan semua data kedalam array
                String[] data = {kode,nama,harga,jumlah,total};
                //menambahakan baris sesuai dengan data yang tersimpan di array
                table.addRow(data);
            }
            //melakukan set nilai yang ditampung agar muncul di tabel keranjang
            jTable_keranjang.setModel(table);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void tanggal(){
        Date now = new Date();
        jDateChooser1.setDate(now); 
    }
    
    private void clear(){
        jTextField_namabarang.setText(null);
        jTextField_harga.setText(null);
        jTextField_jumlah.setText(null);
        jTextField_total.setText(null);
        jTextField_idbarang.setText(null);
    }

    private void hapusData(){
        int i = jTable_keranjang.getSelectedRow();
        
        String kode = table.getValueAt(i, 0).toString();
        
        Connection connect = Database.getKoneksi();
        
        String query = "DELETE FROM keranjang WHERE keranjang.idtransaksi = '"+kode+"' ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
            tampilData();
            reset();
        }
        subtotal();
    }
    
    private void keranjang(){
        String kode = jTextField_idbarang.getText();
        String nama = jTextField_namabarang.getText();
        String harga = jTextField_harga.getText();
        String jumlah = jTextField_jumlah.getText();
        String total = jTextField_total.getText();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(date.format(jDateChooser1.getDate()));
        
        Connection connect = Database.getKoneksi();
        //menjalankan query untuk memasukkan data
        
        String query = "INSERT INTO transaksi (tanggal, idtransaksi, idbarang, namabarang, harga, jumlah, total) "
                + "VALUES ('"+tanggal+"', NULL, '"+kode+"', '"+nama+"', '"+harga+"', '"+jumlah+"', '"+total+"')";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Masuk Ke-Keranjang");
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
        }finally{
            tampilData();
            clear();
        }
        
        subtotal();
    }
    
    private void subtotal(){
        String procedures = "CALL totalharga()";
        
        try{
            Connection connect = Database.getKoneksi(); //memanggil koneksi
            Statement sttmnt = connect.createStatement(); //membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures); //menjalanakan query
                while(rslt.next()){
                    jTextField_subtotal.setText(rslt.getString(1));
                }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void total(){
        String harga = jTextField_harga.getText();
        String jumlah = jTextField_jumlah.getText();
        
        int hargaa = Integer.parseInt(harga);
        try{
            int jumlahh = Integer.parseInt(jumlah);
            int total = hargaa * jumlahh;
            String total_harga = Integer.toString(total);
            jTextField_total.setText(total_harga);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Only Number");
            jTextField_jumlah.setText(null);
        }
    }
    
    private void reset(){
        jTextField_bayar.setText(null);
    }
    
    //public void kembalian(){
        //double hargadiskon = Double.parseDouble(jTextField_hargadiskon.getText());
        //double bayar = Double.parseDouble(jTextField_bayar.getText());
        //double kembalian = bayar - hargadiskon;
        //String kembali = Double.toString(kembalian);
        //jTextField_kembali.setText(kembali);
    //}
    
    //public void kembalianTanpaDiskon(){
        //double harga = Double.parseDouble(jTextField_subtotal.getText());
        //double bayar = Double.parseDouble(jTextField_bayar.getText());
        //double kembalian = bayar - harga;
        //String kembali = Double.toString(kembalian);
        //jTextField_kembali.setText(kembali);
    //}
    
    //public void hitungDiskon(){
        //double subtotal = Double.parseDouble(jTextField_subtotal.getText());
        //double grandtotal;
        
        //if((subtotal >= 20000) && (subtotal < 50000) ){
            //grandtotal = subtotal - (0.1 * subtotal);
            //String total = Double.toString(grandtotal);
            //jTextField_hargadiskon.setText(total);
        //}else if((subtotal >= 50000) && (subtotal < 100000) ){
            //grandtotal = subtotal - (0.2 * subtotal);
            //String total = Double.toString(grandtotal);
            //jTextField_hargadiskon.setText(total);
        //}else if(subtotal >= 100000){
            //grandtotal = subtotal - (0.3 * subtotal);
            //String total = Double.toString(grandtotal);
            //jTextField_hargadiskon.setText(total);
        //}else{
            //grandtotal = subtotal;
            //String total = Double.toString(grandtotal);
            //jTextField_hargadiskon.setText(total);
        //}
    //}
    
    public void member(){
        Customer cust = new Customer();
        int opsi = JOptionPane.showConfirmDialog(null, "Apakah Customer memakai member ?", "Customer", JOptionPane.YES_NO_OPTION);
        if(opsi == JOptionPane.YES_OPTION){
            cust.member();
        }else{
            cust.nonmember();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton_pilih = new javax.swing.JButton();
        jTextField_idbarang = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jTextField_namabarang = new javax.swing.JTextField();
        jTextField_harga = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_jumlah = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_total = new javax.swing.JTextField();
        jButton_add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_keranjang = new javax.swing.JTable();
        jButton_delete = new javax.swing.JButton();
        jButton_reset = new javax.swing.JButton();
        jTextField_subtotal = new javax.swing.JTextField();
        jButton_bayar = new javax.swing.JButton();
        jTextField_bayar = new javax.swing.JTextField();
        jTextField_kembali = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton_back = new javax.swing.JButton();
        jTextField_hargadiskon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel1.setText("TRANSAKSI");

        jButton_pilih.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_pilih.setText("PILIH BARANG");
        jButton_pilih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_pilihMouseClicked(evt);
            }
        });
        jButton_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_pilihActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ID BARANG");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("NAMA BARANG");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("HARGA");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("JUMLAH");

        jTextField_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_jumlahKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("TOTAL");

        jTextField_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextField_totalMouseReleased(evt);
            }
        });
        jTextField_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_totalActionPerformed(evt);
            }
        });

        jButton_add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_add.setText("ADD TO CART");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jTable_keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_keranjangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_keranjang);

        jButton_delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_delete.setText("DELETE");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_reset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_reset.setText("RESET");
        jButton_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resetActionPerformed(evt);
            }
        });

        jTextField_subtotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextField_subtotalMouseReleased(evt);
            }
        });

        jButton_bayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_bayar.setText("BAYAR");
        jButton_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bayarActionPerformed(evt);
            }
        });

        jTextField_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_bayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_bayarKeyTyped(evt);
            }
        });

        jTextField_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_kembaliActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("SUBTOTAL");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("BAYAR");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("KEMBALI");

        jButton_back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton_back.setText("BACK");
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
            }
        });

        jTextField_hargadiskon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextField_hargadiskonMouseReleased(evt);
            }
        });
        jTextField_hargadiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_hargadiskonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("HARGA DISKON");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton_add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_total, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_jumlah, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_namabarang, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_pilih, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jTextField_harga, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jButton_back))
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_idbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_delete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_reset))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_kembali, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextField_bayar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_bayar))
                                    .addComponent(jTextField_subtotal, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField_hargadiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_pilih)
                                .addComponent(jTextField_idbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButton_add))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_delete)
                            .addComponent(jButton_reset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_hargadiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addComponent(jButton_back)
                .addGap(21, 21, 21))
        );

        jDateChooser1.setEnabled(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_kembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_kembaliActionPerformed

    private void jTextField_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_totalActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        // TODO add your handling code here:
        keranjang();
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_pilihActionPerformed
        // TODO add your handling code here:
        new PilihBarang().setVisible(true);
    }//GEN-LAST:event_jButton_pilihActionPerformed

    private void jButton_pilihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_pilihMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton_pilihMouseClicked

    private void jTable_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_keranjangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_keranjangMouseClicked

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // TODO add your handling code here:
        hapusData();
        jTextField_bayar.setText(null);
        jTextField_kembali.setText(null);
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resetActionPerformed
        // TODO add your handling code here:
        
        try{
            String clear = "TRUNCATE keranjang";
            Connection connect = Database.getKoneksi();
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(clear);
            ps.execute();
        
        }catch(Exception e){
            System.out.println(e);
        }finally{
            tampilData();
            subtotal();
            jTextField_bayar.setText(null);
            jTextField_kembali.setText(null);
            jTextField_hargadiskon.setText(null);
        }
    }//GEN-LAST:event_jButton_resetActionPerformed

    private void jTextField_subtotalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_subtotalMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_subtotalMouseReleased

    private void jButton_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bayarActionPerformed
        // TODO add your handling code here:
        member();
    }//GEN-LAST:event_jButton_bayarActionPerformed

    private void jTextField_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_bayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_bayarKeyPressed

    private void jTextField_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_bayarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_bayarKeyReleased

    private void jTextField_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_bayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_bayarKeyTyped

    private void jTextField_totalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_totalMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_totalMouseReleased

    private void jTextField_jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_jumlahKeyReleased
        // TODO add your handling code here:
        total();
    }//GEN-LAST:event_jTextField_jumlahKeyReleased

    private void jButton_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_backActionPerformed
        // TODO add your handling code here:
        new MenuPetugas().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton_backActionPerformed

    private void jTextField_hargadiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_hargadiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_hargadiskonActionPerformed

    private void jTextField_hargadiskonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_hargadiskonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_hargadiskonMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_back;
    private javax.swing.JButton jButton_bayar;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_pilih;
    private javax.swing.JButton jButton_reset;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable_keranjang;
    public static javax.swing.JTextField jTextField_bayar;
    public javax.swing.JTextField jTextField_harga;
    public static javax.swing.JTextField jTextField_hargadiskon;
    public javax.swing.JTextField jTextField_idbarang;
    public javax.swing.JTextField jTextField_jumlah;
    public static javax.swing.JTextField jTextField_kembali;
    public javax.swing.JTextField jTextField_namabarang;
    public static javax.swing.JTextField jTextField_subtotal;
    public javax.swing.JTextField jTextField_total;
    // End of variables declaration//GEN-END:variables
}
