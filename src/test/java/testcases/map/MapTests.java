package testcases.map;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/map/map.feature"},
    glue = {"utils", "testcases"},
    plugin = {"pretty", "html:targer/cucumber"})
public class MapTests extends AbstractTestNGCucumberTests {}
