Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I am user "DANNY"

  @EndToEnd @DCUMIN @Critical
  Scenario: End to end flow with DCU MIN CaseType
    When I create a single case "DCU MIN"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "DCU MIN"
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue

  @EndToEnd @Critical
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single case "DCU N10"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "DCU N10"
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue

  @EndToEnd @Critical
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single case "DCU TRO"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "DCU TRO"
    And I complete the QA response stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue


