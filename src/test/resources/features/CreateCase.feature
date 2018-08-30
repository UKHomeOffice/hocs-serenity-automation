Feature: HOCS User is able to create a case

  @HOCS-423 @HOCS-419
  Scenario: I am in one tenant
    Given I am user "A"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "case types 1,2,3 only"

  @HOCS-423, @HOCS-419
  Scenario: I am in more than one tenant
    Given I am user "B"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "case types 1,2,3 and 4,5,6 only"

  @HOCS-423, @HOCS-419
  Scenario: I am in a tenant that doesn't have a case type
    Given I am user "C"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "no case types"

  @HOCS-423, @HOCS-419
  Scenario: I am in a tenant that does have case types but I have no permissions
    Given I am user "D"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "no case types"

  @HOCS-341 @HOCS-491 @HOCS-236 @critical
  Scenario Outline: I can create a case
    Given I am user "Dom"
    When I create a "<case>" case "<with / without>" a document
    Then A case is created successfully
    Examples:
      | case    | with / without |
      | DCU min | with           |
      | DCU min | without        |
      | DCU TRO | with           |
      | DCU TRO | without        |
      | DCU TEN | with           |
      | DCU TEN | without        |

  Scenario: I can bulk upload cases
    Given I am user "Dom"
    When I bulk create 40 "DCU MIN" cases
    Then A case is created successfully