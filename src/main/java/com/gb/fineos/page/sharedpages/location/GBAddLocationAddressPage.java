package com.gb.fineos.page.sharedpages.location;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.LeavingPagePopup;
import com.gb.fineos.page.utils.RandomData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GBAddLocationAddressPage extends BasePage {

    private LeavingPagePopup leavingPagePopup;

    public GBAddLocationAddressPage() {
        super("Add Address Page");
        this.leavingPagePopup = PageFactory.initElements(LeavingPagePopup.class);
        this.leavingPagePopup.addPageNamePrefix(getPageName());
    }

    public LeavingPagePopup getLeavingPagePopup() {
        return leavingPagePopup;
    }

    @FindBy(xpath = "//input[contains(@id,'_Override_QAS_CHECKBOX')]")
    private WebElement overrideQAS;
    @FindBy(css = "select[id$='_Country']")
    private WebElement country;
    @FindBy(css = "input[id$='_PostCode']")
    private WebElement postcode;
    @FindBy(css = "input[id$='_Suburb']")
    private WebElement suburb;
    @FindBy(css = "input[id$='_PremiseNo']")
    private WebElement premiseNo;
    @FindBy(css = "input[id$='_Street']")
    private WebElement street;
    @FindBy(css = "select[id$='_Street_Type']")
    private WebElement streetType;
    @FindBy(css = "input[id$='_AddressLine1']")
    private WebElement addressLine1;
    @FindBy(css = "input[id$='_AddressLine2']")
    private WebElement addressLine2;
    @FindBy(css = "input[id$='_AddressLine3']")
    private WebElement addressLine3;
    @FindBy(xpath = "//input[contains(@id,'_AddressLine4')]")
    private WebElement addressLine4;
    @FindBy(xpath = "//select[contains(@id,'AUEditCountryFormatAddressWidget_') and contains(@id,'_State')]")
    private WebElement state;
    @FindBy(xpath = "//td[contains(@class,'raiseMessageText')]")
    private WebElement validationMessage;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel_cloned')]")
    private WebElement cancelBtn;
    @FindBy(xpath = "//input[contains(@id,'_Unit_Number')]")
    private WebElement unitNumber;
    @FindBy(xpath = "//span[contains(@id,'PageMessage1')]")
    private WebElement message1;
    @FindBy(xpath = "//span[contains(@id,'PageMessage2')]")
    private WebElement message2;
    @FindBy(xpath = "//span[contains(@id,'PageMessage3')]")
    private WebElement message3;
    @FindBy(xpath = "//span[contains(@id,'PageMessage4')]")
    private WebElement message4;
    @FindBy(xpath = "//a[contains(@id,'btn_close_popup_msg')]")
    private WebElement closeValidationMessageBtn;
    @FindBy(css = "input[id$='_editPageSave_cloned']")
    private WebElement save;

    public WebElement getCloseValidationMessageBtn() {
        return closeValidationMessageBtn;
    }

    public WebElement getOverrideQAS() {
        return overrideQAS;
    }

    public WebElement getCountry() {
        return country;
    }

    public WebElement getPostcode() {
        return postcode;
    }

    public WebElement getSuburb() {
        return suburb;
    }

    public WebElement getPremiseNo() {
        return premiseNo;
    }

    public WebElement getStreet() {
        return street;
    }

    public WebElement getStreetType() {
        return streetType;
    }

    public WebElement getAddressLine1() {
        return addressLine1;
    }

    public WebElement getAddressLine2() {
        return addressLine2;
    }

    public WebElement getAddressLine3() {
        return addressLine3;
    }

    public WebElement getAddressLine4() {
        return addressLine4;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getUnitNumber() {
        return unitNumber;
    }

    public WebElement getState() {
        return state;
    }

    public WebElement getSave() {
        return save;
    }

    public void addAddress(final GBAddLocationAddressPageRequest pageRequest) {
        closeTheAddressWindow();
        doDisableQAS(pageRequest);
        if (pageRequest.isTestInstance(TestInstance.NZ) || pageRequest.isTestInstance(TestInstance.UK)) {
            selectCountry(pageRequest);
            inputAddressLine1(pageRequest);
            inputAddressLine2(pageRequest);
            inputAddressLine3(pageRequest);
            inputPostcode(pageRequest);
        } else {
            selectCountry(pageRequest);
            inputPostcode(pageRequest);
            inputSuburb(pageRequest);
            inputPremiseNo(pageRequest);
            inputStreetName(pageRequest);
            selectStreetType(pageRequest);
            selectState(pageRequest);
            pageRequest.log(getPageName(), "All Address Fields are entered");
        }
        doSaveAddress(pageRequest);
    }

    public void inputAddressLine1(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Inputting Address Line 1 as:" + pageRequest.getAddressLine1());
        clearAndInput(pageRequest.getAddressLine1(), addressLine1);
    }

    public void inputAddressLine2(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Inputting Address Line 2 as:" + pageRequest.getAddressLine2());
        clearAndInput(pageRequest.getAddressLine2(), addressLine2);
    }

    public void inputAddressLine3(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Inputting Address Line 3 as:" + pageRequest.getAddressLine3());
        clearAndInput(pageRequest.getAddressLine3(), addressLine3);
    }


    public void doSaveAddress(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking save button");
        click(getSave());
    }

    public void doDisableQAS(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking override QAS");
        clickModalBox(overrideQAS);
    }

    public void selectCountry(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering country as:" + pageRequest.getCountry());
        selectValue(pageRequest.getCountry(), country);

    }

    public void inputPostcode(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Postcode as:" + pageRequest.getPostcode());
        clearAndInput(pageRequest.getPostcode(), postcode);
    }

    public void inputSuburb(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering suburb as:" + pageRequest.getSuburb());
        clearAndInput(pageRequest.getSuburb(), suburb);
    }

    public void inputPremiseNo(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Premise No as:" + pageRequest.getPremiseNo());
        clearAndInput(pageRequest.getPremiseNo(), premiseNo);
    }

    public void inputStreetName(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Street Name as:" + pageRequest.getStreetName());
        clearAndInput(pageRequest.getStreetName(), street);
    }

    public void selectStreetType(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Street Type as:" + pageRequest.getStreetType());
        selectValue(pageRequest.getStreetType(), streetType);
    }

    public void selectState(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting state as:" + pageRequest.getState());
        selectValue(pageRequest.getState(), state);

    }

    public void clickCancel(final GBAddLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel");
        clickModalBox(getCancelBtn());
    }

    public static class GBAddLocationAddressPageRequest extends AbstractPageRequest {
        public GBAddLocationAddressPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getAddressLine1() {
            getContext().getData().putIfAbsent("addressLine1", RandomData.getRandomDataService(this).address().buildingNumber());
            return getContext().getData().get("addressLine1");
        }

        public String getAddressLine2() {
            getContext().getData().putIfAbsent("addressLine2", RandomData.getRandomDataService(this).address().streetAddress());
            return getContext().getData().get("addressLine2");
        }

        public String getAddressLine3() {
            return get("addressLine3");
        }

        public String getAddressLine4() {
            return get("addressLine4");
        }

        public String getAddressLabel() {
            return get("addressLabel");
        }

        public String getState() {
            return get("State");
        }

        public String getPremiseNo() {
            getContext().getData().putIfAbsent("premiseNo", RandomData.getRandomDataService(this).address().streetAddressNumber());
            return getContext().getData().get("premiseNo");
        }

        public String getCountry() {
            return get("country");
        }

        public String getPostcode() {
            return get("postcode");
        }

        public String getSuburb() {
            return get("suburb");
        }

        public String getStreetName() {
            getContext().getData().putIfAbsent("streetName", RandomData.getRandomDataService(this).address().streetName());
            return getContext().getData().get("streetName");
        }

        public String getStreetType() {
            return get("streetType");
        }
    }


}
