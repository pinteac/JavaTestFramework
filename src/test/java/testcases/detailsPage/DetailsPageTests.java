package testcases.detailsPage;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/detailspage/detailsPage.feature"},
    glue = {"utils", "testcases"},
    plugin = {"pretty", "html:target/cucumber"})
public class DetailsPageTests extends AbstractTestNGCucumberTests {}
