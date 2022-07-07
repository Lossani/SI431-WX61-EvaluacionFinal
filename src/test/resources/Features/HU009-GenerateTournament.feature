Feature: HU009-Generate a tournament
  Scenario: CA01
    Given that the user is at the tournament section
    When the user press the slider Post a tournament
    And fill the form with the data requested
    And press the button Save
    Then the tournament will be created successfully
  Scenario: CA02
    Given that the user is at the tournament section
    When the user press the slider Post a tournament
    And fill the form with the data requested
    And press the button cancel
    Then the data will not be stored and the tournament will not be created