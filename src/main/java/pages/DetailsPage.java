package pages;

import configuration.WaitForMain;
import configuration.WebDriverMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.WebElementsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DetailsPage extends BasePage {

    private static DetailsPage instance = null;
    private static WaitForMain wait = WaitForMain.getCustomWait();

    private DetailsPage() {
    }

    public static DetailsPage getInstance() {
        if (instance == null)
            instance = new DetailsPage();
        return instance;
    }

    public void initializePageElements(WebDriverMain webDriverMain) {
        webDriverMain.initElements(this);
        waitForPageElements();
    }

    private void waitForPageElements() {
        List<WebElement> webElementList = Arrays.asList(propertyPictures,showAllAmenitiesButton);
        wait.generalWaitForElements(webElementList);
       // wait.generalWaitForElements(elementsToWait);
    }

    // Selectors
    @FindBy(css = "a._13e0raay")
    public static WebElement showAllAmenitiesButton;

    @FindBy(css = "div._1crk6cd")
    public static List<WebElement> categoriesInAmenities;

    @FindBy(xpath = "(//div[@class='_pog3hg'])[1]")
    public static WebElement propertyPictures;

    private static By className = By.className("_1dotkqq");

    public final String FACILITIES = "facilities";
    public final String POOL = "Pool";
    List<WebElement> result = new ArrayList<>();

    public void checkIfPoolIsUnderFacilitiesInAmenities(List<WebElement> result)
    {
        WebElementsUtils.moveToElement(showAllAmenitiesButton);
        wait.generalWaitForElements(categoriesInAmenities);

        for (WebElement element : categoriesInAmenities
        ) {
            if (element.getText().contains(FACILITIES)) {
                WebElementsUtils.scrollIntoView(element);
                result.add(element);
                WebElement webElement = element.findElement(By.xpath(".."));
                List<WebElement> list = webElement.findElements(className);
                for (WebElement el : list
                ) {
                    if (el.getText().equalsIgnoreCase(POOL))
                        result.add(el);
                }
            }
        }
    }
}
