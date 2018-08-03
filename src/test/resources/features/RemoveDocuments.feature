Feature:  A user can remove any documents attached to the case
  
  Scenario:  Document can be removed from the case
    Given I am DCU Performance and Process Team user at the DCU "Data entry" stage
    And I am viewing a case with "one or more" documents attached
    When I click the "Remove" link
    Then the document is removed from the case