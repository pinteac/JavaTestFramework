Feature: Second Assignment
Search for properties that match the same filters as the first test.
Go trough all the results on the first page and find the one with the lowest price.
Hover over that property.
Find the property on the map with a different colour and same title as the hovered one (do not assume that it's on the same position on the map, as in the search results).
Click the property from the map and open the details page (not the details card on the map).
Verify that the details match the details from the search result.



  Background:
    Given the user opens the application
    And the user selects "Rome, Italy" location
    And the user selects a Check-In date 7 days from current date
    And the user selects a Check-Out date 7 days from the Check-In date
    And the user selects 2 adults and 1 child
    When I click on the search button


  Scenario:  Verify that the results and details page match the extra filter
    Given Go trough all the results on the first page and find the one with the lowest price
    And Hover over that property
    And Find the property on the map with a different colour and same title as the hovered one
    When Click the property from the map and open the details page
    Then Verify that the details match the details from the search result