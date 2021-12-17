import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    By usernameLocator = By.id("txtUsername");
    By passwordLocator = By.id("txtPassword");
    By btnLoginLocator = By.id("btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    public MainPage Login(String username, String password) {
        System.out.println("[INFO] Login...");
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(btnLoginLocator).click();

        String ERROR_LOGIN_URL = "https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials";
        if (driver.getCurrentUrl().equals(ERROR_LOGIN_URL)) {
            throw new RuntimeException("Incorrect username or password");
        }

        System.out.println("[INFO] Login successfully");
        return new MainPage(driver);
    }
}
