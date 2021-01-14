package configuration;

import configuration.enums.Browser;

public class WebDriverFactory {

    public WebDriverFactory(){}

    public static WebDriverMain getDriver(Browser browser)
    {
            switch (browser)
            {
                case CHROME:
                    return new ChromeWebDriver();

                default:
                     // nothing
            }
        return null;
    }

}
