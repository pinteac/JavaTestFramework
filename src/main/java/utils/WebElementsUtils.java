package utils;

import configuration.WebDriverMain;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebElementsUtils {

    public static final String ATTRIBUTE = "data-testid";
    public static final String ARIA_LABEL = "aria-label";
    public static final String VALUE = "value";

    public static class TAG{
        public static String LINK = "a", BUTTON = "button", DIV="div";
    }


    private WebElementsUtils(){}

    public static void insertTextOnElement(String inputText, WebElement element)
    {
        String js = "arguments[0].setAttribute('value','"+inputText+"')";
        //String js = "arguments[0].value="+inputText+";";
        ((JavascriptExecutor) WebDriverMain.getWebDriver()).executeScript(js, element);
    }

    public static void executorClick(WebElement element)
    {
        String js = "arguments[0].click();";
        ((JavascriptExecutor) WebDriverMain.getWebDriver()).executeScript(js, element);
    }

    public static void scrollIntoView(WebElement webElement)
    {
        //JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView();", Element);
        String js = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) WebDriverMain.getWebDriver()).executeScript(js,webElement);
    }

    public static void moveToElement(WebElement webElement)
    {
        Actions actions = new Actions(WebDriverMain.getWebDriver());
        actions.moveToElement(webElement).click();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    public static void hoverOverElement(WebElement webElement)
    {
        Actions action = new Actions(WebDriverMain.getWebDriver());
        action.moveToElement(webElement).perform();
    }

    public static String getAttribute(WebElement webElement, String attribute) {
        return webElement.getAttribute(attribute);
    }
}
