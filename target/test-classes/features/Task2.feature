@individual_task_2
Feature: Personal task cucumber part 2
  As a test engineer
  I want to be able to write and execute a scenario outline

  #TODO - In Task2.feature create 1 scenario outline and create scenario or scenario outlines
  # for page https://kristinek.github.io/site/tasks/list_of_people.html or
  # https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html in order to test that user can:

#  add a new person
#  edit a person
#  remove a person
#  reset original list after:
#  adding a person
#  editing a person
#  removing a person
#  check that clear button on adding a user works correctly

  Background:
    Given I am on people with job page

  Scenario Outline: Adding a person
    When I click add person
    And I enter new person:
      | name | <name> |
      | job  | <job>  |
    And I click add
    Then I check that I am on people with job page
    And I see person "<name>" added
    And I see person "<job>" added
    And I reset original list
    Examples:
      | name   | job     |
      | Carlos | Emperor |

  Scenario: Clearing input fields
    When I click add person
    And I enter new person:
      | name | <name> |
      | job  | <job>  |
    And I click clear button
    Then I check that fields are empty

  Scenario: Edit a person
    When I click edit "Mike" person
    Then I check that I am on edit page
    And I put "John" to name field
    And I click edit button
    Then I check that I am on people with job page
    And I see person "John" edited
    And I reset original list

  Scenario: Delete a person
    When I click delete "Jill" person
    Then I check person "Jill" was deleted
    And I reset original list