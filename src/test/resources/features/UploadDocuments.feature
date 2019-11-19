Feature: A user can upload documents to a case #this test can be run at any stage

Given I log in as the designated user
  And I am working on 'stage'
  
  @HOCS-273, @HOCS-238
  Scenario: Document has a File Type that is not on the whitelist
    When I select 'manage documents'
    And choose document type 'draft'
    And upload a 'mp3' file
    Then I should see the "document type failure" message
    And invalid files are not uploaded

  @HOCS-273, @HOCS-238
  Scenario: Document exceeds the file size limit
    When I select 'manage documents'
    And choose document type 'draft'
    And upload a 15MB 'PDF' file
    Then I should see the "document size failure" message
    And invalid files are not uploaded

  @HOCS-273, @HOCS-238
  Scenario: When multiple files are selected, and one or more has a file type or size limit validation error, files without errors are uploaded
    When I select 'manage documents'
    And choose document type 'draft'
    And upload a 'docx' file
    And upload a 'mp3' file
    Then I should see the "document type failure" message
    And invalid files are not uploaded

  @HOCS-273, @HOCS-238
  Scenario: A selected document passes validation
    When I select 'manage documents'
    And choose document type 'draft'
    And upload a 2MB 'Docx' file
    Then I return to the 'case page'
    And the document is in the document list in a 'pending' state

