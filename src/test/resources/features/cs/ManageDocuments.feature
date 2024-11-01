@ManageDocuments @CS
Feature: Manage Documents

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression @CSE2ETests
  Scenario Outline: As a Correspondence System user, I want to be able to upload, preview, and remove any document that is of an allowed filetype,
  so I can complete my work
    And I manage the documents of a new case
    And I add a "<fileType>" type file to the case as a document
    Then the document should be listed under the expected Document Type header
    When I remove the "<fileType>" document
    Then I cannot see the "<fileType>" file in the uploaded document list
    Examples:
      | fileType |
      | docx     |
      | txt      |
      | pdf      |
      | tiff     |
      | xlsx     |
      | gif      |
      | html     |
      | jpg      |
      | png      |
      | bmp      |
      | doc      |

  Scenario Outline: As a DECS user, I want to be able to select the type of an uploaded document, so the document can be easily identified later
    And I manage the documents of a new "<caseType>" case
    When I click add documents
    Then I should see a dropdown containing the expected Document Types for the case I am working on
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |
      | MPAM     |
      | MTS      |
      | COMP     |
      | COMP2    |
      | IEDET    |
      | BF       |
      | BF2      |
      | TO       |
      | POGR     |
      | POGR2    |

  @Validation
  Scenario: As a Correspondence System user, I want to be informed when I fail to select a document type, so I can rectify the mistake
    And I manage the documents of a new case
    And I click add documents
    When I upload a file of type "docx"
    Then an error message should be displayed as I have not selected a document type

  @Validation
  Scenario: As a Correspondence System user, I want to be informed when I fail to select a file to upload, so I can rectify the mistake
    And I manage the documents of a new case
    And I click add documents
    When I select a document type
    And I click the "Add" button
    Then an error message should be displayed as I have not selected a file to upload

  @Validation
  Scenario: As a Correspondence System user, I want to be informed when I attempt to upload a file of unsupported filetype, so I can rectify the mistake
    And I manage the documents of a new case
    And I click add documents
    When I select a document type
    And I upload a file of type "csv"
    Then an error message should be displayed as I have selected a file type which is not allowed
    And I cannot see the "csv" file in the uploaded document list

  @CSRegression
  Scenario: As a Correspondence System user, when I add a document that fails to convert to PDF, I want to be informed of the failure
    And I manage the documents of a new case
    When I upload a file that fails to convert to PDF
    Then document should have the Failed Conversion tag

  @CSRegression
  Scenario: As a Correspondence System user, when I add a document that fails during the virus scan, I want to be informed of the failure
    And I manage the documents of a new case
    When I upload a file that fails during the virus scan
    Then document should have the Failed Virus Scan tag

  @CSRegression
  Scenario: As a Correspondence System user, I want to be able to select which document to preview, so I can inspect different uploaded documents
    And I manage the documents of a new case
    And I upload a 5MB and a 10MB file
    And I click the preview button of the "5MB" file
    And the "5MB" document should be selected to be displayed in the preview pane
    And I click the preview button of the "10MB" file
    Then the "10MB" document should be selected to be displayed in the preview pane