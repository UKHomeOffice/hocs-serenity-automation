@ManageDocuments @DECS
Feature: Manage Documents

  Background:
    Given I log in to "DECS" as user "DECS_USER"

  @Regression
  Scenario Outline: User can upload and preview a file of allowed file types
    And I click to manage the documents of a new "MIN" case
    And I click add documents
    When I choose the document type "Original"
    And I upload a file of type "<fileType>"
    Then I can see the "<fileType>" file in the uploaded document list
    And the "<fileType>" document should be select to be displayed in the preview pane
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

  @DCURegression
  Scenario Outline: User can select document type when uploading documents on a DCU case
    And I click to manage the documents of a new "MIN" case
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType  |
      | ORIGINAL |
      | DRAFT    |
      | FINAL    |

  @UKVIRegression
  Scenario Outline: User can select document type when uploading documents on a MPAM case
    And I click to manage the documents of a new "MPAM" case
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType                                     |
      | Original correspondence                     |
      | Further correspondence from MPs Office      |
      | Contributions requested                     |
      | Contributions received                      |
      | Draft response (includes QA rejected)       |
      | Background note                             |
      | Final response                              |
      | Additional correspondence (Holding Replies) |

  @Validation
  Scenario: User must select a document type when uploading a document
    And I click to manage the documents of a new "MIN" case
    And I click add documents
    When I upload a file of type "docx"
    Then an error message should be displayed as I have not selected a document type

  @Validation
  Scenario: User must select a file when uploading a document
    And I click to manage the documents of a new "MIN" case
    And I click add documents
    When I choose the document type "Draft"
    And I click the "Add" button
    Then an error message should be displayed as I have not selected a file to upload


  @Validation
  Scenario: User cannot upload a document that has a file type that is not on the whitelist
    And I click to manage the documents of a new "MIN" case
    And I click add documents
    When I choose the document type "Draft"
    And I upload a file of type "csv"
    Then an error message should be displayed as I have selected a file type which is not allowed
    And I cannot see the "csv" file in the uploaded document list

  @OtherTests
  Scenario: A document has the pending tag whilst it is being converted
    And I click to manage the documents of a new "MIN" case
    And I click add documents
    When I choose the document type "Draft"
    And I upload a file that is 5MB in size
    Then the document should have the Pending tag

  @Regression
  Scenario: User can select which document to preview
    And I click to manage the documents of a new "MIN" case
    And I upload a 5MB and a 10MB file
    And the "5MB" document should be select to be displayed in the preview pane
    And I click the preview button of the "10MB" file
    Then the "10MB" document should be select to be displayed in the preview pane

  @Regression
  Scenario Outline: User can remove any document
    And I click to manage the documents of a new "MPAM" case
    And I add a "<fileType>" document to the case
    And I click manage documents
    And I select to remove the "<fileType>" document
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

  @DCURegression
  Scenario: User checks that the primary draft tag is visible after the initial draft stage
    And I create a "MIN" case and move it to the "QA Response" stage
    And I load and claim the current case
    Then the primary draft tag is next to the primary draft document