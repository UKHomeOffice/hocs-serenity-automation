Feature: HOCS User is able to draft a response

  Background:
    Given I am user "<string>"
    And I am at the "Initial Draft" stage

  @HOCS-285, @HOCS-239
  Scenario: User sees a case deadline
    Then I can see the drafting deadline for a case

  @HOCS-287, @HOCS-239
  Scenario: User decides the case is not for them, and completes a rejection note
    When I select a case "should not" answered by my team
    And I "complete" the rejection note
    Then the case is moved to the "Markup" stage
    And a email is sent to the "Markup Team"

  @HOCS-287, @HOCS-239
  Scenario: User decides the case is not for them, does not complete a rejection note
    When I select a case "should not" answered by my team
    And I "do not complete" the rejection note
    Then an error message appears instructing me to add rejection reasons

  @HOCS-288, @HOCS-239
  Scenario: User responds by Phone and fills out call details to complete the stage
    Given I select to reply by "phone"
    When I "complete" the call details
    Then I am taken to the "home" page
    And the case is completed

  @HOCS-288, @HOCS-239
  Scenario: Phone response does not have info in free text field
    Given I select to reply by "phone"
    And I "do not complete" the call details
    Then I see an error message instructing me to enter call notes

  @HOCS-293, @HOCS-239
  Scenario: User is working on a case that has a standard line
    When I select to reply by "letter"
    And I am working on a case that contains a standard line
    And I download the standard line for the case
    Then the standard line downloads locally to my machine so I can edit it as part of my response

  @HOCS-291, @HOCS-291
  Scenario: User downloads a template
    When I select to reply by "letter"
    Then I can download a template for the case type
    And it contains the name and address details of the correspondent
    And it is editable

  @HOCS-296, @HOCS-239
  Scenario: A user selects and sends a case to a QA user
    Given I click the "online QA" button
    And I select an "online" Quality Assurer
    When I progress the case
    Then the case will progress to the QA stage
    And the nominated person of the team that own the case receives an email

  @HOCS-297, @HOCS-298, @HOCS-239
  Scenario: User selects offline QA
    Given I click the "offline QA" button
    And I select an "offline" Quality Assurer from a list of members of the team that own the case
    When I progress the case
    Then the Quality Assurer will receive a notification to say they have QA’d that case
    And the case will progress to the "Private Office" stage
    And I am taken to the "to do" Page
