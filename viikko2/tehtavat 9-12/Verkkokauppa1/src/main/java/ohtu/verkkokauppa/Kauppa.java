package ohtu.verkkokauppa;

public class Kauppa {

    //private Varasto varasto;
    private VarastoInterface varIntF;
    //private Pankki pankki;
    private PankkiInterface pankkiIntF;
    private Ostoskori ostoskori;
    //private Viitegeneraattori viitegeneraattori;
    private ViitegenerInterface viiteIntF;
    private String kaupanTili;

    public Kauppa(VarastoInterface varIntF, PankkiInterface pankkiIntF, ViitegenerInterface viiteIntF) {
        this.varIntF = Varasto.getInstance();
        this.pankkiIntF = Pankki.getInstance();
        this.viiteIntF = Viitegeneraattori.getInstance();
        this.kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varIntF.haeTuote(id); 
        varIntF.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varIntF.saldo(id)>0) {
            Tuote t = varIntF.haeTuote(id);             
            ostoskori.lisaa(t);
            varIntF.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viiteIntF.uusi();
        int summa = ostoskori.hinta();
        
        return pankkiIntF.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
