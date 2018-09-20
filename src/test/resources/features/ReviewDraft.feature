Feature: Review Draft

  Background:
    Given I am user "<string>"
    And I am at the "QA" stage

  @HOCS-309, @HOCS-240
  Scenario: Draft QA user approves case for Private Office
    When I "approve" a case
    Then the case moves to 'Private Office' Stage (see BPMN link in link section)
    And I am taken to the "to do" page

  @HOCS-310, @HOCS-240
  Scenario: User rejects a case
    When I "reject" a case
    Then the case is returned to the Draft stage (BR - DCU MIN – 15) (see Validation for details on 'Reject' button)
    And the drafter becomes the owner
    And I am taken to the "to do" page

  @HOCS-310, @HOCS-240
  Scenario: User does not complete a rejection note
    When I attempt to reject a case without reason
    Then an error message appears instructing me to add rejection reasons
