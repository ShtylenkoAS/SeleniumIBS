import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(value = Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"org/selenium/framework/steps", "org/selenium/framework/hooks"},
        features = "src/test/features",
        tags = "@all"
)
public class CucumberRunner {
}
