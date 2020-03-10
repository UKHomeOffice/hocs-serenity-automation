Feature: Team members can allocate work

  Background:
    Given I am user "EAMON"

  Scenario: User creates a case and allocates to another user
    And I create a new case and view it in the Performance and Process team workstack
    When I assign the current case number to "CASEY"
    Then the owner field should display "CASEY"

  Scenario: User creates a case and assigns it to themselves from the workstack
    And I create a new case and view it in the Performance and Process team workstack
    When I assign this case to me, and check if it has been correctly allocated

  Scenario: User creates 3 cases and allocates these cases to another User
    And I create three cases, and view them in performance and process workstack
    Then I assign these three cases to "CASEY"
    And I check that the three cases created have been correctly assigned to "CASEY"

  Scenario: User creates and allocates 3 cases to another User, then unallocates these cases
    And I create three cases, and assign them to "CASEY"
    Then I view these cases in Performance and Process workstack, and unallocate from "CASEY"
    And I then check whether the correct cases have been unallocated

  Scenario: User creates a Ministerial case and view it in the Ministerial Performance and Process workstack
    And I create a new "MIN" case and go home
    Then I view this "MIN" case in it's respective Performance and Process workstack

  Scenario: User creates a Number 10 case and view it in the Number 10 Performance and Process workstack
    And I create a new "DTEN" case and go home
    Then I view this "DTEN" case in it's respective Performance and Process workstack

  Scenario: User creates a Treat Official case and view it in the Treat Official Performance and Process workstack
    And I create a new "TRO" case and go home
    Then I view this "TRO" case in it's respective Performance and Process workstack

