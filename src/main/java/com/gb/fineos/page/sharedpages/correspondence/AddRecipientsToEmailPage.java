package com.gb.fineos.page.sharedpages.correspondence;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddRecipientsToEmailPage extends BasePage {

    @FindBy(xpath = "//input[starts-with(@id, 'SendEmailWidget_') and contains(@id, '_emailSubjectTextBox')]")
    private WebElement subject;

    @FindBy(xpath = "//input[starts-with(@id, 'SelectEmailRecipientsContainerWidget') and contains(@id, 'toButtonBean')]")
    private WebElement toButton;

    @FindBy(xpath = "//input[starts-with(@id, 'SelectEmailRecipientsContainerWidget') and contains(@id, 'ccButtonBean')]")
    private WebElement ccButton;

    @FindBy(xpath = "//input[contains(@id,'_okButton_cloned')]")
    private WebElement okButton;

    @FindBy(xpath = "//input[contains(@id,'_searchPageCancel_cloned')]")
    private WebElement cancelButton;

    public AddRecipientsToEmailPage() {
        super("ADD RECIPIENTS TO EMAIL");
    }

    public void enterSubject(final AddRecipientsToEmailPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Subject: " + pageRequest.getSubject());
        clearAndInput(pageRequest.getSubject(), subject);
    }

    public void addRecipients(final AddRecipientsToEmailPageRequest pageRequest) {
        final List<String> treeNodeDivIds = getDriver().findElements(By.xpath("//div[starts-with(@id, 'treenode_availableEmailRecipientsTreeviewControlBean')]"))
            .stream()
            .map(webElement -> webElement.getAttribute("id"))
            .collect(Collectors.toList());

        pageRequest.getToRecipientsMap().forEach(selectRecipients(pageRequest, treeNodeDivIds, toButton));
        pageRequest.getCcRecipientsMap().forEach(selectRecipients(pageRequest, treeNodeDivIds, ccButton));
    }

    public void clickOk(final AddRecipientsToEmailPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Ok Button");
        click(okButton);
    }

    public void clickCancel(final AddRecipientsToEmailPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Cancel Button");
        click(cancelButton);
    }

    private BiConsumer<String, List<String>> selectRecipients(
        final AddRecipientsToEmailPageRequest pageRequest,
        final List<String> treeNodeDivIds,
        final WebElement recipientTypeButton) {

        return (payeeType, names) -> {
            pageRequest.log(getPageName(), "Selecting '" + recipientTypeButton.getAttribute("value") + "' recipients: " + String.join(", ", names) + " for payee type: " + payeeType);

            treeNodeDivIds.stream()
                .filter(treeNodeDivId -> {
                    final List<WebElement> nodeElementSpans = getDriver().findElements(By.xpath("//div[@id='" + treeNodeDivId + "']/span[@id='node']/span[@class='TreeNodeElement']"));

                    return nodeElementSpans.stream().anyMatch(nodeElementSpan -> nodeElementSpan.getText().startsWith(payeeType))
                        && nodeElementSpans.stream().anyMatch(nodeElementSpan -> names.contains(nodeElementSpan.getText()));
                })
                .forEach(selectRecipient(recipientTypeButton));
        };
    }

    private Consumer<String> selectRecipient(final WebElement recipientTypeButton) {
        return treeNodeDivId -> {
            final By xpath = By.xpath("//div[@id='" + treeNodeDivId + "']/span[@id='node']");

            getDriver().findElement(xpath).click();

            new WebDriverWait(getDriver(), Long.parseLong(getProperty("Default_WaitTime")))
                .until(ExpectedConditions.attributeContains(xpath, "class", "TreeNodeSelected"));

            recipientTypeButton.click();
        };
    }

    public static class AddRecipientsToEmailPageRequest extends AbstractPageRequest {

        public AddRecipientsToEmailPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getSubject() {
            return get("Subject");
        }

        public Map<String, List<String>> getToRecipientsMap() {
            return getRecipientsMap("ToRecipients");
        }

        public Map<String, List<String>> getCcRecipientsMap() {
            return getRecipientsMap("CcRecipients");
        }

        private Map<String, List<String>> getRecipientsMap(final String recipientsKey) {
            final Map<String, List<String>> recipientMap = new HashMap<>();

            Stream.of(get(recipientsKey).split(",")).map(String::trim).filter(StringUtils::isNotBlank).collect(Collectors.toList()).forEach(s -> {
                final String[] tokens = s.split("\\|");
                final String role = tokens[0];
                final String name = tokens[1];

                final List<String> recipients = recipientMap.getOrDefault(role, new ArrayList<>());
                recipients.add(name);

                recipientMap.putIfAbsent(role, recipients);
            });

            return recipientMap;
        }
    }
}
