package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/search/search.feature",
                            "src/test/resources/features/detailspage/detailsPage.feature",
                            "src/test/resources/features/map/map.feature"},
    glue = {"utils", "testcases"},
    plugin = {"pretty", "html:target/cucumber"})
public class RunAllTestsSuite extends AbstractTestNGCucumberTests {}
