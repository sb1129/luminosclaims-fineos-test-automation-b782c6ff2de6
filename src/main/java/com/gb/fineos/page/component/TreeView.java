package com.gb.fineos.page.component;

import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TreeView extends BasePage implements IComponent {
    private String pageName = "TREE VIEW";

    // Treeview Element
    @FindBy(xpath = "//div[contains(@class,'TreeView')]")
    private WebElement treeViewElement;

    public TreeView() {
        super("TREE VIEW");
    }

    public String getPageName() {
        return this.pageName;
    }

    public void addPageNamePrefix(final String pageNamePrefix) {
        this.pageName = pageNamePrefix + " - " + this.pageName;
    }

    public void clickChildNodeElement(final PageRequest request, final String parentNodeText, final String nodeText) {
        WebElement nodeElement = findNode(parentNodeText, nodeText);
        if (nodeElement == null) {
            request.log(pageName, "Failed to locate " + parentNodeText + " -> " + nodeText);
        } else {
            request.log(pageName, "Clicking " + parentNodeText + " -> " + nodeText);
            click(nodeElement);
        }
    }

    private WebElement findNode(final String parentNodeText, final String nodeText) {
        List<WebElement> treeNodes = getTreeNodes();
        if (treeNodes.isEmpty()) {
            return null;
        }
            WebElement parentNode = getTreePathNode(treeNodes, parentNodeText);
            if (parentNode == null) {
                return null;
            }
            WebElement childrenContainer = parentNode.findElement(By.xpath("//span[contains(@id,'treenode_') and contains(@id,'_children')]"));
            List<WebElement> nodeElementContainer = childrenContainer.findElements(By.className("TreeNodeContainer"));
            for (WebElement node : nodeElementContainer) {
                List<WebElement> nodeElements = node.findElements(By.id("nodeElement"));
                for (WebElement element : nodeElements) {
                    String nodeBodyText = element.getText();
                    if (nodeBodyText.equalsIgnoreCase(nodeText)) {
                        return element;
                    }
                }
            }
        return null;
    }

    private WebElement getTreePathNode(final List<WebElement> treeNodes, final String parentNodeText) {
        for (WebElement parent : treeNodes) {
            try {
                WebElement treePathNodeId = parent.findElement(By.id("node"));
                String parentDisplayText = treePathNodeId.findElement(By.tagName("span")).getText();
                if (parentDisplayText.equalsIgnoreCase(parentNodeText)) {
                    return parent;
                }
            } catch (NoSuchElementException e) {
                getAssertionHelper().handleNoSuchElementExceptionAndThrow(parent, e);
            }
        }
        return null;
    }

    private List<WebElement> getTreeNodes() {
        return treeViewElement.findElements(By.className("TreeNodeContainer"));
    }

}
