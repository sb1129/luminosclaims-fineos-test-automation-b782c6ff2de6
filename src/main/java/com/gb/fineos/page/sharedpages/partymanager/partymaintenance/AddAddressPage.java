package com.gb.fineos.page.sharedpages.partymanager.partymaintenance;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddAddressPage extends BasePage {

    //Initialize web element for Override QAS CHECKBOX  field
    @FindBy(xpath = "//input[contains(@id,'EditAddressWidget') and contains(@id,'_Override_QAS_CHECKBOX')]")
    private WebElement addressPageQASCheckBox;
    //Initialize web element for Country dropdown  field
    @FindBy(xpath = "//select[contains(@id,'EditAddressWidget_') and contains(@id,'_Country')]")
    private WebElement addressPageCountryDropdown;
    //Initialize web element for Address Page Building Name 1 TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Building_Name_1')]")
    private WebElement addressPageBuildingName1TextBox;
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Building_Name_2')]")
    private WebElement addressPageBuildingName2TextBox;
    //Initialize web element for Address Page Premise Number TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_PremiseNo')]")
    private WebElement addressPagePremiseNumberTextBox;
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Lot_Number')]")
    private WebElement addressPageLotNumberTextBox;
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Postal_Number')]")
    private WebElement addressPagePostalNumberTextBox;
    //Initialize web element for Address Page street TextBox  field
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Street')]")
    private WebElement addressPageStreetTextBox;
    //Initialize web element for Address Page Street Type dropdown field
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Street_Type')]")
    private WebElement addressPageStreetTypeDropdown;
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget') and contains(@id,'_Postal_Type')]")
    private WebElement addressPagePostalTypeDropdown;
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



    ///******************NZ and UK Address webelements ******************
    //Initialize web element for Address line1 TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_AddressLine1')]")
    private WebElement addressLine1TextBox;
    @FindBy(xpath = "//input[contains(@id,'_AddressLine2')]")
    private WebElement addressLine2TextBox;
    //Initialize web element for Suburb/City/Town TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_AddressLine3')]")
    private WebElement suburbTextBox;
    //Initialize web element for State/Province TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_AddressLine4')]")
    private WebElement stateTextBox;
    //Initialize web element for State/Province TextBox  field
    @FindBy(xpath = "//input[contains(@id,'DefaultEditCountryFormatAddressWidget') and contains(@id,'_PostCode')]")
    private WebElement postCodeTextBox;
    @FindBy(xpath = "//td[contains(@class,'raiseMessageText')]")
    private WebElement validationMessage;
    @FindBy(xpath = "//a[contains(@id,'btn_close_popup_msg')]")
    private WebElement validationCloseBtn;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel_cloned')]")
    private WebElement cancelBtn;
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget_') and contains(@id,'_Unit_Type')]")
    private WebElement unitType;
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget_') and contains(@id,'_Unit_Number')]")
    private WebElement unitNumber;
    @FindBy(xpath = "//input[contains(@id,'AUEditCountryFormatAddressWidget_') and contains(@id,'_Floor_Level_Number')]")
    private WebElement floorNumber;
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget_') and contains(@id,'_Floor_Level_Type')]")
    private WebElement floorType;

    public AddAddressPage() {
        super("ADD ADDRESS PAGE");
    }

    //TODO Need to split this method in specific address type method
    public void doAddingAddress(final AddAddressPageRequest pageRequest) {
        try {
            if (pageRequest.isTestInstance(TestInstance.AU) || pageRequest.isTestInstance(TestInstance.ICARE)) {
                closeTheAddressWindow();
                clickOnOverrideQASCheckBox(pageRequest);
                selectCountry(pageRequest);
                enterBuildingName1Text(pageRequest);
                enterPremiseNumberText(pageRequest);
                enterStreetText(pageRequest);
                selectStreetType(pageRequest);
                enterSuburb(pageRequest);
                selectState(pageRequest);
                enterPostcode(pageRequest);
                enterDeliveryPointID(pageRequest);
            } else if (pageRequest.isTestInstance(TestInstance.NZ) || pageRequest.isTestInstance(TestInstance.UK)) {
                closeTheAddressWindow();
                clickOnOverrideQASCheckBox(pageRequest);
                selectCountry(pageRequest);
                enterAddressLine1(pageRequest);
                enterSuburbForDefaultWidget(pageRequest);
                enterStateForDefaultWidget(pageRequest);
                enterPostcodeForDefaultWidget(pageRequest);
            }
            clickOkOnAddressPage(pageRequest);
        } catch (NoSuchElementException e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred when adding a Address.", e);
        }
    }

    //***** Adding address pages methods****************
    public void clickOnOverrideQASCheckBox(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Override QAS check box address page");
        click(addressPageQASCheckBox);
    }

    public void selectCountry(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Country as : " + pageRequest.getCountry());
        selectValue(pageRequest.getCountry(), addressPageCountryDropdown);
    }

    public void enterBuildingName1Text(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Building Name 1 text as : " + pageRequest.getBuildingName1());
        clearAndInput(pageRequest.getBuildingName1(), addressPageBuildingName1TextBox);
    }

    public void enterPremiseNumberText(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Premise Number text as : " + pageRequest.getPremiseNumber());
        clearAndInput(pageRequest.getPremiseNumber(), addressPagePremiseNumberTextBox);
    }

    public void enterStreetText(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Street Number text as : " + pageRequest.getStreet());
        clearAndInput(pageRequest.getStreet(), addressPageStreetTextBox);
    }

    public void selectStreetType(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Street type as: " + pageRequest.getStreetType());
        selectValue(pageRequest.getStreetType(), addressPageStreetTypeDropdown);
    }

    public void enterSuburb(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Suburb text as : " + pageRequest.getSuburb());
        clearAndInput(pageRequest.getSuburb(), addressPageSuburbTextBox);
    }

    public void selectState(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting State as : " + pageRequest.getState());
        selectValue(pageRequest.getState(), addressPageStateDropdown);
    }

    public void enterPostcode(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Postcode as : " + pageRequest.getPostcode());
        clearAndInput(pageRequest.getPostcode(), addressPagePostCodeTextBox);
    }

    public void enterDeliveryPointID(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Delivery Point ID as : " + pageRequest.getDeliveryPointID());
        clearAndInput(pageRequest.getDeliveryPointID(), addressPageDeliveryPointIDTextBox);
    }

    public void enterAddressLine1(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering address Line 1 as : " + pageRequest.getBuildingName1());
        clearAndInput(pageRequest.getBuildingName1(), addressLine1TextBox);
    }

    public void enterSuburbForDefaultWidget(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering suburb as : " + pageRequest.getSuburb());
        clearAndInput(pageRequest.getSuburb(), suburbTextBox);
    }

    public void enterStateForDefaultWidget(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering state as : " + pageRequest.getState());
        clearAndInput(pageRequest.getState(), stateTextBox);
    }

    public void enterPostcodeForDefaultWidget(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Postcode as : " + pageRequest.getPostcode());
        clearAndInput(pageRequest.getPostcode(), postCodeTextBox);
    }

    public void clickOkOnAddressPage(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Add button");
        click(addressPageOkButton);
    }

    public void inputAddressLine2(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Enter Address Line 2");
        clearAndInput(pageRequest.getAddressLine2(), addressLine2TextBox);
    }

    public void inputAddressLine1(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Enter Address Line 1");
        clearAndInput(pageRequest.getAddressLine1(), addressLine1TextBox);
    }

    public String getValidationError(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Getting Validation Message");
        String valError = validationMessage.getText().replaceAll("[\\n\\t ]", " ");
        return valError;
    }

    public void closeValidationError(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "closing Validation error pop-up");
        clickModalBox(validationCloseBtn);
    }

    public void clickCancel(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel button");
        click(cancelBtn);
    }

    public void clearAddressLine1(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Address Line 1");
        addressLine1TextBox.clear();
    }

    public void clearAddressLine2(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Address Line 2");
        addressLine2TextBox.clear();
    }


    public void clearSuburbDefault(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Suburb");
        suburbTextBox.clear();
    }

    public void clearSuburb(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Suburb");
        addressPageSuburbTextBox.clear();
    }

    public void clearState(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear State");
        stateTextBox.clear();
    }


    public void clearPostcode(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Postcode");
        addressPagePostCodeTextBox.clear();
    }

    public void clearPostcodeDefault(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Postcode");
        postCodeTextBox.clear();
    }

    public void clearUnitNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Unit Number");
        unitNumber.clear();
    }

    public void clearPremiseNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Premise Number");
        addressPagePremiseNumberTextBox.clear();
    }

    public void selectUnitType(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Unit Type as : " + pageRequest.getUnitType());
        selectValue(pageRequest.getUnitType(), unitType);
    }

    public void selectFloorType(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Floor Type as : " + pageRequest.getFloorType());
        selectValue(pageRequest.getFloorType(), floorType);
    }

    public void inputUnitNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Enter Unit Number as : " + pageRequest.getUnitNumber());
        clearAndInput(pageRequest.getUnitNumber(), unitNumber);
    }

    public void inputFloorNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Enter Floor Number as : " + pageRequest.getFloorNumber());
        clearAndInput(pageRequest.getFloorNumber(), floorNumber);
    }

    public void clearFloorNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Floor Number");
        floorNumber.clear();
    }

    public void clearDeliveryPointID(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Clear Delivery Point ID");
        addressPageDeliveryPointIDTextBox.clear();
    }

    public void clearPostalNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Clear Postal Number");
        addressPagePostalNumberTextBox.clear();
    }

    public void clearLotNumber(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Clear Lot Number");
        addressPageLotNumberTextBox.clear();
    }

    public void clearBuildingName1(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Clear Building Name 1");
        addressPageBuildingName1TextBox.clear();
    }

    public void clearBuildingName2(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Clear Building Name 2");
        addressPageBuildingName2TextBox.clear();
    }

    public void selectPostalType(final AddAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Floor Type as : " + pageRequest.getPostalType());
        selectValue(pageRequest.getPostalType(), addressPagePostalTypeDropdown);
    }

    public static class AddAddressPageRequest extends AbstractPageRequest {

        public AddAddressPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getBuildingName1() {
            return get("Building Name 1");
        }

        public String getPremiseNumber() {
            return get("Premise Number");
        }

        public String getStreet() {
            return get("Street");
        }

        public String getStreetType() {
            return get("Street Type");
        }

        public String getSuburb() {
            return get("Suburb");
        }

        public String getState() {
            return get("State");
        }

        public String getPostcode() {
            return get("Postcode");
        }

        public String getCountry() {
            return get("Country");
        }

        public String getDeliveryPointID() {
            return get("Delivery Point ID");
        }

        public String getAddressLine2() {
            return get("addressLine2");
        }

        public String getAddressLine1() {
            return get("addressLine1");
        }

        public String getUnitType() {
            return get("unitType");
        }

        public String getUnitNumber() {
            return get("unitNo");
        }

        public String getFloorType() {
            return get("floorType");
        }

        public String getFloorNumber() {
            return get("floorNo");
        }

        public String getPostalType() {
            return get("postalType");
        }

        public String getValError() {
            return get("validationError");
        }
    }
}
