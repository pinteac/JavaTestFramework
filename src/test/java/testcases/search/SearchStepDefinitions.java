package testcases.search;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.HeaderPage;
import utils.SearchCheck;
import utils.TestBase;
import static pages.HeaderPage.*;

public class SearchStepDefinitions extends TestBase {

    private SearchCheck propertiesSearchCheck = SearchCheck.getInstance();

    @Given("the user selects {string} location")
    public void the_user_selects_location(String location) {
        headerPage.initializePageElements(driver);
        headerPage.insertLocation(location);
    }

    @Given("the user selects a Check-In date {int} days from current date")
    public void the_user_selects_a_check_in_date_days_from_current_date(int days) {
        headerPage.setCheckInDate(days,driver);
    }

    @Given("the user selects a Check-Out date {int} days from the Check-In date")
    public void the_user_selects_a_check_out_date_days_from_the_check_in_date(int days) {
        headerPage.setCheckOutDate(days);
    }

    @Given("the user selects {int} adults and {int} child")
    public void the_user_selects_adults_and_child(int nrOfAdults, int nrOfChilds) {
        headerPage.addGuests(nrOfAdults,nrOfChilds);
    }

    @When("I click on the search button")
    public void i_click_on_the_search_button() {
        headerPage.saveSearchFilters(propertiesSearchCheck);
        headerPage.clickOnSearch();
    }

    @Then("Verify that the applied filters are correct")
    public void verify_that_the_applied_filters_are_correct() {
        headerPage.waitForCheckDateFilter();

        Assert.assertEquals(checkDateFilter.getText(), propertiesSearchCheck.checkInDate,"Error on checking checkDate filter!");
        Assert.assertEquals(locationFilter.getText(), propertiesSearchCheck.searchedLocation,"Error on checking location filter!");
        Assert.assertEquals(guestsFilter.getText(), propertiesSearchCheck.numberOfGuests, "Error on checking guests number!");
    }

    @Then("Verify that the properties displayed on the first page can accommodate at least the selected number of guests")
    public void verify_that_the_properties_displayed_on_the_first_page_can_accommodate_at_least_the_selected_number_of_guests() {
        searchPage.initializePageElements(driver);

        for (WebElement property: searchPage.accommodationList
        ) {
            Assert.assertTrue(Integer.valueOf(searchPage.getPropertyGuestsDisplayedInSearch(property)) >= Integer.valueOf(HeaderPage.guestsFilter.getText().substring(0,1)), "PropertyCheck displayed with less guests than the selected number of guests!");
        }
    }
}
