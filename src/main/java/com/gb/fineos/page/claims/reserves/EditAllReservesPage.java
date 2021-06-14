package com.gb.fineos.page.claims.reserves;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditAllReservesPage extends BasePage {


    //Reserve Expenses
    @FindBy(xpath = "//table[contains(@id,'_ReservesListView')]/tbody/tr[1]/td[5]/input[contains(@id,'_amountBeanTL')]")
    private WebElement reserveAmountTextBox;
    @FindBy(xpath = "//table[contains(@id,'_ReservesListView')]/tbody/tr[1]/td[7]/span/select[contains(@id,'_statusEnumBeanTL')]")
    private WebElement reserveStatusDropDown;
    @FindBy(xpath = "//table[contains(@id,'ReservesListViewWidget0') and contains(@id,'_ReservesListView')]/tbody/tr")
    private List<WebElement> reserveExpensesTable;

    @FindBy(xpath = "//table[contains(@id,'ReservesListViewWidget1') and contains(@id,'_ReservesListView')]/tbody/tr")
    private List<WebElement> reserveSettlementTable;


    @FindBy(xpath = "//input[contains(@id,'_localOKButton_cloned')]")
    private WebElement okButton;

    public EditAllReservesPage() {
        super("EDIT ALL RESERVE PAGE");
    }

    public void enterReserveAmount(final EditAllReservesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Reserve Amount as : " + pageRequest.getReserveAmount());
        clearAndInput(pageRequest.getReserveAmount(), reserveAmountTextBox);
    }
// below method handles both expenses and settlement reserves
    public void enterReserveAmount(final EditAllReservesPageRequest pageRequest, final String rowUniqueValue, final String reserveSectionType) {
        int col = 0;
        List<WebElement> reserveTable;
        String widget;

        if (reserveSectionType.equals("Expenses")) {
            reserveTable = reserveExpensesTable;
            widget = "ReservesListViewWidget0";
        } else {
            reserveTable = reserveSettlementTable;
            widget = "ReservesListViewWidget1";
        }

        for (int row = 0; row < reserveTable.size(); row++) {
            List<WebElement> cells = reserveTable.get(row).findElements(By.tagName("td"));
            if (cells.get(col).getText().equalsIgnoreCase(rowUniqueValue)) {
                int actualRow = row + 1;
                WebElement reserveAmount = getElement("//table[contains(@id,'" + widget + "') and contains(@id,'_ReservesListView')]/tbody/tr[" + actualRow + "]/td[5]/input[contains(@id,'_amountBean')]");
                WebElement status = getElement("//table[contains(@id,'" + widget + "') and contains(@id,'_ReservesListView')]/tbody/tr[" + actualRow + "]/td[7]/span/select[contains(@id,'_statusEnumBean')]");
                clearAndInput(pageRequest.getReserveAmount(), reserveAmount);
                selectValue(pageRequest.getReserveStatus(), status);
                break;

            }
        }
    }

    public void selectReserveStatus(final EditAllReservesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Reserve Status type as : " + pageRequest.getReserveStatus());
        selectValue(pageRequest.getReserveStatus(), reserveStatusDropDown);
    }

    public void clickOkButton(final EditAllReservesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on OK button ");
        click(okButton);
    }


    public static class EditAllReservesPageRequest extends AbstractPageRequest {

        public EditAllReservesPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getReserveAmount() {
            return get("ReserveExpenseAmount");
        }

        public String getReserveType() {
            return get("ReserveType");
        }

        public String getReserveSectionType() {
            return get("ReserveSectionType");
        }

        public String getReserveStatus() {
            return get("ReserveStatus");
        }
    }

}
