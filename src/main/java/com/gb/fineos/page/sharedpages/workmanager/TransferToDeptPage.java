package com.gb.fineos.page.sharedpages.workmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.Filter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferToDeptPage extends BasePage {


    private final Filter filter;

    public TransferToDeptPage() {
        super("TRANSFER TO DEPT PAGE");
        this.filter = PageFactory.initElements(Filter.class);
        this.filter.addPageNamePrefix(getPageName());
    }
    public Filter getFilter() {
        return filter;
    }

    @FindBy(xpath = "//input[contains(@id,'_editPageSave_')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@id,'_DeptCaseTransferRecord_ok')]")
    private WebElement caseTransferOk;
    @FindBy(xpath = "//div[contains(@id,'divListviewGrid')]")
    private WebElement list;
    @FindBy(xpath = "(//td[contains(@class,'ListCell') and contains(@title,'External Claim Review')]/parent::tr/td[not(text())]/parent::tr)[1]")
    private WebElement externalClaimReviewDept;

    public void selectDeptWithNoRole(final TransferToDeptPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Dept to transfer");
        click(externalClaimReviewDept);
    }

    public void clickOk(final TransferToDeptPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Ok");
        clickModalBox(okButton);
    }

    public void caseTransferOk(final TransferToDeptPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Confirm Case Transfer. Clicking Ok");
        clickModalBox(caseTransferOk);
    }

    public static class TransferToDeptPageRequest extends AbstractPageRequest {

        public TransferToDeptPageRequest(final TestCaseContext context) {
            super(context);
        }

    }

}
