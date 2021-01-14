Feature: Search

  Background:
    Given the user opens the application

  Scenario: Verify that the results match the search criteria
     Given the user selects "Rome, Italy" location
     And the user selects a Check-In date 7 days from current date
     And the user selects a Check-Out date 7 days from the Check-In date
     And the user selects 2 adults and 1 child
     When I click on the search button
     Then Verify that the applied filters are correct
     And Verify that the properties displayed on the first page can accommodate at least the selected number of guests



