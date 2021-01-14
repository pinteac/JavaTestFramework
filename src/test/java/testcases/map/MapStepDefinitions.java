package testcases.map;

import io.cucumber.java.en.*;

import org.testng.Assert;
import utils.PropertyCheck;
import utils.TestBase;
import utils.WebElementsUtils;

public class MapStepDefinitions extends TestBase {

    private PropertyCheck propertyCheck = PropertyCheck.getInstance();
    private PropertyCheck property_Check_map = PropertyCheck.getInstance();

    @When("the user hovers over the {int} element")
    public void the_user_hovers_over_the_element(int elementNumber) {
        searchPage.initializePageElements(driver);
        searchPage.hoverOverElement(elementNumber);
    }

    @Then("Verify that the property is displayed on the map")
    public void verify_that_the_property_is_displayed_on_the_map() {
        propertyCheck.PROPERTY_NAME = searchPage.getNameOfHoveredProperty();
        propertyCheck.PROPERTY_BUTTON = searchPage.getPropertyButton(propertyCheck.PROPERTY_NAME);

        Assert.assertTrue(propertyCheck.PROPERTY_BUTTON.getAttribute(WebElementsUtils.ARIA_LABEL).contains(propertyCheck.PROPERTY_NAME));
    }

    @Then("Verify that the color changes to indicate the selection")
    public void verify_that_the_color_changes_to_indicate_the_selection() {
        Assert.assertFalse(searchPage.getTheColorOfThePropertyButtonOnTheMap(propertyCheck.PROPERTY_BUTTON).contains(searchPage.PROPERTY_COLOR_WHITE));
    }

    @When("the user click on the property on the map")
    public void the_user_click_on_the_property_on_the_map() {
        propertyCheck.PROPERTY_NAME = searchPage.getNameOfHoveredProperty();
        propertyCheck.PROPERTY_BUTTON = searchPage.getPropertyButton(propertyCheck.PROPERTY_NAME);
        searchPage.clickOnPropertyOnTheMap(propertyCheck.PROPERTY_BUTTON);
    }

    @Then("Verify that the details shown in the map popup are the same as the ones shown in the search results")
    public void verify_that_the_details_shown_in_the_map_popup_are_the_same_as_the_ones_shown_in_the_search_results() {
        searchPage.getPropertyDetailsFromSearchResultAndFromTheMap(propertyCheck, property_Check_map);

        Assert.assertEquals(propertyCheck.PROPERTY_NAME, property_Check_map.PROPERTY_NAME,"PropertyCheck names not the same!");
        Assert.assertEquals(propertyCheck.PROPERTY_TYPE, property_Check_map.PROPERTY_TYPE,"PropertyCheck type not the same!");
        Assert.assertEquals(propertyCheck.PROPERTY_RATING, property_Check_map.PROPERTY_RATING,"PropertyCheck rating not the same!");
        Assert.assertEquals(propertyCheck.PROPERTY_PRICE_PER_NIGHT, property_Check_map.PROPERTY_PRICE_PER_NIGHT,"PropertyCheck price per night not the same!");
    }
}
