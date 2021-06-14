package com.gb.fineos.page.widgets.documentmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExtraDataPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack_cloned')]")
    private WebElement cancelButton;

    public ExtraDataPage() {
        super("EXTRA DATA PAGE");
    }

    public void clickOKOrClose(final ExtraDataPageRequest pageRequest) {
        final WebElement closeButton =
            getDriver().findElements(By.xpath("//input[contains(@id,'_editPageSave_cloned')]"))
                .stream()
                .findFirst()
                .orElse(cancelButton);

        pageRequest.log(getPageName(), "Clicking on " + closeButton.getText() + " button");
        click(closeButton);
    }

    public static class ExtraDataPageRequest extends AbstractPageRequest {
        public ExtraDataPageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
