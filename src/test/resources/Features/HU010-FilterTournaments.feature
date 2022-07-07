Feature: HU010-Filter tournaments
  Scenario: CA01
    Given that the user is at the tournament section
    When the user press Solo or Teams tournament filter
    Then only the tournaments referring to this filter will be displayed
