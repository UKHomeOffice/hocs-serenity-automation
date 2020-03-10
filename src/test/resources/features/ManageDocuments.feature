@ManageDocuments
Feature: A user can manage documents associated to a case

  Background:
    Given I log in as the designated user
    And I create a "MIN" case "without" a document
    And I go to the case from the successful case creation screen
    And I click manage documents

  @SmokeTests
  Scenario Outline: User can upload and preview a file of allowed file types
    And I click add documents
    When I choose the document type "ORIGINAL"
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

  @SmokeTests
  Scenario Outline: : User can select document type when uploading documents
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the document should be under the "<docType>" header
    Examples:
      | docType  |
      | Original |
      | Draft    |
      | Final    |

  @Validation
  Scenario: User must select a document type when uploading a document
    And I click add documents
    When I upload a file of type "docx"
    Then an error message should be displayed as I have not selected a document type

  @Validation
  Scenario: User must select a file when uploading a document
    And I click add documents
    When I choose the document type "Draft"
    And I click the "add" button
    Then an error message should be displayed as I have not selected a file to upload


  @Validation
  Scenario: User cannot upload a document that has a file type that is not on the whitelist
    And I click add documents
    When I choose the document type "Draft"
    And I upload a file of type "csv"
    Then an error message should be displayed as I have selected a file type which is not allowed
    And I cannot see the "csv" file in the uploaded document list
    
  @Validation @Ignore
  Scenario: Document exceeds the file size limit
    And I click add documents
    When I choose the document type "Draft"
    And I select a file that is 51MB in size
    Then an error message should be displayed as I have selected a file which is larger than the allowed limit
    And I cannot see the "51MB" file in the uploaded document list

  Scenario: A document has the pending tag whilst it is being converted
    And I click add documents
    When I choose the document type "Draft"
    And I select a file that is 5MB in size
    Then the document should have the Pending tag

  @SmokeTests
  Scenario: User can select which document to preview
    And I upload a docx and a txt file
    And the "docx" document should be select to be displayed in the preview pane
    And I click the preview button of the "txt" file
    Then the "txt" document should be select to be displayed in the preview pane

  @SmokeTests
  Scenario Outline: User can remove any document
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
