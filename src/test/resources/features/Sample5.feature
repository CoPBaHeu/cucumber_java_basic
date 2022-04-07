@regression @part5
Feature: Introduction to cucumber part 4
  As a test engineer
  I want to be able to write and execute a scenario with steps that have 2 column tables

  Background:
    Given I am on age page

  Scenario: a new scenario with 2-column table
    When I enter values:
      | name | Ann |
      | age  | 5   |
    And I click submit age
    Then I see message: "Hello, Ann, you are a kid"

  Scenario: another new scenario with 2-column table
    When I enter values:
      | name | Bob |
      | age  | 61  |
    And I click submit age
    Then I see message: "Hello, Bob, you are an adult"

  Scenario Outline: a new scenario outline 2
    When I enter values:
      | name | <name> |
      | age  | <age>  |
    And I click submit age
    Then I see message: "<message>"
    Examples:
      | name | age | message                      |
      | Ann  | 5   | Hello, Ann, you are a kid    |
      | Bob  | 61  | Hello, Bob, you are an adult |

# TODO - create Scenario for 'Give us your feedback!' page
  # URL: https://kristinek.github.io/site/tasks/provide_feedback
  # Navigate to page
  # Set Name, Age and Genre
  # - All input MUST be done in single step
  # - Step can use Map or Domain object
  # Click "Send" button and verify that previous input is displayed in correct fields


  Scenario: a new scenario with the map
    Given I am on feedback page
    When Input from map
      | name  | Carlos |
      | age   | 16     |
      | genre | male   |
    And I click send feedback
    Then I see name "Carlos" in feedback confirmation
    And I see age "16" in feedback confirmation
    And I see genre "male" in feedback confirmation

  @Map
  Scenario Outline: a new scenario outline with the map
    Given I am on feedback page
    When Input from map
      | name  | <name>  |
      | age   | <age>   |
      | genre | <genre> |
    And I click send feedback
    Then I see name "<name>" in feedback confirmation
    And I see age "<age>" in feedback confirmation
    And I see genre "<genre>" in feedback confirmation
    Examples:
      | name     | age | genre  |
      | Carlos   | 16  | male   |
      | Isabella | 20  | female |