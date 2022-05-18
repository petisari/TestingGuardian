import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends BasePage {

    private By searchBarTogglerBy = By.xpath("//header/nav/div[2]/a[@class='top-bar__item popup__toggle hide-until-desktop js-search-toggle js-toggle-ready']");
    private By searchBarBy = By.xpath("//*[@id='gsc-i-id1']");
    private By loginBy = By.xpath("//a[@class='top-bar__item js-navigation-sign-in my-account']");
    private By cookieConsentBy = By.xpath("//button[@class='message-component message-button no-children focusable btn-primary sp_choice_type_11']");
    private By iframeBy = By.xpath("//*[@id='sp_message_iframe_631096']");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.theguardian.com/international");
        
        WebElement iframe = this.driver.findElement(iframeBy);
        this.driver.switchTo().frame(iframe);
        this.waitAndReturnElement(cookieConsentBy).click();
        this.driver.switchTo().defaultContent();
    }    
    
    public SearchResult search(String searchQuery) {
        this.waitAndReturnElement(searchBarTogglerBy).click();
        
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResult(this.driver);
    }

    public LoginPage openLogin() {
        this.waitAndReturnElement(loginBy).click();
        return new LoginPage(this.driver);
    }
}
