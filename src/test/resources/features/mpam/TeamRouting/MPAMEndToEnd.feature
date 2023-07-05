@MPAMEndToEnd @MPAM @MPAMWorkflow
Feature: MPAM End To End

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  Scenario Outline: User moves a case with a specific Business Area and Reference Type to Triage stage
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage  |
      | UKVI                   | Ministerial | Triage |
      | BF                     | Ministerial | Triage |
      | IE                     | Ministerial | Triage |
      | EUSS                   | Ministerial | Triage |
      | HMPO                   | Ministerial | Triage |
      | Windrush               | Ministerial | Triage |
      | Coronavirus (COVID-19) | Ministerial | Triage |
      | UKVI                   | Official    | Triage |
      | BF                     | Official    | Triage |
      | IE                     | Official    | Triage |
      | EUSS                   | Official    | Triage |
      | HMPO                   | Official    | Triage |
      | Windrush               | Official    | Triage |
      | Coronavirus (COVID-19) | Official    | Triage |

  Scenario Outline: User moves a case with a specific Business Area and Reference Type to Draft stage
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | Draft |
      | BF                     | Ministerial | Draft |
      | IE                     | Ministerial | Draft |
      | EUSS                   | Ministerial | Draft |
      | HMPO                   | Ministerial | Draft |
      | Windrush               | Ministerial | Draft |
      | Coronavirus (COVID-19) | Ministerial | Draft |
      | UKVI                   | Official    | Draft |
      | BF                     | Official    | Draft |
      | IE                     | Official    | Draft |
      | EUSS                   | Official    | Draft |
      | HMPO                   | Official    | Draft |
      | Windrush               | Official    | Draft |
      | Coronavirus (COVID-19) | Official    | Draft |

  Scenario Outline: User moves a case with a specific Business Area and Reference Type to QA stage
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | QA    |
      | BF                     | Ministerial | QA    |
      | IE                     | Ministerial | QA    |
      | EUSS                   | Ministerial | QA    |
      | HMPO                   | Ministerial | QA    |
      | Windrush               | Ministerial | QA    |
      | Coronavirus (COVID-19) | Ministerial | QA    |
      | UKVI                   | Official    | QA    |
      | BF                     | Official    | QA    |
      | IE                     | Official    | QA    |
      | EUSS                   | Official    | QA    |
      | HMPO                   | Official    | QA    |
      | Windrush               | Official    | QA    |
      | Coronavirus (COVID-19) | Official    | QA    |

  Scenario Outline: User moves a case with a specific Business Area and Reference Type to its appropriate dispatch stage
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage             |
      | UKVI                   | Ministerial | Private Office    |
      | BF                     | Ministerial | Private Office    |
      | IE                     | Ministerial | Private Office    |
      | EUSS                   | Ministerial | Private Office    |
      | HMPO                   | Ministerial | Private Office    |
      | Windrush               | Ministerial | Private Office    |
      | Coronavirus (COVID-19) | Ministerial | Private Office    |
      | UKVI                   | Official    | Awaiting Dispatch |
      | BF                     | Official    | Awaiting Dispatch |
      | IE                     | Official    | Awaiting Dispatch |
      | EUSS                   | Official    | Awaiting Dispatch |
      | HMPO                   | Official    | Awaiting Dispatch |
      | Windrush               | Official    | Awaiting Dispatch |
      | Coronavirus (COVID-19) | Official    | Awaiting Dispatch |

  @MPAMRegression1 @SmokeTests @E2ETests
  Scenario Outline: User closes a Ministerial case with specific Business Area and Reference Type
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    Examples:
      | businessArea           | refType     | stage       |
      | UKVI                   | Ministerial | Case Closed |
      | BF                     | Ministerial | Case Closed |
      | IE                     | Ministerial | Case Closed |
      | EUSS                   | Ministerial | Case Closed |
      | HMPO                   | Ministerial | Case Closed |
      | Windrush               | Ministerial | Case Closed |
      | Coronavirus (COVID-19) | Ministerial | Case Closed |

  @MPAMRegression1 @SmokeTests
  Scenario Outline: User closes a Official case with specific Business Area and Reference Type
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    Examples:
      | businessArea           | refType  | stage       |
      | UKVI                   | Official | Case Closed |
      | BF                     | Official | Case Closed |
      | IE                     | Official | Case Closed |
      | EUSS                   | Official | Case Closed |
      | HMPO                   | Official | Case Closed |
      | Windrush               | Official | Case Closed |
      | Coronavirus (COVID-19) | Official | Case Closed |
      
 # HOCS- 6705
  @E2ETests
  Scenario Outline: End to End workflow for a Ministerial case with specific Business Area and Reference Type
    And I wipe the record data
    And I choose not to wipe the record data until the end
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
Examples:
      | businessArea           | refType     | stage       |
      | UKVI                   | Ministerial | Case Closed |
      | BF                     | Ministerial | Case Closed |
      | IE                     | Ministerial | Case Closed |
      | EUSS                   | Ministerial | Case Closed |
      | HMPO                   | Ministerial | Case Closed |
      | Windrush               | Ministerial | Case Closed |
      | Coronavirus (COVID-19) | Ministerial | Case Closed |


 # HOCS- 6705
  @E2ETests
  Scenario Outline: End to End workflow for a Official case with specific Business Area and Reference Type
    And I wipe the record data
    And I choose not to wipe the record data until the end
    When I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary tab should display the details entered at various stages
    Examples:
      | businessArea           | refType  | stage       |
      | UKVI                   | Official | Case Closed |
      | BF                     | Official | Case Closed |
      | IE                     | Official | Case Closed |
      | EUSS                   | Official | Case Closed |
      | HMPO                   | Official | Case Closed |
      | Windrush               | Official | Case Closed |
      | Coronavirus (COVID-19) | Official | Case Closed |

