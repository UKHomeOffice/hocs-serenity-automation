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
    When I "reject" a case
    Then the case is returned to the Mark Up stage (BR - DCU MIN – 12) (see Validation for details on 'Reject' button)
    And I am taken to the "to do" Page

  @HOCS-287, @HOCS-239
  Scenario: User does not complete a rejection note
    Given I attempt to reject a case
    When I do not enter my rejection reasons in the free text field
    Then I cannot click to exit the case
    And an error message appears instructing me to enter my rejection reasons in the free text field

  @HOCS-288, @HOCS-239
  Scenario: User selects to answer correspondence by phone
    When I click to answer by phone (BR - DCU MIN 14) (see validation 'How do you want to reply')
    Then I can see a free text field to enter call notes

  @HOCS-288, @HOCS-239
  Scenario: Phone response has info in free text field
    Given I click to answer by "phone"
    When I enter call notes in the free text field
    Then I can click to exit the case
    And I am taken to the "to do" Page

  @HOCS-288, @HOCS-239
  Scenario: Phone response does not have info in free text field
    Given I click to answer by "phone"
    And I do not enter call notes in the free text field
    When I click to exit the case
    Then I see an error message to enter call notes in the free text field

  @HOCS-291, @HOCS-239
  Scenario: User downloads template for a postal response
    When I click to answer by "post"
    Then I can download a template for the case type
    And it contains the name and address details of the correspondent (see BR MIN-13)
    And it is editable

  @HOCS-291, @HOCS-239
  Scenario: User downloads a template for an email response
    When I click to answer by "email"
    Then I can download a template for the case type
    And it contains the name and email address details of the correspondent (see BR MIN-13)
    And it is editable

  @HOCS-293, @HOCS-239
  Scenario: User sees standard lines on a case
    When I view that case
    Then I can click to download the standard line for that FAQ topic (see BR MIN-21) (see Validation 'Standard Lines')

  @HOCS-293, @HOCS-239
  Scenario: User selects standard line
    When I click the FAQ response (see Validation 'Standard Lines')
    Then the standard line for that case is downloaded
    And I can edit it and used it as part of my draft response

  @HOCS-295, @HOCS-239
  Scenario: User does not choose online or offline QA
    Given I have not selected either ‘online QA’ or ‘offline QA’
    When I click to progress the case
    Then I can see an error message instructing me to select either ‘online QA’ or ‘offline QA’
    And I remain on the page

  @HOCS-296, @HOCS-239
  Scenario: A user selects and sends a case to a QA user
    And I can see a list of ‘online QA’ Quality Assurers
    When I select a Quality Assurer from that list
    Then that Quality Assurer will receive a notification to review the case
    And the case moves on to the next stage as per BPMN
    And I am navigated to my 'to do' page

  @HOCS-297, @HOCS-298, @HOCS-239
  Scenario: User selects offline QA
    And I have clicked ‘offline QA’
    And I can see a list of ‘offline QA’ Quality Assurers
    When I select a Quality Assurer from that list
    Then that Quality Assurer will receive a notification to say they have QA’d that case
    And the case moves on to the next stage as per BPMN?
    And I am navigated to my "to do" page