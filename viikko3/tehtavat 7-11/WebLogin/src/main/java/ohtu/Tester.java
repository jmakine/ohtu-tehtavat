package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
   
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        //WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println("Tulostetaan aloitussivu");
        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        // tulostetaan sivu konsoliin
        System.out.println("Tulostetaan 'login' sivu");
        System.out.println(driver.getPageSource());
        
        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        // tulostetaan sivu konsoliin
        System.out.println("Tulostetaan sivu, kun ollaan kirjoitettu username ja password ja painettu 'login'");        
        System.out.println(driver.getPageSource());
        
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
