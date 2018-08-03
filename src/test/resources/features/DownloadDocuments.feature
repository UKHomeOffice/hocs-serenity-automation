Feature: A user can download any documents attached to the case

  @HOCS-272, @HOCS-397
  Scenario: Document can be previewed (see attached screenshot)
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "previewable" documents attached
    When I click the "Download" link
    Then the file is downloaded

  @HOCS-272, @HOCS-397
  Scenario: Document cannot be previewed
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "non previewable" documents attached
    Then the file is downloaded

  @HOCS-272, @HOCS-397
  Scenario: Document can be previewed (see attached screenshot)
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "previewable" documents attached
    When I click the "Download" link
    And the download fails
    Then I see the "Download failed" message

  @HOCS-272, @HOCS-397
  Scenario: No Document attached to the case
    Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
    And I am viewing a case with "no" documents attached
    Then I see the "No Documents" message

