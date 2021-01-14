package utils;

import configuration.enums.Browser;
import configuration.WaitForMain;
import configuration.WebDriverFactory;
import configuration.WebDriverMain;

public abstract class TestBase {

    protected static WebDriverMain driver;
    protected static WaitForMain wait;
    public static boolean startUp = false;

    protected pages.HeaderPage headerPage = pages.HeaderPage.getInstance();
    protected pages.SearchPage searchPage = pages.SearchPage.getInstance();
    protected pages.DetailsPage detailsPage = pages.DetailsPage.getInstance();


    static {startSuite();}
    private static void startSuite()
    {
        try{
            // Choose the browser the suite should run
            driver = WebDriverFactory.getDriver(Browser.CHROME);
            wait = WaitForMain.getCustomWait();
            startUp = true;
        }catch (Exception e)
        { e.printStackTrace(); }
    }

}
