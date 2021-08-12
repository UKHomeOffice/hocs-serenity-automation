@WCSManageDocuments @WCS
Feature: WCS Manage Documents

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I click manage documents

  @WCSRegression
  Scenario Outline: User can upload and preview a file of allowed file types
    And I click add documents
    When I choose the document type "Matrix"
    And I upload a file of type "<fileType>"
    Then I can see the "<fileType>" file in the uploaded document list
    And the "<fileType>" document should be selected to be displayed in the preview pane
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

  @WCSRegression
  Scenario Outline: User can select document type when uploading documents
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType             |
      | Claim form          |
      | Supporting evidence |
      | Matrix              |
      | Letter sent         |

  @WCSRegression
  Scenario: User can select which document to preview
    And I upload a 5MB and a 10MB file
    And the "5MB" document should be selected to be displayed in the preview pane
    And I click the preview button of the "10MB" file
    Then the "10MB" document should be selected to be displayed in the preview pane

  @WCSRegression
  Scenario: User can remove a document
    And I add a "docx" document to the claim
    When I remove the "docx" document
    Then I cannot see the "docx" file in the uploaded document list

  @Validation
  Scenario: User must select a document type when uploading a document
    And I click add documents
    When I upload a file of type "docx"
    Then an error message should be displayed as I have not selected a document type

  @Validation
  Scenario: User must select a file when uploading a document
    And I click add documents
    When I choose the document type "Matrix"
    And I click the "add" button
    Then an error message should be displayed as I have not selected a file to upload

  @Validation
  Scenario: User cannot upload a document that has a file type that is not on the whitelist
    And I click add documents
    When I choose the document type "Matrix"
    And I upload a file of type "csv"
    Then an error message should be displayed as I have selected a file type which is not allowed
    And I cannot see the "csv" file in the uploaded document list

  Scenario: User can see a pending tag whilst a document is being converted to PDF
    And I click add documents
    When I choose the document type "Matrix"
    And I upload a file that is 5MB in size
    Then the document should have the Pending tag

  Scenario Outline: User can remove any document
    And I add a "<fileType>" document to the claim
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


