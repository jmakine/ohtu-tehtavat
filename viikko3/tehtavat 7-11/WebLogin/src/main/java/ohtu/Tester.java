package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Random;

public class Tester {
   
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        //WebDriver driver = new ChromeDriver(); t‰‰ ei toiminu..
        driver.get("http://localhost:4567");
                
        System.out.println("Tulostetaan etusivu 'localhost:4567/'");
        System.out.println(driver.getPageSource());
        
        //sleep(2); n‰ill‰ ei tee mit‰‰n htmldriverin kanssa
        
        // Koska username pit‰‰ olla uniikki, auttaa t‰m‰ uuden k‰ytt‰j‰n luomisen testaamisessa.
        Random r = new Random();    
        
        // --
        //SKENAARIO 1: ep‰onnistunut kirjautuminen: k‰ytt‰j‰‰ ei ole
        // --
        
        System.out.println("SKENAARIO 1: ep‰onnistunut kirjautuminen, k‰ytt‰j‰‰ ei ole:");
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();        
                
        System.out.println("Tulostetaan 'login' sivu:");
        System.out.println(driver.getPageSource());
       
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");        
        element = driver.findElement(By.name("login"));        
        element.submit();   
        
        System.out.println("Tulostetaan 'login' sivu ep‰onnistuneen kirjautumisyrityksen j‰lkeen:");
        System.out.println(driver.getPageSource());
        
        //klikataan eka 'back to home' -> etusivulle
        element = driver.findElement(By.linkText("back to home")); 
        element.click();
        
        //sleep(2);
        
        System.out.println("Tulostetaan etusivu '/':");
        System.out.println(driver.getPageSource());
        
        // --
        //SKENAARIO 2: uuden k‰ytt‰j‰tunnuksen luominen
        // --
        
        System.out.println("SKENAARIO 2: uuden k‰ytt‰j‰tunnuksen luominen:");   
        element = driver.findElement(By.linkText("register new user"));
        element.click();
                
        System.out.println("Tulostetaan 'register new user' sivu:");
        System.out.println(driver.getPageSource());
        
        //Luodaan k‰ytt‰j‰ "jenni+r.nextInt(100000)" salasanalla "jennipenni"      
        element = driver.findElement(By.name("username"));
        element.sendKeys("jenni"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("jennipenni");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jennipenni");
        element = driver.findElement(By.name("signup"));        
        element.submit();
        
        System.out.println("Tulostetaan 'Welcome' sivu");
        System.out.println(driver.getPageSource());
        
        // --
        //SKENAARIO 3: uuden k‰ytt‰j‰tunnuksen luomisen j‰lkeen tapahtuva ulkoskirjautuminen sovelluksesta
        // --
        
        System.out.println("SKENAARIO 3: uuden k‰ytt‰j‰tunnuksen luomisen j‰lkeen tapahtuva ulkoskirjautuminen sovelluksesta:");  
        
        //klikataan 'continue to application mainpage'
        element = driver.findElement(By.linkText("continue to application mainpage")); 
        element.click();
        
        System.out.println("Tulostetaan 'Ohtu Application main page' sivu");
        System.out.println(driver.getPageSource());
        
        //klikataan 'logout' -> etusivulle
        element = driver.findElement(By.linkText("logout")); 
        element.click();
        
        System.out.println("Tulostetaan etusivu '/'");
        System.out.println(driver.getPageSource());
        
        // --
        //SKENAARIO 4: ep‰onnistunut kirjautuminen: oikea k‰ytt‰j‰nimi, v‰‰r‰ salasana
        // --
        
        System.out.println("SKENAARIO 4: ep‰onnistunut kirjautuminen: oikea k‰ytt‰j‰nimi, v‰‰r‰ salasana:");   
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        System.out.println("Tulostetaan 'login' sivu");
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("jenni"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("penni");
        element = driver.findElement(By.name("login"));        
        element.submit();        
        
        System.out.println("Tulostetaan 'login' sivu ep‰onnistuneen kirjautumisyrityksen j‰lkeen (v‰‰r‰ salasana):");
        System.out.println(driver.getPageSource());
               
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
