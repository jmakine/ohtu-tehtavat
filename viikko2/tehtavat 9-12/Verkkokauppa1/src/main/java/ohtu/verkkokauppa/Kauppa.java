package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoInterface varasto;
    private PankkiInterface pankki;
    private Ostoskori ostoskori;
    private ViitegenerInterface viite;
    private String kaupanTili;

    @Autowired
    public Kauppa(VarastoInterface varIntF, PankkiInterface pankkiIntF, ViitegenerInterface viiteIntF) {
        this.varasto = varIntF; //Varasto.getInstance();
        this.pankki = pankkiIntF; //Pankki.getInstance();
        this.viite = viiteIntF; //Viitegeneraattori.getInstance();
        this.kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        this.ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = this.varasto.haeTuote(id); 
        this.varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (this.varasto.saldo(id)>0) {
            Tuote t = this.varasto.haeTuote(id);             
            this.ostoskori.lisaa(t);
            this.varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int newviite = this.viite.uusi();
        int summa = this.ostoskori.hinta();
        
        return this.pankki.tilisiirto(nimi, newviite, tiliNumero, this.kaupanTili, summa);
    }

}
