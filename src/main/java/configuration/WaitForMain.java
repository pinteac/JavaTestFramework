package configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitForMain {

    private static Wait wait;
    private static WebDriverWait webDriverWait;
    private static WaitForMain waitForMain;

    private WaitForMain(WebDriver webDriver) {configureFluentWait(webDriver); configureGeneralWait(webDriver);}

    static void createCustomWait(WebDriver webDriver)
    {
        waitForMain = new WaitForMain(webDriver);
    }

    public static WaitForMain getCustomWait()
    {
        return waitForMain;
    }

    static void deleteCustomWait()
    {
        if(waitForMain != null) waitForMain = null;
        if(wait != null) wait = null;
        if(webDriverWait != null) webDriverWait = null;
    }

    private static void configureFluentWait(WebDriver webDriver)
    {
        if(wait == null)
            wait = new FluentWait<>(webDriver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(10))
                    .ignoring(Exception.class);
    }

    private static void configureGeneralWait(WebDriver webDriver)
    {
        if(webDriverWait == null)
            webDriverWait = new WebDriverWait(webDriver,10);
    }

    public void configureImplicitWait(WebDriver webDriver)
    {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void generalWaitForElement(final WebElement webElement)
    {
        try
        {
            Object dumping = wait.until(new Function<WebDriver,WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return webElement;
                }
            });
        }catch(Exception e)
        {
             //do nothing
        }
    }

    public void generalWaitForElements(List<WebElement> webElements)
    {
        if(webDriverWait != null)
            webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void generalWaitForPresenceOfAllElements(By webElements)
    {
        if(webDriverWait != null)
            webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfAllElementsLocatedBy(webElements));
    }

    public void waitForElementNotDisplayed(WebElement webElement)
    {
        if(webDriverWait != null)
            webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(webElement));
    }

    public void waitForElementToBeVisible(WebElement webElement)
    {
        if(webDriverWait != null)
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToBeClickable(WebElement webElement)
    {
        if(webDriverWait != null)
            webDriverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForElementTobeLocated(By by)
    {
        if(webDriverWait != null)
            webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitForPageLoad()
    {
        WebDriverMain.driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
    }
}
