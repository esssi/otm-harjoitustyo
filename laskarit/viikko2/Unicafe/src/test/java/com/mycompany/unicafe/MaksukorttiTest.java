package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        // saldo on nyt 10+5=15
        
        assertTrue(kortti.saldo() == 15);
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(2);
        //saldo on 8
        
        assertTrue(kortti.saldo() == 8);
    }
    
    @Test
    public void saldoEiMuutuJosMenisiMiinukselle() {
        kortti.otaRahaa(11);
        
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosSaldoa() {
        assertTrue(kortti.otaRahaa(9) == true);
    }
    
    @Test
    public void otaRahaaPalauttaaFalseSaldonYlittyessa() {
        assertTrue(kortti.otaRahaa(11) == false);
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertTrue(kortti.toString().equals("saldo: " + 0 + "." + 10));
    }
}
