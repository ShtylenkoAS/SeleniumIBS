import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(value = Cucumber.class)
@CucumberOptions(
        features = "src.test.features",
        glue = {"org.selenium.framework.steps", "org.selenium.framework.hooks"},
        tags = "all"
)
public class CucumberRunner {
}
