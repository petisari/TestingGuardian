import java.sql.Time;

import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class LoginPage extends BasePage {

    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String email, String password) {
        this.waitAndReturnElement(emailInput).sendKeys(email);
        this.waitAndReturnElement(passwordInput).sendKeys(password+"\n");
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }        
        return new DashboardPage(this.driver);
    }
}
