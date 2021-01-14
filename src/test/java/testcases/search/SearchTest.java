package testcases.search;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/search/search.feature"},
    glue = {"utils", "testcases"},
    plugin = {"pretty", "html:target/cucumber"})
public class SearchTest extends AbstractTestNGCucumberTests {}
