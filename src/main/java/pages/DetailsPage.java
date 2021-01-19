package pages;

import configuration.WaitForMain;
import configuration.WebDriverMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PropertyCheck;
import utils.WebElementsUtils;
import java.util.Arrays;
import java.util.List;

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
    }

    // Selectors
    @FindBy(css = "a._13e0raay")
    public static WebElement showAllAmenitiesButton;

    @FindBy(css = "div._1crk6cd")
    public static List<WebElement> categoriesInAmenities;

    @FindBy(xpath = "(//div[@class='_pog3hg'])[1]")
    public static WebElement propertyPictures;

    @FindBy(css = "div._mbmcsn")
    public static WebElement propertyTitle;

    @FindBy(xpath = "(//div[@class='_tqmy57'])[2]")
    public static List<WebElement> propertyDetails;

    @FindBy(css = "div._ymq6as")
    public static WebElement price_per_night;

    public static By nightPrice = By.className("_pgfqnw");


    @FindBy(xpath = "(//button[@class='_z75ovys'])[1]")
    public static WebElement rating;

    public static By ratingNumber = By.className("_1jpdmc0");

    private static By className = By.className("_1dotkqq");

    public final String FACILITIES = "facilities";
    public final String POOL = "Pool";

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

    public void getPropertyDetails(PropertyCheck propertyCheck)
    {
        propertyCheck.PROPERTY_NAME = propertyTitle.getText();
        propertyCheck.PROPERTY_RATING = rating.findElement(ratingNumber).getText();
        propertyCheck.PROPERTY_PRICE_PER_NIGHT = price_per_night.findElement(nightPrice).getText();
    }


}
