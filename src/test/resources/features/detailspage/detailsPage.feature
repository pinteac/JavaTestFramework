Feature: Details Page

  Background:
    Given the user opens the application
    And the user selects "Rome, Italy" location
    And the user selects a Check-In date 7 days from current date
    And the user selects a Check-Out date 7 days from the Check-In date
    And the user selects 2 adults and 1 child
    When I click on the search button


  Scenario:  Verify that the results and details page match the extra filter
    Given the user selects more filters
    And the user select number of bedrooms 5
    And the user selects Pool from Facilities
    When the user clicks Show Stays button
    Then Verify that the properties displayed on the first page have at least the number of selected bedroom
    And the user opens the details of the 1 property
    And Verify that in the Amenities popup Pool is displayed under Facilities category