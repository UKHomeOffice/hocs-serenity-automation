@ManageDocuments @CS
Feature: Manage Documents

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression
  Scenario Outline: As a Correspondence System user, I want to be able to upload, preview, and remove any document that is of an allowed filetype,
  so I can complete my work
    And I manage the documents of a new case
    And I add a "<fileType>" document to the case
    Then I can see the "<fileType>" file in the uploaded document list
    And the "<fileType>" document should be selected to be displayed in the preview pane
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

  @DCURegression
  Scenario Outline: As a DCU user, I want to be able to select the type of an uploaded document, so the document can be easily identified later
    And I manage the documents of a new DCU case
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType         |
      | ORIGINAL        |
      | DRAFT           |
      | FINAL           |
      | CONTRIBUTION    |
      | BACKGROUND NOTE |

  @MPAMRegression2
  Scenario Outline: As a MPAM user, I want to be able to select the type of an uploaded document, so the document can be easily identified later
    And I manage the documents of a new MPAM or MTS case
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

  @ComplaintsRegression
  Scenario Outline: As a Complaints user, I want to be able to select the type of an uploaded document, so the document can be easily identified later
    And I manage the documents of a new Complaints case
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType               |
      | To document           |
      | Public correspondence |
      | Complaint leaflet     |
      | Complaint letter      |
      | Email                 |
      | CRF                   |
      | DRAFT                 |
      | Appeal Leaflet        |
      | IMB Letter            |

  #HOCS-3661
  @ComplaintsRegression
  Scenario Outline: As an IEDET User, I want to be able to select from additional document types, so the document can be easily identified later
    When I manage the documents of a new "IEDET" case
    And I upload a "<docType>" document
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType                |
      | Acknowledgement letter |
      | Interim response       |
      | Final response         |

  @FOIRegression
  Scenario Outline: As a FOI user, I want to be able to select the type of an uploaded document, so the document can be easily identified later
    And I manage the documents of a new "FOI" case
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType          |
      | Request          |
      | Initial response |
      | Draft response   |
      | Clearances       |
      | Final responses  |
      | Correspondence   |
      | Contribution     |
      | Miscellaneous    |
      | Appeal Response  |

  @FOIRegression
  Scenario Outline: As a UKVI Treat Official user, I want to be able to select the type of an uploaded document, so the document can be easily
  identified later
    And I manage the documents of a new "TO" case
    And I click add documents
    When I choose the document type "<docType>"
    And I upload a file of type "docx"
    Then the "docx" document should be under the "<docType>" header
    Examples:
      | docType                |
      | Initial Correspondence |

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


  Scenario: As a Correspondence System user, I want to be able to tell when a document is still pending conversion, so I am informed about its status
    And I manage the documents of a new case
    And I click add documents
    When I select a document type
    And I upload a file that is 5MB in size
    Then the document should have the Pending tag

  @CSRegression
  Scenario: As a Correspondence System user, when I add a document that fails to convert to PDF, I want to be informed of the failure
    And I manage the documents of a new case
    When I upload a file that fails to convert to PDF
    Then document should have the Failed Conversion tag

  @CSRegression
  Scenario: As a Correspondence System user, I want to be able to select which document to preview, so I can inspect different uploaded documents
    And I manage the documents of a new case
    And I upload a 5MB and a 10MB file
    And the "5MB" document should be selected to be displayed in the preview pane
    And I click the preview button of the "10MB" file
    Then the "10MB" document should be selected to be displayed in the preview pane