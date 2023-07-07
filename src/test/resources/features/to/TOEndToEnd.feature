@TOEndToEnd @TO
Feature: TOEndToEnd

@E2ETests
Scenario: Robust End to End test for TO Casetype
Given I am logged into "CS" as user "TO_USER"
And I wipe the record data
And I get a "TO" case at the "Dispatch" stage
And I choose not to wipe the record data until the end
When I add a "Final Response" type document to the case
And I enter the details of the dispatch
And I confirm the case is completed
Then the case should be closed
And all case data should be visible in the read-only Case Details accordion
And the summary tab should display the details entered at various stages
And I wipe the record data
