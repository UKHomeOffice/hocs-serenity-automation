@FOIDispatch @FOI @FOIWorkflow
Feature: FOI Dispatch

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Dispatch" stage

  Scenario: User is able to complete the Dispatch stage
    And I select "FOI" as the case type
    And I select "Email" as the response channel
    And I submit "Information released in full" as the outcome of the case
    And I confirm my answers for the outcome of the case
    And I select that I "do" want to dispatch the case
    And I submit the date the Final Response was sent
    And I upload a copy of the Final Response
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage

  @FOIRegression
  Scenario: User is able to reject a case at the Dispatch stage
    And I select "FOI" as the case type
    And I select "Email" as the response channel
    And I submit "Information released in full" as the outcome of the case
    And I confirm my answers for the outcome of the case
    And I select that I "do not" want to dispatch the case
    Then the case should be returned to the "Approval" stage
    And the rejected column of the case in the "FOI Approval" workstack should display rejected by "Dispatch"

  @FOIRegression
  Scenario: User is able to select Exemptions on an applicable case
    When I select "FOI" as the case type
    And I select "Email" as the response channel
    And I submit a non-dispatch option as the outcome of the case
    And I select an Exemption
    And I confirm my answers for the outcome of the case
    And I select that I "do" want to dispatch the case
    And I submit the date the Final Response was sent
    And I upload a copy of the Final Response
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage

  @FOIRegression
  Scenario: User is able to select Exceptions on an applicable case
    And I select "EIR" as the case type
    And I select "Email" as the response channel
    And I submit a non-dispatch option as the outcome of the case
    And I select an Exception
    And I confirm my answers for the outcome of the case
    And I select that I "do" want to dispatch the case
    And I submit the date the Final Response was sent
    And I upload a copy of the Final Response
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage

  @FOIRegression
  Scenario: User is able to select Exemptions and Exceptions on an applicable case
    And I select "FOI & EIR" as the case type
    And I select "Email" as the response channel
    And I submit a non-dispatch option as the outcome of the case
    And I select an Exception and an Exemption
    And I confirm my answers for the outcome of the case
    And I select that I "do" want to dispatch the case
    And I submit the date the Final Response was sent
    And I upload a copy of the Final Response
    And I click the "Complete Dispatch" button
    Then the case should be moved to the "Soft Close" stage
