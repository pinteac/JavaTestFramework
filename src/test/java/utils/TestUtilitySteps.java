package utils;

import io.cucumber.java.en.Given;

public class TestUtilitySteps extends TestBase {

    @Given("the user opens the application")
    public void the_user_opens_the_application() {
        driver.startApplicationURL();
    }

//    @Given("the user clicks on the search button")
//    public void the_user_clicks_on_the_search_button() {
//        headerPage.clickOnSearch();
//    }
}
