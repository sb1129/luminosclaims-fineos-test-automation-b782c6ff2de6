package com.gb.fineos.page.component;

import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GBClientFieldSearchPage extends BasePage implements IComponent {
    private String pageName = "MAIN HEADER";
    protected static final String BUS_ROUTE_DEFAULT_FIRST_INDEX = "1";

    //
    @FindBy(xpath = "//select[contains(@id,'GBClientFieldlistViewWidget_') and contains(@id,'_CodeSearch')]")
    private WebElement searchText;
    @FindBy(xpath = "//input[contains(@id,'_searchPageOk_cloned')]")
    private WebElement okButton;

    public GBClientFieldSearchPage() {
        super("CLIENT FIELD SEARCH PAGE");
    }

    public String getPageName() {
        return this.pageName;
    }

    public void addPageNamePrefix(final String pageNamePrefix) {
        this.pageName = pageNamePrefix + " - " + this.pageName;
    }
    private void clickOkButton(final PageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Ok Button");
        click(okButton);
    }

    private void selectClientData(final PageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Client Data Search...");
        selectByIndex(BUS_ROUTE_DEFAULT_FIRST_INDEX, searchText);
    }

    public void searchClientData(final PageRequest pageRequest) {
        selectClientData(pageRequest);
        click(okButton);
    }
}
