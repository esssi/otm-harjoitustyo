/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eesaario
 */
public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @Test
     public void konstruktoriAsettaaSaldonOikein(){
      
        assertEquals("Kortilla on rahaa 10.0 euroa",  kortti.toString());
     }
     
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
    
        kortti.syoEdullisesti();
    
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {

        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {

        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        // nyt kortin saldo on 2
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }  
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        // saldo 2.5
        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 2.5 euroa", kortti.toString());
        
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaSaldoa() {
        kortti.lataaRahaa(-1);
        //saldon tulee olla yhä 10
        
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiKunSaldoaEdullisenLounaanVerran() {
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        //nyt saldo on tasan 2.5 eli edullisen lounaan verran
        kortti.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiKunSaldoaMaukkaanLounaanVerran() {
        kortti.lataaRahaa(2.0);
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        //huomaa, että syoMaukkaasti ei toimi jos saldo on 4 tai alle
        kortti.syoMaukkaasti();
        //tämä testi ei siis mene läpi, vaan saldo onkin 4
        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
}
