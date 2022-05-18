import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.beans.Transient;
import java.util.*;  


public class Testing {
    public WebDriver driver;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void testLoginAndLogout() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginpage = mainPage.openLogin();

        DashboardPage dash = loginpage.login("petisari+testing@gmail.com", "ghnyZXH2XnZREEu");
        String bodyText = dash.getBodyText();
        Assert.assertTrue(bodyText.contains("My account"));
        
        dash.logout();
        bodyText = dash.getBodyText();
        Assert.assertTrue(bodyText.contains("Sign in"));
    }
    
    @Test
    public void testReadMainPageTitle() {
        MainPage mainPage = new MainPage(this.driver);

        String title = this.driver.getTitle();
        
        Assert.assertEquals(title, "News, sport and opinion from the Guardian's global edition | The Guardian");
    
    }

    @Test
    public void testLoginAndSetPhoneNumber() {

        By numberBy = By.name("localNumber");
        MainPage main = new MainPage(this.driver);
        LoginPage loginpage = main.openLogin();

        DashboardPage dash = loginpage.login("petisari+testing@gmail.com", "ghnyZXH2XnZREEu");
        dash.setPhoneNumber();

        String bodyText = dash.getBodyText();
        WebElement number = this.driver.findElement(numberBy);
        String val = number.getAttribute("value");
        
        Assert.assertEquals(val, "303333333");
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

