package testcases.secondAssignment;

import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.PropertyCheck;
import utils.TestBase;

public class SecondAssignmentStepDefinitions extends TestBase {

    int lowestPriceIndex;
    private PropertyCheck propertyCheck = new PropertyCheck();
    private PropertyCheck property_Check_map = new PropertyCheck();
    private PropertyCheck propertyDetailsPage = new PropertyCheck();

    @Given("Go trough all the results on the first page and find the one with the lowest price")
    public void go_trough_all_the_results_on_the_first_page_and_find_the_one_with_the_lowest_price() {
        //searchPage.initPage(driver);
        searchPage.initializePageElements(driver);
        lowestPriceIndex = searchPage.getPropertyIndexWithTheLowestPrice();
    }

    @Given("Hover over that property")
    public void hover_over_that_property() {
        searchPage.hoverOverElementWithScroll(lowestPriceIndex);
    }

    @Given("Find the property on the map with a different colour and same title as the hovered one")
    public void find_the_property_on_the_map_with_a_different_colour_and_same_title_as_the_hovered_one() {

        propertyCheck.PROPERTY_NAME = searchPage.getNameOfHoveredProperty();
        property_Check_map.PROPERTY_BUTTON = searchPage.getPropertyButton(propertyCheck.PROPERTY_NAME);
        searchPage.clickOnPropertyOnTheMap(property_Check_map.PROPERTY_BUTTON);

        // searchPage.getPropertyDetailsFromSearchResultAndFromTheMap(propertyCheck,property_Check_map);
        searchPage.getPropertyDetailsFromSearchResult(propertyCheck);
        //propertyCheck.PROPERTY_NAME = searchPage.getNameOfHoveredProperty();
        //propertyCheck.PROPERTY_BUTTON = searchPage.getPropertyButton(propertyCheck.PROPERTY_NAME);
    }

    @When("Click the property from the map and open the details page")
    public void click_the_property_from_the_map_and_open_the_details_page() {
        searchPage.clickOnPropertyOnTheMap(property_Check_map.PROPERTY_BUTTON);
        searchPage.clickOnTheMapPropertyAndOpenDetailsPage();
    }

    @Then("Verify that the details match the details from the search result")
    public void verify_that_the_details_match_the_details_from_the_search_result() {
        detailsPage.initializePageElements(driver);
        detailsPage.getPropertyDetails(propertyDetailsPage);

        Assert.assertEquals(propertyCheck.PROPERTY_NAME, propertyDetailsPage.PROPERTY_NAME,"PropertyCheck names not the same!");
        Assert.assertEquals(propertyCheck.PROPERTY_RATING, propertyDetailsPage.PROPERTY_RATING,"PropertyCheck rating not the same!");
        Assert.assertEquals(propertyCheck.PROPERTY_PRICE_PER_NIGHT, propertyDetailsPage.PROPERTY_PRICE_PER_NIGHT,"PropertyCheck price per night not the same!");
    }
}
