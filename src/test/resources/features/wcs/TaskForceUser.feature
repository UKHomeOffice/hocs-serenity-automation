@TaskForceUser @WCS
Feature: Task Force User

  Background:
    Given I am logged into "WCS" as user "WCS_USER"

  Scenario: A task force user can search for the WCS reference of a claim created by a non-task force user
    And I create a single "WCS" claim
    And I move the claim to the "CASEWORK" stage
    When I logout of the application
    And I enter the login credentials for user "TASK_FORCE_USER" and click the login button
    And I navigate to the "SEARCH" page
    And I search for the claim by reference number
    Then the created claim should be displayed as a search result

  Scenario: A task force user can search for a claim by claimant name
    And I create a WCS claim and enter the claimant name "Micky Mouse"
    And I create a WCS claim and enter the claimant name "Minny Mouse"
    When I logout of the application
    And I enter the login credentials for user "TASK_FORCE_USER" and click the login button
    And I navigate to the "search" page
    And I search by the claimant name "Mouse"
    Then all search results should have a claimant name that contains "Mouse"

  @WCSRegression
  Scenario: A task force user should be able to search for, load, and view the documents attached to a claim
    And I create a single "WCS" claim
    And I click manage documents
    And I click add documents
    And I choose the document type "Matrix"
    And I upload a file of type "docx"
    When I logout of the application
    And I enter the login credentials for user "TASK_FORCE_USER" and click the login button
    And I navigate to the "SEARCH" page
    And I search for the claim by reference number
    And I click the reference of the claim in search results
    Then I can see the "docx" file in the uploaded document list
    And the "docx" document should be selected to be displayed in the preview pane
