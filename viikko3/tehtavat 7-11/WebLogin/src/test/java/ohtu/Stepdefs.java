package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @When("nonexistent username {string} is given")
    public void nonexistentUsernameIsGiven(String username){
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);        
        element = driver.findElement(By.name("login"));
        element.submit();
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }   
    
    @Given("command new user is selected")
    public void registerNewIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
    } 

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validDataForRegistrationIsEntered(String username, String password){
        createNewUser(username, password, password);
    }
    
    @Then("a new user is created")
    public void userIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    } 
    
    @When("too short username {string} and valid password {string} and matching password confirmation are entered")
    public void tooShortUsernameIsEntered(String username, String password){
        createNewUser(username, password, password);
    }
    
    @Then("user is not created and error \"username should have at least 3 characters\" is reported")
    public void usernameTooShortErrorMessageIsGiven(){
        pageHasContent("username should have at least 3 characters");
        pageHasContent("Create username and give password");
    }
    
    @When("a valid username {string} and too short password {string} and matching password confirmation are entered")
    public void tooShortPasswordIsEntered(String username, String password){
        createNewUser(username, password, password);
    }
    
    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void passwordTooShortErrorMessageIsGiven(){
        pageHasContent("password should have at least 8 characters");
        pageHasContent("Create username and give password");
    }
    
    @When("a valid username {string} and password {string} are entered, but password confirmation {string} doesn't match")
    public void unmatchingPasswordConfirmationIsGiven(String username, String password, String passwordconf){
        createNewUser(username, password, passwordconf);
    }
    
    @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void passwordconfirmationErrorMessageIsGiven(){
        pageHasContent("password and password confirmation do not match");
        pageHasContent("Create username and give password");
    }
    
    @Given("user with username {string} with password {string} is successfully created")
    public void userIsCreated(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
        createNewUser(username, password, password);
    }
     
    @Given("user with username {string} and password {string} is tried to be created")
    public void userIsTriedToBeCreated(String username, String password){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
        createNewUser(username, password, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    private void createNewUser(String username, String password, String passwordConf){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConf);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
