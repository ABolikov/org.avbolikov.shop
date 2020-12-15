Feature: Login

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login page
    When Open dropdown menu
    And Click login button
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When Open dropdown menu
    And click logout button
    Then Check logout

    Examples:
      | username | password | name |
      | admin | admin | admin |



