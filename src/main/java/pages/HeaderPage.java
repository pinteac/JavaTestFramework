package pages;

import configuration.WaitForMain;
import configuration.WebDriverMain;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.SearchCheck;
import utils.WebElementsUtils;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class HeaderPage extends BasePage{

    private static HeaderPage instance = null;
    private static WaitForMain wait = WaitForMain.getCustomWait();

    private HeaderPage() {}

    public static HeaderPage getInstance()
    {
        if(instance == null)
            instance = new HeaderPage();
        return instance;
    }

    // Selectors
    @FindBy(xpath = "(//input[@data-testid='structured-search-input-field-query'])")
    public static WebElement locationSearchInput;

    @FindBy(css = "div._gor68n")
    public static WebElement locationSearchButton;

    @FindBy(css = "button._1mzhry13")
    public static WebElement searchButton;

    @FindBy(xpath = "(//div[@class='_1g5ss3l'])[1]")
    public static WebElement locationFilter;

    @FindBy(xpath = "(//div[@class='_1g5ss3l'])[2]")
    public static WebElement checkDateFilter;

    @FindBy(xpath = "(//div[@class='_1g5ss3l'])[3]")
    public static WebElement guestsFilter;

    @FindBy(xpath = "(//div[@class='_vuaqekp'])[1]")
    public static WebElement checkinDate;

    @FindBy(xpath = "(//div[@class='_vuaqekp'])[2]")
    public static WebElement checkoutDate;

    @FindBy(css = "div._uh2dzp")
    public static WebElement addGuestsInput;

    @FindBy(xpath = "(//button[@class='_7hhhl3'])[2]")
    public static WebElement increaseAdultsButton;

    @FindBy(xpath = "(//button[@class='_7hhhl3'])[4]")
    public static WebElement increaseChildsButton;

    @FindBy(xpath = "(//table[@class='_cvkwaj']/tbody/tr/td)")
    public static List<WebElement> monthDays;

    @FindBy(xpath = "(//div[@class='_umpo2eg'])[2]")
    public static WebElement monthAndYear;

    @FindBy(xpath = "(//button[@class='_187sg6v'])[2]")
    public static WebElement nextMonthButton;

    @FindBy(xpath = "(//div[@class='_uh2dzp'])[1]")
    public static WebElement selectCheckIn;

    @FindBy(xpath = "(//span[@class='_5afswi'])[1]")
    public static WebElement nrOFADULTS;

    @FindBy(xpath = "(//span[@class='_5afswi'])[2]")
    public static WebElement nrOFCHILDS;

    private static By suggestedCityBy = By.className("_1tfpcfm");
    private static By travelSuggestionsBy = By.className("_3uceys");

    private LocalDate keepCheckInDate = null;

    // Methods
    public void initializePageElements(WebDriverMain webDriverMain)
    {
        webDriverMain.initElements(this);
        waitForHeaderElements();
    }

    private void waitForHeaderElements()
    {
        List<WebElement> webElementList = Arrays.asList(locationSearchInput, locationSearchButton);
        wait.generalWaitForElements(webElementList);
        wait.waitForElementTobeLocated(suggestedCityBy);
        wait.generalWaitForPresenceOfAllElements(travelSuggestionsBy);
    }

    public void insertLocation(String location) {
        wait.waitForElementToBeClickable(locationSearchButton);
        WebElementsUtils.executorClick(locationSearchButton);
        locationSearchInput.sendKeys(location);
    }

    public void setCheckInDate(int nrOfdays, WebDriverMain webDriverMain)
    {
        wait.waitForElementToBeClickable(selectCheckIn);
        webDriverMain.initElements(this);
        WebElementsUtils.executorClick(selectCheckIn);
        keepCheckInDate = LocalDate.now().plusDays(nrOfdays);
        calculateDate(keepCheckInDate);
    }

    public void setCheckOutDate(int nrOfdays)
    {
        keepCheckInDate = keepCheckInDate.plusDays(nrOfdays);
        calculateDate(keepCheckInDate);
        keepCheckInDate = null;
    }


    public void addGuests(int noOfAdults, int noOfChilds)
    {
        wait.waitForElementToBeClickable(addGuestsInput);
        WebElementsUtils.executorClick(addGuestsInput);
        for(int i=1;i<=noOfAdults;i++)
            increaseAdultsButton.click();
        for(int i=1;i<=noOfChilds;i++)
            increaseChildsButton.click();
    }

    public void clickOnSearch()
    {
        searchButton.click();
    }

    public void saveSearchFilters(SearchCheck savedSearch)
    {
        savedSearch.searchedLocation = extractLocation(locationSearchInput);
        savedSearch.checkOutDate = extractCheckoutDate(checkinDate,checkoutDate);
        savedSearch.checkInDate = checkinDate.getText() +" – "+savedSearch.checkOutDate;
        savedSearch.numberOfGuests = (Integer.valueOf(extractNumber(nrOFADULTS)) + Integer.valueOf(extractNumber(nrOFCHILDS)) + " guests");
    }

    private String extractLocation(WebElement webElement)
    {
        String[] strings = webElement.getAttribute(WebElementsUtils.VALUE).split(",",2);
        return strings[0];
    }

    private String extractCheckoutDate(WebElement checkinDate, WebElement checkoutDate)
    {
        String checkinMonth, checkoutMonth;
        checkoutMonth = checkoutDate.getText().replaceAll("[^a-zA-Z]", "");
        checkinMonth = checkinDate.getText().replaceAll("[^a-zA-Z]", "");
        if(checkinMonth.equals(checkoutMonth))
            return checkoutDate.getText().replaceAll("[^0-9]","");
        else
            return checkoutDate.getText();
    }

    private String extractNumber(WebElement webElement)
    {
        return webElement.getText().replaceAll("[^0-9]","");
    }

    public void waitForCheckDateFilter()
    {
        wait.waitForElementToBeVisible(checkDateFilter);
    }

    private void calculateDate(LocalDate checkDate)
    {
        String yearMonth = checkDate.getMonth() + " " + checkDate.getYear();
        while(!monthAndYear.getText().equalsIgnoreCase(yearMonth))
        {
            nextMonthButton.click();
            wait.waitForElementToChange(monthAndYear);

           // wait.waitForElementToBeVisible(monthAndYear);
            //waitForHeaderElements();
        }

        int day = checkDate.getDayOfMonth();
        for(WebElement element : monthDays)
        {
            if(element.getText().equals(String.valueOf(day)))
            {
                element.click();
                break;
            }
        }
    }
}
