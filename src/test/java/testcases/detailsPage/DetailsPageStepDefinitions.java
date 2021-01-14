package testcases.detailsPage;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.TestBase;

import java.util.ArrayList;
import java.util.List;

public class DetailsPageStepDefinitions extends TestBase {

    private List<WebElement> poolIsInFacilities = new ArrayList<>();
    private int selected_number_of_bedrooms;

    @Given("the user selects more filters")
    public void the_user_selects_more_filters() {
        searchPage.initializePageElements(driver);
        searchPage.selectMoreFilters();
    }

    @Given("the user select number of bedrooms {int}")
    public void the_user_select_number_of_bedrooms(Integer nrOfBedrooms) {
        selected_number_of_bedrooms = nrOfBedrooms;
        searchPage.selectNumberOfBedrooms(nrOfBedrooms);
    }

    @Given("the user selects Pool from Facilities")
    public void the_user_selects_pool_from_facilities() {
        searchPage.selectPoolFacility();
    }

    @When("the user clicks Show Stays button")
    public void the_user_clicks_show_stays_button() {
        searchPage.clickToShowStays();
    }

    @Then("Verify that the properties displayed on the first page have at least the number of selected bedroom")
    public void verify_that_the_properties_displayed_on_the_first_page_have_at_least_the_number_of_selected_bedroom() {
        searchPage.refreshTheAccommodationList(driver);
        for (WebElement bedroom: searchPage.accommodationList
        )
            Assert.assertTrue(Integer.valueOf(searchPage.getBedroomCapacity(bedroom)) >= selected_number_of_bedrooms, "The number of bedrooms that is displayed for a property is smaller than the selected number of bedrooms!");
    }

    @Then("the user opens the details of the {int} property")
    public void the_user_opens_the_details_of_the_property(Integer propertyNumber) {
        searchPage.clickOnProperty(propertyNumber);
    }

    @Then("Verify that in the Amenities popup Pool is displayed under Facilities category")
    public void verify_that_in_the_amenities_popup_pool_is_displayed_under_facilities_category() {
        detailsPage.initializePageElements(driver);
        detailsPage.checkIfPoolIsUnderFacilitiesInAmenities(poolIsInFacilities);

        Assert.assertNotNull(poolIsInFacilities, "Facilities category not found in amenities popup!");
        Assert.assertTrue(poolIsInFacilities.get(0).getText().contains(detailsPage.FACILITIES), "Facilities was not found in the amenities!");
        Assert.assertTrue(poolIsInFacilities.get(1).getText().equalsIgnoreCase(detailsPage.POOL), "Pool was not found under facilities!");
    }
}
