@SMCPermissions @Complaints
Feature: SMC Permissions

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Registration user, when viewing a case, I should only see non-sensitive case data
    Given I am logged into "CS" as user "SMC_USER"
    And I create a "SMC" case and move it to the "CASE CLOSED" stage
    When I switch to user "SMC_REGISTRATION_USER_1"
    And I load the current case
    Then I can only view sections of the Case Details accordion that contain non-sensitive case data
    And I can only see non-sensitive information in the Summary tab

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Registration user, when viewing a case that has passed Registration, I should not be able to see any allocation information
    Given I am logged into "CS" as user "SMC_USER"
    And I create a "SMC" case and move it to the "Triage" stage
    When I switch to user "SMC_REGISTRATION_USER_1"
    And I load the current case
    Then I should not be able to see which stage the case is at
    And I should not be able to see which team the case is currently assigned to
    And I should not be able to see which user is currently assigned to the case

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Casework user, when viewing a case, I should be able to see all case data
    Given I am logged into "CS" as user "SMC_USER"
    And I create a "SMC" case and move it to the "CASE CLOSED" stage, recording all case data
    When I switch to user "SMC_CASEWORK_USER"
    And I load the current case
    Then all case data should be visible in the read-only Case Details accordion
    And I can see all of the cases Summary data

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Registration user, when I view a case, I should only be able to see documents that I uploaded to the case
    Given I am logged into "CS" as user "SMC_REGISTRATION_USER_1"
    And I manage the documents of a new "SMC" case
    And I add a document to the case as the first Registration user
    When I switch to user "SMC_REGISTRATION_USER_2"
    And I add a document to the case as the second Registration user
    Then I should not be able to see the document uploaded by the previous user
    And I should be able to see the document uploaded by the current user

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Casework user, when I view a case, I should be able to see all documents that have been uploaded to the case
    Given I am logged into "CS" as user "SMC_REGISTRATION_USER_1"
    And I manage the documents of a new "SMC" case
    And I add a document to the case as the Registration user
    When I switch to user "SMC_CASEWORK_USER"
    And I add a document to the case as the Casework user
    Then I should be able to see the document uploaded by the previous user
    And I should be able to see the document uploaded by the current user

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Registration user, when I view a case, I should only be able to see case notes that I added to the timeline
    Given I am logged into "CS" as user "SMC_REGISTRATION_USER_1"
    And I create a single "SMC" case
    And I go to the case from the successful case creation screen
    And I add a new case note to the timeline
    When I switch to user "SMC_REGISTRATION_USER_2"
    And I load the current case
    And I add a new case note to the timeline
    Then I should not be able to see the case note added by the previous user
    And I should be able to see the case note added by the current user

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Casework user, when I view a case, I should be able to see all case notes that have been added to the timeline
    Given I am logged into "CS" as user "SMC_REGISTRATION_USER_1"
    And I create a single "SMC" case
    And I go to the case from the successful case creation screen
    And I add a new case note to the timeline
    When I switch to user "SMC_CASEWORK_USER"
    And I load the current case
    And I add a new case note to the timeline
    Then I should be able to see the case note added by the previous user
    And I should be able to see the case note added by the current user

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Registration user, when I view a cases timeline, I should only be able to see logs for case actions I took
    Given I am logged into "CS" as user "SMC_USER"
    And I create a single "SMC" case
    And I switch to user "SMC_REGISTRATION_USER_1"
    And I move the case from "REGISTRATION" stage to "TRIAGE" stage
    And I load the current case
    When I view the "Timeline" tab
    Then I should be able to see logs for case actions taken by the current user
    And I should not be able to see logs for case actions taken by the previous user

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Casework user, when I view a cases timeline, I should only be able to see all of the logs
    Given I am logged into "CS" as user "SMC_REGISTRATION_USER_1"
    And I create a "SMC" case and move it to the "Triage" stage
    And I switch to user "SMC_CASEWORK_USER"
    And I move the case from "TRIAGE" stage to "DRAFT" stage
    And I load the current case
    When I view the "Timeline" tab
    Then I should be able to see logs for case actions taken by the current user
    And I should be able to see logs for case actions taken by the previous user

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: As a SMC Registration user, I should not be able to complete any actions against a case
    Given I am logged into "CS" as user "SMC_REGISTRATION_USER_1"
    And I get a new "SMC" case
    When I attempt to view the "Actions" tab
    Then I should be returned to the dashboard
