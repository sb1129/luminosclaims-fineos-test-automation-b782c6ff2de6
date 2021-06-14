package com.gb.fineos.page.sharedpages.partymanager.partymaintenance;


import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPersonPage extends BasePage {

    private static final Logger LOG = Logger.getLogger(AddPersonPage.class);

    private static final String PREFERRED_CONTACT_METHOD_PHONE = "Phone";
    private static final String PREFERRED_CONTACT_METHOD_MOBILE = "Mobile";
    private static final int VERTICAL_LENGTH = 400;
    private static final int SCROLL_TO_ADDRESS = 120;

    // ***************Search Objects creation Starts *********************
    //Initialize web element for parties link field
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.SidebarMenu_MENUITEM.SearchParties_MENUITEM.SearchPartieslink')]")
    private WebElement searchPartiesLink;
    //Initialize web element for person radio button link field
    @FindBy(xpath = "//input[contains@id,'_Person_GROUP')]")
    private WebElement personRadioButton;
    //Initialize web element for organization radio button link field
    @FindBy(xpath = "//input[contains(@id,'_Organisation_GROUP')]")
    private WebElement organizationRadioButton;
    //Initialize web element for both radio button link field
    @FindBy(xpath = "//input[contains(@id,'_Both_GROUP')]")
    private WebElement bothRadioButton;
    //Initialize web element for firstName text field
    @FindBy(xpath = "//input[contains(@id,'_First_Name')]")
    private WebElement firstNameTextBox;
    //Initialize web element for surName text field
    @FindBy(xpath = "//input[contains(@id,'_Last_Name')]")
    private WebElement surNameTextBox;
    //Initialize web element for serach button field
    @FindBy(xpath = "//input[contains(@id,'_searchButton')]")
    private WebElement searchButton;
    //Initialize web element for serach button field
    @FindBy(xpath = "//input[contains(@id,'_newSearchButton')]")
    private WebElement newSearchButton;
    // ***************Search Objects creation end *********************

    //Initialize web element for add button field
    @FindBy(xpath = "//input[contains(@id,'_addButton')]")
    private WebElement addPersonButton;
    //*******************Person tab ****************************
    //Initialize web element for title selection field
    @FindBy(xpath = "//select[contains(@id,'PersonalDetailsWidget') and contains(@id,'_titleEnum')]")
    private WebElement titleDropdown;
    //Initialize web element for title other checkbox field
    @FindBy(xpath = "//input[contains(@id,'_otherTitleCheckBox_CHECKBOX')]")
    private WebElement titleOtherCheckBox;
    //Initialize web element for preferredNameTextBox field
    @FindBy(xpath = "//input[contains(@id,'_preferredNameTextBox')]")
    private WebElement preferredNameTextBox;
    //Initialize web element for Last Name Prefix field
    @FindBy(xpath = "//select[contains@id,'_surnamePrefixEnum')]")
    private WebElement lastNamePrefixDropdown;
    //Initialize web element for Last Name Prefix other checkbox field
    @FindBy(xpath = "//select[contains(@id,'_surnamePrefixEnum')]")
    private WebElement lastNamePrefixCheckBox;
    //Initialize web element for pronounced As TextBox field
    @FindBy(xpath = "//input[contains(@id,'_pronouncedAsTextBox')]")
    private WebElement pronouncedAsTextBox;
    //Initialize web element for middleInitial TextBox field
    @FindBy(xpath = "//input[contains(@id,'_middleInitialTextBox')]")
    private WebElement middleInitialTextBox;
    //Initialize web element for previous Name TextBox field
    @FindBy(xpath = "//input[contains(@id,'_previousNameTextBox')]")
    private WebElement previousNameTextBox;
    //Initialize web element for letters TextBox field
    @FindBy(xpath = "//input[contains(@id,'_lettersTextBox')]")
    private WebElement lettersTextBox;
    //Initialize web element for Date_of_Birth field
    @FindBy(xpath = "//input[contains(@id,'_Date_of_Birth')]")
    private WebElement dateOfBirthCalender;
    //Initialize web element for exactDateCheckBox CHECKBOX field
    @FindBy(xpath = "//input[contains(@id,'_exactDateCheckBox_CHECKBOX')]")
    private WebElement exactDateCheckBoxCheckBox;
    //Initialize web element for gender selection field
    @FindBy(xpath = "//select[contains(@id,'_genderEnum')]")
    private WebElement genderDropdown;
    //Initialize web element for marital Status selection field
    @FindBy(xpath = "//select[contains(@id,'_maritalStatusEnum')]")
    private WebElement maritalStatusEnum;
    //Initialize web element for nationality selection field
    @FindBy(xpath = "//select[contains(@id,'_nationalityEnum')]")
    private WebElement nationalityEnum;
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
    private WebElement preferredMobileCheckbox;
    //Initialize web element for Mobile checkbox field
    @FindBy(xpath = "//input[contains(@id,'_intCode')]")
    private WebElement intCodeMobileTextBox;
    //Initialize web element for area code Mobile checkbox field
    @FindBy(xpath = "//input[contains(@id,'_areaCode')]")
    private WebElement areCodeMobileTextBox;
    //Initialize web element for Mobile checkbox field
    @FindBy(xpath = "//input[contains(@id,'_telephoneNo')]")
    private WebElement mobileNumberTextbox;

    //Initialize web element for DOB text box field
    @FindBy(xpath = "//input[contains(@id,'_Date_of_Birth')]")
    private WebElement dateOfBirthTextBox;
    //Initialize web element for TimeZone text box field
    @FindBy(xpath = "//input[contains(@id,'EditContactMediaWidgetEditFlatAddressMediumWidget3_') and contains(@id,'_flatAddress')]")
    private WebElement timeZoneTextBox;
    //Initialize web element for email text box field
    @FindBy(xpath = "//input[contains(@id,'EditContactMediaWidgetEditFlatAddressMediumWidget2_') and contains(@id,'_flatAddress')]")
    private WebElement emailAddressTextBox;

    // ************* validation message objects *********
    //Initialize web element for validation message field
    @FindBy(xpath = "//span[contains(@id,'PageMessage1')]")
    private WebElement validationMessage;
    //Initialize web element for validation message field
    @FindBy(xpath = "//span[@id='DynamicPartySearchWidget_payee']/table/tbody/tr[3]/td")
    private WebElement validationMsgCloseIcon;

    //***********Address related objects *******************
    //Initialize web element for New button field
    @FindBy(xpath = "//input[contains(@id,'AddressesForPartyWidget') and contains(@id,'_NewButton')]")
    private WebElement addAddressNewButton;
    //Initialize web element for Alert Select Address Type  field
    @FindBy(xpath = "//select[contains(@id,'_AddUsageDropDown_DropDown')]")
    private WebElement alertSelectAddressType;
    //Initialize web element for Alert OK button  field
    @FindBy(css = "input[id$='_AddUsageDropDown_yes']")
    private WebElement confirmAddAddressOkBtn;
    //Initialize web element for Alert Cancel button  field
    @FindBy(css = "input[id$='_AddUsageDropDown_no']")
    private WebElement confirmAddAddressCancelBtn;

    @FindBy(xpath = "//input[contains(@id,'_AddUsageDropDown_no')]")
    private WebElement alertCancel;
    // ****************search from existing records
    //Initialize web element for SingleLine Radio button field
    @FindBy(xpath = "//input[@id='Singleline']")
    private WebElement addAddressSingleLine;
    //Initialize web element for Enter search textbox field
    @FindBy(xpath = "//input[@id='searchText']")
    private WebElement addAddressEnterSearchTextBox;
    //Initialize web element for search button  field
    @FindBy(xpath = "//input[@id='ButtonSearch']")
    private WebElement addAddressButtonSearch;
    //Initialize web element for Accept button  field
    @FindBy(xpath = "//input[@id='Accept']")
    private WebElement addAddressAcceptButton;

    @FindBy(xpath = "//input[contains(@id,'_correspondenceButton')]")
    private WebElement setDefaultButton;
    //Initialize web element for Ok button field
    @FindBy(xpath = "//input[contains(@id,'_editPageSave')]")
    private WebElement okPersonSaveButton;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel')]")
    private WebElement cancelBtn;
    @FindBy(xpath = "//div[@class='pageheader_heading']/h2")
    private WebElement personNameElement;
    @FindBy(xpath = "//span[contains(@id,'AddressForPartyAddressVersionWidget_') and contains(@id,'_Address')]")
    private WebElement addressOnParty;


    public AddPersonPage() {
        super("ADD PERSON");
    }

    public WebElement getValidationMessage() {
        return validationMessage;
    }

    /*
     * Method Name : SearchForExistingPerson()
     * Description : Search for existing person
     */
    public void searchForPerson(final AddPersonPageRequest pageRequest) {
        try {
            LOG.info("**** Adding person party script Started. ****");
            waitForElementPresent(searchPartiesLink);
            clickSearchPartiesIcon(pageRequest);
            waitForElementPresent(firstNameTextBox);
            enterFirstName(pageRequest);
            enterLastName(pageRequest);
            clickSearchButton(pageRequest);
            if (isElementPresent(validationMessage)) {
                LOG.info("search record not found");
                waitForElementPresent(validationMessage);
                if (getText(validationMessage).equalsIgnoreCase("There are no records found using this criteria")) {
                    LOG.info("searching party Not found and creating new party.");
                    clickCloseIcon(pageRequest);
                    //addingAPerson(pageRequest);
                }
            } else {
                throw new AddPersonException("searching person party is found--> Script is not adding a Person, pleas update the test data");
            }
        } catch (AddPersonException a) {
            pageRequest.getContext().log(getPageName(), a.getMessage());
            LOG.warn("searching person party is found--> Script is not adding a Person, pleas update the test data");
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred when searching for parties.", e);
        }
    }

    //TODO This method needs to go. Break the method and add individual actions to the tests
    public void doAddingPersonDetails(final AddPersonPageRequest pageRequest) {
        clickAddPersonButton(pageRequest);
        waitForElementPresent(titleDropdown);
        selectTitle(pageRequest);
        selectGender(pageRequest);
    }

    public void doAddingPersonalContactInformation(final AddPersonPageRequest pageRequest) {
        try {
            clickOnSetDefaultAddressBtn(pageRequest);
            if (pageRequest.getPreferredContactMethod().equalsIgnoreCase(PREFERRED_CONTACT_METHOD_PHONE)) {
                preferredContactMethodPhone(pageRequest);
                enterInternationalCodeForPhone(pageRequest);
                enterAreaCodeForPhone(pageRequest);
                enterPhoneNumber(pageRequest);
            } else if (pageRequest.getPreferredContactMethod().equalsIgnoreCase(PREFERRED_CONTACT_METHOD_MOBILE)) {
                preferredContactMethodMobile(pageRequest);
                enterInternationalCodeForMobile(pageRequest);
                enterAreaCodeForMobile(pageRequest);
                enterMobileNumber(pageRequest);
            }
            enterEmailAddress(pageRequest);
            enterTimeZone(pageRequest);
            clickOkToSave(pageRequest);

        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred when adding a person.", e);
        }
    }

    public void doAddingAddressDetails(final AddPersonPageRequest pageRequest) {
        clickOnNewButtonToAddAddress(pageRequest);
        setAlertSelectAddressType(pageRequest);
        clickOkToConfirmAddAddress(pageRequest);
    }


    private void clickSearchPartiesIcon(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Parties Icon");
        click(searchPartiesLink);
    }

    public void enterFirstName(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering First Name as : " + pageRequest.getFirstName() + pageRequest.getTestRunIdentifier());
        clearAndInput(pageRequest.getFirstName() + pageRequest.getTestRunIdentifier(), firstNameTextBox);
    }

    public void enterLastName(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Last Name/surname as : " + pageRequest.getLastName() + pageRequest.getTestRunIdentifier());
        clearAndInput(pageRequest.getLastName() + pageRequest.getTestRunIdentifier(), surNameTextBox);
    }

    private void clickSearchButton(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Parties Icon");
        click(searchButton);
    }

    private void clickCloseIcon(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Validation message close Icon");
        click(validationMsgCloseIcon);
    }

    public void clickAddPersonButton(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on add Button");
        click(addPersonButton);
    }

    public void selectTitle(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Title as : " + pageRequest.getTitle());
        selectValue(pageRequest.getTitle(), titleDropdown);
    }

    public void selectGender(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Gender as : " + pageRequest.getGender());
        selectValue(pageRequest.getGender(), genderDropdown);
    }

    private void preferredContactMethodPhone(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Preferred Contact Method Phone checkbox");
        click(preferredPhoneCheckbox);
    }

    private void enterInternationalCodeForPhone(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering International code as : " + pageRequest.getInternationalCodePhone());
        clearAndInput(pageRequest.getInternationalCodePhone(), intCodePhoneTextBox);
    }

    private void enterAreaCodeForPhone(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering area code as : " + pageRequest.getAreaCodePhone());
        clearAndInput(pageRequest.getAreaCodePhone(), areaCodeTextBox);
    }

    private void enterPhoneNumber(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering phone number as : " + pageRequest.getPhoneNumber());
        clearAndInput(pageRequest.getPhoneNumber(), telNoPhoneTextBox);
    }

    private void preferredContactMethodMobile(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Preferred Contact Method Mobile checkbox");
        click(preferredMobileCheckbox);
    }

    private void enterInternationalCodeForMobile(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering International code as : " + pageRequest.getInternationalCodeMobile());
        clearAndInput(pageRequest.getInternationalCodeMobile(), intCodeMobileTextBox);
    }

    private void enterAreaCodeForMobile(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering area code as : " + pageRequest.getAreaCodeMobile());
        clearAndInput(pageRequest.getAreaCodeMobile(), areCodeMobileTextBox);
    }

    private void enterMobileNumber(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Mobile number as : " + pageRequest.getMobileNumber());
        clearAndInput(pageRequest.getMobileNumber(), mobileNumberTextbox);
    }

    private void enterEmailAddress(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Time zone as: " + pageRequest.getEmailAddress());
        clearAndInput(pageRequest.getEmailAddress(), emailAddressTextBox);
    }

    private void enterTimeZone(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Time zone as : " + pageRequest.getTimeZone());
        clearAndInput(pageRequest.getTimeZone(), timeZoneTextBox);
    }

    public void clickOnNewButtonToAddAddress(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Add button while adding a person");
        scrollIntoView(addAddressNewButton);
        clickModalBox(addAddressNewButton);
    }

    public void setAlertSelectAddressType(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Street type as : " + pageRequest.getAddressType());
        selectValue(pageRequest.getAddressType(), alertSelectAddressType);
    }

    public void clickOkToConfirmAddAddress(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on OK Button");
        click(confirmAddAddressOkBtn);
    }

    private void clickOkToSave(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on OK Button to save");
        click(okPersonSaveButton);
    }

    private void clickOnSetDefaultAddressBtn(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on set Default Button");
        click(setDefaultButton);
    }

    //TODO this method needs to be refactored as it doesn't do any verification
    public void verifyPerson(final AddPersonPageRequest pageRequest) {
        final String personName = getText(personNameElement);
        pageRequest.log(getPageName(), "Created Person full Name  is : " + personName);
    }

    public void clickCancel(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel button");
        scrollPageVertically(VERTICAL_LENGTH);
        click(cancelBtn);
    }

    public String getAddressLabel(final AddPersonPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Getting address from: " + getPageName());
        scrollPageVertically(SCROLL_TO_ADDRESS);
        String addressOnPage = addressOnParty.getText().replaceAll("[\\n\\t ]", " ");
        return addressOnPage;
    }

    public void addNewPersonParty(final AddPersonPageRequest pageRequest) {
        selectTitle(pageRequest);
        if (pageRequest.isTestInstance(TestInstance.ICARE)) {
            selectGender(pageRequest);
        }
        clickOnNewButtonToAddAddress(pageRequest);
        setAlertSelectAddressType(pageRequest);
        clickOkToConfirmAddAddress(pageRequest);
    }


    public static class AddPersonPageRequest extends AbstractPageRequest {

        public AddPersonPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getUsername() {
            return get("UserName");
        }

        public String getPassword() {
            return get("Password");
        }

        public String getFirstName() {
            return get("firstName");
        }

        public String getLastName() {
            return get("lastName");
        }

        public String getTitle() {
            return get("Title");
        }

        public String getGender() {
            return get("Gender");
        }

        public String getInternationalCodePhone() {
            return get("International Code");
        }

        public String getPreferredContactMethod() {
            return get("Preferred Contact Method");
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

        public String getMobileNumber() {
            return get("MobileNumber");
        }

        public String getEmailAddress() {
            return get("EmailAddress");
        }

        public String getTimeZone() {
            return get("TimeZone");
        }

        public String getAddressType() {
            return get("Address Type");
        }

        public String getAddressLabel() {
            return get("addressLabel");
        }

    }

    static class AddPersonException extends Exception {

        protected AddPersonException(final String msg) {
            super(msg);
        }
    }

}

