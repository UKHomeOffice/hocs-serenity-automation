Feature: HOCS User Team Queues, indicators and case assignment

  Background: Given I am user "DANNY"
    And I am on the "HOME" page


    @HOCS-575
    #Number of cases/ per team/ Team member / Unassigned
    Given I am on the "HOME" page
    And I have X cases in my teamqueue
    When I navigate to the "TEAMQUEUE" page
    Then The total of all cases per case type in the workflowqueue will be equal to X

    @HOCS-575
    Scenario: Case is overdue
      Given I create a case <casetype>
      And Set the case to (deadline -1 day)
      When I am on the "HOME" page
      Then My teamqueue should display 1 overdue case

    @HOCS-575
      Given I create a case <casetype>
      And Set the case to (deadline -1 day)
      When I navigate to the "TEAMQUEUE" page
      Then My workflowqueue should display 1 overdue case for that <casetype>

    @HOCS-575
      Given I create X cases
      And Y cases are (deadline -1day)
      And I assign Z to me
      # X - (Z + Y) really
      Then The unassigned cases should be equal to X

    @HOCS-575
      Given I create X cases
      And Y cases are (deadline -1 day)
      And I assign Z to <anotherUser>
      # X - (Z + Y) really
      Then The unassigned cases should be equal to X

    @HOCS-575
      Given I am viewing the 'team' page
      When I view the table
      Then Columns <columns> should be visible

    @HOCS-575
      #Assigning Cases
      Given I create a case
      # Commit case reference number to a VAR and assert against it in final step
      And I am viewing the 'team' page
      When I select the reference number
      Then I will be taken to the casework of that case at the relevant stage

    @HOCS-575
      Given I am on the "TEAMQUEUES" page
      When I select the reference number
      And I assign them to <anotherUser>
      Then The cases are assigned to <anotherUser>

    @HOCS-575
      Given I am on the "TEAMQUEUES" page
      When I select multiple unassigned cases
      And I assign them to me
      Then The cases are assigned to me
      And These cases are added to the total assigned to me on the 'home' page myworkqueue
      And These cases are added to the total assigned to me on the 'team' page

    @HOCS-575
    #Reassign cases to myself
      Given I am on the "HOME" page
      And I have X cases in myworkqueue
      #when i navigate to the team page
      When I navigate to the "TEAMQUEUES" page
      And I select multiple cases assigned to <anotherUser>
      And I assign them to myself
      Then I am taken to the "HOME" page
      And These cases are added to the total assigned to me on the 'home' page myworkqueue
      And These cases are added to the total assigned to me on the 'teams' page

    @HOCS-575
    Scenario reassign cases to another team mate
      Given I am on the "HOME" page
      # and i navigate to the 'team' page
      And I navigate to the "TEAMQUEUES" page
      When I select multiple cases assigned to <anotherUser>
      And I assign them to <anotherUser2>
      Then I am taken to the "HOME" page
      And These cases are not added to the total assigned to me on the 'home' page
      And These cases are not added to the total assigned to me on the 'team' page
      And <anotherUser2> can see the cases are assigned to them on the 'home' page
      And <anotherUser2> can see the cases are assigned to them on the 'team' page

    @HOCS-575
    Scenario Filters
      Given I am on the "TEAMQUEUES" page
      When I enter "Teammate_Name" into the filter
      Then USERS column should only represent Teammate_Name

      Given I am on the "TEAMQUEUES" page
      When I enter "Unassigned" into the filter
      Then USERS column should only represent Unassigned

    @HOCS-575
      Given I am on the "TEAMQUEUES" page
      When I enter <"CaseType"> into the filter
      Then The CASETYPE column should only represent someString

    @HOCS-575
      Given I am on the "TEAMQUEUES" page
      When I enter <"Stage"> into the filter
      | Data Input |
      | Draft      |
      | QA         |
      | Private Office |
      | Minister Sign Off |
      | Dispatch          |
      Then STAGE column should only represent <Stage>

    @HOCS-575
      Given I am on the "TEAMQUEUES" page
      When I enter 'Teammate_Name' into the filter
      Then USERS column should only represent Teammate_Name

    # Hidden Filters
    @HOCS-575
      Given I have a case with <Primary Correspondent>
      # Generate case and commit reference to VAR
      # Below AND comes from a @Given step def, does this work :thinking_face:
      And I am on the "TEAMQUEUES" page
      When I enter <"Primary Correspondent"> into the filer
      Then The case with CaseReference is displayed

    @HOCS-575
      Given I create a case with <Topic>
      # Generate case and commit reference to VAR
      # Below AND comes from a @Given step def, does this work :thinking_face:
      And I am on the "TEAMQUEUES" page
      When I enter <TOPIC> into the filter
      Then The case with CaseReference is displayed

    @HOCS-575
    # BreadCrumbs
      Given I am on the "HOME" page
      When I navigate to the "TEAMQUEUES" page
      Then I am taken to the "TEAMQUEUES" page
      And a Breadcrumb represents the 'team' page which was navigated to

    @HOCS-575
    # Max 20 results Per Page

      Given I bulk create 30 "DCU MIN" cases
      |MIN|
      |TRO|
      |DC10|
      When I navigate to the "TEAMQUEUES" page
      #Commented out in TeamQ stepdefs
      Then I should only see 20 results

    @HOCS-575
    #Filter considers all cases not just first 20 results.

      Given I bulk create 30 "DCU MIN" cases
      # How to generate 30 cases with different data so as to verify what was not 
      # in the initial view is parted of the filtered view.
      When I navigate to the "TEAMQUEUES" page
      And I enter <SomeFilter> into the Filter
      Then the results should include cases from outside the 20 visible results




