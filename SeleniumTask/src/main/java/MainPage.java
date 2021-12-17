import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public PayGradesPage GoToPayGradesPage() {
        return new PayGradesPage(driver);
    }
}
