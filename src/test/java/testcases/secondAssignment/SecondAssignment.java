package testcases.secondAssignment;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/secondAssignment/secondAssignment.feature"},
    glue = {"utils", "testcases"},
    plugin = {"pretty", "html:target/cucumber"})
public class SecondAssignment extends AbstractTestNGCucumberTests {
}
