package utils;

import configuration.WebDriverMain;
import configuration.enums.Browser;
import configuration.WaitForMain;
import configuration.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class HookBase extends TestBase {

    private static final String SCREENSHOT_PATH = "SCREENSHOT_PATH";
    @Before
    public void initDriver()
    {
        if(!startUp)
        {
            // Choose the browser the suite should run
            TestBase.driver = WebDriverFactory.getDriver(Browser.CHROME);
            wait = WaitForMain.getCustomWait();
        }
        else
            startUp = false;
    }

    @After
    public void cleanUp(Scenario scenario)
    {
        String scenarioName = scenario.getName();
        boolean isFailed = scenario.isFailed();
        if (isFailed) {
            File screenshot = ((TakesScreenshot) WebDriverMain.getWebDriver()).getScreenshotAs(OutputType.FILE);
            try
            {
                FileUtils.getFileUtils().copyFile(screenshot,new File(System.getenv(SCREENSHOT_PATH) + scenarioName +".png"));
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        driver.closeBrowser();
    }
}
