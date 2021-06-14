package com.gb.fineos.page.sharedpages.casemanager.displaycase;

import com.gb.fineos.domain.ClaimType;
import com.gb.fineos.domain.TestCaseContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DisplayCasePageNotificationBase extends DisplayCasePageBase {

    @FindBy(xpath = "//div[contains(@id, '_CaseTabControlBean')]")
    private WebElement menuBar;
    // Create Claim dropdown
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddSubCase_Property_Damage_Claim_Property_Damage_Claimlink')]")
    private WebElement menuBarLiabilityNotificationDropdownCreateClaimPropertyDamageClaim;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddSubCase_Personal_Injury_Claim_Personal_Injury_Claimlink')]")
    private WebElement menuBarLiabilityNotificationDropdownCreateClaimPersonalInjuryClaim;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddSubCase_Injury_Liability_Claim_Injury_Liability_Claimlink')]")
    private WebElement menuBarLiabilityClaimDropdownInjuryLiabilityClaimLink;
    @FindBy(xpath = "//a[contains(@id, 'GeneralLiability_DROPDOWN.AddSubCase_Property_Liability_Claim_Property_Liability_Claimlink')]")
    private WebElement menuBarLiabilityClaimDropdownCreateClaimPropertyLiabilityClaim;
    @FindBy(xpath = "//a[contains(@id, 'MiscNotification_DROPDOWN.AddSubCase_Injury_Claim_Injury_Claimlink')]")
    private WebElement menuBarMiscInjuryClaim;
    @FindBy(xpath = "//a[contains(@id, 'MiscNotification_DROPDOWN.AddSubCase_Property_Claim_Property_Claimlink')]")
    private WebElement menuBarMiscPropertyClaim;
    @FindBy(xpath = "//a[contains(@id, 'PropertyNotification_DROPDOWN.AddSubCase_DROPDOWN.AddSubCaselink')]")
    private WebElement propertyClaim;

    // Add Note dropdown
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Claimant_Contact_Claimant_Contactlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteClaimantContact;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Close_Claim_Comment_Close_Claim_Commentlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteCloseClaimComment;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Excess_Update_Excess_Updatelink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteExcessUpdate;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_File_Review_File_Reviewlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteFileReview;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Insurer/Broker_Contact_Insurer/Broker_Contactlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteInsurerBrokerContact;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Other_Party_Contact_Other_Party_Contactlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteOtherPartyContact;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Plan_of_Action_Plan_of_Actionlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNotePlanOfAction;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Re-Open_Note_Re-Open_Notelink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteReOpenNote;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Recurring_Payment_Recurring_Paymentlink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteRecurringPayment;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_Withdrawal_Note_Withdrawal_Notelink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteWithdrawalNote;
    @FindBy(xpath = "//a[contains(@id, '_DROPDOWN.AddEform_chooseOtherCaseActivity_chooseOtherCaseActivitylink')]")
    private WebElement menuBarLiabilityNotificationDropdownAddNoteOtherEForm;
    //Notification level tabs
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_PropertyIncidentTab_cell')]")
    private WebElement displayTabIncidentDetails;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_PaymentHistoryTab_cell')]")
    private WebElement displayTabPaymentHistory;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_CoveragesTab_cell')]")
    private WebElement displayTabPolicy;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Linked_Cases_cell')]")
    private WebElement displayTabLinkedCases;

    // Display Tabs Dialogue
    @FindBy(xpath = "//td[contains(@id, '_GeneralDetails_cell')]")
    private WebElement displayTabDialogueGeneralForIncidentDetails;
    @FindBy(xpath = "//td[contains(@id, '_Property_cell')]")
    private WebElement displayTabDialogueLossCauseForIncidentDetails;
    @FindBy(xpath = "//td[contains(@id, '_PaymentsMade_cell')]")
    private WebElement displayTabDialoguePaymentsMadeForPaymentHistory;
    @FindBy(xpath = "//td[contains(@id, '_AmountsPending_cell')]")
    private WebElement displayTabDialogueAmountsPendingForPaymentHistory;
    @FindBy(xpath = "//td[contains(@id, '_OverpaymentSummary_cell')]")
    private WebElement displayTabDialogueOverpaymentSummaryForPaymentHistory;
    @FindBy(xpath = "//td[contains(@id, '_documents_cell')]")
    private WebElement displayTabDialogueDocumentsForCaseForDocuments;
    @FindBy(xpath = "//td[contains(@id, '_documentgroups_cell')]")
    private WebElement displayTabDialogueDocumentGroupsForDocuments;
    @FindBy(xpath = "(//a[contains(@id,'_Outbound_Correspondence')])")
    private List<WebElement> outboundCorrespondenceDocumentLink;
    @FindBy(xpath = "//td[contains(@id, '_TasksView_cell')]")
    private WebElement displayTabDialogueTasksForTasks;
    @FindBy(xpath = "//td[contains(@id, '_ProcessesView_cell')]")
    private WebElement displayTabDialogueProcessesForTasks;
    @FindBy(xpath = "(//td[contains(@title, 'SELENIUMAUTOMATIONTEST_FAKE_EMAIL_5_ATT.EML')])[1]")
    private WebElement documentName;
    @FindBy(xpath = "//input[contains(@id, '_DocumentProperties')]")
    private WebElement documentProperties;
    @FindBy(xpath = "//input[contains(@id,'ViewGenIncidentDetailsContainerWidget_') and contains(@id,'_com.fineos.claims.property.ViewGenIncidentDetailsContainerWidgetEDIT_LINKEDIT_BUTTONGeneral_Incident_Details')]")
    private WebElement editIncidentDetails;
    @FindBy(xpath = "//input[contains(@id,'_LINKEDIT_BUTTONCase_Details')]")
    private WebElement editSummaryCaseDetails;
    @FindBy(xpath = "//input[contains(@id,'_LINKEDIT_BUTTONClaim_Details')]")
    private WebElement editSummaryClaimDetails;
    @FindBy(xpath = "//div[contains(@class,'pageheader_heading')]/h2")
    private WebElement notificationName;
    @FindBy(xpath = "//a[contains(@id,'_DROPDOWNMENU.CaseOwnershipTransferCaselink')]")
    private WebElement transferCaseDropDown;
    @FindBy(xpath = "//a[contains(@id,'_MENUITEM.CaseOwnershipAssignToDepartmentlink')]")
    private WebElement assignToDept;


    public DisplayCasePageNotificationBase() {
        super("NOTIFICATION PAGE");
    }

    //Notification level tabs

    public WebElement getDisplayTabIncidentDetails() {
        return displayTabIncidentDetails;
    }

    public WebElement getDisplayTabPaymentHistory() {
        return displayTabPaymentHistory;
    }

    public WebElement getDisplayTabPolicy() {
        return displayTabPolicy;
    }

    public WebElement getDisplayTabLinkedCases() {
        return displayTabLinkedCases;
    }

    public List<WebElement> getOutboundCorrespondenceDocumentLink() {
        return outboundCorrespondenceDocumentLink;
    }

    public WebElement getNotificationName() {
        return notificationName;
    }

    public void clickOKButton(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on OK button to save edit details ");
        click(getSaveButton());
    }

    public void clickCreatePropertyDamageClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Property Damage Claim in the Create Claim Dropdown");
        click(menuBarLiabilityNotificationDropdownCreateClaimPropertyDamageClaim);
    }

    public void clickCreatePersonalInjuryClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Personal Injury Claim in the Create Claim Dropdown");
        click(menuBarLiabilityNotificationDropdownCreateClaimPersonalInjuryClaim);
    }

    public void clickCreatePropertyLiabilityClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Property Liability Claim in the Create Claim Dropdown");
        click(menuBarLiabilityClaimDropdownCreateClaimPropertyLiabilityClaim);
    }

    public void clickCreateInjuryLiabilityClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Injury Liability Claim in the Create Claim Dropdown");
        click(menuBarLiabilityClaimDropdownInjuryLiabilityClaimLink);
    }

    public void clickCreateMiscInjuryClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on (Misc) Injury Claim in the Create Claim Dropdown");
        click(menuBarMiscInjuryClaim);
    }

    public void clickCreateMiscPropertyClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on (Misc) Property Claim in the Create Claim Dropdown");
        click(menuBarMiscPropertyClaim);
    }

    public void clickOnCreatePropertyClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Property Claim");
        click(propertyClaim);
    }
    public void clickAddNoteClaimantContact(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Claimant Contact in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteClaimantContact);
    }

    public void clickAddNoteCloseClaimComment(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Close Claim Comment in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteCloseClaimComment);
    }

    public void clickAddNoteExcessUpdate(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Excess Update in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteExcessUpdate);
    }

    public void clickAddNoteFileReview(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on File Review in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteFileReview);
    }

    public void clickAddNoteInsurerBrokerContact(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Insurer/Broker Contact in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteInsurerBrokerContact);
    }

    public void clickAddNoteOtherPartyContact(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Other Party Contact in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteOtherPartyContact);
    }

    public void clickAddNotePlanOfAction(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Plan of Action in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNotePlanOfAction);
    }

    public void clickAddNoteReOpenNote(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Re-Open Note in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteReOpenNote);
    }

    public void clickAddNoteRecurringPayment(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Recurring Payment in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteRecurringPayment);
    }

    public void clickAddNoteWithdrawalNote(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Withdrawal Note in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteWithdrawalNote);
    }

    public void clickAddNoteOtherEForm(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Other eForm in the Add Note Dropdown");
        click(menuBarLiabilityNotificationDropdownAddNoteOtherEForm);
    }

    private void clickIncidentDetailsTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Incident Details Tab");
        click(displayTabIncidentDetails);
    }

    private void clickPaymentHistoryTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Payment History Tab");
        click(displayTabPaymentHistory);
    }

    private void clickPolicyTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Policy Tab");
        click(displayTabPolicy);
    }

    private void clickReservesTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Reserves Tab");
        click(getDisplayTabReserves());
    }

    private void clickCaseActionsTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Case Actions Tab");
        click(getDisplayTabCaseActions());
    }

    private void clickLinkedCasesTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the LinkedCases Tab");
        click(displayTabLinkedCases);
    }

    public void clickEditIncidentDetails(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Edit Incident Details: ");
        clickModalBox(editIncidentDetails);
    }

    public void clickEditSummaryCaseDetails(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Edit summary case Details: ");
        click(editSummaryCaseDetails);
    }

    public void clickEditSummaryClaimDetails(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Edit summary Claim Details: ");
        click(editSummaryClaimDetails);
    }

    public void clickGeneraTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on General");
        click(displayTabDialogueGeneralForIncidentDetails);
        getAssertionHelper().assertTabIsSelected("Incident Details - General", displayTabDialogueGeneralForIncidentDetails);
        getAssertionHelper().assertIsDisplayed("General Incident Details Widget", GENERAL_INCIDENT_DETAILS_WIDGET_WEB_ELEMENT_ID);
    }

    public void clickLossCauseTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Loss Cause");
        click(displayTabDialogueLossCauseForIncidentDetails);
        getAssertionHelper().assertTabIsSelected("Incident Details - Loss Cause", displayTabDialogueLossCauseForIncidentDetails);
        getAssertionHelper().assertIsDisplayed("Quick Add Item Widget", "QuickAddPropertyItemWidget");
        getAssertionHelper().assertIsNotDisplayed("General Incident Details Widget", GENERAL_INCIDENT_DETAILS_WIDGET_WEB_ELEMENT_ID);
    }

    public void clickPaymentsMadeTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Payments Made");
        click(displayTabDialoguePaymentsMadeForPaymentHistory);
        getAssertionHelper().assertTabIsSelected("Payment History - Payments Made", displayTabDialoguePaymentsMadeForPaymentHistory);
        getAssertionHelper().assertIsDisplayed(PAYMENTS_MADE_WIDGET, PAYMENT_HISTORY_TAB_WEB_ELEMENT_ID);
    }

    public void clickAmountsPendingTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Amounts Pending");
        click(displayTabDialogueAmountsPendingForPaymentHistory);
        getAssertionHelper().assertTabIsSelected("Payment History - Amounts Pending", displayTabDialogueAmountsPendingForPaymentHistory);
        getAssertionHelper().assertIsDisplayed("Amounts Pending Widget", "amountspendingtabWidget");
    }

    public void clickOverpaymentSummaryTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Overpayment Summary");
        click(displayTabDialogueOverpaymentSummaryForPaymentHistory);
        getAssertionHelper().assertTabIsSelected("Payment History - Overpayment Summary", displayTabDialogueOverpaymentSummaryForPaymentHistory);
        getAssertionHelper().assertIsDisplayed("Overpayment Record Totals Widget", "OverpaymentsSummaryTotalsWidget");
    }

    public void clickDocumentsForCaseTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Documents For Case");
        click(displayTabDialogueDocumentsForCaseForDocuments);
        getAssertionHelper().assertTabIsSelected("Documents - Documents For Case", displayTabDialogueDocumentsForCaseForDocuments);
        getAssertionHelper().assertIsDisplayed(DOCUMENTS_FOR_CASE_WIDGET, DOCUMENTS_FOR_CASE_WIDGET_ID);
        getAssertionHelper().assertIsNotDisplayed("Document Groups Widget", "DocumentGroupsForCaseLVW");
    }

    public void clickDocumentGroupsTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Document Groups");
        click(displayTabDialogueDocumentGroupsForDocuments);
        getAssertionHelper().assertTabIsSelected("Document Groups - ", displayTabDialogueDocumentGroupsForDocuments);
        getAssertionHelper().assertIsDisplayed("Document Groups Widget", "DocumentGroupsForCaseLVW");
        getAssertionHelper().assertIsNotDisplayed(DOCUMENTS_FOR_CASE_WIDGET, DOCUMENTS_FOR_CASE_WIDGET_ID);
    }

    public void clickTasksTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on " + TASKS);
        click(displayTabDialogueTasksForTasks);
        getAssertionHelper().assertTabIsSelected(TASKS + " - " + TASKS, displayTabDialogueTasksForTasks);
        getAssertionHelper().assertIsDisplayed(TASKS_WIDGET, TASKS_TAB_WEB_ELEMENT_ID);
    }

    public void clickProcessesTabDialogue(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Processes");
        click(displayTabDialogueProcessesForTasks);
        getAssertionHelper().assertTabIsSelected(TASKS + " - " + PROCESSES, displayTabDialogueProcessesForTasks);
        getAssertionHelper().assertIsDisplayed("Activities Widget", "ActivityTreeviewWidget");
        getAssertionHelper().assertIsNotDisplayed(TASKS_WIDGET, TASKS_TAB_WEB_ELEMENT_ID);
    }

    protected void clickOnPersonalInjuryClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddSubCase(pageRequest);
        getAssertionHelper().assertIsDisplayed("Personal Injury Claim", menuBarLiabilityNotificationDropdownCreateClaimPersonalInjuryClaim);
        clickCreatePersonalInjuryClaim(pageRequest);
    }

    protected void clickOnPropertyDamageClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddSubCase(pageRequest);
        getAssertionHelper().assertIsDisplayed("Property Damage Claim", menuBarLiabilityNotificationDropdownCreateClaimPropertyDamageClaim);
        clickCreatePropertyDamageClaim(pageRequest);
    }

    protected void clickOnInjuryLiabilityClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddSubCase(pageRequest);
        getAssertionHelper().assertIsDisplayed("Injury Liability Claim", menuBarLiabilityClaimDropdownInjuryLiabilityClaimLink);
        clickCreateInjuryLiabilityClaim(pageRequest);
    }

    protected void clickOnPropertyLiabilityClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddSubCase(pageRequest);
        getAssertionHelper().assertIsDisplayed("Property Liability Claim", menuBarLiabilityClaimDropdownCreateClaimPropertyLiabilityClaim);
        clickCreatePropertyLiabilityClaim(pageRequest);
    }

    protected void clickOnMiscInjuryClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddSubCase(pageRequest);
        getAssertionHelper().assertIsDisplayed("Injury Claim", menuBarMiscInjuryClaim);
        clickCreateMiscInjuryClaim(pageRequest);
    }

    protected void clickOnMiscPropertyClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddSubCase(pageRequest);
        getAssertionHelper().assertIsDisplayed("Property Claim", menuBarMiscPropertyClaim);
        clickCreateMiscPropertyClaim(pageRequest);
    }

    public void doClickOnCreateClaim(final DisplayCasePageNotificationBaseRequest pageRequest) {
        switch (pageRequest.getClaimType()) {
            case MOTOR_CLAIM:
            case PROPERTY_CLAIM:
                clickAddSubCase(pageRequest);
                break;
            case PERSONAL_INJURY_CLAIM:
                clickOnPersonalInjuryClaim(pageRequest);
                break;
            case PROPERTY_DAMAGE_CLAIM:
                clickOnPropertyDamageClaim(pageRequest);
                break;
            default:
                pageRequest.log(getPageName(), "Claim type is not present in Application." + pageRequest.getClaimType());
                break;
        }
    }

    public void clickIncidentDetailsTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Incident Details Tab");
        click(displayTabIncidentDetails);
    }

    public void clickPaymentHistoryTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Payment History Tab");
        click(displayTabPaymentHistory);
    }

    public void clickPolicyTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Policy Tab");
        click(displayTabPolicy);
    }
    public void clickLinkedCasesTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the LinkedCases Tab");
        click(displayTabLinkedCases);
    }

    @Override
    public void clickValidationsTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Validations Tab");
        click(getDisplayTabValidations());
    }

    public void doOpenSummaryTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabSummary(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Summary Tab is already selected");
        } else {
            getAssertionHelper().assertTabIsNotSelected(CASE_SUMMARY, getDisplayTabSummary());
            clickSummaryTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected(CASE_SUMMARY, getDisplayTabSummary());
        getAssertionHelper().assertIsDisplayed("Case Details Widget", CASE_DETAILS_WEB_ELEMENT_ID);
        getAssertionHelper().assertIsNotDisplayed("General Incident Details", GENERAL_INCIDENT_DETAILS_WIDGET_WEB_ELEMENT_ID);
    }

    public void doOpenIncidentDetailsTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(displayTabIncidentDetails, "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Incident Details Tab is already selected");
        } else {
            getAssertionHelper().assertTabIsNotSelected("Incident Details", displayTabIncidentDetails);
            clickIncidentDetailsTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Incident Details", displayTabIncidentDetails);
        getAssertionHelper().assertIsDisplayed("General Incident Details", GENERAL_INCIDENT_DETAILS_WIDGET_WEB_ELEMENT_ID);
        getAssertionHelper().assertIsDisplayed("General Display Tab", displayTabDialogueGeneralForIncidentDetails);
        getAssertionHelper().assertIsDisplayed("Loss Cause Display Tab", displayTabDialogueLossCauseForIncidentDetails);
    }

    public void doOpenPaymentHistoryTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(displayTabPaymentHistory, "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Payment History Tab is already selected");
        } else {
            getAssertionHelper().assertTabIsNotSelected("Payment History", displayTabPaymentHistory);
            clickPaymentHistoryTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Payment History", displayTabPaymentHistory);
        getAssertionHelper().assertIsDisplayed(PAYMENTS_MADE_WIDGET, PAYMENT_HISTORY_TAB_WEB_ELEMENT_ID);
        getAssertionHelper().assertIsDisplayed("Payments Made Display Tab", displayTabDialoguePaymentsMadeForPaymentHistory);
        getAssertionHelper().assertIsDisplayed("Amounts Pending Display Tab", displayTabDialogueAmountsPendingForPaymentHistory);
        getAssertionHelper().assertIsDisplayed("Overpayment Summary Display Tab", displayTabDialogueOverpaymentSummaryForPaymentHistory);
    }

    public void doOpenPolicyTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(displayTabPolicy, "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Policy Tab is already selected");
        } else {
            clickPolicyTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Policy", displayTabPolicy);
        getAssertionHelper().assertIsDisplayed("Policy Details Widget", "ClaimsContractStubListviewWidget");
    }

    public void doOpenReservesTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabReserves(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Reserves Tab is already selected");
        } else {
            clickReservesTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Reserves", getDisplayTabReserves());
        getAssertionHelper().assertIsDisplayed("Reserve Summary Widget", "MotorAccidentCaseTreeViewForReserveSummaryWidgetWidget");
        getAssertionHelper().assertIsDisplayed("Reserve Totals Widget", "ReserveTotalsWidget");
    }

    public void doOpenCaseActionsTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabCaseActions(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Case Actions Tab is already selected");
        } else {
            clickCaseActionsTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Case Actions", getDisplayTabCaseActions());
        getAssertionHelper().assertIsDisplayed("Available Actions Widget", "CaseActionsWidget");
    }

    public void doOpenLinkedCasesTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(displayTabLinkedCases, "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Linked Cases Tab is already selected");
        } else {
            clickLinkedCasesTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Linked Cases", displayTabLinkedCases);
        getAssertionHelper().assertIsDisplayed("Linked Cases Widget", "CaseLinksListviewWidget");
    }

    public void doOpenValidationsTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabValidations(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Validations Tab is already selected");
        } else {
            clickValidationsTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Validations", getDisplayTabValidations());
        getAssertionHelper().assertIsDisplayed("Validations List View Widget", "ValidationsListViewWidget");
    }

    public void doOpenDocumentsTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabDocuments(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Documents Tab is already selected");
        } else {
            getAssertionHelper().assertTabIsNotSelected("Documents", getDisplayTabDocuments());
            clickDocumentsTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Documents", getDisplayTabDocuments());
        getAssertionHelper().assertIsDisplayed(DOCUMENTS_FOR_CASE_WIDGET, DOCUMENTS_FOR_CASE_WIDGET_ID);
        getAssertionHelper().assertIsDisplayed("Documents For Case Display Tab", displayTabDialogueDocumentsForCaseForDocuments);
        getAssertionHelper().assertIsDisplayed("", displayTabDialogueDocumentGroupsForDocuments);
    }

    public void doOpenActivitiesTab(final DisplayCasePageNotificationBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabActivities(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Activities Tab is already selected");
        } else {
            getAssertionHelper().assertTabIsNotSelected("Activities", getDisplayTabActivities());
            clickActivitiesTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Activities", getDisplayTabActivities());
        getAssertionHelper().assertIsDisplayed("Activities Widget", "NewsFeedForCaseWidgetList");
    }

    public void clickDocumentTitle(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Click on the documents title to open properties");
        click(documentName);
    }

    public void clickProperties(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Click document properties");
        click(documentProperties);
    }

    public void doClickAddNote(final DisplayCasePageNotificationBaseRequest pageRequest) {
        clickAddNote(pageRequest);
        getAssertionHelper().assertIsDisplayed("Claimant Contact Button", menuBarLiabilityNotificationDropdownAddNoteClaimantContact);
        getAssertionHelper().assertIsDisplayed("Close Claim Comment Button", menuBarLiabilityNotificationDropdownAddNoteCloseClaimComment);
        getAssertionHelper().assertIsDisplayed("Excess Update Button", menuBarLiabilityNotificationDropdownAddNoteExcessUpdate);
        getAssertionHelper().assertIsDisplayed("File Review Button", menuBarLiabilityNotificationDropdownAddNoteFileReview);
        getAssertionHelper().assertIsDisplayed("Insurer/Broker Contact Button", menuBarLiabilityNotificationDropdownAddNoteInsurerBrokerContact);
        getAssertionHelper().assertIsDisplayed("Other Party Contact", menuBarLiabilityNotificationDropdownAddNoteOtherPartyContact);
        getAssertionHelper().assertIsDisplayed("Plan of Action Button", menuBarLiabilityNotificationDropdownAddNotePlanOfAction);
        getAssertionHelper().assertIsDisplayed("Re-Open Note Button", menuBarLiabilityNotificationDropdownAddNoteReOpenNote);
        getAssertionHelper().assertIsDisplayed("Recurring Payment Button", menuBarLiabilityNotificationDropdownAddNoteRecurringPayment);
        getAssertionHelper().assertIsDisplayed("Withdrawal Note Button", menuBarLiabilityNotificationDropdownAddNoteWithdrawalNote);
        getAssertionHelper().assertIsDisplayed("Other eForm... Button", menuBarLiabilityNotificationDropdownAddNoteOtherEForm);
    }

    public void doAddNoteClaimantContact(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteClaimantContact(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteCloseClaimComment(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteCloseClaimComment(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteExcessUpdate(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteExcessUpdate(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteFileReview(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteFileReview(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteInsurerBrokerContact(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteInsurerBrokerContact(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteOtherPartyContact(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteOtherPartyContact(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNotePlanOfAction(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNotePlanOfAction(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteReOpenNote(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteReOpenNote(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteRecurringPayment(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteRecurringPayment(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteWithdrawalNote(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteWithdrawalNote(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public void doAddNoteOtherEForm(final DisplayCasePageNotificationBaseRequest pageRequest) {
        doClickAddNote(pageRequest);
        clickAddNoteOtherEForm(pageRequest);
        getAssertionHelper().assertIsNotDisplayed(MENU_BAR, menuBar);
    }

    public String currentNotificationName(final DisplayCasePageNotificationBaseRequest pageRequest) {
        return  getText(getNotificationName()).toUpperCase().trim();
    }

    public void clickTransferCase(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Transfer Case");
        clickModalBox(transferCaseDropDown);
    }

    public void clickAssignToDept(final DisplayCasePageNotificationBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on assign to dept");
        clickModalBox(assignToDept);
    }

    public static class DisplayCasePageNotificationBaseRequest extends DisplayCasePageBaseRequest {

        public DisplayCasePageNotificationBaseRequest(final TestCaseContext context) {
            super(context);
        }

        public ClaimType getClaimType() {
            return ClaimType.valueOfProperty(get("Claim_Type"));
        }
    }
}
