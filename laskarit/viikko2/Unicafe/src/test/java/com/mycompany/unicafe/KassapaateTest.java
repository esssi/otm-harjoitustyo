/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @Test
    public void kassassaOikeinRahaaAlussa(){
        assertTrue(paate.kassassaRahaa() == 100000);
    }
   
    @Test
    public void eiMyytyjaEdullisiaLounaitaAlussa(){
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void eiMyytyjaMaukkaitaLounaitaAlussa(){
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kassanRahamaaraKasvaaOikeinEdullisellaLounaalla(){
        paate.syoEdullisesti(300);
        
        assertTrue(paate.kassassaRahaa() == 100240);
    }
    
    @Test
    public void kassanRahamaaraKasvaaOikeinMaukkaallaLounaalla(){
        paate.syoMaukkaasti(410);
        
        assertTrue(paate.kassassaRahaa() == 100400);
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraOikea(){
        paate.syoEdullisesti(400);
        
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraOikea(){
        paate.syoMaukkaasti(400);
        
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void riittamatonKateismaksuPalautetaanEdullisellaLounaalla(){
        paate.syoEdullisesti(200);
        
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void riittamatonKateismaksuPalautetaanMaukkaallaLounaalla(){
        paate.syoMaukkaasti(300);
        
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void riittamatonKateismaksuEiKasvataEdullistenMaaraa(){
        paate.syoEdullisesti(200);
        
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void riittamatonKateismaksuEiKasvataMaukkaidenMaaraa(){
        paate.syoMaukkaasti(390);
        
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
    }
    
    //maksukorttiin liittyvät testit
    
    @Test
    public void kortiltaVeloitetaanEdullinenPalauttaaTrue(){      
        assertTrue(paate.syoEdullisesti(kortti) == true);
    }
    
    @Test
    public void kortiltaVeloitetaanMaukasPalauttaaTrue(){
        assertTrue(paate.syoMaukkaasti(kortti) == true);
    }
    
    @Test
    public void kortiltaVeloitetaanEdullisenHinta(){
        paate.syoEdullisesti(kortti);
        
        assertTrue(kortti.saldo() == 260);
    }
    
    @Test
    public void kortiltaVeloitetaanMaukkaanHinta(){
        paate.syoMaukkaasti(kortti);
        
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void korttiostoKasvattaaEdullistenMaaraaOikein(){
        paate.syoEdullisesti(kortti);
        
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void korttiostoKasvattaaMaukkaidenMaaraaOikein(){
        paate.syoMaukkaasti(kortti);
        
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josSaldoEiRiitaEdulliseenPalautetaanFalse(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        
        assertTrue(paate.syoEdullisesti(kortti) == false);
    }
    
    @Test
    public void josSaldoEiRiitaMaukkaaseenPalautetaanFalse(){
        paate.syoMaukkaasti(kortti);
        
        assertTrue(paate.syoMaukkaasti(kortti) == false);
    }
    
    @Test
    public void josSaldoEiRiitaEdullistenMaaraEiKasva(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        
        assertTrue(paate.edullisiaLounaitaMyyty() == 2);
    }
    
    @Test
    public void josSaldoEiRiitaMaukkaidenMaaraEiKasva(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void saldoEiMuutuJosEiRahaaEdulliseenLounaaseen(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        
        assertTrue(kortti.saldo() == 20);
    }
    
    @Test
    public void saldoEiMuutuJosEiRahaaMaukkaaseenLounaaseen(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void edullisenLounaanOstoKortillaEiKasvataKassaa(){
        paate.syoEdullisesti(kortti);
        
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void maukkaanLounaanOstoKortillaEiKasvataKassaa(){
        paate.syoMaukkaasti(kortti);
        
        assertTrue(paate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaOikein(){
        paate.lataaRahaaKortille(kortti, 300);
        
        assertTrue(kortti.saldo() == 800);
    }
    
    @Test
    public void kortinLataaminenKasvattaaKassanRahamaaraaOikein(){
        paate.lataaRahaaKortille(kortti, 100);
        
        assertTrue(paate.kassassaRahaa() == 100100);
    }
    
    @Test
    public void kortinLataaminenNegatiivisellaMaarallaEiKasvataSaldoa(){
        paate.lataaRahaaKortille(kortti, -1);
        
        assertTrue(kortti.saldo() == 500);
    }
    
    @Test
    public void kortinLataaminenNegatiivisellaMaarallaEiKasvataKassaa(){
        paate.lataaRahaaKortille(kortti, -1);
        
        assertTrue(paate.kassassaRahaa() == 100000);
    }
}
