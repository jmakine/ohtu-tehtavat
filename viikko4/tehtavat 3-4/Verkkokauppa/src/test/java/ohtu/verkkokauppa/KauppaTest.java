package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        // ja kauppa
        k = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei v‰litetty kutsussa k‰ytetyist‰ parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla (pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa))
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
        // toistaiseksi ei v‰litetty kutsussa k‰ytetyist‰ parametreista
    }
    
    @Test
    public void kahdenEriOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // m‰‰ritell‰‰n ett‰ tuote numero 2 on leip‰ jonka hinta on 5 ja saldo 10
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leip‰", 5));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);       // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);       // ostetaan tuotetta numero 2 eli leip‰‰
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla (pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa))
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));          
    }
    
    @Test
    public void kahdenSamanOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);       // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);       // ostetaan toinen maito
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla (pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa))
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));          
    }
    
    @Test
    public void kahdenEriOstoksenJoistaToinenOnLoppuPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // m‰‰ritell‰‰n ett‰ tuote numero 2 on leip‰ jonka hinta on 5 ja saldo 10
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leip‰", 5));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);       // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);       // ostetaan tuotetta numero 2 eli leip‰‰, joka on loppu
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        // oikealla asiakkaalla, tilinumerolla ja summalla (pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa))
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));          
    }
    
}
