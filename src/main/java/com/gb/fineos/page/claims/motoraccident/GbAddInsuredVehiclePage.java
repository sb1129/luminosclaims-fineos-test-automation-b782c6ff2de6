package com.gb.fineos.page.claims.motoraccident;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// FIXME: 10/02/2020 This page is extending the ProcessStatusPageBase where it should extend BasePage
public class GbAddInsuredVehiclePage extends ProcessStatusPageClaimBase {
    private static final String PAGE_NAME = "ADDING INSURED VEHICLE DETAILS";

    //Motor claim  --> Vehicle Details
    @FindBy(xpath = "//input[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_vinTextBox')]")
    private WebElement mClaimVehicleVinNumberTextBox;
    @FindBy(xpath = "//input[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_plateNumberTextbox')]")
    private WebElement mClaimVehicleRegistrationNumberTextBox;
    @FindBy(xpath = "//select[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_MakeDropDown')]")
    private WebElement mClaimVehicleMakeDropdown;
    @FindBy(xpath = "//select[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_ModelDropDown')]")
    private WebElement mClaimVehicleModelDropDown;
    @FindBy(xpath = "//select[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_yrManfEnum')]")
    private WebElement mClaimVehicleYearDropDown;
    @FindBy(xpath = "//select[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_colourEnum')]")
    private WebElement mClaimVehicleColorDropDown;
    @FindBy(xpath = "//select[contains(@id,'VehicleInAccidentWidget_') and contains(@id,'_bodyTypeEnum')]")
    private WebElement mClaimVehicleTypeDropDown;
    @FindBy(xpath = "//select[contains(@id,'_VehicleDuty')]")
    private WebElement mClaimVehicleDutyDropDown;
    @FindBy(xpath = "//select[contains(@id,'_Purpose_of_Use')]")
    private WebElement mClaimVehicleUsageDropDown;
    @FindBy(css = "input[id$='_editPageSave_cloned']")
    private WebElement save;


    public WebElement getMClaimVehicleVinNumberTextBox() {
        return mClaimVehicleVinNumberTextBox;
    }

    public WebElement getMClaimVehicleRegistrationNumberTextBox() {
        return mClaimVehicleRegistrationNumberTextBox;
    }

    public WebElement getMClaimVehicleMakeDropdown() {
        return mClaimVehicleMakeDropdown;
    }

    public WebElement getMClaimVehicleModelDropDown() {
        return mClaimVehicleModelDropDown;
    }

    public WebElement getMClaimVehicleYearDropDown() {
        return mClaimVehicleYearDropDown;
    }

    public WebElement getMClaimVehicleColorDropDown() {
        return mClaimVehicleColorDropDown;
    }

    public WebElement getMClaimVehicleTypeDropDown() {
        return mClaimVehicleTypeDropDown;
    }

    public WebElement getMClaimVehicleDutyDropDown() {
        return mClaimVehicleDutyDropDown;
    }

    public WebElement getMClaimVehicleUsageDropDown() {
        return mClaimVehicleUsageDropDown;
    }


    public GbAddInsuredVehiclePage() {
        super();
    }

    @Override
    public void doAddInsuredVehicleDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {
        try {
            enterVehicleVinNumber(pageRequest);
            enterVehicleRegistrationNumber(pageRequest);
            selectingVehicleMakeFromDropdown(pageRequest);
            selectingVehicleModelFromDropdown(pageRequest);
            selectingVehicleYearFromDropdown(pageRequest);
            selectingVehicleColorFromDropdown(pageRequest);
            selectingVehicleTypeFromDropdown(pageRequest);
            selectingVehicleDutyFromDropdown(pageRequest);
            selectingVehicleUsageFromDropdown(pageRequest);
            clickOnOKButton(pageRequest);
            if (webElementIsDisplayed(getSave())) {
                clickOnOKButton(pageRequest);
            }
        } catch (NoSuchElementException e) {
            pageRequest.getContext().error(PAGE_NAME, e);
            throw new AssertionError("An error occurred when adding an Organisation. ", e);
        }
    }

    //Adding vehicle details methods
    public void enterVehicleVinNumber(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Entering Vehicle VinNumber Text as : " + pageRequest.getVehicleVinNumber());
        input(pageRequest.getVehicleVinNumber(), mClaimVehicleVinNumberTextBox);
    }

    protected void enterVehicleRegistrationNumber(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Entering Vehicle Registration Number as : " + pageRequest.getVehicleRegistrationNumber());
        input(pageRequest.getVehicleRegistrationNumber(), mClaimVehicleRegistrationNumberTextBox);
    }

    private void selectingVehicleMakeFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Selecting Claim Vehicle Make as : " + pageRequest.getVehicleMake());
        selectValue(pageRequest.getVehicleMake(), mClaimVehicleMakeDropdown);
    }

    private void selectingVehicleModelFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Selecting Claim Vehicle Model as : " + pageRequest.getVehicleModel());
        selectValue(pageRequest.getVehicleModel(), mClaimVehicleModelDropDown);
    }

    private void selectingVehicleYearFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Selecting Claim Vehicle Year as : " + pageRequest.getVehicleYear());
        selectValue(pageRequest.getVehicleYear(), mClaimVehicleYearDropDown);

    }

    private void selectingVehicleColorFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Selecting Claim Vehicle color as : " + pageRequest.getVehicleColor());
        selectValue(pageRequest.getVehicleColor(), mClaimVehicleColorDropDown);
    }

    private void selectingVehicleTypeFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Selecting Claim Vehicle Type as : " + pageRequest.getVehicleType());
        selectValue(pageRequest.getVehicleType(), mClaimVehicleTypeDropDown);
    }

    private void selectingVehicleDutyFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Selecting Claim Vehicle Duty as : " + pageRequest.getVehicleDuty());
        selectValue(pageRequest.getVehicleDuty(), mClaimVehicleDutyDropDown);
    }

    private void selectingVehicleUsageFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Claim Vehicle Usage as : " + pageRequest.getVehicleUsage());
        selectValue(pageRequest.getVehicleUsage(), mClaimVehicleUsageDropDown);
    }

    public static class GbAddInsuredVehiclePageRequest extends ProcessStatusPageClaimBaseRequest {

        public GbAddInsuredVehiclePageRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
