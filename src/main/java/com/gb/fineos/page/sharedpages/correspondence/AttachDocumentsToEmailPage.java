package com.gb.fineos.page.sharedpages.correspondence;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AttachDocumentsToEmailPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_EmailButton_cloned')]")
    private WebElement emailButton;

    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack_cloned')]")
    private WebElement cancelButton;

    public AttachDocumentsToEmailPage() {
        super("ATTACH DOCUMENTS TO EMAIL");
    }

    public void clickEmailButton() {
        click(emailButton);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }

    public void actionConfirmNoAttachmentsPopup(final AttachDocumentsToEmailPageRequest pageRequest) {
        final WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Long.parseLong(getProperty("Default_WaitTime")));

        if (pageRequest.isConfirmNoDocumentsAndContinue()) {
            click(webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'_confirmNoSelectedPopup_yes')]"))));
        } else {
            click(webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'_confirmNoSelectedPopup_no')]"))));
        }
    }

    public static class AttachDocumentsToEmailPageRequest extends AbstractPageRequest {

        public AttachDocumentsToEmailPageRequest(final TestCaseContext context) {
            super(context);
        }

        public boolean isConfirmNoDocumentsAndContinue() {
            return Boolean.parseBoolean(get("ConfirmNoDocumentsAndContinue"));
        }
    }
}
