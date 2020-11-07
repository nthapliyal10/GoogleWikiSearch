@Regression
Feature: To confirm data on an Article without relying on a manual search.


  Scenario Outline: To confirm data on an Article without relying on a manual search.
    Given user opens the google search home page with '<google_url>'
    And search '<wiki_url>' to navigate to wikipedia search page
    When user searches for '<wiki_search>' page in wikipedia
    And wiki search page is displayed with relevant information
    Then verify that coordinates information is correct
    And verify that the logistics data is displayed correctly
    And verify that the site concerns data displayed is correctly
    And open new tab for google maps '<gmap_url>' and verify the coordinates

    Examples:
      | google_url             | wiki_url                  | wiki_search | gmap_url                    |
      | https://www.google.com | https://www.wikipedia.com | Giga Berlin | https://www.google.com/maps |
