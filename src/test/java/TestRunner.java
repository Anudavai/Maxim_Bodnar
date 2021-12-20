import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/DropboxAPI.feature",
                 glue = "com.webapi.stepdefs",
                 plugin = "pretty")
public class TestRunner {
}
