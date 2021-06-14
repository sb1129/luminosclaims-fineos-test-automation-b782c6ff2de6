package com.gb.fineos.page.component;

import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeavingPagePopup extends BasePage implements IComponent {

    private String pageName = "LEAVING PAGE";

    @FindBy(xpath = "//input[contains(@id,'_warningsLeavingPagePopup_yes')]")
    private WebElement yesButton;

    @FindBy(xpath = "//input[contains(@id,'_warningsLeavingPagePopup_no')]")
    private WebElement noButton;

    @FindBy(xpath = "//input[contains(@id,'_warningsLeavingPagePopup_topCloseButton')]")
    private WebElement closeButton;

    public LeavingPagePopup() {
        super("LEAVING PAGE");
    }

    public String getPageName() {
        return this.pageName;
    }

    public void addPageNamePrefix(final String pageNamePrefix) {
        this.pageName = pageNamePrefix + " - " + this.pageName;
    }

    public void clickYesButton(final PageRequest request) {
        request.log(getPageName(), "Clicking the LeavingPagePopup Yes Button");
        click(yesButton);
    }

    public void clickNoButton(final PageRequest request) {
        request.log(getPageName(), "Clicking the LeavingPagePopup No Button");
        click(noButton);
    }

    public void clickCloseButton(final PageRequest request) {
        request.log(getPageName(), "Clicking the LeavingPagePopup Close Button");
        click(closeButton);
    }
}
