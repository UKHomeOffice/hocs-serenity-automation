@ComplaintsSearch @Complaints
Feature: Complaints Search

#     UKVI COMPLAINTS

  # HOCS-2838
  @COMPRegression @UKVIComplaints
  Scenario: User can search for a UKVI complaint case by its case reference
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case
    And I search for the case by its case reference
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

  # HOCS-4079S
  @BFRegression @BFComplaints
  Scenario: User can search for a BF complaint case by its case reference
    Given I am logged into "CS" as user "BF_USER"
    When I create a single "BF" case
    And I search for the case by its case reference
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

  @IEDETAndSMCRegression @IEDETComplaints
  Scenario: User can search for a IEDET complaint case by its case reference
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a single "IEDET" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results


#     SMC COMPLAINTS

  @IEDETAndSMCRegression @SMCComplaints
  Scenario: User can search for a SMC complaint case by its case reference
    Given I am logged into "CS" as user "SMC_USER"
    When I create a single "SMC" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results


#     POGR COMPLAINTS

  @POGRRegression @POGRComplaints
  Scenario: User can search for a POGR complaint case by its case reference
    Given I am logged into "CS" as user "POGR_USER"
    When I create a single "POGR" case
    And I search for the case by its case reference
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