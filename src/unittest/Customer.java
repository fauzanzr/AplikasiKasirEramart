/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unittest;

/**
 *
 * @author G512
 */
public class Customer {
    
    public double diskon(double hargaDiskon){
        if((hargaDiskon >= 20000) && (hargaDiskon < 50000)){
            return hargaDiskon - (0.1 * hargaDiskon);
        }else if((hargaDiskon >= 50000) && (hargaDiskon < 100000)){
            return hargaDiskon - (0.2 * hargaDiskon);
        }else if(hargaDiskon >= 100000){
            return hargaDiskon - (0.3 * hargaDiskon);
        }else{
            return hargaDiskon;
        }
    }
    
    public double kembaliMember(double bayar, double hargaDiskon){
        return bayar - hargaDiskon;
    }
    
    public double kembaliNonMember(double bayar, double harga){
        return bayar - harga;
    }
}
