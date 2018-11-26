Feature: Team members can allocate work

  Background:
    Given I am a user from the "<string>" team
    And I am on the Workstacks page

    @HOCS-402
    Scenario: A user is in no teams
      Given I am in no teams
      When I view my workstacks
      Then I see a sign up message

    @HOCS-402
    Scenario: A user is in a team with no or zero cases
      Given I am in "team A"
      And "team A" has 0 cases
      When I view my workstacks
      Then I see a 0 value for "team A"

    @HOCS-402
      # Another team member should be 'assign to' some specific user
    Scenario: A user is in one team
      Given I am in "team A"
      And "team A" has 5 cases
      Then I assign 3 to me
      # And leave one unassigned (this is implied)
      And assign 1 case to another team member
      When I view the workstacks page
      Then I see that I have 3 cases assigned to me
      And I see "team A" has 5 cases in total
      And I see "team A" has 1 unassigned case

    @HOCS-402
    Scenario: A user is in a team with cases and a team without cases
      Given I am in "team A"
      And I am in "team B"
      And I create 5 cases
      And I assign 5 cases to "team A"
      # And "team B" has 0 cases this is implied
      When I view my workstacks
      Then I see that team A has 5 cases total
      And I do not see team B

    @HOCS-402
    Scenario: A user is in two teams
      Given I am in team A
      And team A has 5 cases (Generate or not)
      Then I assign 3 to me
      And leave 1 unassigned
      And assign 1 to another team member
      And I am in team C
      And team C has 3 cases
      And I assign 1 case to myself
      And leave one case unassigned (no action needed here)
      When I view my workstacks
      Then I see that I have 5 cases assigned to me
      And I see team A has 5 cases total  (consider run this test X times and have a single assertion per test)
      And I see team A has 1 unassigned case
      And I see team C has 3 cases total
      And I see team C has 1 unassigned case

    @HOCS-402
    Scenario: A user has no allocated work
      Given I am in team A
      And team A has 5 cases (generate these)
      And none are assigned to me
      And 1 is unassigned
      And 1 is assigned to someone else
      When I view my workstacks
      Then I see that I have 0 cases assigned to me  (again, re run test, single assertion per run, create a 'No Allocated Work' tag or configuration)
      And I see team A has 5 cases total
      And I see team A has 1 unassigned case

    @HOCS-402
    Scenario: A user has an overdue case 
    #(How do you make a case overdue?  deadline of -1 day)
      Given I am in team A
      And I create a case with "deadline -1 day"
      And I assign this to myself
      And I create 4 more cases
      And assign 2 to myself
      When I view my workstacks
      Then I see that I have 3 cases assigned to me
      And I see 1 of my cases is overdue
      And I see team A has 5 cases total  (duplicate test and assert 3 times singularly)

    @HOCS-402
    Scenario: A user is in a team with an overdue case
      Given I am in team A
      And I create a case with deadline -1 day
      And I create 4 more cases
      When I view my workstacks
      And I see team A has 5 cases total
      And I see 1 is overdue

    @HOCS-402
    Scenario: A user has a priority case
      Given I am in team A
      And I generate 5 cases in my team
      And assign 3 to myself
      And a case assigned to me is priority (how does it become priority, how is it noted as a priority)
      When I view my workstacks
      Then I see that I have 3 cases assigned to me
      And I see 1 of my cases is priority
      And I see team A has 5 cases total

    @HOCS-402
    Scenario: A user is in a team with a priority case
      Given I am in "team A"
      And I generate 5 cases in my team
      And assign 3 to myself
      And a case not assigned to me is priority
      When I view my workstacks
      Then I see that I have 3 cases assigned to me
      And I see team A has 5 cases total
      And I see 1 is priority