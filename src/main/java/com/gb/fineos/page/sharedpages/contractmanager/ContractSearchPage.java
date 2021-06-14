package com.gb.fineos.page.sharedpages.contractmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContractSearchPage extends BasePage {

    @FindBy(xpath = "//td[contains(@id, '_recent_cell')]")
    private WebElement recentTab;
    @FindBy(xpath = "//td[contains(@id, '_contractSearch_cell')]")
    private WebElement policySearchTab;
    @FindBy(xpath = "//td[contains(@id, '_contractSearchByParty_cell')]")
    private WebElement partyTab;
    @FindBy(xpath = "//input[contains(@id,'_searchPageCancel_')]")
    private WebElement cancelBtn;

    public WebElement getRecentTab() {
        return recentTab;
    }

    public WebElement getPolicySearchTab() {
        return policySearchTab;
    }

    public WebElement getPartyTab() {
        return partyTab;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public ContractSearchPage() {
        super("CONTRACT SEARCH PAGE");
    }

    public void clickCancel(final ContractSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel");
        click(cancelBtn);
    }

    public static class ContractSearchPageRequest extends AbstractPageRequest {

        public ContractSearchPageRequest(final TestCaseContext context) {
            super(context);
        }
    }

}
