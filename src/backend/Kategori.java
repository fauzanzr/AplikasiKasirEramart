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
public class Kategori {
    private int idkategori;
    private String namakategori;
    
    public Kategori(){
        
    }
    
    public Kategori(int idkategori, String namakategori){
        this.idkategori = idkategori;
        this.namakategori = namakategori;
    }
    
    public void setNamaKategori(String namakategori){
        this.namakategori = namakategori;
    }
    
    public String getNamaKategori(){
        return namakategori;
    }
    
    public void setIdKategori(int idkategori){
        this.idkategori = idkategori;
    }
    
    public int getIdKategori(){
        return idkategori;
    }
}
