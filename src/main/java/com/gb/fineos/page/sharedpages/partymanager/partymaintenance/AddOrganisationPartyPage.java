package com.gb.fineos.page.sharedpages.partymanager.partymaintenance;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddOrganisationPartyPage extends BasePage {

    private static final Logger LOG = Logger.getLogger(AddOrganisationPartyPage.class);

    private static final String PREFERRED_CONTACT_METHOD_PHONE = "Phone";
    private static final String PREFERRED_CONTACT_METHOD_MOBILE = "Mobile";

    private static final String ADD_ORGANISATION_ERROR = "An error occurred when searching for parties and adding Organisation.";

    // ***************Search Objects creation Starts *********************
    //Initialize web element for parties link field
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.SidebarMenu_MENUITEM.SearchParties_MENUITEM.SearchPartieslink')]")
    private WebElement searchPartiesLink;
    //Initialize web element for organization radio button link field
    @FindBy(xpath = "//input[contains(@id,'_Organisation_GROUP')]")
    private WebElement organizationRadioButton;
    //Initialize web element for Name textbox button
    @FindBy(xpath = "//input[contains(@id,'_Name')]")
    private WebElement organizationNameTextBox;
    //Initialize web element for Short name text box field
    @FindBy(xpath = "//input[contains(@id,'_Short_Name')]")
    private WebElement shortNameTextBox;
    //Initialize web element for search button field
    @FindBy(xpath = "//input[contains(@id,'_searchButton')]")
    private WebElement searchButton;

    // ************* validation message objects *********
    //Initialize web element for validation message field
    @FindBy(xpath = "//span[contains(@id,'PageMessage1')]")
    private WebElement validationMessage;
    //Initialize web element for validation message field
    @FindBy(xpath = "//span[@id='DynamicPartySearchWidget_payee']/table/tbody/tr[3]/td")
    private WebElement validationMsgCloseIcon;

    //Initialize web element for add button field
    @FindBy(xpath = "//input[contains(@id,'_addButton')]")
    private WebElement organizationAddButton;

    //Initialize web element for Legal Business text box field
    @FindBy(xpath = "//input[contains(@id,'_Organisation_Legal_Business_Name')]")
    private WebElement legalBusinessTextBox;
    //Initialize web element for Doing Business As text box field
    @FindBy(xpath = "//input[contains(@id,'_Organisation_Doing_Business_As')]")
    private WebElement doingBusinessAsTextBox;
    //Initialize web element for  Legal Status Dropdown field
    @FindBy(xpath = "//select[contains(@id,'_legalStatus')]")
    private WebElement legalStatusDropdown;
    //Initialize web element for  registration Number text box field
    @FindBy(xpath = "//input[contains(@id,'_registrationNumber')]")
    private WebElement registrationNumberTextBox;
    //Initialize web element for  registration Number text box field
    @FindBy(xpath = "//input[contains(@id,'_financialYear')]")
    private WebElement financialYearEndCalender;

    //Initialize web element for Phone checkbox field
    @FindBy(xpath = "//input[contains(@id,'_preferredContactMethodCheckbox_CHECKBOX')]")
    private WebElement preferredPhoneCheckbox;
    //Initialize web element for Phone int.code textbox  field
    @FindBy(xpath = "//input[contains(@id,'_intCode')]")
    private WebElement intCodePhoneTextBox;
    //Initialize web element for area code number textbox field
    @FindBy(xpath = "//input[contains(@id,'_areaCode')]")
    private WebElement areaCodeTextBox;
    //Initialize web element for Phone number textbox field
    @FindBy(xpath = "//input[contains(@id,'_telephoneNo')]")
    private WebElement telNoPhoneTextBox;
    // ********* mobile details ****
    //Initialize web element for Mobile checkbox field
    @FindBy(xpath = "//input[contains(@id,'_preferredContactMethodCheckbox_CHECKBOX')]")
    private WebElement preferredMobileCheckBox;
    //Initialize web element for Mobile checkbox field
    @FindBy(xpath = "//input[contains(@id,'_intCode')]")
    private WebElement intCodeMobileTextBox;
    //Initialize web element for area code Mobile checkbox field
    @FindBy(xpath = "//input[contains(@id,'_areaCode')]")
    private WebElement areCodeMobileTextBox;
    //Initialize web element for Mobile number textbox field
    @FindBy(xpath = "//input[contains(@id,'_telephoneNo')]")
    private WebElement mobileNumberTextBox;

    //***********Address related objects *******************
    //Initialize web element for New button field
    @FindBy(xpath = "//input[contains(@id,'AddressesForPartyWidget_') and contains(@id,'_NewButton')]")
    private WebElement addAddressNewButton;
    //Initialize web element for Alert Select Address Type  field
    @FindBy(xpath = "//select[contains(@id,'AddressesForPartyWidget') and contains(@id,'_AddUsageDropDown_DropDown')]")
    private WebElement alertSelectAddressType;
    //Initialize web element for Alert OK button  field
    @FindBy(xpath = "//input[contains(@id,'AddressesForPartyWidget_') and contains(@id,'_AddUsageDropDown_yes')]")
    private WebElement confirmAddAddressOkBtn;
    //Initialize web element for Alert Cancel button  field
    @FindBy(xpath = "//input[contains(@id,'AddressesForPartyWidget_') and contains(@id,'_AddUsageDropDown_no')]")
    private WebElement confirmAddAddressCancelBtn;

    // ****************adding new address from test data ******
    // ****************adding new address from test data ******
    //Initialize web element for Override QAS CHECKBOX  field
    @FindBy(xpath = "//input[contains(@id,'EditAddressWidget') and contains(@id,'_Override_QAS_CHECKBOX')]")
    private WebElement addressPageQASCheckBox;
    //Initialize web element for Country dropdown  field
    @FindBy(xpath = "//select[contains(@id,'_Country')]")
    private WebElement addressPageCountryDropdown;
    //Initialize web element for Address Page Building Name 1 TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Building_Name_1')]")
    private WebElement addressPageBuildingName1TextBox;
    //Initialize web element for Address Page Premise Number TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_PremiseNo')]")
    private WebElement addressPagePremiseNumberTextBox;
    //Initialize web element for Address Page street TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Street')]")
    private WebElement addressPageStreetTextBox;
    //Initialize web element for Address Page Street Type dropdown field
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Street_Type')]")
    private WebElement addressPageStreetTypeDropdown;
    //Initialize web element for Address Page Premise Number TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Suburb')]")
    private WebElement addressPageSuburbTextBox;
    //Initialize web element for Address Page State dropdown field
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_State')]")
    private WebElement addressPageStateDropdown;
    //Initialize web element for Address Page PostCode TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_PostCode')]")
    private WebElement addressPagePostCodeTextBox;
    //Initialize web element for Address Page Delivery Point ID TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Delivery_Point_ID')]")
    private WebElement addressPageDeliveryPointIDTextBox;
    //Initialize web element for OK button  field
    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement addressPageOkButton;
    //Initialize web element for set Default button  field
    @FindBy(xpath = "//input[contains(@id,'_correspondenceButton')]")
    private WebElement setDefaultButton;

    //Initialize web element for TimeZone text box field
    @FindBy(xpath = "//input[contains(@id,'EditContactMediaWidgetEditFlatAddressMediumWidget3_') and contains(@id,'_flatAddress')]")
    private WebElement timeZoneTextBox;
    //Initialize web element for email text box field
    @FindBy(xpath = "//input[contains(@id,'EditContactMediaWidgetEditFlatAddressMediumWidget2_') and contains(@id,'_flatAddress')]")
    private WebElement emailAddressTextBox;
    //Initialize web element for Ok button field
    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement okOrganisationSaveButton;
    //Initialize web element for Ok button field
    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement organisationSaveButton;

    //******************NZ and UK Address webelements ******************
    //Initialize web element for Address line1 TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_AddressLine1')]")
    private WebElement addressLine1NZTextBox;
    //Initialize web element for Suburb/City/Town TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_AddressLine3')]")
    private WebElement suburbNZTextBox;
    //Initialize web element for State/Province TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_AddressLine4')]")
    private WebElement stateNZTextBox;
    //Initialize web element for State/Province TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_PostCode')]")
    private WebElement postCodeNZTextBox;
    //Initialize web element for effective From Start TextBox  field
    @FindBy(xpath = "//input[contains(@id,'EditEffectiveDatesWidget') and contains(@id,'_effectiveFrom')]")
    private WebElement effectiveFromStartTextBox;
    //Initialize web element for effective To End TextBox  field
    @FindBy(xpath = "//input[contains(@id,'EditEffectiveDatesWidget') and contains(@id,'_effectiveTo')]")
    private WebElement effectiveToEndTextBox;
    @FindBy(xpath = "//div[@class='pageheader_heading']/h2")
    private WebElement organisationNameTextBox;


    /**
     * Method Name : AddPersonPage()
     * Description : Parameterized Constructor - To initialize object
     */
    public AddOrganisationPartyPage() {
        super("ADD ORGANISATION PAGE");
    }
    public WebElement getValidationMessage() {
        return validationMessage;
    }

    public void searchForOrganisation(final AddOrganisationPartyPageRequest pageRequest) {
        try {
            waitForElementPresent(searchPartiesLink);
            clickSearchPartiesIcon(pageRequest);
            clickOrganisationRadioBtn(pageRequest);
            waitForElementPresent(organizationNameTextBox);
            enterOrganisationName(pageRequest);
            enterShortName(pageRequest);
            clickSearchButton(pageRequest);
            if (isElementPresent(validationMessage)) {
                LOG.info("search record not found");
                waitForElementPresent(validationMessage);
                if (getText(validationMessage).equalsIgnoreCase("There are no records found using this criteria")) {
                    LOG.info("searching party Not found and creating new party.");
                    clickCloseIcon(pageRequest);
                }
            } else {
                throw new AddOrganisationException("searching Organisation party is found--> Script is not adding an Organisation, pleas update the test data");
            }
        } catch (AddOrganisationException a) {
            pageRequest.getContext().error(getPageName(), a);
            LOG.warn("searching Organisation party is found--> Script is not adding an Organisation, pleas update the test data");
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError(ADD_ORGANISATION_ERROR, e);
        }
    }

    public void doAddingOrganisationDetails(final AddOrganisationPartyPageRequest pageRequest) {
        try {
            clickAddOrganisationButton(pageRequest);
            enterLegalBusiness(pageRequest);
            enterDoingBusinessAs(pageRequest);
            enterFinancialYearEndDate(pageRequest);
            selectLegalStatus(pageRequest);
            enterRegistrationNumber(pageRequest);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError(ADD_ORGANISATION_ERROR, e);
        }
    }

    public void doAddingContactInformation(final AddOrganisationPartyPageRequest pageRequest) {
        try {
            if (pageRequest.getPreferredContactMethod().equalsIgnoreCase(PREFERRED_CONTACT_METHOD_PHONE)) {
                preferredContactMethodPhone(pageRequest);
                enterInternationalCodeForPhone(pageRequest);
                enterAreaCodeForPhone(pageRequest);
                enterPhoneNumer(pageRequest);
            } else if (pageRequest.getPreferredContactMethod().equalsIgnoreCase(PREFERRED_CONTACT_METHOD_MOBILE)) {
                preferredContactMethodMobile(pageRequest);
                enterInternationalCodeForMobile(pageRequest);
                enterAreaCodeForMobile(pageRequest);
                enterMobileNumber(pageRequest);
            }
            enterEmailAddress(pageRequest);
            enterTimeZone(pageRequest);
            clickOkToSave(pageRequest);
            clickOnSetDefaultButton(pageRequest);
            clickOkToSave(pageRequest);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError(ADD_ORGANISATION_ERROR, e);
        }
    }

    public void doAddingAddressDetails(final AddOrganisationPartyPageRequest pageRequest) {
        clickOnNewButtonToAddAddress(pageRequest);
        setAlertSelectAddressType(pageRequest);
        clickOkToConfirmAddAddress(pageRequest);
    }

    /**
     * Method Names : Creating resuable methods ()
     */
    private void clickSearchPartiesIcon(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Parties Icon");
        click(searchPartiesLink);
    }

    private void clickOrganisationRadioBtn(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Organisation radio button");
        click(organizationRadioButton);
    }

    private void enterOrganisationName(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Name as : " + pageRequest.getOrganisationName() + pageRequest.getTestRunIdentifier());
        clearAndInput(pageRequest.getOrganisationName() + pageRequest.getTestRunIdentifier(), organizationNameTextBox);
    }

    private void enterShortName(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Short Name as : " + pageRequest.getShortName() + pageRequest.getTestRunIdentifier());
        clearAndInput(pageRequest.getShortName() + pageRequest.getTestRunIdentifier(), shortNameTextBox);
    }

    private void clickSearchButton(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Parties Icon");
        click(searchButton);
    }

    private void clickCloseIcon(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Validation message close Icon");
        click(validationMsgCloseIcon);
    }

    public void clickAddOrganisationButton(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on add Button");
        click(organizationAddButton);
    }

    public void enterLegalBusiness(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Legal business as : " + pageRequest.getLegalBusiness());
        clearAndInput(pageRequest.getLegalBusiness(), legalBusinessTextBox);
    }

    public void enterDoingBusinessAs(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Legal business as : " + pageRequest.getDoingBusinessAs());
        clearAndInput(pageRequest.getDoingBusinessAs(), doingBusinessAsTextBox);
    }

    public void enterFinancialYearEndDate(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Financial Year End date as : " + pageRequest.getFinancialYearEnd());
        clearAndInput(pageRequest.getFinancialYearEnd(), financialYearEndCalender);
    }

    public void selectLegalStatus(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Legal Status as : " + pageRequest.getLegalStatus());
        selectValue(pageRequest.getLegalStatus(), legalStatusDropdown);
    }

    public void enterRegistrationNumber(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Registration Or ABN Number as : " + pageRequest.getRegistrationOrABN());
        clearAndInput(pageRequest.getRegistrationOrABN(), registrationNumberTextBox);
    }

    private void clickOnNewButtonToAddAddress(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Add button while adding a Address");
        scrollIntoView(addAddressNewButton);
        clickModalBox(addAddressNewButton);
    }

    private void setAlertSelectAddressType(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Address type as : " + pageRequest.getAddressType());
        scrollIntoView(alertSelectAddressType);
        selectValue(pageRequest.getAddressType(), alertSelectAddressType);
    }
    private void clickOkToConfirmAddAddress(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on OK Button while adding address");
        click(confirmAddAddressOkBtn);
    }

    private void clickOkToSave(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on OK Button to save");
        click(organisationSaveButton);
    }

    private void clickOnSetDefaultButton(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Set Default button");
        click(setDefaultButton);
    }

    private void preferredContactMethodPhone(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Preferred Contact Method Phone checkbox");
        click(preferredPhoneCheckbox);
    }

    private void enterInternationalCodeForPhone(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering International code as : " + pageRequest.getInternationalCodePhone());
        clearAndInput(pageRequest.getInternationalCodePhone(), intCodePhoneTextBox);
    }

    private void enterAreaCodeForPhone(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering area code as : " + pageRequest.getAreaCodePhone());
        clearAndInput(pageRequest.getAreaCodePhone(), areaCodeTextBox);
    }

    private void enterPhoneNumer(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering phone number as : " + pageRequest.getPhoneNumber());
        clearAndInput(pageRequest.getPhoneNumber(), telNoPhoneTextBox);
    }

    private void preferredContactMethodMobile(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Preferred Contact Method Mobile checkbox");
        click(preferredMobileCheckBox);
    }

    private void enterInternationalCodeForMobile(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering International code as : " + pageRequest.getInternationalCodeMobile());
        clearAndInput(pageRequest.getInternationalCodeMobile(), intCodeMobileTextBox);
    }

    private void enterAreaCodeForMobile(final AddOrganisationPartyPageRequest pageRequest) {
        clearAndInput(pageRequest.getAreaCodeMobile(), areCodeMobileTextBox);
        pageRequest.log(getPageName(), "Entered area code as : " + pageRequest.getAreaCodeMobile());
    }

    private void enterMobileNumber(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Mobile number as : " + pageRequest.getPhoneNumber());
        clearAndInput(pageRequest.getPhoneNumber(), mobileNumberTextBox);
    }

    private void enterEmailAddress(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Time zone as: " + pageRequest.getEmailAddress());
        clearAndInput(pageRequest.getEmailAddress(), emailAddressTextBox);
    }

    private void enterTimeZone(final AddOrganisationPartyPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Time zone as : " + pageRequest.getTimeZone());
        clearAndInput(pageRequest.getTimeZone(), timeZoneTextBox);

    }
    public void verifyOrganisation(final AddOrganisationPartyPageRequest pageRequest) {
        final String organisationName = getText(organisationNameTextBox);
        pageRequest.log(getPageName(), "Created Organisation full Name  is : " + organisationName);
    }

    public void addNewOrgParty(final AddOrganisationPartyPageRequest pageRequest) {
        enterOrganisationName(pageRequest);
        enterShortName(pageRequest);
        enterLegalBusiness(pageRequest);
        enterDoingBusinessAs(pageRequest);
        enterFinancialYearEndDate(pageRequest);
        selectLegalStatus(pageRequest);
        enterRegistrationNumber(pageRequest);
        clickOnNewButtonToAddAddress(pageRequest);
        setAlertSelectAddressType(pageRequest);
        clickOkToConfirmAddAddress(pageRequest);
    }



    public static class AddOrganisationPartyPageRequest extends AbstractPageRequest {

        public AddOrganisationPartyPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getUsername() {
            return get("UserName");
        }

        public String getPassword() {
            return get("Password");
        }

        public String getOrganisationName() {
            return get("Organisation Name");
        }

        public String getShortName() {
            return get("Short Name");
        }

        public String getLegalBusiness() {
            return get("Legal Business");
        }

        public String getDoingBusinessAs() {
            return get("Doing Business As");
        }

        public String getLegalStatus() {
            return get("Legal Status");
        }

        public String getRegistrationOrABN() {
            return get("RegistrationOrABN");
        }

        public String getFinancialYearEnd() {
            return get("Financial Year End");
        }

        public String getPreferredContactMethod() {
            return get("Preferred Contact Method");
        }

        public String getInternationalCodePhone() {
            return get("International Code");
        }

        public String getAreaCodePhone() {
            return get("Area Code");
        }

        public String getPhoneNumber() {
            return get("PhoneNumber");
        }

        public String getInternationalCodeMobile() {
            return get("International Code");
        }

        public String getAreaCodeMobile() {
            return get("Area Code");
        }

        public String getEmailAddress()  {
            return get("EmailAddress");
        }

        public String getTimeZone() {
            return get("TimeZone");
        }

        public String getAddressType() {
            return get("Address Type");
        }

    }
    static class AddOrganisationException extends Exception {

    protected AddOrganisationException(final String msg) {
        super(msg);
    }
}
}
