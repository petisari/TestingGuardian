import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class DashboardPage extends BasePage {

    private By logoutTogglerBy = By.id("my-account-toggle");
    private By logoutBy = By.xpath("//div[1]/header/nav/div[2]/div[3]/ul/li[8]/a");
    private By settingsBy = By.xpath("//div[1]/header/nav/div[2]/div[3]/ul/li[5]/a");
    private By numberInput = By.name("localNumber");
    private By countryCode = By.name("countryCode");
    private By saveButton = By.xpath("//button[@class='css-1u7v6r']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        this.waitAndReturnElement(logoutTogglerBy).click();
        this.waitAndReturnElement(logoutBy).click();
    }

    public void setPhoneNumber() {
        this.waitAndReturnElement(logoutTogglerBy).click();
        this.waitAndReturnElement(settingsBy).click();
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }        
        Select code = new Select(this.driver.findElement(countryCode));
        code.selectByValue("36");
        this.waitAndReturnElement(numberInput).sendKeys("303333333");
        this.waitAndReturnElement(saveButton).click();
    }
}
