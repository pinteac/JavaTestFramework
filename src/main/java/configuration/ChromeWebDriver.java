package configuration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

class ChromeWebDriver extends WebDriverMain {

    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_WINDOWS_PATH = "drivers\\windows\\chromedriver.exe";
    private static final String CHROME_UNIX_PATH = "drivers\\unix\\chromedriver.exe";
    private static final String CHROME_MAC_PATH = "drivers\\mac\\chromedriver.exe";

    @Override
    void initDriver() {
        if (OperatingSystem.isWindows())
            System.setProperty(CHROME_DRIVER,CHROME_WINDOWS_PATH);
        else if(OperatingSystem.isMac())
            System.setProperty(CHROME_DRIVER,CHROME_MAC_PATH);
        else if(OperatingSystem.isUnix())
            System.setProperty(CHROME_DRIVER,CHROME_UNIX_PATH);
        else
            Assert.fail("The current operating system is not supported by the framework!");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setHeadless(isHeadless);
        driver = new ChromeDriver(chromeOptions);
        WaitForMain.createCustomWait(driver);
    }
}
