Feature: Beer cans

  Scenario: Choose a feature of a post
    Given I choose a filter of a feature
    And I select number bathrooms
    When I click on search
    Then I should see posts with that feature