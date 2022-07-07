Feature: HU016-Filter tournaments
  Scenario: CA01
    Given that the user is at the news section
    When the user applies a filter Videogames Steam Twitch
    Then only the news referring to this filter will be displayed
