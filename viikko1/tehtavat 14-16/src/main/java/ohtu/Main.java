package ohtu;

import ohtu.laskin.Laskin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*Ensimm‰inen rivi luo sovelluskontekstin. 
T‰m‰n j‰lkeen kontekstilta pyydet‰‰n Laskin-olio ja kutsutaan laskimen k‰ynnist‰v‰‰ metodia.
Spring siis luo konfiguraatioiden ansiosta automaattisesti laskimen ja injektoi sille KonsoliIO-olion.*/
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Laskin laskin = ctx.getBean(Laskin.class);
        laskin.suorita();
    }
}
