Feature: QA Case #this test can be reused for both the private office and minister sign off stages

    Background:
    Given I am user "<string>"
    And I am at the "QA" stage
 
    @HOCS-310
    Scenario: User reviews draft, rejects it and provides a rejection reason
      When I "reject" the case
      Then the case should be moved to the "draft" stage
      And the case allocated to the "original drafter"
      And the "original drafter" and "nominated person" receive the "qa rejected email"
      And I am taken to the "home" page
      
    @HOCS-310
    Scenario: User reviews draft, rejects it and does not provide a rejection reason
      When I attempt to reject a case without reason
      Then an error message appears instructing me to add rejection reasons
      
    @HOCS-309
    Scenario: User reviews draft and accepts the case
      When I "accept" the case
      Then the case should be moved to the "private office" stage
      And the "nominated person" for the next owning team receives a notification email
      And I am taken to the "home" page
