package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WebDriverMain {

    protected static WebDriver driver;
    protected boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless","false"));
    private final String applicationURL = "http://www.airbnb.com";

    public WebDriverMain(){initDriver();}

    abstract void initDriver();

    public WebDriver getDriver()
    {
        return driver;
    }

    public static WebDriver getWebDriver()
    {
        return driver;
    }

    public void startApplicationURL()
    {
        driver.get(applicationURL);
        WaitForMain.getCustomWait().waitForPageLoad();
    }

    public void initElements(Object page)
    {
        PageFactory.initElements(driver,page);
    }

    public void closeBrowser()
    {
        if(driver != null)
        {
            driver.quit();
            WaitForMain.deleteCustomWait();
        }
    }
}
