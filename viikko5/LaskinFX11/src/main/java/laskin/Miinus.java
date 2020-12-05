/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author jenni.makinen
 */
public class Miinus extends Komento{
    
    private Sovelluslogiikka sovellus;
    //private TextField tuloskentta; 
    private TextField syotekentta; 
    /*private Button nollaa;
    private Button undo;*/
    
    public Miinus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super();
        /*this.tuloskentta=tuloskentta;
        this.syotekentta=syotekentta;
        this.nollaa=nollaa;
        this.undo=undo;*/
        this.sovellus=sovellus;
    }

    @Override
    public void suorita() {   
        int tulosAlussa = sovellus.tulos();
        int lisattavaLuku = Integer.parseInt(this.syotekentta.getText());
        sovellus.miinus(tulosAlussa);
    }
    
    @Override
    public void peru() { 
        
    }
}