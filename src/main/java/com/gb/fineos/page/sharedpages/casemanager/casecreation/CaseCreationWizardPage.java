package com.gb.fineos.page.sharedpages.casemanager.casecreation;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.ClaimType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.Filter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class CaseCreationWizardPage extends BasePage {

    private Filter filter;

    public CaseCreationWizardPage() {
        super("CASE_CREATION_WIZARD_PAGE");
        this.filter = PageFactory.initElements(Filter.class);
        this.filter.addPageNamePrefix(getPageName());
    }

    public Filter getFilter() {
        return filter;
    }

    @FindBy(css = "tr[class^='ListRow']")
    private List<WebElement> caseTypes;
    @FindBy(css = "input[id$='_next']")
    private WebElement next;
    @FindBy(css = "input[id$='_AddContract']")
    private WebElement addContract;
    @FindBy(xpath = "//input[contains(@id,'pcrList_') and contains(@id,'_partyCaseRoles_cmdAdd')]")
    private WebElement addLinkedParty;
    @FindBy(xpath = "//option[contains(text(),'Notifier')]")
    private WebElement addNotifier;
    @FindBy(css = "input[id$='_FindPartyButtonBean']")
    private WebElement nextLinkParty;
    @FindBy(css = "input[id$='_First_Name']")
    private WebElement firstName;
    @FindBy(css = "input[id$='_Last_Name']")
    private WebElement lastName;
    @FindBy(css = "input[id$='_searchButton']")
    private WebElement searchNotifierBtn;
    @FindBy(css = "input[id$='_ReferenceNo5']")
    private WebElement versionRef;
    @FindBy(css = "input[id$='_searchButton']")
    private WebElement searchBtn;
    @FindBy(css = "table[id$='_contractslistview']")
    private WebElement searchedPolicy;
    @FindBy(css = "input[id$='_searchPageOk_cloned']")
    private WebElement searchPageOk;
    @FindBy(xpath = "//input[contains(@id,'_next_cloned')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel_cloned')]")
    private WebElement cancelButton;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'_partyCaseRoles_Role')]")
    private WebElement roleFilter;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'_partyCaseRoles_Name')]")
    private WebElement nameFilter;
    @FindBy(xpath = "//span[contains(@id,'pcrList')]//tr[contains(@class,'ListRow')]")
    private List<WebElement> linkedPartiesRows;
    @FindBy(xpath = "//td[contains(@id,'_partyCaseRolesName')]")
    private WebElement linkedPartyName;
    @FindBy(xpath = "//td[contains(@id,'_partyCaseRolesRole')]")
    private WebElement linkedPartyRole;
    @FindBy(xpath = "//td[@class='raiseMessageText']/ul/li/span")
    private List<WebElement> errorValidation;

    //Linked parties
    @FindBy(xpath = "//table[contains(@id,'pcrList_') and contains(@id,'_partyCaseRoles')]/tbody/tr")
    private List<WebElement> linkedPartiesTable;

    public List<WebElement> getLinkedPartiesTable() {
        return linkedPartiesTable;
    }

    public List<WebElement> getLinkedPartiesRows() {
        return linkedPartiesRows;
    }

    public void clickOnAddContract(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add button to add the Policy");
        click(addContract);
    }

    public void enterPolicyVersionRef(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering The Policy Search String as: " + pageRequest.getVersionRef());
        input(pageRequest.getVersionRef(), versionRef);
    }

    public void clickOnSearchButton(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Button");
        click(searchBtn);

        final WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//table[contains(@id, 'ContractsListviewWidget_') and contains(@id, '_contractslistview')]/tbody/tr[@class='ListRowSelected']"))
        );
    }

    public void clickOnSearchPageOk(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK Button");
        click(searchPageOk);
    }

    public void clickAddNotifier(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Role as Notifier");
        click(addNotifier);
    }

    public void clickOnNextAtLinkPartyPage(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Next on LinkParty");
        click(nextLinkParty);
    }

    public void selectCaseType(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Case Type as: " + pageRequest.getCaseType());
        clickTextFromList(caseTypes, pageRequest.getCaseType());
    }

    public void inputSearchString(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering FirstName as: " + pageRequest.getFirstName());
        clearAndInput(pageRequest.getFirstName(), firstName);
        pageRequest.log(getPageName(), "Entering LastName as: " + pageRequest.getLastName());
        clearAndInput(pageRequest.getLastName(), lastName);
    }

    public void clickSearch(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Search");
        click(searchBtn);
    }

    public void clickAddLinkedParty(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Add Linked Party button");
        clickModalBox(addLinkedParty);
    }

    public void clickOkButton(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK button");
        click(okButton);
    }

    public void clickOnNextButton(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Next button");
        waitForElementPresent(next);
        click(next);
    }

    public void cancelCreateClaim(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking cancel Button");
        click(cancelButton);
    }

    public void clickRoleFilter(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click the Role Filter");
        clickModalBox(roleFilter);
        filter.setFilter("pcrList", "partyCaseRoles", "Role");
    }

    public void inputRoleToFilter(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Input Role Filter as" + pageRequest.getRole());
        filter.inputFilterText(pageRequest, pageRequest.getRole());
    }

    public String getActualLinkedPartyUser(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Get the Actual Linked Party user added : " + linkedPartyName.getText());
        return linkedPartyName.getText();
    }

    public String getExpectedLinkedPartyUser(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Get the Expected Linked Party user : " + pageRequest.getTitle() + " " + pageRequest.getFirstName() + " " + pageRequest.getTestRunIdentifier() + " " + pageRequest.getLastName());
        return (pageRequest.getTitle() + " " + pageRequest.getFirstName() + " " + pageRequest.getTestRunIdentifier() + " " + pageRequest.getLastName());
    }

    public String getActualRole(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Get the role ");
        return linkedPartyRole.getText();
    }

    public void clickCancel(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel");
        click(cancelButton);
    }

    public String getActualLinkedOrgPartyUser(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Get the Actual Linked Party user added : " + linkedPartyName.getText());
        return linkedPartyName.getText();
    }

    public String getExpectedLinkedOrgPartyUser(final CaseCreationWizardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Get the Expected Linked Party user : " + pageRequest.getOrgName() + " " + pageRequest.getTestRunIdentifier());
        return pageRequest.getOrgName() + " " + pageRequest.getTestRunIdentifier();
    }


    public static class CaseCreationWizardPageRequest extends AbstractPageRequest {

        public CaseCreationWizardPageRequest(final TestCaseContext context) {
            super(context);
        }

        public ClaimType getClaimType() {
            return ClaimType.valueOfProperty(get("Claim_Type"));
        }

        public String getCaseType() {
            return get("caseType");
        }

        public String getVersionRef() {
            return get("versionRef");
        }

        public String getTitle() {
            return get("Title");
        }

        public String getFirstName() {
            return get("firstName");
        }

        public String getLastName() {
            return get("lastName");
        }

        public String getRole() {
            return get("Linked Party");
        }

        public String getOrgName() {
            return get("Organisation Name");
        }

        public String getParameterValue(final String parameterValue) {
            return get(parameterValue);
        }

        public List<String> getExpectedValidationMessages(final String key, final String delimiter) {
            return Arrays.asList(get(key).split(delimiter));
        }
    }
}
