Feature: QA Case #this test can be reused for both the private office and minister sign off stages

    Background:
    Given I am user "EAMON"
    And I get a "DCU TRO" case at "QA RESPONSE" stage
 
    @HOCS-310
    Scenario: User reviews draft, rejects it and provides a rejection reason
      When I "reject" the case
      Then the case should be moved to the "draft" stage
      And the case allocated to the "original drafter"
      And the "original drafter" and "nominated person" receive the "qa rejected email"
      And I am taken to the "home" page
      
    @HOCS-310 @FixMe
    Scenario: User reviews draft, rejects it and does not provide a rejection reason
      When I attempt to reject the "INITIAL DRAFT" case without reason
      Then an error message appears instructing me to add rejection reasons
      
    @HOCS-309
    Scenario: User reviews draft and accepts the case
      When I "accept" the case
      Then the case should be moved to the "private office" stage
      And the "nominated person" for the next owning team receives a notification email
      And I am taken to the "home" page

    @Navigation
    Scenario: Clicking the cancel button on the allocate case screen at the QA Response stage should take the user back to
    the dashboard
      And I click the cancel button
      Then I should be taken to the homepage

    @Validation
    Scenario: User must select a radio button to indicate whether they approve the QA response
      And I click the "CONTINUE" button
      Then an error message should be displayed as I have not selected a radio button on the QA approve response screen

    @Validation
    Scenario: User must enter their feedback for the a disapproved QA response in the text box
      And I click the "FINISH" button on the "QA RESPONSE FEEDBACK" page
      Then an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response

    @Validation
    Scenario: User must enter text in the text box when creating a Case note at the QA Response stage
      And I click the add button when creating a case note
      Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"