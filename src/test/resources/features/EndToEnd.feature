Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I am user "DANNY"

  @EndToEnd
  Scenario: End to end flow with <caseType> CaseType
    When I create a single case "DCU TRO"
    # Select Create Single Case *
    # Select a dot option, DCUMIN, TRO, DC10, click next *
    # Upload a Doc & Click finish *
    # Remember the case reference number in a VAR *
    # Select Back button or Correspondence System(home) button *
    And I complete the Data Input stage
    # Select Team 4444
    # Identify Reference via Reference number and Select Casework
    # Will become select case number                ^^^^^^^
    # Select Dot option, email, post, phone, No.10
    # Enter the date Correspondence Sent
    # Enter the date Correspondence received or accept default
    # Select or Dont Select Send to #10
    # Click continue
    # Click finish, do not add a correspondent
    # Add correspondent currently broken
    # If not returned to homepage, select the homepage
    And I complete the markup stage
    # Select team 1111
    # Identify case and select Allocate
    # Select dot option Policy Response, FAQ, Refer OGD, No Reply
    # Select continue
    # Do not add topic this is broken currently ADD ME LATER
    # Select Continue
    # Add Markup Unit from DropDown
    # Add Markup Team from Dropdown
    # Add sign off minister from DropDown
    # sendKeys some Allocation note and select Finish
    And I complete the initial draft stage
    # Select team 3333 - placeholder
    # Identify case at Initial Draft stage and select Allocate
    # Select Dot options YES or NO (YES)
    # Select Next/Continue
    # Select Dot Option Letter
    # Select Continue
    # Add a document and select continue
    # Select Dot option No to QA this ONLINE
    # Select continue
    And I complete the QA response stage
    # Select team 3333 and identify case
    # If coming from previous step you are already on team 3333
    # Select QA Response - Casework
    # Dot response - Accept and select Continue
    And I complete the Private Office stage
    # Select team 3333 and identify case
    # If coming from previous step you are already on team 3333
    # Select Allocate
    # Dot option Accept/Reject/Change Min (Accept)
    # Select Finish
    And I complete the minister sign off stage
    # Select team 3333 and identify case
    # If coming from previous step you are already on team 3333
    # Select Allocate
    # Dot option Accept
    And I complete the dispatch stage
    # Select team 3333 and identify case
    # If coming from previous step you are already on team 3333
    # Select Allocate
    # Dot option Accept
    # Select Finish
    Then Should no longer be available to perform actions on in any of the Team Queues

