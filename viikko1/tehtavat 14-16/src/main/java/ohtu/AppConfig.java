
package ohtu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*K‰yt‰nnˆss‰ luokka siis konfiguroi, ett‰ k‰ytet‰‰n annotaatioihin perustuvaa m‰‰rittely‰, 
ja etsit‰‰n annotoituja luokkia pakkauksen ohtu.laskin alta.*/
@Configuration
@ComponentScan(basePackages = "ohtu.laskin")
public class AppConfig  {}