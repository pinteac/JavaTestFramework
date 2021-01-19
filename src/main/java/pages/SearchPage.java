package pages;

import configuration.WaitForMain;
import configuration.WebDriverMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebElementsUtils;
import utils.PropertyCheck;

import java.util.Arrays;
import java.util.List;

public class SearchPage extends BasePage{

    private static SearchPage instance = null;
    private static WaitForMain wait = WaitForMain.getCustomWait();

    private SearchPage() {}

    public static SearchPage getInstance()
    {
        if(instance == null)
            instance = new SearchPage();
        return instance;
    }

    // Selectors
    @FindBy(xpath = "(//button[@data-testid='filterItem-rooms_and_beds-stepper-min_bedrooms-0-increase-button'])")
    public static WebElement addBedroomsButton;

    @FindBy(xpath = "(//button[@data-testid='more-filters-modal-submit-button'])")
    public static WebElement showStaysButton;

    @FindBy(id = "filterItem-facilities-checkbox-amenities-7")
    public static WebElement selectPoolFacility;

    @FindBy(xpath = "(//button[@class='_t6p96s'])[5]")
    public static WebElement moreFiltersButton;

    @FindBy(css = "div._8s3ctt")
    public static List<WebElement> listOfLinks;

    @FindBy(css = "div._8ssblpx")
    public List<WebElement> accommodationList;

    //@FindBy(xpath = "(//div[@aria-label='Map'])")
    @FindBy(css = "aside._1dagk84")
    public static WebElement allMapElements;

    @FindBy(css = "div._1q6k59c")
    public static WebElement linkOfThePropertyOnTheMap;

    public static By roomDetails = By.cssSelector("div._kqh46o");
    private static By property_name = By.cssSelector("div._bzh5lkq");
    private static By property_type = By.cssSelector("div._b14dlit");
    private static By property_rating = By.cssSelector("div._vaj62s");
    private static By property_price = By.xpath("(//div[@class='_ls0e43']//div//div)");
    private static By pagePropertyDetails = By.cssSelector("div._tmwq9g");
    private static By mapPropertyDetails = By.cssSelector("div._1j2gyhf");
    private static By property_map_name = By.cssSelector("div._1isz8pdq");
    private static By property_map_type = By.cssSelector("ol._194e2vt2");
    private static By property_map_rating = By.cssSelector("div._1c50qo6");
    private static By propertyPrice = By.className("_olc9rf0");

    public static WebElement property;
    public static WebElement propertyDetails;
    private static WebElement propertyMapDetails;
    private static int BEDROOM_CAPACITY = 3;
    public String PROPERTY_COLOR_WHITE = "background-color: rgb(255, 255, 255)";


    // Methods
    public void initializePageElements(WebDriverMain webDriverMain)
    {
        webDriverMain.initElements(this);
        waitForPageElements();
    }

    public void initPage(WebDriverMain webDriverMain)
    {
        webDriverMain.initElements(this);
        wait.generalWaitForElements(accommodationList);
    }

    private void waitForPageElements()
    {
        List<WebElement> webElementList = Arrays.asList(allMapElements, moreFiltersButton);
        wait.generalWaitForElements(webElementList);
        wait.generalWaitForElements(accommodationList);
    }

    public void selectMoreFilters()
    {
        wait.generalWaitForElement(moreFiltersButton);
        moreFiltersButton.click();
    }

    public void selectNumberOfBedrooms(int nrOfBedrooms)
    {
        wait.waitForElementToBeClickable(addBedroomsButton);
        for(int i=0;i<nrOfBedrooms;i++)
            addBedroomsButton.click();
    }

    public void selectPoolFacility()
    {
        WebElementsUtils.scrollIntoView(selectPoolFacility);
        selectPoolFacility.click();
    }

    public void clickToShowStays()
    {
        showStaysButton.click();
    }

    public void refreshTheAccommodationList(WebDriverMain webDriverMain)
    {
        wait.generalWaitForElements(accommodationList);
        webDriverMain.initElements(this);
        wait.generalWaitForElements(accommodationList);
    }
    public String getBedroomCapacity(WebElement webElement)
    {
        String[] strings = webElement.findElement(roomDetails).getText().split(" ");
        return strings[BEDROOM_CAPACITY];
    }

    public void clickOnProperty(int propertyNumber)
    {
        wait.waitForElementToBeClickable(listOfLinks.get(propertyNumber));
        openNewTab(listOfLinks.get(propertyNumber));
    }

    public void hoverOverElement(int elementNumber)
    {
        property = accommodationList.get(elementNumber-1);
        WebElementsUtils.hoverOverElement(property);
    }

