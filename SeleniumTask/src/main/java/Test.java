import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test {
    WebDriver driver;
    String username, password, pgName, pgCurrency;

    public Test(String username, String password, String pgName, String pgCurrency) {
        String driverPath = System.getProperty("user.dir").concat("\\dependencies\\chromedrivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.username = username;
        this.password = password;
        this.pgName = pgName;
        this.pgCurrency = pgCurrency;
    }

    public void Start() {
        System.out.println("[INFO] Starting test...");
        LoginPage logPg = new LoginPage(driver);
        MainPage mainPg = logPg.Login(username, password);
        PayGradesPage payGradesPg = mainPg.GoToPayGradesPage();

        payGradesPg.AddPayGrade(pgName, pgCurrency, 400, 500);
        payGradesPg.ShowPayGradesGrid();
        payGradesPg.CheckPayGrade(pgName, pgCurrency);

        payGradesPg.DeletePayGrade(pgName, pgCurrency);
        payGradesPg.ShowPayGradesGrid();
        payGradesPg.CheckPayGrade(pgName, pgCurrency);

        System.out.println("[INFO] Test finished");
    }

    public void Finish() {
        driver.quit();
    }
}
