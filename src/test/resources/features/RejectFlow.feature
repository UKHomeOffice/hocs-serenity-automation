Feature: If the response is rejected the case is returned to certain stages in the flow

  Background:
    Given I am user "DANNY"

  @RejectFlow @QAResponse @Critical @Workflow @SmokeTests @DCUMIN
    Scenario: DCU MIN Case returned to Initial Draft stage when rejected by QA Response Team
      When The current user creates a single case "DCU MIN"
      And The Data Input Stage is completed for "DCU MIN" caseType
      And I complete the markup stage
      And I complete the Initial Draft stage
      And I reject the case at the QA Response stage
      Then The case should be moved to the "INITIAL DRAFT" stage

  @RejectFlow @QAResponse @Workflow @SmokeTests
  Scenario: DCU TRO Case returned to Initial Draft stage when rejected by QA Response team
    When The current user creates a single case "DCU TRO"
    And The Data Input Stage is completed for "DCU TRO" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I reject the case at the QA Response stage
    Then The case should be moved to the "INITIAL DRAFT" stage

  @RejectFlow @QAResponse @Workflow @SmokeTests
  Scenario: DCU N10 Case returned to Initial Draft stage when rejected by QA Response team
    When The current user creates a single case "DCU N10"
    And The Data Input Stage is completed for "DCU N10" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I reject the case at the QA Response stage
    Then The case should be moved to the "INITIAL DRAFT" stage

  @RejectFlow @PrivateOffice @Workflow @SmokeTests @DCUMIN
  Scenario: DCU MIN Case returned to Initial Draft stage when rejected by Private Office Team
    When The current user creates a single case "DCU MIN"
    And The Data Input Stage is completed for "DCU MIN" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And The case is rejected at the Private Office stage
    Then The case should be moved to the "INITIAL DRAFT" stage

  @RejectFlow @PrivateOffice @Workflow @SmokeTests
  Scenario: DCU N10 Case returned to Initial Draft stage when rejected by Private Office Team
    When The current user creates a single case "DCU N10"
    And I complete the Data Input stage
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And The case is rejected at the Private Office stage
    Then The case should be moved to the "INITIAL DRAFT" stage

  @RejectFlow @MinisterSignOff @Workflow @SmokeTests @DCUMIN
  Scenario: DCU MIN Case returned to Initial Draft stage when rejected by the Minister
    When The current user creates a single case "DCU MIN"
    And The Data Input Stage is completed for "DCU MIN" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And The case is rejected by the Minister
    Then The case should be moved to the "INITIAL DRAFT" stage