package com.gb.fineos.page.sharedpages.partymanager.partydetails;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.Filter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PartyDetailsPage extends BasePage {
    private final Filter filter;

    private static final int SCROLL_TO_ADDRESS = 120;

    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_documents_cell')]")
    private WebElement documentsTab;
    @FindBy(xpath = "//td[contains(@id,'_FINEOS.DocumentManager.Documents.Person.Not_Applicable_documents_cell')]")
    private WebElement documentsForPartyTab;

    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_paymentPreferences_cell')]")
    private WebElement paymentPreferencesTab;
    @FindBy(xpath = "//input[contains(@id, 'PaymentPreferencesForPartyWidget_') and contains(@id, '_viewButton')]")
    private WebElement viewPaymentPreferenceBtn;

    @FindBy(xpath = "(//a[contains(@id,'_Outbound_Correspondence')])")
    private List<WebElement> outboundCorrespondenceDocumentLink;
    @FindBy(xpath = "((//td[contains(@id,'_DocumentsViewControlBusinessType')]/a[contains(@id,'_Outbound_Correspondence')])[1]")
    private WebElement documentLink;
    @FindBy(xpath = "//input[contains(@id, '_addDocumentButton')]")
    private WebElement addDocumentBtn;
    @FindBy(xpath = "//input[contains(@id,'AddressesForPartyWidget_') and contains(@id,'_EditButton')]")
    private WebElement editAddressForPartyBtn;
    @FindBy(xpath = "//input[contains(@id,'AddressesForPartyWidget_') and contains(@id,'_editChangeYesNoPopup_yes')]")
    private WebElement editAddressConfirmationPopUpYesBtn;
    @FindBy(xpath = "//span[contains(@id,'AddressForPartyAddressVersionWidget_') and contains(@id,'_Address')]")
    private WebElement partyAddress;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@title,'Filter by:Document Type')]")
    private WebElement filterDocumentType;
    @FindBy(xpath = "//tr[contains(@class,'ListRow')]")
    private List<WebElement> resultRows;
    @FindBy(xpath = "//td[contains(@id,'_DocumentsViewControlMultiSelectCol0')]")
    private WebElement documentTableFirstCheckbox;
    @FindBy(xpath = "//input[contains(@id,'_DocumentProperties')]")
    private WebElement documentPropertiesButton;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_ContactDetails_cell')]")
    private WebElement contactsTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_tasks_cell')]")
    private WebElement tasksTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_portfolio_cell')]")
    private WebElement portfolioTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_relationships_cell')]")
    private WebElement relationshipTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_cases_cell')]")
    private WebElement casesTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_Payments_cell')]")
    private WebElement paymentHistoryTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_client_cell')]")
    private WebElement customerTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_extrainfo_cell')]")
    private WebElement extraInfoTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_partyroles_cell')]")
    private WebElement partyRolesTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_taxcodes_cell')]")
    private WebElement taxTab;
    @FindBy(xpath = "//td[contains(@id,'tpd_') and contains(@id,'_Applicable_certifications_cell')]")
    private WebElement certificationsTab;
    @FindBy(xpath = "//span[contains(@id,'PortfolioSummaryWidget')]")
    private List<WebElement> portfolioWidget;
    @FindBy(xpath = "//span[contains(@id,'ViewLastContactDetailsWidget')]")
    private List<WebElement> lastContactDetailsWidget;
    @FindBy(xpath = "//span[contains(@id,'PointsOfContactForPartyListViewWidget')]")
    private List<WebElement> pointsOfContactWidget;

    public PartyDetailsPage() {
        super("Party Details Page");
        this.filter = PageFactory.initElements(Filter.class);
        this.filter.addPageNamePrefix(getPageName());
    }

    public Filter getFilter() {
        return filter;
    }

    public List<WebElement> getResultRows() {
        return resultRows;
    }

    public WebElement getContactsTab() {
        return contactsTab;
    }

    public WebElement getDocumentsTab() {
        return documentsTab;
    }

    public WebElement getTasksTab() {
        return tasksTab;
    }

    public WebElement getPortfolioTab() {
        return portfolioTab;
    }

    public WebElement getRelationshipTab() {
        return relationshipTab;
    }

    public WebElement getCasesTab() {
        return casesTab;
    }

    public WebElement getPaymentHistoryTab() {
        return paymentHistoryTab;
    }

    public WebElement getExtraInfoTab() {
        return extraInfoTab;
    }

    public WebElement getPartyRolesTab() {
        return partyRolesTab;
    }

    public WebElement getPaymentPreferencesTab() {
        return paymentPreferencesTab;
    }

    public WebElement getTaxTab() {
        return taxTab;
    }

    public WebElement getCustomerTab() {
        return customerTab;
    }

    public WebElement getCertificationsTab() {
        return certificationsTab;
    }

    public List<WebElement> getPortfolioFor() {
        return portfolioWidget;
    }

    public List<WebElement> getLastContactDetails() {
        return lastContactDetailsWidget;
    }

    public List<WebElement> getPointsOfContact() {
        return pointsOfContactWidget;
    }

    public void clickDocumentsTab(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Click on Documents Tab");
        clickModalBox(documentsTab);
    }

    public void clickPaymentPreferencesTab(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Click on Payment Preferences Tab");
        click(paymentPreferencesTab);
    }

    public void selectPaymentPreference(final PageRequest pageRequest, final String paymentMethod) {
        getDriver().findElements(By.xpath("//table[contains(@id, '_PaymentPreferencesForPartyWidget')]/tbody/tr[td[@title='" + paymentMethod + "'] and td[contains(@id, '_PaymentPreferencesForPartyWidgetDefault') and @title='Yes']]"))
            .stream()
            .findFirst()
            .ifPresent(paymentPreferenceRow -> {
                pageRequest.log(getPageName(), "Selecting " + paymentMethod + " Payment Preference");
                click(paymentPreferenceRow);
            });
    }

    public void clickViewPaymentPreferenceButton(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking at the View Payment Preference Button");
        click(viewPaymentPreferenceBtn);
    }

    public void clickDocumentsTabAndNavigateToDocumentsForParty(final PartyDetailsPageRequest pageRequest) {
        clickDocumentsTab(pageRequest);
        getAssertionHelper().assertTabIsSelected("Documents forParty", documentsForPartyTab);
    }

    public void clickAddDocumentButton(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking at the Add Document Button");
        clickModalBox(addDocumentBtn);
    }

    public void clickOutboundCorrespondenceDocumentLinkToOpen(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Outbound Correspondence Document Link To Open");
        clickModalBox(outboundCorrespondenceDocumentLink.get(0));
    }

    public void clickEditAddressForPartyBtn(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Edit Party Address button");
        click(editAddressForPartyBtn);
    }

    public void clickEditAddressConfirmationYes(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Yes on Edit Address confirmation Pop-up");
        click(editAddressConfirmationPopUpYesBtn);
    }

    public String getPartyAddress(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Getting address from: " + getPageName());
        scrollPageVertically(SCROLL_TO_ADDRESS);
        String addressOnPage = partyAddress.getText().replaceAll("[\\n\\t ]", " ");
        return addressOnPage;
    }

    public void clickDocumentTypeFilter(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Document Type Filter");
        clickModalBox(filterDocumentType);
        filter.setFilter("DocumentsForPartyListviewWidget", "DocumentsViewControl", "BusinessType");
    }

    public void selectFirstCheckbox(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on First Checkbox");
        clickModalBox(documentTableFirstCheckbox);
    }

    public void clickOnPropertiesButton(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Properties Button");
        clickModalBox(documentPropertiesButton);
    }

    public void clickCustomerTab(final PartyDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Customer Tab");
        clickModalBox(customerTab);
    }

    public static class PartyDetailsPageRequest extends AbstractPageRequest {

        public PartyDetailsPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getPartyAddress() {
            return get("addressLabel");
        }
        public String getDocumentType() {
            return get("BusinessType");
        }
        public String getOrgName() {
            return get("OrgName");
        }
        public String getOrgShortName() {
            return get("OrgShortName");
        }
    }
}
