package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements ViitegenerInterface {
        
    private int seuraava;
    
    public Viitegeneraattori(){
        this.seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return this.seuraava++;
    }
}
