Feature: HOCS User is able to draft a response

  Background:
    Given I am user "<string>"
    And I am at the "Drafting" stage

  @HOCS-285, @HOCS-239
  Scenario: User sees a case deadline
    Then I can see the drafting deadline for a case

  @HOCS-285, @HOCS-239
  Scenario: Case at drafting stage has gone beyond drafting deadline
    When a case has gone beyond the drafting deadline
    Then I can see the final deadline for that case

  @HOCS-287, @HOCS-239
  Scenario: User completes a rejection note
    When a case is not answered by my team
    Then the case is moved to the "Markup" stage

  @HOCS-287, @HOCS-239
  Scenario: User does not complete a rejection note
    When I attempt to reject a case without reason
    Then an error message appears instructing me to add rejection reasons

  @HOCS-288, @HOCS-239
  Scenario: Phone response has info in free text field
    Given I select to reply by "phone"
    And I enter call notes
    When I finish drafting
    And I am taken to the "to do" page

  @HOCS-288, @HOCS-239
  Scenario: Phone response does not have info in free text field
    Given I select to reply by "phone"
    And I do not enter call notes
    When I finish drafting
    Then I see an error message instructing me to enter call notes

  @HOCS-293, @HOCS-239
  Scenario: User selects standard line
    When I click the "FAQ response" button
    And I download the standard line for the case
    Then I can edit it and use it as part of my draft response

  @HOCS-295, @HOCS-239
  Scenario: User does not choose online or offline QA
    Given I have not selected either ‘online QA’ or ‘offline QA’
    When I progress the case
    Then I can see an error message instructing me to select either ‘online QA’ or ‘offline QA’
    And I remain on the page

  @HOCS-296, @HOCS-239
  Scenario: A user selects and sends a case to a QA user
    Given I click the "online QA" button
    And I select an "online" Quality Assurer
    When I progress the case
    Then that Quality Assurer will receive a notification to review the case
    And I am taken to the "to do" page

  @HOCS-297, @HOCS-298, @HOCS-239
  Scenario: User selects offline QA
    Given I click the "offline QA" button
    And I select an "offline" Quality Assurer
    When I progress the case
    Then that Quality Assurer will receive a notification to say they have QA’d that case
    And I am taken to the "to do" page