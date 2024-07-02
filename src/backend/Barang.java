/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author G512
 */
public class Barang {
    private int idbarang;
    Kategori idkategori = new Kategori();
    private String namabarang;
    private int harga;
    private int stok;
    private String tanggal;
    
    public Barang(){
        
    }
    
    public Barang(Kategori idkategori, String namabarang, int harga, int stok, String tanggal){
        this.idkategori = idkategori;
        this.namabarang = namabarang;
        this.harga = harga;
        this.stok = stok;
        this.tanggal = tanggal;
    }
    
    public void setIdBarang(int idbarang){
        this.idbarang = idbarang;
    }
    
    public int getIdBarang(){
        return idbarang;
    }
    
    public void setIdKategori(Kategori idkategori){
        this.idkategori = idkategori;
    }
    
    public Kategori getIdKategori(){
        return idkategori;
    }
    
    public void setNamaBarang(String namabarang){
        this.namabarang = namabarang;
    }
    
    public String getNamaBarang(){
        return namabarang;
    }
    
    public void setHarga(int harga){
        this.harga = harga;
    }
    
    public int getHarga(){
        return harga;
    }
    
    public void setStok(int stok){
        this.stok = stok;
    }
    
    public int getStok(){
        return stok;
    }
    
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    
    public String getTanggal(){
        return tanggal;
    }
}
