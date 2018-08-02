Feature: A user can preview any documents attached to a case

  Scenario: Documents viewed in the Documents pane
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    When I am viewing a case with "multiple" documents attached
    Then I can see a list of documents for that case
    And documents are ordered by date/time uploaded
    And the latest uploaded document is displayed first
    And none of the document lines are highlighted
    And each document line has a document name
    And each document line has a document type
    And each document line has a document date/time uploaded

  Scenario: Document can be previewed (see attached screenshot)
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "one or more" documents attached
    And the document(s) are previewable
    When I click the preview link for a document
    Then I can see a preview of the document
    And that document line is highlighted

  Scenario: Document cannot be previewed
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "one or more" documents attached
    And one or more documents are not previewable
    Then no preview link is available for that document

  Scenario: No Document attached to the case
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "no" documents attached
    Then I see the "No Documents" message

  Scenario: Document pending
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    When a document has "not completed" processing
    Then I see the "Document pending" message
    And no preview or download buttons are available for that document

  Scenario: Document failed
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And a document has "failed" processing
    Then I see the "Document upload failed" message
    And no preview or download buttons are available for that document

