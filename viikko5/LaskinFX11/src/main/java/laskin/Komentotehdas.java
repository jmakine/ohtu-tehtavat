/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author jenni.makinen
 */
public class Komentotehdas {
    private HashMap<String, Komento> komennot;
    private Komento tuntematon;
    
    private Sovelluslogiikka sovellus;
    private TextField tuloskentta; 
    private TextField syotekentta; 
    private Button plus;
    private Button miinus; 
    private Button nollaa;
    private Button undo;
    
    public Komentotehdas() {
        komennot = new HashMap<String, Komento>();
        komennot.put("plus", new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus));
        komennot.put("miinus", new Miinus(tuloskentta, syotekentta,  nollaa, undo, sovellus));
        //komennot.put("nollaa", new Nollaa());
        //tuntematon = new Tuntematon(io);
    }

    public Komento hae(String operaatio) {
        return komennot.getOrDefault(operaatio, tuntematon);
    }
}
