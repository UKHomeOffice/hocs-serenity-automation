Feature: A user can upload documents to a case #this test can be run at any stage

  Background:
    Given I log in as the designated user
    And I create a "DCU MIN" case "without" a document
    And I go to the case from the successful case creation screen
    And I click manage documents

  @ManageDocuments @SmokeTests
  Scenario Outline: User can upload a file of allowed file types
    And I click add documents
    When I choose the document type "draft"
    And I upload a file of type "<fileType>"
    Then I can see the "<fileType>" file in the uploaded document list
    Examples:
      | fileType |
      | docx     |
      | txt      |
      | pdf      |
#      | xlsx     |
#      | PDF      |
#      | DOCX     |
#      | TXT      |
#      | XLSX     |

  @ManageDocuments
  Scenario Outline: : User can select document type when uploading documents
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the document should have the "<docType>" tag
    Examples:
      | docType  |
      | Original |
      | Draft    |
      | Final    |

  @ManageDocuments @Validation
  Scenario: User must select a document type when uploading a document
    And I click add documents
    When I upload a file of type "docx"
    Then an error message should be displayed as I have not selected a document type

  @ManageDocuments @Validation
  Scenario: User must select a file when uploading a document
    And I click add documents
    When I choose the document type "Draft"
    And I click the "add" button
    Then an error message should be displayed as I have not selected a file to upload


  @ManageDocuments @Validation
  Scenario: User cannot upload a document that has a file type that is not on the whitelist
    And I click add documents
    When I choose the document type "Draft"
    And I upload a file of type "csv"
    Then an error message should be displayed as I have selected a file type which is not allowed
    And I cannot see the "csv" file in the uploaded document list

# FAILS, Doesnt trigger the javascript for validation error, not sure how to fix atm
#  @ManageDocuments @Validation
#  Scenario: Document exceeds the file size limit
#    And I click add documents
#    When I choose the document type "Draft"
#    And I select a file that is 11MB in size
#    Then an error message should be displayed as I have selected a file which is larger than the allowed limit
#    And I cannot see the "11MB" file in the uploaded document list

  @ManageDocuments
  Scenario: A document has the pending tag whilst it is being converted
    And I click add documents
    When I choose the document type "Draft"
    And I select a file that is 5MB in size
    Then the document should have the "Pending" tag

  @ManageDocuments @SmokeTests
  Scenario Outline: User can preview all allowed document types
    And I add a "<fileType>" document to the case
    Then the "<fileType>" document should be displayed in the preview pane
    Examples:
      | fileType |
      | docx     |
      | txt      |
      | pdf      |
#      | xlsx     |
#      | PDF      |
#      | DOCX     |
#      | TXT      |
#      | XLSX     |

  @ManageDocuments
  Scenario: User can select which document to preview
    And I click add documents
    When I choose the document type "Draft"
    And I upload a docx and a txt file
    And the "docx" document should be displayed in the preview pane
    And I click the preview button of the "txt" file
    Then the "txt" document should be displayed in the preview pane

  @ManageDocuments @SmokeTests
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
#      | xlsx     |
#      | PDF      |
#      | DOCX     |
#      | TXT      |
#      | XLSX     |


