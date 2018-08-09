Feature: A user can remove any documents attached to the case

  Scenario:  Document can be removed from the case
    Given I am user "<string>"
    And I am at the "Data Entry" stage
    And I am viewing a case with "one or more" documents attached
    When I remove a document from the case
    Then the document is removed from the case