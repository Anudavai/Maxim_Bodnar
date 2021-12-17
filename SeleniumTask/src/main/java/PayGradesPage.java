import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PayGradesPage {
    WebDriver driver;
    String payGradesLink;
    By btnAddLocator = By.id("btnAdd");
    By inputNameLocator = By.id("payGrade_name");
    By btnSaveLocator = By.id("btnSave");
    By btnAddCurrency = By.id("btnAddCurrency");
    By inputCurrencyNameLocator = By.id("payGradeCurrency_currencyName");
    By inputMinSalaryLocator = By.id("payGradeCurrency_minSalary");
    By inputMaxSalaryLocator = By.id("payGradeCurrency_maxSalary");
    By btnSaveCurrencyLocator = By.id("btnSaveCurrency");
    By btnDeletePayGradeLocator = By.id("btnDelete");
    By btnDialogDeletePayGradeLocator = By.id("dialogDeleteBtn");
    By payGradesLocator = By.xpath("//table[@class='table hover']/tbody/tr");

    public PayGradesPage(WebDriver driver) {
        this.driver = driver;
        payGradesLink = "https://opensource-demo.orangehrmlive.com/index.php/admin/viewPayGrades";
        this.driver.get(payGradesLink);
    }

    public void AddPayGrade(String name, String currency, Integer minSalary, Integer maxSalary) {
        System.out.println("[INFO] Adding Pay Grade...");
        if(minSalary > maxSalary) {
            throw new RuntimeException("Maximum salary should be greater than minimum");
        }

        // Add PayGrade
        driver.findElement(btnAddLocator).click();
        driver.findElement(inputNameLocator).sendKeys(name);
        driver.findElement(btnSaveLocator).click();

        // Assign Currency
        AssignCurrency(currency, minSalary, maxSalary);
    }

    private void AssignCurrency(String currency, Integer minSalary, Integer maxSalary) {
        System.out.println("[INFO] Assigning currency...");
        driver.findElement(btnAddCurrency).click();
        driver.findElement(inputCurrencyNameLocator).sendKeys(currency);
        driver.findElement(inputMinSalaryLocator).sendKeys(minSalary.toString());
        driver.findElement(inputMaxSalaryLocator).sendKeys(maxSalary.toString());
        driver.findElement(btnSaveCurrencyLocator).click();

        this.driver.get(payGradesLink);
    }

    public void DeletePayGrade(String name, String currency) {
        System.out.println("[INFO] Deleting Pay Grade...");
        String checkboxXpath = "//table[@class='table hover']/tbody/tr/td/a[text()='" + name + "']/../..//input";
        driver.findElement(By.xpath(checkboxXpath)).click();

        driver.findElement(btnDeletePayGradeLocator).click();
        driver.findElement(btnDialogDeletePayGradeLocator).click();
    }

    public void ShowPayGradesGrid() {
        List<WebElement> payGrades = driver.findElements(payGradesLocator);
        System.out.println("\nPay Grades Grid:");
        for (WebElement payGrade : payGrades)
        {
            String innerText = payGrade.getAttribute("innerText");
            String[] fields = Arrays.copyOfRange(innerText.split("\t"), 1, 3);
            System.out.println("Name: " + fields[0] + "\tCurrency: " + fields[1]);
        }
        System.out.println();
    }

    public boolean IsPayGradeExist(String name) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        boolean answer = !driver.findElements(By.linkText(name)).isEmpty();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return answer;
    }

    public void CheckPayGrade(String name, String currency) {
        if (IsPayGradeExist(name)) {
            System.out.println("[CHECK] [" + name + ", " + currency + "] is in PayGrades");
        }
        else {
            System.out.println("[CHECK] [" + name + ", " + currency + "] is not in PayGrades");
        }
    }
}
