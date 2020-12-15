Feature: Login

#  Scenario Outline: Successful Login to the page and logout after
#    Given I open web browser
#    When I navigate to login page
#    When Open dropdown menu
#    And Click login button
#    And I provide username as "<username>" and password as "<password>"
#    And I click on login button
#    Then name should be "<name>"
#    When Open dropdown menu
#    And click logout button
#    Then Check logout
#
#    Examples:
#      | username | password | name |
#      | admin | admin | admin |

#  Scenario Outline: Activation of a menu item
#    Given I open web browser
#    When I navigate to login page
#    When Open dropdown menu
#    And Click login button
#    And I provide username as "<username>" and password as "<password>"
#    And I click on login button
#    Then Check menu admin
#    And check active menu
#
#    Examples:
#      | username | password |
#      | admin | admin |

  Scenario Outline: Add product category
    Given I open web browser
    When I navigate to login page
    When Open dropdown menu
    And Click login button
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then I click menu category
    And I click button add new category

    Examples:
      | username | password |
      | admin | admin |