    public void hoverOverElementWithScroll(int elementNumber)
    {
        property = accommodationList.get(elementNumber-1);
        WebElementsUtils.scrollIntoView(property);
        WebElementsUtils.hoverOverElement(property);
    }

    public String getNameOfHoveredProperty()
    {
        WebElement link = property.findElement(By.tagName(WebElementsUtils.TAG.LINK));
        return WebElementsUtils.getAttribute(link,WebElementsUtils.ARIA_LABEL);
    }

    public WebElement getPropertyButton(String property)
    {
        List<WebElement> ListOfButtons = allMapElements.findElements(By.tagName(WebElementsUtils.TAG.BUTTON));
        for  (WebElement button: ListOfButtons
             ) {
            if(button.getAttribute(WebElementsUtils.ARIA_LABEL).contains(property))
                return button;
        }
        return null;
    }

    public String getTheColorOfThePropertyButtonOnTheMap(WebElement propertyButton)
    {
        List<WebElement> webElement = propertyButton.findElements(By.tagName(WebElementsUtils.TAG.DIV));
        for (WebElement web : webElement
            ) {
            String css = web.getAttribute("style");
            if(css.contains("background-color"))
                return css;
        }
        return null;
    }

    public void clickOnPropertyOnTheMap(WebElement button)
    {
        button.click();
    }

    private void getPropertyTypeShortName(String propertyType)
    {
        String[] strings = propertyType.split(" ",3);
        propertyType = strings[0] + " " + strings[1];
    }
    public String getPropertyGuestsDisplayedInSearch(WebElement webElement)
    {
        String[] strings = webElement.findElement(roomDetails).getText().split(" ",2);
        return strings[0];
    }


    public void getPropertyDetailsFromSearchResultAndFromTheMap(PropertyCheck propertyCheckList, PropertyCheck propertyCheckMap)
    {
        propertyDetails = property.findElement(pagePropertyDetails);
        propertyMapDetails = allMapElements.findElement(mapPropertyDetails);

        propertyCheckList.PROPERTY_NAME = propertyDetails.findElement(property_name).getText();
        propertyCheckList.PROPERTY_TYPE = propertyDetails.findElement(property_type).getText();
        getPropertyTypeShortName(propertyCheckList.PROPERTY_TYPE);

        propertyCheckList.PROPERTY_RATING = propertyDetails.findElement(property_rating).getText();
        propertyCheckList.PROPERTY_PRICE_PER_NIGHT = propertyDetails.findElement(property_price).getText();

        propertyCheckMap.PROPERTY_NAME = propertyMapDetails.findElement(property_map_name).getText();
        propertyCheckMap.PROPERTY_TYPE = propertyMapDetails.findElement(property_map_type).getText();
        getPropertyTypeShortName(propertyCheckMap.PROPERTY_TYPE);

        propertyCheckMap.PROPERTY_RATING = propertyMapDetails.findElement(property_map_rating).getText();
        propertyCheckMap.PROPERTY_PRICE_PER_NIGHT = propertyMapDetails.findElement(property_price).getText();
    }

    public int getPropertyIndexWithTheLowestPrice()
    {
        wait.generalWaitForElements(accommodationList);
        //wait.waitForElementTobeLocated(propertyPrice);
        int min = Integer.MAX_VALUE, index = 0, count = 0;
        String util;
        for (WebElement el: accommodationList
             ) {
            util = el.findElement(propertyPrice).getText();
            count++;
            if(Integer.valueOf(getPriceOfPropertyDisplayedInSearch(util)) < min)
            {
                min = Integer.valueOf(getPriceOfPropertyDisplayedInSearch(util));
                index = count;
            }

        }
        return index;
    }

    private String getPriceOfPropertyDisplayedInSearch(String string)
    {
        String[] strings = string.split(" ");
        return strings[1];
    }

    public void clickOnTheMapPropertyAndOpenDetailsPage()
    {
        openNewTab(linkOfThePropertyOnTheMap);
    }

    public String getPropertyRatingDisplayedInSearch(WebElement webElement)
    {
        String[] strings = webElement.findElement(property_rating).getText().split("/n",2);
        return strings[0];
    }
    public void getPropertyDetailsFromSearchResult(PropertyCheck propertyCheck)
    {
        propertyDetails = property.findElement(pagePropertyDetails);

        propertyCheck.PROPERTY_NAME = propertyDetails.findElement(property_name).getText();
        propertyCheck.PROPERTY_RATING = getPropertyRatingDisplayedInSearch(propertyDetails);
        propertyCheck.PROPERTY_PRICE_PER_NIGHT = property.findElement(propertyPrice).getText();
    }
}
