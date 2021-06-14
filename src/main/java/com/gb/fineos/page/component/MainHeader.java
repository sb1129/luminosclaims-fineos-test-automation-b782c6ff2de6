package com.gb.fineos.page.component;

import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainHeader  extends BasePage implements IComponent {
    private String pageName = "MAIN HEADER";

    //Main Buttons
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_MENUITEM.NewWindow_MENUITEM.NewWindowlink')]")
    private WebElement newWindowButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_MENUITEM.TechMode_MENUITEM.TechModelink')]")
    private WebElement toggleConfigModeButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_MENUITEM.CompileLESS_MENUITEM.CompileLESSlink')]")
    private WebElement compileLessButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_MENUITEM.ConfigStudio_MENUITEM.ConfigStudiolink')]")
    private WebElement configStudioButton;

    // Dropdowns
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.ReportingAnalytics_DROPDOWN.ReportingAnalyticslink')]")
    private WebElement reportingAndAnalyticsDropdown;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.LanguageSelection_DROPDOWN.LanguageSelectionlink')]")
    private WebElement languageSelectionDropdown;
    //                                  _MENUBAR.UtilitiesMenu_DROPDOWNMENU.UserOptions_DROPDOWNMENU.UserOptionslink
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWNMENU.UserOptions_DROPDOWNMENU.UserOptionslink')]")
    private WebElement userOptionsDropdown;

    // Reporting and Analytics Buttons
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.ReportingAnalytics_MENUITEM.Reports_MENUITEM.Reportslink')]")
    private WebElement reportsButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.ReportingAnalytics_MENUITEM.ProcessAnalytics_MENUITEM.ProcessAnalyticslink')]")
    private WebElement processAnalyserButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.ReportingAnalytics_MENUITEM.ConfigCatalogue_MENUITEM.ConfigCataloguelink')]")
    private WebElement configReportsButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.ReportingAnalytics_MENUITEM.ViewHistory_MENUITEM.ViewHistorylink')]")
    private WebElement viewHistoryButton;

    // Language Buttons
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.LanguageSelection_English_Englishlink')]")
    private WebElement englishButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.LanguageSelection_French_Frenchlink')]")
    private WebElement frenchButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.LanguageSelection_Spanish_Spanishlink')]")
    private WebElement spanishButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWN.LanguageSelection_German_Germanlink')]")
    private WebElement germanButton;

    // User Options Buttons
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWNMENU.UserOptions_MENUITEM.UserPreferences_MENUITEM.UserPreferenceslink')]")
    private WebElement preferencesButton;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWNMENU.UserOptions_MENUITEM.Logout_MENUITEM.Logoutlink')]")
    private WebElement logoutButton;
    @FindBy(xpath = "//div[contains(@class,'header')]/span[contains(@class,'caption')]")
    private WebElement userFullNameElement;

    public MainHeader() {
        super("MAIN HEADER");
    }

    public String getPageName() {
        return this.pageName;
    }

    public void addPageNamePrefix(final String pageNamePrefix) {
        this.pageName = pageNamePrefix + " - " + this.pageName;
    }

    public WebElement getNewWindowButton() {
        return newWindowButton;
    }
    public WebElement getToggleConfigModeButton() {
        return toggleConfigModeButton;
    }
    public WebElement getCompileLessButton() {
        return compileLessButton;
    }
    public WebElement getConfigStudioButton() {
        return configStudioButton;
    }
    public WebElement getReportingAndAnalyticsDropdown() {
        return reportingAndAnalyticsDropdown;
    }
    public WebElement getLanguageSelectionDropdown() {
        return languageSelectionDropdown;
    }
    public WebElement getUserOptionsDropdown() {
        return userOptionsDropdown;
    }
    public WebElement getReportsButton() {
        return reportsButton;
    }
    public WebElement getProcessAnalyserButton() {
        return processAnalyserButton;
    }
    public WebElement getConfigReportsButton() {
        return configReportsButton;
    }
    public WebElement getViewHistoryButton() {
        return viewHistoryButton;
    }
    public WebElement getEnglishButton() {
        return englishButton;
    }
    public WebElement getFrenchButton() {
        return frenchButton;
    }
    public WebElement getSpanishButton() {
        return spanishButton;
    }
    public WebElement getGermanButton() {
        return germanButton;
    }
    public WebElement getPreferencesButton() {
        return preferencesButton;
    }
    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void clickNewWindow(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking New Window button");
        click(newWindowButton);
    }

    public void clicktoggleConfigMode(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Toggle Config Mode button");
        click(toggleConfigModeButton);
    }

    public void clickCompileLess(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Compile Less button");
        click(compileLessButton);
    }

    public void clickConfigStudio(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Config Studio button");
        click(configStudioButton);
    }

    public void clickReportingAndAnalyticsDropdown(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Reporting and Analytics Dropdown");
        click(reportingAndAnalyticsDropdown);
    }

    public void clickLanguageSelectionDropdown(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Language Selection Dropdown");
        click(languageSelectionDropdown);
    }

    public void clickUserOptionsDropdown(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking User Options Dropdown");
        click(userOptionsDropdown);
    }

    public void clickReports(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Reports");
        click(reportsButton);
    }

    public void clickProcessAnalyser(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Process Analyser");
        click(processAnalyserButton);
    }

    public void clickConfigReports(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Config Reports");
        click(configReportsButton);
    }

    public void clickViewHistory(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking View History");
        click(viewHistoryButton);
    }

    public void clickEnglish(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking English");
        click(englishButton);
    }

    public void clickFrench(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking French");
        click(frenchButton);
    }

    public void clickSpanish(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Spanish");
        click(spanishButton);
    }

    public void clickGerman(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking German");
        click(germanButton);
    }

    public void clickPreferences(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Preferences");
        click(preferencesButton);
    }

    public void clickLogout(final PageRequest pageRequest) {
        pageRequest.log(pageName, "Clicking Logout");
        clickModalBox(logoutButton);
    }
    public String getUserName(final PageRequest pageRequest) {
        pageRequest.log(getPageName(), "Getting the current username : " + getText(userFullNameElement).trim().toUpperCase());
        return getText(userFullNameElement).trim().toUpperCase();
    }
}
