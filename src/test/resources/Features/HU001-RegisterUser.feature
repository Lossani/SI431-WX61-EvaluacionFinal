Feature: HU001-Register an user
  Scenario: CA01
    Given that the user is at the register section
    When the user fill the form with the data requested
    And press the button Cancel
    Then no data will be stored and the user will be redirected to the login
  Scenario: CA02
    Given that the user is at the register section
    When the user fill the form with the data requested
    And press the button Register
    Then a message will show that the registration was successful and the user will be redirected to the login
