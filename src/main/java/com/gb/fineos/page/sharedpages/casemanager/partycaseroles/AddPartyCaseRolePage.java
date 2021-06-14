package com.gb.fineos.page.sharedpages.casemanager.partycaseroles;

import com.gb.fineos.domain.ClaimType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// FIXME: 10/02/2020 This page is extending the ProcessStatusPageBase where it should extend BasePage
public class AddPartyCaseRolePage extends ProcessStatusPageClaimBase {

    protected static final String PAGE_NAME = "LINK A PARTY";
    private static final String PERSON_RADIO_BUTTON = "Person";
    private static final String ORGANISATION_RADIO_BUTTON = "Organisation";


    //link party reusable code

    @FindBy(xpath = "//select[contains(@id,'_Roles')]")
    private WebElement claimantRole;
    @FindBy(xpath = "//input[contains(@id,'_FindPartyButtonBean')]")
    private WebElement linkPartyNextButton;
    @FindBy(xpath = "//input[contains(@id,'_Person_GROUP')]")
    private WebElement personRadioButton;
    @FindBy(xpath = "//input[contains(@id,'_Organisation_GROUP')]")
    private WebElement organisationRadioButton;
    @FindBy(xpath = "//input[contains(@id,'_First_Name')]")
    private WebElement personFirstName;
    @FindBy(xpath = "//input[contains(@id,'_Last_Name')]")
    private WebElement personLastName;
    @FindBy(xpath = "//input[contains(@id,'_Name')]")
    private WebElement organisationName;
    @FindBy(xpath = "//input[contains(@id,'_Short_Name')]")
    private WebElement organisationShortName;
    @FindBy(xpath = "//select[contains(@id,'_Country')]")
    private WebElement claimantOrganisationCountry;
    @FindBy(xpath = "//input[contains(@id,'_searchButton')]")
    private WebElement searchButton;
    @FindBy(xpath = "//span[contains(@id,'PageMessage1')]")
    private WebElement searchError;
    @FindBy(xpath = "//a[contains(@id,'btn_close_popup_msg')]")
    private WebElement closeSearchError;

    public AddPartyCaseRolePage() {
        super();
    }

    public WebElement getSearchButton() {
        return searchButton;
    }
    public WebElement getSearchError() {
        return searchError;
    }

    public void doLinkAClaimantParty(final AddPartyCaseRolePageRequest pageRequest) {
        try {
            selectLinkPartyRole(pageRequest);
            clickOnNextButton(pageRequest);
            if (pageRequest.getLinkPartyType().equalsIgnoreCase(PERSON_RADIO_BUTTON)) {
                clickOnPersonRadioButton(pageRequest);
                enterPersonFirstName(pageRequest);
                enterPersonLastName(pageRequest);
            } else if (pageRequest.getLinkPartyType().equalsIgnoreCase(ORGANISATION_RADIO_BUTTON)) {
                clickOnOrganisationRadioButton(pageRequest);
                enterOrganisationName(pageRequest);
                enterOrganisationShortName(pageRequest);
                selectClaimantCountry(pageRequest);
            }
            clickOnSearchButton(pageRequest);
            // for Motor claim ok button click is required
            if (pageRequest.getClaimType() == ClaimType.MOTOR_CLAIM) {
                clickOnOKButton(pageRequest);
            }
        } catch (Exception e) {
            pageRequest.getContext().error(PAGE_NAME, e);
            throw new AssertionError("An error occurred while linking the party.", e);
        }
    }


    public void selectLinkPartyRole(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting link party role as: " + pageRequest.getLinkPartyRole());
        selectValue(pageRequest.getLinkPartyRole(), claimantRole);
    }

    public void clickOnNextButton(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Next button to link the party");
        click(linkPartyNextButton);
    }

    public void clickOnPersonRadioButton(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Person radio button");
        clickModalBox(personRadioButton);
    }

    public void enterPersonFirstName(final AddPartyCaseRolePageRequest pageRequest) {
        final String linkPartyClaimantFirstName = pageRequest.isUniqueClaimant()
            ? pageRequest.getLinkPartyClaimantFirstName() + " " + pageRequest.getTestRunIdentifier()
            : pageRequest.getLinkPartyClaimantFirstName();
        pageRequest.log(PAGE_NAME, "Entering First Name as: " + linkPartyClaimantFirstName);
        clearAndInput(linkPartyClaimantFirstName, personFirstName);
    }

    public void enterPersonLastName(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Entering Last Name as: " + pageRequest.getLinkPartyClaimantLastNameOrShortName());
        clearAndInput(pageRequest.getLinkPartyClaimantLastNameOrShortName(), personLastName);
    }

    public void clickOnOrganisationRadioButton(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Organisation radio button");
        clickModalBox(organisationRadioButton);
    }

    public void enterOrganisationName(final AddPartyCaseRolePageRequest pageRequest) {
        final String linkPartyClaimantName = pageRequest.isUniqueClaimant()
            ? pageRequest.getLinkPartyClaimantFirstName() + " " + pageRequest.getTestRunIdentifier()
            : pageRequest.getLinkPartyClaimantFirstName();
        pageRequest.log(PAGE_NAME, "Entering First Name as : " + linkPartyClaimantName);
        clearAndInput(linkPartyClaimantName, organisationName);
    }

    public void enterOrganisationShortName(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Entering Short Name as : " + pageRequest.getLinkPartyClaimantLastNameOrShortName());
        clearAndInput(pageRequest.getLinkPartyClaimantLastNameOrShortName(), organisationShortName);
    }

    public void selectClaimantCountry(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Organisation Country as: " + pageRequest.getClaimantOrganisationCountry());
        selectValue(pageRequest.getClaimantOrganisationCountry(), claimantOrganisationCountry);
    }

    public void clickOnSearchButton(final AddPartyCaseRolePageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Search button");
        click(searchButton);
    }

    public static class AddPartyCaseRolePageRequest extends ProcessStatusPageClaimBaseRequest {
        public AddPartyCaseRolePageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
