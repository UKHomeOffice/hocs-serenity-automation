Feature: A user can upload documents to a case

  Scenario: Document has a File Type that is not on the whitelist
  Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
  When I click on add documents
  And I select a document type
  And the file type is not on the upload whitelist
  Then I see ‘document type failure’ message
  And the file is not uploaded

  Scenario: Document exceeds the file size limit
  Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
  When I click on add documents
  And I select a document type
  And the file size is greater than the limit
  Then I see ‘document size failure’ message
  And the file is not uploaded

  Scenario: When multiple files are selected, and one or more has a file type or size limit validation error, files without errors are uploaded
  Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
  When I click on add documents
  And I select a document type
  And I select multiple files to upload
  And any file has a file type or size limit validation error
  Then I see ‘document failure’ messages for those files
  And they are not uploaded
  And other files are displayed with the relevant status

  Scenario: A selected document passes validation
  Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
  When I click on add documents
  And I select a document type
  And the file type is on the whitelist
  And the file is under the document size limit
  When I select a file to upload
  And the file passes validation
  Then the file will be displayed with the relevant status

  Scenario: A selected document fails with a validation error
  Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
  When I click on add documents
  And I select a document type
  And the file type is on the whitelist
  And the file is under the document size limit
  When I select a file to upload
  And the file has a validation error
  Then the file will be displayed with the relevant status

  Scenario: When multiple files are selected, and one or more has a validation error, all files will be  displayed with the relevant status
  Given I am user "DCU Performance and Process Team user at the DCU data entry stage"
  When I click on add documents
  And I select a document type
  And the file types are on the whitelist
  And the files are under the document size limit
  When I select multiple files to upload
  And any files have a validation error
  Then the files with validation errors will be displayed with the relevant status
  And files that pass validation will be displayed with the relevant status