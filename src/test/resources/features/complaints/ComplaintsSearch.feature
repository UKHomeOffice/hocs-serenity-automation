@ComplaintsSearch @Complaints
Feature: Complaints Search

#     UKVI COMPLAINTS

  # HOCS-2838, HOCS-3036
  @ComplaintsRegression1 @UKVIComplaints
  Scenario Outline: User tests UKVI complaint case search criteria
    Given I am logged into "CS" as user "COMP_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "COMP" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
      | infoType                          | infoValue                            |
      | Case Type                         | COMP                                 |
      | Case Type                         | COMP2                                |
      | Correspondent Full Name           | Sam McTester                         |
      | Correspondent Postcode            | AB1 2CD                              |
      | Correspondent Email Address       | SamMcTester@Test.com                 |
      | Complainant Date Of Birth         | 01/01/2001                           |
      | Complainant Home Office Reference | Test entry for Home Office Reference |

  # HOCS-2838
  @ComplaintsRegression1 @UKVIComplaints
  Scenario: User can search for a UKVI complaint case by its case reference
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results

    # HOCS-2847 HOCS-3161
  @ComplaintsRegression1 @UKVIComplaints
  Scenario: UKVI complaints user sees the required information when viewing search results
    Given I am logged into "CS" as user "COMP_USER"
    When I navigate to the "search" page
    And I click the search button on the search page
    Then the "COMP Search" workstack should contain only the expected columns

  @UKVIComplaints
  Scenario: User is able to select a COMP2 case reference from the escalate case column of a COMP case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    And I navigate to the "Search" page
    And I search for the complaints case escalated to stage 2 by it's case reference
    And I load the stage 2 UKVI complaints case by selecting its case reference from the Escalate Case column
    Then the case should be loaded


#     BF COMPLAINTS

  # HOCS-4079
  @ComplaintsRegression2 @BFComplaints
  Scenario Outline: User tests BF complaint case search criteria
    Given I am logged into "CS" as user "BF_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "BF" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType                          | infoValue                             |
    | Correspondent full name           | Sam McTester                          |
    | Correspondent postcode            | AB1 2CD                               |
    | Correspondent email address       | SamMcTester@Test.com                  |
    | Complainant date of birth         | 01/01/2001                            |
    | Complainant Home Office Reference | Test entry for Home Office Reference  |

  # HOCS-4079
  @ComplaintsRegression2 @BFComplaints
  Scenario: User can search for a BF complaint case by its case reference
    Given I am logged into "CS" as user "BF_USER"
    When I create a single "BF" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results

  #HOCS-4079, HOCS-4222
  @ComplaintsRegression2 @BFComplaints
  Scenario: BF user sees the required information when viewing search results
    Given I am logged into "CS" as user "BF_USER"
    When I navigate to the "search" page
    And I click the search button on the search page
    Then the "BF Search" workstack should contain only the expected columns


#     BF STAGE 2 COMPLAINTS

  @ComplaintsRegression2 @BFComplaints
  Scenario: User is able to select a BF2 case reference from the escalate case column of a BF case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Registration" stage
    And I navigate to the "Search" page
    And I search for the complaints case escalated to stage 2 by it's case reference
    And I load the stage 2 UKVI complaints case by selecting its case reference from the Escalate Case column
    Then the case should be loaded


#     IEDET COMPLAINTS

  @ComplaintsRegression2 @IEDETComplaints
  Scenario Outline: User tests IEDET complaint case search criteria
    Given I am logged into "CS" as user "IEDET_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "IEDET" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
      | infoType                          | infoValue                            |
      | Correspondent Full Name           | Sam McTester                         |
      | Correspondent Postcode            | AB1 2CD                              |
      | Correspondent Email Address       | SamMcTester@Test.com                 |
      | Complainant Date Of Birth         | 01/01/2001                           |
      | Complainant Home Office Reference | Test entry for Home Office Reference |

  @ComplaintsRegression2 @IEDETComplaints
  Scenario: User can search for a IEDET complaint case by its case reference
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a single "IEDET" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results


#     SMC COMPLAINTS

  @ComplaintsRegression2 @SMCComplaints
  Scenario Outline: User tests SMC complaint case search criteria
    Given I am logged into "CS" as user "SMC_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "SMC" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
      | infoType                          | infoValue                             |
      | Correspondent full name           | Sam McTester                          |
      | Correspondent postcode            | AB1 2CD                               |
      | Correspondent email address       | SamMcTester@Test.com                  |
      | Complainant date of birth         | 01/01/2001                            |
      | Complainant Home Office Reference | Test entry for Home Office Reference  |
      | PSU Reference                     | 123456789                             |

  @ComplaintsRegression2 @SMCComplaints
  Scenario: User can search for a SMC complaint case by its case reference
    Given I am logged into "CS" as user "SMC_USER"
    When I create a single "SMC" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results