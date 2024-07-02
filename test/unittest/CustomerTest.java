/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package unittest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author G512
 */
public class CustomerTest {
    
    Customer cust = new Customer();
    
    @Test
    public void diskonTest1(){
        double hargaDiskon = 30000;
        double expectedResult = 27000;
        double result = cust.diskon(hargaDiskon);
        assertEquals(expectedResult, result, 0.0);
        System.out.println("Test Diskon 10%");
    }
    
    @Test
    public void diskonTest2(){
        double hargaDiskon = 70000;
        double expectedResult = 56000;
        double result = cust.diskon(hargaDiskon);
        assertEquals(expectedResult, result, 0.0);
        System.out.println("Test Diskon 20%");
    }
    
    @Test
    public void diskonTest3(){
        double hargaDiskon = 100000;
        double expectedResult = 70000;
        double result = cust.diskon(hargaDiskon);
        assertEquals(expectedResult, result, 0.0);
        System.out.println("Test Diskon 30%");
    }
    
    @Test
    public void kembaliMemberTest(){
        double bayar = 100000;
        double hargaDiskon = 70000;
        double expectedResult = 20000;
        double result = cust.kembaliMember(bayar, hargaDiskon);
        assertEquals(expectedResult, result, 0.0);
        System.out.println("Test Kembalian Member");
    }
    
    @Test
    public void kembaliNonMemberTest(){
        double bayar = 100000;
        double harga = 100000;
        double expectedResult = 0;
        double result = cust.kembaliNonMember(bayar, harga);
        assertEquals(expectedResult, result, 0.0);
        System.out.println("Test Kembalian Non-Member");
    }
}
