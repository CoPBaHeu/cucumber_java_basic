@individual_task_1
Feature: Personal task cucumber part 1
  As a test engineer
  I want to be able to write and execute a scenario outline
  
  #TODO - "https://kristinek.github.io/site/tasks/enter_a_number"
#
#Scenario outline for error cases:
#enter number too small
#enter number too big
#enter text instead of the number
#Scenario for correct number

  Background:
    Given I am on sqrt page


  Scenario Outline: Enter an incorrect number
    When I enter number: "<number>"
    And I click submit number
    Then I see error message: "<message>"
    Examples:
      | number | message               |
      | 5      | Number is too small   |
      | 155    | Number is too big     |
      | zzz    | Please enter a number |

  Scenario: Enter a correct number
    When I enter number: "64"
    And I click submit number
    Then I get result: "Square root of 64 is 8.00"
