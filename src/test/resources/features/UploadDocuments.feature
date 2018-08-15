Feature: A user can upload documents to a case

  @HOCS-273, @HOCS-238
  Scenario: Document has a File Type that is not on the whitelist
    Given I am user "User"
    When I upload a document that is not on the whitelist
    Then I see the "document type failure" message
    And invalid files are not uploaded

  @HOCS-273, @HOCS-238
  Scenario: Document exceeds the file size limit
    Given I am user "User"
    When I upload a document that is greater than the file size limits
    Then I see the "document size failure" message
    And invalid files are not uploaded

  @HOCS-273, @HOCS-238
  Scenario: When multiple files are selected, and one or more has a file type or size limit validation error, files without errors are uploaded
    Given I am user "User"
    When I upload multiple files with one validation error
    Then invalid files are not uploaded
    But valid files are uploaded

  @HOCS-273, @HOCS-238
  Scenario: A selected document passes validation
    Given I am user "User"
    When I upload 1 valid document
    Then valid files are uploaded

  @HOCS-273, @HOCS-238
  Scenario: A selected document fails with a validation error
    Given I am user "User"
    When I upload 1 valid document
    And the file has a validation error
    Then invalid files are not uploaded

  @HOCS-273, @HOCS-238
  Scenario: When multiple files are selected, and one or more has a validation error, all files will be  displayed with the relevant status
    Given I am user "User"
    When I upload 2 valid documents
    And any files have a validation error
    Then invalid files are not uploaded
    But valid files are uploaded