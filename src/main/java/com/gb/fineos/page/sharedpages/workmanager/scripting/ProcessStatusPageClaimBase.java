package com.gb.fineos.page.sharedpages.workmanager.scripting;

import com.gb.fineos.domain.ClaimType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.claims.motoraccident.GbAddInsuredVehiclePage;
import com.gb.fineos.page.component.GBClientFieldSearchPage;
import org.openqa.selenium.By;
import com.gb.fineos.page.utils.RandomData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public abstract class ProcessStatusPageClaimBase extends ProcessStatusPageBase {
    private static final String PAGE_NAME = "PROCESS STATUS PAGE BASE - CLAIM INTAKE PAGE";
    private static final int DEFAULT_CLAIM_RECEIVED_DAYS = 2;
    private static final String CLIENT_SPECIFIC_DATA_LABEL = "Client Specific Data";
    private static final int VERTICAL_LENGTH = 400;
    private static final int SLEEP_TIME = 400;


    @FindBy(css = "textarea[id$='_caseDesc']")
    private WebElement claimCaseDetailsDescriptionTextBox;
    @FindBy(xpath = "//input[contains(@id,'_IncurredDateClaimant')]")
    private WebElement claimReceivedDateTextBox;
    @FindBy(xpath = "//select[contains(@id,'_NotifierTimeZone')]")
    private WebElement claimReceivedDateTimeZoneDropdown;
    @FindBy(xpath = "//div[@class='pageheader_heading']/h2/span")
    private WebElement claimNumberElement;

    // Cost Centre for icare
    @FindBy(xpath = "//input[contains(@id,'_costCentreSearch')]")
    private WebElement costCentreSearch;
    @FindBy(xpath = "//select[contains(@id,'_CostCentreCodeSearch')]")
    private WebElement costCentreCodeSearch;
    @FindBy(xpath = "//input[contains(@id,'_searchPageOk_cloned')]")
    private WebElement searchPageOk;

    //Motor claim --> General Incident Details
    @FindBy(xpath = "//select[contains(@id,'_AccidentLighting')]")
    private WebElement selectMClaimAccidentLightingDropdown;
    @FindBy(xpath = "//select[contains(@id,'_RoadSurface')]")
    private WebElement selectMClaimRoadSurfaceDropdown;
    @FindBy(xpath = "//select[contains(@id,'_WeatherCondition')]")
    private WebElement selectMClaimWeatherConditionDropdown;

    //Personal Injury Claim ---> cause of injury codes
    @FindBy(xpath = "//input[contains(@id,'CauseOfInjury_') and contains(@id,'_selInjuryCode_rcbSearchButton_selInjuryCode')]")
    private WebElement injuryCodeSearch;
    @FindBy(xpath = "//input[contains(@id,'MedicalCodesSearchWidget_') and contains(@id,'_code')]")
    private WebElement injuryCodeTextBox;
    @FindBy(xpath = "//input[contains(@id,'MedicalCodesSearchWidget_') and contains(@id,'_searchButton')]")
    private WebElement injuryCodeSearchButton;

    //personal injury claim--> Injury Codes
    @FindBy(xpath = "//select[contains(@id,'_diagnosisCodeIdDynamicDropDownBean_diagnosisCodeIdDynamicDropDownBean_dropDown')]")
    private WebElement injuryDiagnosisCode;
    @FindBy(xpath = "//select[contains(@id,'DiagnosisCodesListviewWidget_') and contains(@id,'_Level')]")
    private WebElement levelIndicatorDropdown;
    @FindBy(xpath = "//input[contains(@id,'gbPersonalInjuryDiagnosisCodesListviewWidget_') and contains(@id,'_quickaddbutton')]")
    private WebElement mPersonalInjuryClaimInjuryQuickAddButton;

    //Property Damage claim--> General Details
    @FindBy(xpath = "//select[contains(@id,'gbGeneralIncidentDetailsWidget_') and contains(@id,'_TypeofIncident')]")
    private WebElement typeOfIncidentDropDown;
    @FindBy(xpath = "//select[contains(@id,'gbGeneralIncidentDetailsWidget_') and contains(@id,'_PropertyIncidentSource')]")
    private WebElement propertyIncidentSourceDropDown;
    @FindBy(xpath = "//select[contains(@id,'gbGeneralIncidentDetailsWidget_') and contains(@id,'_PropertyIncidentSeverity')]")
    private WebElement propertyIncidentSeverityDropDown;
    @FindBy(xpath = "//select[contains(@id,'_Assessment_Type')]")
    private WebElement assessmentTypeDropDown;


    //Property Item Details
    @FindBy(xpath = "//input[contains(@id,'_lvwVehiclesInvolved_cmdAdd')]")
    private WebElement itemDetailsAddButton;
    @FindBy(xpath = "//textarea[contains(@id,'_DescriptionTextArea')]")
    private WebElement propertyDescriptionTextArea;
    @FindBy(xpath = "//select[contains(@id,'_ItemTypeDropDown')]")
    private WebElement propertyItemTypeDropDown;
    @FindBy(xpath = "//select[contains(@id,'EditPropertyItemDetailsWidget_') and contains(@id,'_ItemCategoryDropDown')]")
    private WebElement propertyItemCategoryDropDown;


    //Motor claim--> Vehicles Involved in Accident
    @FindBy(xpath = "//input[contains(@id,'_lvwVehiclesInvolved_cmdAdd')]")
    private WebElement mClaimVehiclesInvolvedAddButton;

    //add vehicle alert VehicleType
    @FindBy(xpath = "//input[contains(@id,'SelectInsuredOrThirdPartyVehiclePopupWidget_') and contains(@id,'_radioButtonBean_GROUP')]")
    private WebElement mClaimInsuredVehicleTypeRadioButton;
    @FindBy(xpath = "//input[contains(@id,'SelectInsuredOrThirdPartyVehiclePopupWidget_') and contains(@id,'_radioButtonBean1_GROUP')]")
    private WebElement mClaimThirdPartyVehicleRadioButton;
    @FindBy(xpath = "//input[contains(@id,'SelectInsuredOrThirdPartyVehiclePopupWidget_') and contains(@id,'_okButtonBean')]")
    private WebElement mClaimVehicleTypePopupWidgetOKButton;

    //HEALTH INJURY CLAIM INTAKE
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_AreaOfPractice')]")
    private WebElement areaOfPracticeDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_ClaimantStatus')]")
    private WebElement claimantStatusDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_ClinicalServiceContext')]")
    private WebElement clinicalServiceContextDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_ClinicalSpeciality')]")
    private WebElement clinicalSpecialityDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_ExtentOfHarm')]")
    private WebElement extentOfHarmDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_HealthServiceSetting')]")
    private WebElement healthServiceSettingDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_PrimaryIncidentCode')]")
    private WebElement primaryIncidentCodeDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_NatureOfClaimIndicator')]")
    private WebElement natureOfClaimIndicatorDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_PracticeContext')]")
    private WebElement practiceContextDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_TypeOfClaim')]")
    private WebElement typeOfClaimDropdown;
    @FindBy(xpath = "//select[contains(@id,'ClassExtensionDetailsWidget_') and contains(@id,'_OtherDefendant')]")
    private WebElement otherDefendantDropdown;
    @FindBy(xpath = "//select[contains(@id,'_ReportingUnit')]")
    private List<WebElement> reportingUnitsList;

    private GBClientFieldSearchPage gbClientFieldSearchPage;


    public WebElement getClaimCaseDetailsDescriptionTextBox() {
        return claimCaseDetailsDescriptionTextBox;
    }

    public WebElement getClaimReceivedDateTextBox() {
        return claimReceivedDateTextBox;
    }

    public WebElement getClaimNumberElement() {
        return claimNumberElement;
    }

    public WebElement getSelectMClaimAccidentLightingDropdown() {
        return selectMClaimAccidentLightingDropdown;
    }

    public WebElement getSelectMClaimRoadSurfaceDropdown() {
        return selectMClaimRoadSurfaceDropdown;
    }

    public WebElement getSelectMClaimWeatherConditionDropdown() {
        return selectMClaimWeatherConditionDropdown;
    }

    public WebElement getInjuryCodeSearch() {
        return injuryCodeSearch;
    }

    public WebElement getInjuryCodeTextBox() {
        return injuryCodeTextBox;
    }

    public WebElement getInjuryCodeSearchButton() {
        return injuryCodeSearchButton;
    }

    public WebElement getInjuryDiagnosisCode() {
        return injuryDiagnosisCode;
    }

    public WebElement getMPersonalInjuryClaimInjuryQuickAddButton() {
        return mPersonalInjuryClaimInjuryQuickAddButton;
    }

    public WebElement getTypeOfIncidentDropDown() {
        return typeOfIncidentDropDown;
    }

    public WebElement getPropertyIncidentSourceDropDown() {
        return propertyIncidentSourceDropDown;
    }

    public WebElement getPropertyIncidentSeverityDropDown() {
        return propertyIncidentSeverityDropDown;
    }

    public WebElement getItemDetailsAddButton() {
        return itemDetailsAddButton;
    }

    public WebElement getPropertyDescriptionTextArea() {
        return propertyDescriptionTextArea;
    }

    public WebElement getPropertyItemTypeDropDown() {
        return propertyItemTypeDropDown;
    }

    public WebElement getPropertyItemCategoryDropDown() {
        return propertyItemCategoryDropDown;
    }

    public WebElement getMClaimVehiclesInvolvedAddButton() {
        return mClaimVehiclesInvolvedAddButton;
    }

    public WebElement getMClaimInsuredVehicleTypeRadioButton() {
        return mClaimInsuredVehicleTypeRadioButton;
    }

    public WebElement getMClaimThirdPartyVehicleRadioButton() {
        return mClaimThirdPartyVehicleRadioButton;
    }

    public WebElement getMClaimVehicleTypePopupWidgetOKButton() {
        return mClaimVehicleTypePopupWidgetOKButton;
    }


    public WebElement getClaimReceivedDateTimeZoneDropdown() {
        return claimReceivedDateTimeZoneDropdown;
    }


    public ProcessStatusPageClaimBase() {
        super();
    }

    public void doAddVehicleDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {
        try {
            clickOnVehiclesInvolvedAddButton(pageRequest);
            if (pageRequest.getInsuredThirdPartyVehicle().equalsIgnoreCase("Insured Vehicle")) {
                clickOnInsuredVehicleTypeRadioButton(pageRequest);
            } else {
                clickOnThirdPartyVehicleRadioButton(pageRequest);
            }
            clickOnVehicleTypePopupWidgetOKButton(pageRequest);
            doAddInsuredVehicleDetails(pageRequest);
        } catch (NoSuchElementException e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred when adding a Vehicle. ", e);
        }
    }

    public void doAddInsuredVehicleDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {

        final GbAddInsuredVehiclePage gbAddInsuredVehiclePage = PageFactory.initElements(getDriver(), GbAddInsuredVehiclePage.class);
        gbAddInsuredVehiclePage.doAddInsuredVehicleDetails(pageRequest);
    }

    public void selectingClientSpecificData(final ProcessStatusPageBaseRequest pageRequest) {
        if (!getDriver().findElements(By.xpath("//tbody//tr//td//span[text()='" + CLIENT_SPECIFIC_DATA_LABEL + "']")).isEmpty()) {
            for (int i = 0; i < reportingUnitsList.size(); i++) {
                WebElement element = getElement("//select[contains(@id,'_ReportingUnit" + i + "')]");
                if (isElementPresent(element)) {
                    pageRequest.log(getPageName(), "Selecting  Reporting Unit..");
                    selectByIndex(REPORTING_UNIT_DEFAULT_FIRST_INDEX, element);
                }
            }
            selectAllClientClaimData(pageRequest);
        }
    }

    private void selectAllClientClaimData(final ProcessStatusPageBaseRequest pageRequest) {
        if (!getDriver().findElements(By.xpath("//span[contains(@id,'gbClientDataWidget') and contains(@id,'_clientDataRelSearch')]")).isEmpty()) {
            final List<WebElement> clientDataRouteList = getDriver().findElements(By.xpath("//span[contains(@id,'gbClientDataWidget')]/table[contains(@class,'WidgetPanel')]"));

            for (int i = 0; i < clientDataRouteList.size(); i++) {
                WebElement element = getElement("//input[contains(@id,'gbClientDataWidget" + i + "_') and contains(@id,'_clientDataRelSearch')]");
                if (isElementPresent(element)) {
                    pageRequest.log(getPageName(), "Selecting Bus Route..");
                    click(element);
                    gbClientFieldSearchPage.searchClientData(pageRequest);
                }
            }
        }
    }

    public void doAddCostCentre(final ProcessStatusPageClaimBaseRequest pageRequest) {
        clickOnCostCentreSearch(pageRequest);
        selectCostCentreCodeSearch(pageRequest);
        clickOnOKCostCentreSearch(pageRequest);
    }

    public void doAddCauseOfInjuryCode(final ProcessStatusPageClaimBaseRequest pageRequest) {
        clickOnInjuryCodeSearch(pageRequest);
        if (isElementPresent(injuryCodeSearch)) {
            clickOnInjuryCodeSearch(pageRequest);
        }
        enterInjuryCodeText(pageRequest);
        clickOnInjuryCodeSearchButton(pageRequest);
    }

    public void doAddInjuryCodes(final ProcessStatusPageClaimBaseRequest pageRequest) {
        try {
            enterInjuryClaimDiagnosisCodeIdText(pageRequest);
            selectingLevelIndicatorFromDropdown(pageRequest);
            clickOnInjuryQuickAddButton(pageRequest);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("An error occurred (InjuryCode) creating a claim.", e);
        }

    }

    public void doAddPropertyDamageGeneralDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {
        selectTypeOfIncident(pageRequest);
        selectIncidentSource(pageRequest);
        selectIncidentSeverity(pageRequest);
    }

    public void doAddPropertyItemDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {
        clickOnItemDetailsAddButton(pageRequest);
        enterPropertyItemDescription(pageRequest);
        selectPropertyItemType(pageRequest);
        selectPropertyCategory(pageRequest);
        clickOnOKButton(pageRequest);
    }

    public void doAddGeneralIncidentDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {
        try {
            selectingAccidentLightingFromDropdown(pageRequest);
            selectingRoadSurfaceFromDropdown(pageRequest);
            selectingWeatherConditionFromDropdown(pageRequest);

        } catch (NoSuchElementException e) {
            pageRequest.getContext().error(PAGE_NAME, e);
            throw new AssertionError("An error occurred creating a claim.", e);
        }
    }

    public void doAddingHealthInjuryCaseDetails(final ProcessStatusPageClaimBaseRequest pageRequest) {
        try {

            selectingAreaOfPracticeFromDropdown(pageRequest);
            selectingClaimantStatusFromDropdown(pageRequest);
            selectingClinicalServiceContextFromDropdown(pageRequest);
            selectingClinicalSpecialityFromDropdown(pageRequest);
            selectingExtentOfHarmFromDropdown(pageRequest);
            selectingHealthServiceSettingFromDropdown(pageRequest);
            selectingPrimaryIncidentCodeFromDropdown(pageRequest);
            selectingNatureOfClaimIndicatorFromDropdown(pageRequest);
            selectingPracticeContextFromDropdown(pageRequest);
            selectingTypeOfClaimFromDropdown(pageRequest);
            selectingOtherDefendantFromDropdown(pageRequest);
        } catch (NoSuchElementException e) {
            pageRequest.getContext().error(PAGE_NAME, e);
            throw new AssertionError("An error occurred creating a claim.", e);
        }
    }


    public void clickOnOKButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on OK Button");
        click(getSave());
    }

    public void enterClaimCaseDetailsDescription(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Entering Claim Case Details Description  as : " + pageRequest.getClaimCaseDetailsDescription());
        input(pageRequest.getClaimCaseDetailsDescription(), claimCaseDetailsDescriptionTextBox);
    }

    public void enterClaimReceiveDate(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Claim Received Date as:" + pageRequest.getClaimReceiveDate());
        clearAndInput(pageRequest.getClaimReceiveDate(), getClaimReceivedDateTextBox());
    }

    private void selectingAreaOfPracticeFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Area Of Practice as : " + pageRequest.getAreaOfPractice());
        selectValue(pageRequest.getAreaOfPractice(), areaOfPracticeDropdown);
    }

    private void selectingClaimantStatusFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Claimant Status as : " + pageRequest.getClaimantStatus());
        selectValue(pageRequest.getClaimantStatus(), claimantStatusDropdown);
    }

    private void selectingClinicalServiceContextFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Clinical Service Context as : " + pageRequest.getClinicalServiceContext());
        selectValue(pageRequest.getClinicalServiceContext(), clinicalServiceContextDropdown);
    }

    private void selectingClinicalSpecialityFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Clinical Speciality as : " + pageRequest.getClinicalSpeciality());
        selectValue(pageRequest.getClinicalSpeciality(), clinicalSpecialityDropdown);
    }

    private void selectingExtentOfHarmFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Extent Of Harm as : " + pageRequest.getExtentOfHarm());
        selectValue(pageRequest.getExtentOfHarm(), extentOfHarmDropdown);
    }

    private void selectingHealthServiceSettingFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Health Service Setting as : " + pageRequest.getHealthServiceSetting());
        selectValue(pageRequest.getHealthServiceSetting(), healthServiceSettingDropdown);
    }

    private void selectingPrimaryIncidentCodeFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Primary Incident Code as : " + pageRequest.getPrimaryIncidentCode());
        selectValue(pageRequest.getPrimaryIncidentCode(), primaryIncidentCodeDropdown);
    }

    private void selectingNatureOfClaimIndicatorFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Nature Of Claim Indicator as : " + pageRequest.getNatureOfClaimIndicator());
        selectValue(pageRequest.getNatureOfClaimIndicator(), natureOfClaimIndicatorDropdown);
    }

    private void selectingPracticeContextFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting practice Context as : " + pageRequest.getPracticeContext());
        selectValue(pageRequest.getPracticeContext(), practiceContextDropdown);
    }

    private void selectingTypeOfClaimFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Type Of Claim as : " + pageRequest.getTypeOfClaim());
        selectValue(pageRequest.getTypeOfClaim(), typeOfClaimDropdown);
    }

    private void selectingOtherDefendantFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Other Defendant as : " + pageRequest.getOtherDefendant());
        selectValue(pageRequest.getOtherDefendant(), otherDefendantDropdown);
    }

    public void selectClaimReceiveDateTimeZone(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Claim Receive Date TimeZone as : " + pageRequest.getClaimReceiveDateTimeZone());
        selectValue(pageRequest.getClaimReceiveDateTimeZone(), claimReceivedDateTimeZoneDropdown);
    }

    public void clickOnCostCentreSearch(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Cost Centre Search link");
        click(costCentreSearch);
    }

    public void selectCostCentreCodeSearch(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Cost Centre Code as : " + pageRequest.getCostCentreCodeSearch());
        selectByIndex(pageRequest.getCostCentreCodeSearch(), costCentreCodeSearch);
    }

    public void clickOnOKCostCentreSearch(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking OK button on Cost Centre");
        click(searchPageOk);
    }

    public void clickOnInjuryCodeSearch(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Injury Code search link");
        clickModalBox(injuryCodeSearch);
    }

    public void enterInjuryCodeText(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Entering Injury code as : " + pageRequest.getCauseOfInjuryCode());
        input(pageRequest.getCauseOfInjuryCode(), injuryCodeTextBox);
        getAssertionHelper().assertIsDisplayed(" A cause of Injury Code", injuryCodeTextBox);
    }

    public void clickOnInjuryCodeSearchButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Injury Code Search Button");
        click(injuryCodeSearchButton);
    }

    private void enterInjuryClaimDiagnosisCodeIdText(final ProcessStatusPageClaimBaseRequest pageRequest) throws InterruptedException {
        pageRequest.log(PAGE_NAME, "Entering Injury Claim Diagnosis Code Id as : " + pageRequest.getInjuryCode());
        input(pageRequest.getInjuryCode(), injuryDiagnosisCode);
        Thread.sleep(SLEEP_TIME);
        injuryDiagnosisCode.sendKeys(Keys.ENTER);
    }

    private void selectingLevelIndicatorFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Level Indicatior as : " + pageRequest.getLevelIndicator());
        selectValue(pageRequest.getLevelIndicator(), levelIndicatorDropdown);
    }

    private void clickOnInjuryQuickAddButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Quick Add Button");
        click(mPersonalInjuryClaimInjuryQuickAddButton);
    }

    public void verifyClaimNumber(final ProcessStatusPageClaimBaseRequest pageRequest) {
        try {
            if (claimNumberElement.isDisplayed()) {
                String claimNumber = claimNumberElement.getText();
                pageRequest.log(PAGE_NAME, "Created " + pageRequest.getClaimType() + " Claim Number  is : " + claimNumber);
            }
        } catch (Exception e) {
            pageRequest.warning(getPageName(), "There was an issue when attempting to retrieve the claim number", true);
            throw e;
        }
    }


    private void selectingAccidentLightingFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Claim Accident Lighting as : " + pageRequest.getMClaimAccidentLightingDropdown());
        selectValue(pageRequest.getMClaimAccidentLightingDropdown(), selectMClaimAccidentLightingDropdown);
    }

    private void selectingRoadSurfaceFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Claim Road Surface as : " + pageRequest.getMClaimRoadSurfaceDropdown());
        selectValue(pageRequest.getMClaimRoadSurfaceDropdown(), selectMClaimRoadSurfaceDropdown);
    }

    private void selectingWeatherConditionFromDropdown(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Claim Weather Condition as : " + pageRequest.getMClaimWeatherConditionDropdown());
        selectValue(pageRequest.getMClaimWeatherConditionDropdown(), selectMClaimWeatherConditionDropdown);
    }

    public void clickNextButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking the first Next Button");
        click(getNextBtn().get(0));
    }

    public void clickNextButton(final ProcessStatusPageClaimBaseRequest pageRequest, final int index) {
        pageRequest.log(PAGE_NAME, "Clicking the " + index + " Next Button");
        click(getNextBtn().get(index));
    }

    public void clickOnNextButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Next Button");
        clickNextButton(pageRequest, 1);
    }

    protected void selectTypeOfIncident(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Type of Incident as : " + pageRequest.getTypeofIncident());
        selectValue(pageRequest.getTypeofIncident(), typeOfIncidentDropDown);
        getAssertionHelper().assertIsDisplayed(pageRequest.getTypeofIncident(), typeOfIncidentDropDown);
    }

    protected void selectIncidentSource(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Incident Source as : " + pageRequest.getIncidentSource());
        selectValue(pageRequest.getIncidentSource(), propertyIncidentSourceDropDown);
        getAssertionHelper().assertIsDisplayed(pageRequest.getIncidentSource(), propertyIncidentSourceDropDown);
    }

    protected void selectIncidentSeverity(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Incident Severity as :" + pageRequest.getIncidentSeverity());
        selectValue(pageRequest.getIncidentSeverity(), propertyIncidentSeverityDropDown);
    }

    public void clickOnItemDetailsAddButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Add button for adding property Item details");
        click(itemDetailsAddButton);
    }

    public void enterPropertyItemDescription(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Entering Property Item Description as : " + pageRequest.getPropertyItemDescription());
        input(pageRequest.getPropertyItemDescription(), propertyDescriptionTextArea);
    }

    public void selectPropertyItemType(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Property Item Type as : " + pageRequest.getPropertyItem());
        selectValue(pageRequest.getPropertyItem(), propertyItemTypeDropDown);
    }

    public void selectPropertyCategory(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Property Item Category as : " + pageRequest.getPropertyCategory());
        selectValue(pageRequest.getPropertyCategory(), propertyItemCategoryDropDown);
    }

    public void clickOnThirdPartyVehicleRadioButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Third Party Vehicle Radio Button");
        clickModalBox(mClaimThirdPartyVehicleRadioButton);
    }

    public void clickOnInsuredVehicleTypeRadioButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Insured Vehicle Type Radio Button");
        clickModalBox(mClaimInsuredVehicleTypeRadioButton);
    }

    public void clickOnVehiclesInvolvedAddButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Scrolling Page...");
        scrollPageVertically(VERTICAL_LENGTH);
        pageRequest.log(PAGE_NAME, "Clicking on Vehicles Involved Add Button");
        clickModalBox(mClaimVehiclesInvolvedAddButton);
    }

    public void clickOnVehicleTypePopupWidgetOKButton(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Vehicle Type Popup Widget OK Button");
        click(mClaimVehicleTypePopupWidgetOKButton);
    }

    public static class ProcessStatusPageClaimBaseRequest extends ProcessStatusPageBaseRequest {

        public ProcessStatusPageClaimBaseRequest(final TestCaseContext context) {
            super(context);
        }

        public ClaimType getClaimType() {
            return ClaimType.valueOfProperty(get("Claim_Type"));
        }

        public String getCaseNumber() {
            return get("CaseNumber");
        }

        public String getLinkPartyType() {
            return get("Party Type");
        }

        public String getLinkPartyRole() {
            return get("Linked Party");
        }

        public String getLinkPartyClaimantFirstName() {
            return get("Claimant_FirstName");
        }

        public boolean isUniqueClaimant() {
            return Boolean.parseBoolean(get("IsUniqueClaimant"));
        }

        public String getLinkPartyClaimantLastNameOrShortName() {
            return get("Claimant_Surname");
        }

        public String getClaimantOrganisationCountry() {
            return get("Claimant_Country");
        }

        public String getClaimReceiveDate() {
            getContext().getData().putIfAbsent("Claim Received Date", RandomData.getCalculatedDateAndTime(DEFAULT_CLAIM_RECEIVED_DAYS));
            return getContext().getData().get("Claim Received Date");
        }

        public String getInjuryCode() {
            return get("Injury Code");
        }

        public String getCauseOfInjuryCode() {
            return get("CauseOfInjuryCode");
        }

        public String getClaimReceiveDateTimeZone() {
            return get("Claim Received TimeZone");
        }

        public String getClaimReceivedTimeZoneIndex() {
            return get("Claim Received TimeZone Index");
        }

        public String getTypeofIncident() {
            return get("TypeofIncident");
        }

        public String getIncidentSource() {
            return get("IncidentSource");
        }

        public String getIncidentSeverity() {
            return get("IncidentSeverity");
        }

        public String getPropertyItemDescription() {
            return get("Property Item Desc");
        }

        public String getPropertyItem() {
            return get("Property Item");
        }

        public String getPropertyCategory() {
            return get("Property Category");
        }

        public String getClaimCaseDetailsDescription() {
            return get("Claim_Desc");
        }

        public String getMClaimAccidentLightingDropdown() {
            return get("AccidentLighting");
        }

        public String getMClaimRoadSurfaceDropdown() {
            return get("RoadSurface");
        }

        public String getMClaimWeatherConditionDropdown() {
            return get("WeatherCondition");
        }

        public String getInsuredThirdPartyVehicle() {
            return get("VehicleType");
        }

        public String getVehicleVinNumber() {
            return get("VinNumber");
        }

        public String getVehicleRegistrationNumber() {
            return get("RegistrationNumber");
        }

        public String getVehicleMake() {
            return get("Make");
        }

        public String getVehicleModel() {
            return get("Model");
        }

        public String getVehicleYear() {
            return get("VehicleYear");
        }

        public String getVehicleColor() {
            return get("VehicleColour");
        }

        public String getVehicleType() {
            return get("Type");
        }

        public String getVehicleDuty() {
            return get("VehicleDuty");
        }

        public String getVehicleUsage() {
            return get("Usage");
        }

        public String getCostCentreCodeSearch() {
            return get("Cost Centre Code");
        }

        public String getLevelIndicator() {
            return get("LevelIndicator");
        }

        public String getAreaOfPractice() {
            return get("AreaOfPractice");
        }

        public String getClaimantStatus() {
            return get("ClaimantStatus");
        }

        public String getClinicalServiceContext() {
            return get("ClinicalServiceContext");
        }

        public String getClinicalSpeciality() {
            return get("ClinicalSpeciality");
        }

        public String getExtentOfHarm() {
            return get("ExtentOfHarm");
        }

        public String getHealthServiceSetting() {
            return get("HealthServiceSetting");
        }

        public String getPrimaryIncidentCode() {
            return get("PrimaryIncidentCode");
        }

        public String getNatureOfClaimIndicator() {
            return get("NatureOfClaimIndicator");
        }

        public String getPracticeContext() {
            return get("PracticeContext");
        }

        public String getTypeOfClaim() {
            return get("TypeOfClaim");
        }

        public String getOtherDefendant() {
            return get("OtherDefendant");
        }
    }

}
