package pages;

import configuration.WaitForMain;
import configuration.WebDriverMain;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

public class BasePage {

    void clickOn(WebElement webElement) {
        int attempts = 0;
        while(attempts < 2) {
            try {
                webElement.click();
                break;
            } catch(StaleElementReferenceException e) {/* Do nothing */ }
            attempts++;
        }
    }

    void openNewTab(WebElement webElement){

        String window=WebDriverMain.getWebDriver().getWindowHandle();

        webElement.click();
        WaitForMain.getCustomWait().waitForPageLoad();

        Set s=WebDriverMain.getWebDriver().getWindowHandles();
        Iterator ite=s.iterator();

        while(ite.hasNext())
        {
            String popupHandle=ite.next().toString();
            if(!popupHandle.contains(window))
                WebDriverMain.getWebDriver().switchTo().window(popupHandle);
        }
    }
}
