Feature: Map

  Background:
    Given the user opens the application
    And the user selects "Rome, Italy" location
    And the user selects a Check-In date 7 days from current date
    And the user selects a Check-Out date 7 days from the Check-In date
    And the user selects 2 adults and 1 child
    When I click on the search button

  Scenario: Verify that a property is displayed on the map correctly
    When the user hovers over the 1 element
    Then Verify that the property is displayed on the map
    And Verify that the color changes to indicate the selection

  Scenario: Verify that the property displayed on the map has the same details as the property from the page
    When the user hovers over the 1 element
    And the user click on the property on the map
    Then Verify that the details shown in the map popup are the same as the ones shown in the search results
