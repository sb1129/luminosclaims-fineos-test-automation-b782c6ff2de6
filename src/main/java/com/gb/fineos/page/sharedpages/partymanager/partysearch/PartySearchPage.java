package com.gb.fineos.page.sharedpages.partymanager.partysearch;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartySearchPage extends BasePage {

    private static final String PARTIES_TYPE = "PartiesType";
    private static final String PERSON_PARTY_TYPE = "Person";
    private static final String ORGANISATION_PARTY_TYPE = "Organisation";

    @FindBy(css = " a[id$='_MENUBAR.SidebarMenu_MENUITEM.SearchParties_MENUITEM.SearchPartieslink']")
    private static WebElement sidebarPartiesLink;
    @FindBy(xpath = "//input[contains(@id,'_First_Name')]")
    private static WebElement firstName;
    @FindBy(xpath = "//input[contains(@id,'_Last_Name')]")
    private static WebElement lastName;
    @FindBy(xpath = "//input[contains(@id,'_searchButton')]")
    private static WebElement searchBtn;
    @FindBy(css = "a[id$='_MENUBAR.PartySubjectMenu_MENUITEM.AddCase_MENUITEM.AddCaselink']")
    private WebElement addCase;

    @FindBy(xpath = "//input[contains(@id,'_Name')]")
    private WebElement organizationNameTextBox;
    //Initialize web element for Short name text box field
    @FindBy(xpath = "//input[contains(@id,'_Short_Name')]")
    private WebElement shortNameTextBox;

    @FindBy(xpath = "//input[contains(@id,'_Organisation_GROUP')]")
    private WebElement organisationRadioButton;
    @FindBy(xpath = "//input[contains(@id,'_Person_GROUP')]")
    private WebElement personRadioButton;

    @FindBy(xpath = "//input[contains(@id,'_addButton')]")
    private WebElement addPartyButton;
    @FindBy(xpath = "//span[contains(@id,'PageMessage1')]")
    private WebElement searchError;
    @FindBy(xpath = "//a[contains(@id,'btn_close_popup_msg')]")
    private WebElement closeSearchError;
    @FindBy(xpath = "//input[contains(@id,'_newSearchButton')]")
    private WebElement newSearchButton;
    @FindBy(xpath = "//td[contains(@id,'_recent_cell')]")
    private WebElement recentCell;
    @FindBy(xpath = "//input[contains(@id,'_searchPageCancel_')]")
    private WebElement cancelBtn;


    public PartySearchPage() {
        super("PARTY SEARCH PAGE");
    }

    public WebElement getSearchError() {
        return searchError;
    }

    public WebElement getRecentTab() {
        return recentCell;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public void doPartySearch(final PartySearchPageRequest pageRequest) {
        try {
            clickSidebarPartiesLink(pageRequest);
            inputSearchString(pageRequest);
            clickSearch(pageRequest);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred when searching for a party.", e);
        }
    }

    public void doPartySearchForAddCase(final PartySearchPageRequest pageRequest) {
        try {
            clickSidebarPartiesLink(pageRequest);
            inputSearchString(pageRequest);
            clickSearch(pageRequest);
            clickAddCase(pageRequest);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred when searching for a party.", e);
        }
    }

    public void clickSidebarPartiesLink(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking sidebar Parties Link");
        click(sidebarPartiesLink);
    }

    public void inputSearchString(final PartySearchPage.PartySearchPageRequest pageRequest) {
        inputFirstName(pageRequest);
        inputLastName(pageRequest);
    }

    public void inputFirstName(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering FirstName as: " + pageRequest.getFirstName());
        clearAndInput(pageRequest.getFirstName(), firstName);
    }

    public void inputLastName(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering LastName as: " + pageRequest.getLastName());
        clearAndInput(pageRequest.getLastName(), lastName);
    }

    public void clickSearch(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Search button");
        click(searchBtn);
    }

    public void clickAddCase(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add Case button");
        click(addCase);
    }

    public void clickOnOrganisationRadioButton(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Organisation Radio Button");
        click(organisationRadioButton);
    }

    public void clickOnPersonRadioButton(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Person Radio Button");
        click(personRadioButton);
    }

    public void clickPartyRadioButton(final PartySearchPageRequest pageRequest) {
        if (pageRequest.getPartiesType().equalsIgnoreCase(ORGANISATION_PARTY_TYPE)) {
            clickOnOrganisationRadioButton(pageRequest);
        } else {
            clickOnPersonRadioButton(pageRequest);
        }
    }

    public void enterOrganisationName(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Name as : " + pageRequest.getFirstName());
        clearAndInput(pageRequest.getFirstName(), organizationNameTextBox);
    }

    public void enterShortName(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Short Name as : " + pageRequest.getLastName());
        clearAndInput(pageRequest.getLastName(), shortNameTextBox);
    }

    public void enterPartyDetails(final PartySearchPageRequest pageRequest) {
        if (pageRequest.getPartiesType().equalsIgnoreCase(PERSON_PARTY_TYPE)) {
            clickOnPersonRadioButton(pageRequest);
            inputFirstName(pageRequest);
            inputLastName(pageRequest);
        } else if (pageRequest.getPartiesType().equalsIgnoreCase(ORGANISATION_PARTY_TYPE)) {
            clickOnOrganisationRadioButton(pageRequest);
            enterOrganisationName(pageRequest);
            enterShortName(pageRequest);
        }

        clickSearch(pageRequest);
    }

    public void clickAddNewPartyButton(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on add Button");
        click(addPartyButton);
    }

    public void clickCloseSearchError(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Close Search Error");
        click(closeSearchError);
    }

    public void clickNewSearch(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click New Search");
        click(newSearchButton);
    }

    public void clickCancel(final PartySearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel");
        click(cancelBtn);
    }

    public void clickOkButton(final PartySearchPageRequest pageRequest) {
        getDriver().findElements(By.xpath("//input[contains(@id,'_searchPageOk_cloned')]"))
            .stream()
            .findFirst()
            .ifPresent(webElement -> {
                pageRequest.log(getPageName(), "Click Ok Button");
                click(webElement);
            });
    }

    public void searchAndAddNewParty(final PartySearchPageRequest pageRequest) {
        if (pageRequest.getPartyType().equalsIgnoreCase(PERSON_PARTY_TYPE)) {
            clickOnPersonRadioButton(pageRequest);
            pageRequest.log(getPageName(), "Search for Party");
            inputFirstName(pageRequest);
            inputLastName(pageRequest);
        } else {
            clickOnOrganisationRadioButton(pageRequest);
            enterOrganisationName(pageRequest);
            enterShortName(pageRequest);
        }
        clickSearch(pageRequest);
        clickAddNewPartyButton(pageRequest);
    }

    public static class PartySearchPageRequest extends AbstractPageRequest {

        public PartySearchPageRequest(final TestCaseContext context) {
            super(context);
        }

        public boolean isPerson() {
            return getOrDefault(PARTIES_TYPE, PERSON_PARTY_TYPE).equals(PERSON_PARTY_TYPE);
        }

        public boolean isOrganisation() {
            return getOrDefault(PARTIES_TYPE, PERSON_PARTY_TYPE).equals(ORGANISATION_PARTY_TYPE);
        }

        String getFirstName() {
            return get("firstName");
        }

        String getLastName() {
            return get("lastName");
        }

        String getPayeeFirstName() {
            return get("payee_firstName");
        }

        String getPayeeLastName() {
            return get("payee_lastName");
        }

        String getPartiesType() {
            return get(PARTIES_TYPE);
        }

        String getPartyType() {
            return get("Party Type");
        }
    }
}
