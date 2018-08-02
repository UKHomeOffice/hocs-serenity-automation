Feature: A user can preview any documents attached to a case

  @HOCS-272, @HOCS-238
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

  @HOCS-272, @HOCS-238
  Scenario: Document can be previewed (see attached screenshot)
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "previewable" documents attached
    When I click the "Preview" link
    Then I "am" able to see a preview of the document

  @HOCS-272, @HOCS-238
  Scenario: Document cannot be previewed
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "non previewable" documents attached
    Then no preview link is available for that document

  @HOCS-272, @HOCS-238
  Scenario: No Document attached to the case
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "no" documents attached
    Then I see the "No Documents" message

  @HOCS-272, @HOCS-238
  Scenario: Document pending
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    When a document has "not completed" processing
    Then I see the "Document pending" message
    And no preview or download buttons are available for that document

  @HOCS-272, @HOCS-238
  Scenario: Document failed
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And a document has "failed" processing
    Then I see the "Document upload failed" message
    And no preview or download buttons are available for that document

