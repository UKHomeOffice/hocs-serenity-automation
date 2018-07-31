Feature: HOCS User is able to create a case

  @HOCS-423, @HOCS-419
  Scenario: I am in one tenant
    Given I am user "A"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "case types 1,2,3 only"

  @HOCS-423, @HOCS-419
  Scenario I am in more than one tenant
    Given I am user "B"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "case types 1,2,3 and 4,5,6 only"

  @HOCS-423, @HOCS-419
  Scenario I am in a tenant that doesn't have a case type
    Given I am user "C"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "no case types"

  @HOCS-423, @HOCS-419
  Scenario I am in a tenant that does have case types but I have no permissions
    Given I am user "D"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "no case types"