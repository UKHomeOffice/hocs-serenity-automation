package com.hocs.test.pages.complaints;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.LoginPage;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.SummaryTab;
import config.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.pages.WebElementFacade;

public class ComplaintsRegistrationAndDataInput extends BasePage {

    RecordCaseData recordCaseData;

    SummaryTab summaryTab;

    LoginPage loginPage;

    Dashboard dashboard;

    Boolean pogrPriority = true;

    public void enterComplainantDOB(String complainantDOB) {
        if(iedetCase()){
            recordCaseData.enterDateIntoDateFieldsWithHeading(complainantDOB, "Date of birth");
        } else {
            recordCaseData.enterDateIntoDateFieldsWithHeading(complainantDOB, "Date of Birth");
        }
    }

    public void selectAGender() {
        if(!iedetCase()) {
            recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gender");
        }
    }

    public void selectANationality() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Nationality");
    }
    public void selectAComplainantsNationality() {
              selectRandomOptionFromDropdownWithHeading("Complainant's nationality");
    }

    public void enterACompanyName() {
        if (iedetCase() || bfCase() || bf2Case()){
            recordCaseData.enterTextIntoTextFieldWithHeading("Company name");
        }else{
            recordCaseData.enterTextIntoTextFieldWithHeading("Company Name");
        }
    }

    public void enterAHomeOfficeReference(String HOReference) {
        if(iedetCase() || bfCase() || bf2Case()){
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading(HOReference, "Home Office reference");
        } else {
            recordCaseData.enterSpecificTextIntoTextFieldWithHeading(HOReference, "Home Office Reference");
        }
    }

    public void enterAPortReference() {
        if(iedetCase() || bfCase() || bf2Case()){
            recordCaseData.enterTextIntoTextFieldWithHeading("Port reference");
        } else {
            recordCaseData.enterTextIntoTextFieldWithHeading("Port Reference");
        }
    }

    public void enterComplainantDetails() {
        enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        selectANationality();
        enterACompanyName();
        enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
        enterAPortReference();
        if(iedetCase()){
            clickFinishButton();
        } else {
            clickContinueButton();
        }

    }

    public void selectASpecificComplaintType(String complaintType) {

        if(iedetCase()){
              recordCaseData.selectSpecificRadioButton(complaintType);
        } else if (compCase()||comp2Case()||comp2DirectCase()||bfCase()||bf2Case()) {
            recordCaseData.selectSpecificRadioButton(complaintType);
        } else {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(complaintType, "Complaint Type");
        }
        setSessionVariable("complaintType").to(complaintType);
        if((bfCase()||bf2Case())){
            clickTheButton("Submit");
        } else {
            clickContinueButton();
        }
        System.out.println("Complaint type: " + complaintType);
    }

    public void selectAComplaintType() {
        String complaintType = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Complaint Type");
        setSessionVariable("complaintType").to(complaintType);
    }

    public void selectAComplaintChannel() {
        if (pogrCase() || pogr2Case()) {
            recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Complaint Channel");
        } else {
            recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel");
        }
    }

    public void selectASpecificComplaintChannel(String channel) {
        if (pogrCase() || pogr2Case()) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(channel,"Complaint Channel");
        } else {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(channel,"Channel");
        }
    }

    public void selectAdditionalInformation() {
        List<String> additionalInfoList = Arrays.asList("Safe Guarding","Vulnerable","Case is made by / on behalf of a minor");
        Random rand = new Random();
        String additionalInfo = additionalInfoList.get(rand.nextInt(additionalInfoList.size()));
        recordCaseData.checkSpecificCheckbox(additionalInfo);
    }

    public void selectComplaintOrigin() {
        String selectedComplaintOrigin;
        if (iedetCase()){
            selectedComplaintOrigin = recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint origin");
        } else {
            selectedComplaintOrigin = recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint Origin");
        }
        if (selectedComplaintOrigin.equalsIgnoreCase("Other")) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Other Complaint Origin");
        }
    }

    public void selectASpecificComplaintOrigin(String optionText) {
        if (iedetCase()){
            recordCaseData.selectSpecificOptionFromDropdownWithHeading(optionText,"Complaint origin");
        } else {
            recordCaseData.selectSpecificOptionFromDropdownWithHeading(optionText,"Complaint Origin");
        }
        if (optionText.equalsIgnoreCase("Other")) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Other Complaint Origin");
        }
    }

    public void enterADescriptionOfTheComplaint() {
        String complaintType = sessionVariableCalled("complaintType");
        if (pogrCase() || pogr2Case()) {
            recordCaseData.enterTextIntoTextAreaWithHeading("Description of Complaint");
        } else if (iedetCase()){
            recordCaseData.enterTextIntoTextAreaWithHeading("Case summary");
        } else if (compCase() || comp2Case() || comp2DirectCase()) {
            recordCaseData.enterTextIntoTextAreaWithHeading("Case summary");

        } else {
            recordCaseData.enterTextIntoTextAreaWithHeading("Case Summary");
        }
    }

    public void selectASeverity() {
        String selectedSeverity = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Severity");
        if (selectedSeverity.equalsIgnoreCase("Very high")| selectedSeverity.equalsIgnoreCase("High")) {
            recordCaseData.checkSpecificCheckbox("Safe guarding");
            recordCaseData.checkSpecificCheckbox("Vulnerable");
        }
    }

    public void enterAPreviousUKVIComplaintReference() {
        String complaintType = sessionVariableCalled("complaintType");
        if(compCase() || comp2Case() || comp2DirectCase()){
            recordCaseData.enterTextIntoTextFieldWithHeading("Previous UKVI complaint reference");
        }else {
            recordCaseData.enterTextIntoTextFieldWithHeading("Previous UKVI Complaint Ref");
        }
    }

    public void enterAPreviousUKVIPSUComplaintReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous UKVI complaint reference");
    }
    public void enterAPreviousComplaintReference() {
        if(bfCase() || bf2Case()){
            recordCaseData.enterTextIntoTextFieldWithHeading("Previous complaint reference");
        } else{
            recordCaseData.enterTextIntoTextFieldWithHeading("Previous Complaint Reference");
        }

    }

    public void enterAThirdPartyReference() {
        String complaintType = sessionVariableCalled("complaintType");

        if (iedetCase() || bfCase() || bf2Case()) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Third party reference");
        } else if(compCase() || comp2Case() || comp2DirectCase()) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Third party reference");
        } else {recordCaseData.enterTextIntoTextFieldWithHeading("Third Party Reference");}
    }

    public void enterAThirdPartyReferencePSU() {
       recordCaseData.enterTextIntoTextFieldWithHeading("Third party reference");

    }

    public void enterComplaintDetails() {
        selectAComplaintChannel();
        enterADescriptionOfTheComplaint();
        selectASeverity();
        enterAPreviousUKVIComplaintReference();
        enterAThirdPartyReference();
        if(comp2DirectCase()){
            selectExternalContractor();
        }
    }

    public void openTheServiceComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Service");
        setSessionVariable("complaintCategory").to("Service");
    }

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious and Minor");
    }

    public void openTheSeriousComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious");
        setSessionVariable("complaintCategory").to("Serious");
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//input[not(@checked)]/following-sibling::label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory: claimCategories) {
            if (claimCategory.isCurrentlyVisible()) {
                visibleClaimCategories.add(claimCategory);
            }
        }
        recordCaseData.checkRandomCheckboxFromList(visibleClaimCategories);
    }

    public void selectAnOwningCSU() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Owning CSU");
    }

    public void selectSpecificOwningCSU(String owningCSU) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(owningCSU, "Owning CSU");
    }

    public void selectSpecificBusinessArea(String businessArea) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(businessArea, "Business Area");
        setSessionVariable("businessArea").to(businessArea);
        if (businessArea.equalsIgnoreCase("HMPO")) {
            setSessionVariable("investigatingTeam").to("HMPO Complaints");
        }
    }

    public void selectBusinessArea() {
        String businessArea = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
        setSessionVariable("businessArea").to(businessArea);
    }

    public void selectNationComplaintWasMadeFrom() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Nation from which complaint is being made");
    }

    public void enterApplicationReference() {
        String applicationRef = "APR"+getCurrentCaseReference().replace('/','0');
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(applicationRef,"Application Reference");
        setSessionVariable("searchApplicationReference").to(applicationRef);
    }

    public void enterPassportNumber() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Passport Number");
    }

    public void enterAccountNumber() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Case/Account Number");
    }

    public void enterDateOfCorrespondence() { recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of Correspondence"); }

    public void selectPOGRCategory() { recordCaseData.selectRandomOptionFromDropdownWithHeading("Category"); }

    public void selectComplaintCategory() { recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint Category"); }

    public void selectComplaintReason() { recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint Reason"); }

    public void selectLocation() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Location");
    }

    public void checkPriorityCheckbox() {
        checkSpecificCheckbox("Yes");
    }

    public void selectIsLoARequired() {
        String selectedOption = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Is a Letter of Authority required?");
        setSessionVariable("isLoARequired").to(selectedOption);
    }

    public void completeDataInputScreen() {
        enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        selectAGender();
        enterDateOfCorrespondence();
        selectNationComplaintWasMadeFrom();
        enterACompanyName();
        if (pogr2Case()) {
            summaryTab.selectSummaryTab();
            String businessArea = summaryTab.getSummaryTabValueForGivenHeader("Business Area");
            setSessionVariable("businessArea").to(businessArea);
        }
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("HMPO")) {
            enterApplicationReference();
            enterPassportNumber();
            selectLocation();
        } else if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            enterAccountNumber();
        }
        selectComplaintCategory();
        selectComplaintReason();
        enterADescriptionOfTheComplaint();
        selectAComplaintChannel();
        if (pogrPriority) {
            checkPriorityCheckbox();
        }
        enterAThirdPartyReference();
        selectIsLoARequired();
    }

    public void setPOGRPriority(Boolean priority) {
        this.pogrPriority = priority;
    }

    public void enterDateInterimLetterSent() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date Letter Sent");
    }

    public void selectInvestigatingTeam() {
        String investigatingTeam = selectRandomOptionFromDropdownWithHeading("Investigating Team");
        setSessionVariable("investigatingTeam").to(investigatingTeam);
    }

    public void selectNewComplaintCategory() {
        String newComplaintCategory = selectRandomOptionFromDropdownWithHeading("Complaint Category");
        setSessionVariable("complaintCategory").to(newComplaintCategory);
    }

    public void selectNewComplaintReason() {
        String newComplaintsReason = selectRandomOptionFromDropdownWithHeading("Complaint Reason");
        setSessionVariable("complaintReason").to(newComplaintsReason);
    }
    public void setPOGRCaseBusinessArea() {
        String businessArea = sessionVariableCalled("businessArea");
        if (businessArea != null) {
            selectSpecificBusinessArea(businessArea);
        } else {
            selectBusinessArea();
        }
        clickContinueButton();
        waitForDECSPageWithTitle("Complaint Correspondents");
    }

    public void enterAPSUReference() {
        String psuReference = recordCaseData.enterTextIntoTextFieldWithHeading("PSU reference");
        setSessionVariable("psuReference").to(psuReference);
    }

    public void enterASpecificPSUReference(String psuRef) {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(psuRef,"PSU reference" );
    }
    public void selectYesForSeriousCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Is this serious misconduct case for PSU to investigate?");
    }

    public void selectRandomCaseOutcomeToProgress() {
        String[] validChoices = new String[]{"Substantiated", "Partially substantiated","Unsubstantiated"};
        int rnd = new Random().nextInt(validChoices.length);
        setSessionVariable("complaintOutcome").to(validChoices[rnd]);
        recordCaseData.selectSpecificRadioButton(validChoices[rnd]);
    }

    public void selectExternalContractor() {
        String complaintOrigin = sessionVariableCalled("complaintOrigin");
        if(complaintOrigin.equalsIgnoreCase("External contractor")){
           String externalContractor = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("External contractor the complaint is about");
           setSessionVariable("selectExternalContractor").to(externalContractor);
        }

    }

    public void selectStage2CaseType(String radioOption) {

        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(radioOption, "Continue with this case?");
        if(radioOption.equalsIgnoreCase("Yes - it’s a complaint about an external contractor")) {
            recordCaseData.addHeadingAndValueRecord("Complaint origin", "External contractor");
            String complaintOrigin = "External contractor";
            setSessionVariable("complaintOrigin").to(complaintOrigin);
        }else if(radioOption.equalsIgnoreCase("No - close the case")) {
            recordCaseData.addHeadingAndValueRecord("Complaint origin", "Closed");
            String complaintOrigin = "Closed";
            setSessionVariable("complaintOrigin").to(complaintOrigin);
        }else if(radioOption.equalsIgnoreCase("Yes - it’s a further stage 2 case")) {
            recordCaseData.addHeadingAndValueRecord("Complaint origin", "Further stage 2 case");
            String complaintOrigin = "Further stage 2 case";
            setSessionVariable("complaintOrigin").to(complaintOrigin);
        }
    }

    public void selectCOMP2CaseOption() {
        String[] validChoices = new String[]{"Yes - it’s a complaint about an external contractor", "Yes - it’s a further stage 2 case"};
        int rnd = new Random().nextInt(validChoices.length);
        String radioOption = validChoices[rnd];
        selectStage2CaseType(radioOption);
    }

    public void enterAdditionalDetails() {
        selectAComplainantsNationality();
        enterAPreviousComplaintReference();
        enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
        enterAPortReference();
        enterAThirdPartyReference();
        enterACompanyName();
        clickContinueButton();

    }

    public void switchToPSUUser() {
        clickTheLink("Logout");
        loginPage.enterUsername(User.valueOf("PSU_USER").getUsername());
        loginPage.enterPassword(User.valueOf("PSU_USER").getPassword());
        clickContinueButton();
        dashboard.getAndClaimCurrentCase();
    }
}
