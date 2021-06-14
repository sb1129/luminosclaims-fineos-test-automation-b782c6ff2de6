package com.gb.fineos.page.component;

import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Filter extends BasePage implements IComponent {
    private String pageName = "FILTER";
    private String widget;
    private String widgetType;

    private WebElement closeButton;
    private WebElement textInput;
    private WebElement applyButton;
    private WebElement clearButton;

    public Filter() {
        super("FILTER");
    }

    public String getPageName() {
        return this.pageName;
    }

    public void addPageNamePrefix(final String pageNamePrefix) {
        this.pageName = pageNamePrefix + " - " + this.pageName;
    }

    public void setFilter(final String widgetName, final String widgetTypeName, final String columnName) {
        this.widget = widgetName;
        this.widgetType = widgetTypeName;
        this.textInput = createXpath(columnName + "_textFilter");
        this.closeButton = createXpath(columnName + "_topCloseButton");
        this.applyButton = createXpath("cmdFilter");
        this.clearButton = createXpath("cmdCancelFilter");
    }

    public void clickCloseButton(final PageRequest request) {
        request.log(pageName, "Clicking the Close Button");
        click(closeButton);
    }

    public void clickApplyButton(final PageRequest request) {
        request.log(pageName, "Clicking the Apply Button");
        click(applyButton);
    }

    public void clickClearButton(final PageRequest request) {
        request.log(pageName, "Clicking the Clear Button to remove the current filter '" + textInput.getAttribute("value") + "'");
        click(clearButton);
    }

    public void inputFilterText(final PageRequest request, final String inputText) {
        request.log(pageName, "Entering '" + inputText + "' into the Filter Text Box");
        clearAndInput(inputText, textInput);
    }

    private WebElement createXpath(final String elementIdentifier) {
        return getDriver().findElement(By.xpath("//span[contains(@class,'PopupBean') and contains(@style,'visible')]//input[contains(@id,'" + widget + "_') and contains(@id,'_" + widgetType + "_" + elementIdentifier + "')]"));
    }

}

