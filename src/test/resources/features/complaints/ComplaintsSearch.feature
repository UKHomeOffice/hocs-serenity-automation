@ComplaintsSearch @Complaints @Search
Feature: Complaints Search

#     UKVI COMPLAINTS

  @COMPRegression @UKVIComplaints
  Scenario: User tests UKVI complaints case search criteria
    Given I am logged into "CS" as user "COMP_USER"
    When I generate a "COMP" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "COMP" into the "Case Type" search field
    And I enter "Sam McTester" into the "Correspondent Full Name" search field
    And I enter "AB1 2CD" into the "Correspondent Postcode" search field
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field
    And I enter "01/01/2001" into the "Complainant Date of Birth" search field
    And I enter "Test entry for HO Reference" into the "Complainant Home Office Reference" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results

    # HOCS-2847 HOCS-3161
  @COMPRegression @UKVIComplaints
  Scenario: UKVI complaints user sees the required information when viewing search results
    Given I am logged into "CS" as user "COMP_USER"
    When I navigate to the "search" page
    And I click the search button on the search page
    Then the "COMP Search" workstack should contain only the expected columns


#     UKVI STAGE 2 COMPLAINTS

  @UKVIComplaints
  Scenario: User is able to select a COMP2 case reference from the escalate case column of a COMP case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    And I navigate to the "Search" page
    And I search for the complaints case escalated to stage 2 by it's case reference
    And I load the stage 2 complaints case by selecting its case reference from the Escalate Case column
    Then the case should be loaded


#     BF COMPLAINTS

  @BFRegression @BFComplaints
  Scenario: User tests BF complaints case search criteria
    Given I am logged into "CS" as user "BF_USER"
    When I generate a "BF" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "Sam McTester" into the "Correspondent Full Name" search field
    And I enter "AB1 2CD" into the "Correspondent Postcode" search field
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field
    And I enter "01/01/2001" into the "Complainant Date of Birth" search field
    And I enter "Test entry for HO Reference" into the "Complainant Home Office Reference" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results

  #HOCS-4079, HOCS-4222
  @BFRegression @BFComplaints
  Scenario: BF user sees the required information when viewing search results
    Given I am logged into "CS" as user "BF_USER"
    When I navigate to the "search" page
    And I click the search button on the search page
    Then the "BF Search" workstack should contain only the expected columns


#     BF STAGE 2 COMPLAINTS

  @BFRegression @BFComplaints
  Scenario: User is able to select a BF2 case reference from the escalate case column of a BF case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Registration" stage
    And I navigate to the "Search" page
    And I search for the complaints case escalated to stage 2 by it's case reference
    And I load the stage 2 complaints case by selecting its case reference from the Escalate Case column
    Then the case should be loaded


#     IEDET COMPLAINTS

  @IEDETRegression @IEDETComplaints
  Scenario: User tests IEDET complaint case search criteria
    Given I am logged into "CS" as user "IEDET_USER"
    When I generate a "IEDET" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "Sam McTester" into the "Correspondent Full Name" search field
    And I enter "AB1 2CD" into the "Correspondent Postcode" search field
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field
    And I enter "01/01/2001" into the "Complainant Date of Birth" search field
    And I enter "Test entry for HO reference" into the "Complainant Home Office reference" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results


#     POGR COMPLAINTS

  @POGRRegression @POGRComplaints
  Scenario: User tests POGR complaints case search criteria
    Given I am logged into "CS" as user "POGR_USER"
    When I generate a "POGR" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "Sam McTester" into the "Correspondent Full Name" search field
    And I enter "AB1 2CD" into the "Correspondent Postcode" search field
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field
    And I enter "01/01/2001" into the "Complainant Date of Birth" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results


#     POGR2 STAGE 2 COMPLAINTS

  @POGRRegression @POGRComplaints
  Scenario: User is able to select a POGR2 case reference from the escalate case column of a POGR case
    Given I am logged into "CS" as user "POGR_USER"
    When I create a "POGR2" case and move it to the "Data Input" stage
    And I navigate to the "Search" page
    And I search for the complaints case escalated to stage 2 by it's case reference
    And I load the stage 2 complaints case by selecting its case reference from the Escalate Case column
    Then the case should be loaded


@POGRRegression @POGRComplaints
Scenario: User is able to search for a POGR HMPO case by its Application reference
  Given I am logged into "CS" as user "POGR_USER"
  And I create a "HMPO" non-Priority POGR case
  And I navigate to the "Search" page
  And I search for the case by the Application Reference
  Then The Pogr case should be visible in the search results


  @POGRRegression @POGRComplaints
  Scenario: User is able to search for a POGR stage 2 HMPO case by its Application reference
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Investigation" stage
    And I navigate to the "Search" page
    And I search for the case by the Application Reference
    Then The Pogr case should be visible in the search results

#  SMC workflow cancelled. Steps and code might be useful for future work implementing PSU specific sub-workflow into other complaints workflows

#     SMC COMPLAINTS

  @SMCComplaints
  Scenario: User tests SMC complaints case search criteria
    Given I am logged into "CS" as user "SMC_USER"
    When I generate a "SMC" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "Sam McTester" into the "Correspondent Full Name" search field
    And I enter "AB1 2CD" into the "Correspondent Postcode" search field
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field
    And I enter "01/01/2001" into the "Complainant Date of Birth" search field
    And I enter "Test entry for HO Reference" into the "Complainant Home Office Reference" search field
    And I enter "123456789" into the "PSU Reference" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results