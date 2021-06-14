package com.gb.fineos.script.regression.autoupload;


import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.sharedpages.correspondence.GBEmailViewerPage;
import com.gb.fineos.page.sharedpages.documentmanager.DocumentPropertiesPage;
import com.gb.fineos.page.utils.DocumentUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.gb.fineos.page.utils.SearchUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static com.gb.fineos.domain.CaseType.BENEFIT;
import static com.gb.fineos.domain.CaseType.CLAIM;
import static com.gb.fineos.domain.CaseType.NOTIFICATION;
import static org.testng.Assert.assertEquals;


public class AutoUploadTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void newDownloadViewButtonTest(final Map<String, String> testData) {
        doTest("Auto Upload Upload And View Button Test", "Verify that the new download and View buttons are available", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyUploadAndViewButton).test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au, regression.icare", "regression.nz", "regression.uk"})
    public void viewOneAttachmentTest(final Map<String, String> testData) {
        doTest("Auto upload view one selected attachment Test", "Verify that one attachment can be selected and viewed", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyViewOneAttachment).test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au, regression.icare", "regression.nz", "regression.uk"})
    public void viewMultipleAttachmentTest(final Map<String, String> testData) {
        doTest("Auto upload Multiple Attachment view Test", "Verify that Multiple attachment can be selected and view button is disabled", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyViewMultipleAttachments).test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void verifyPrefixAndOrderOfAttachments(final Map<String, String> testData) {
        doTest("Auto Upload Documents Prefix and order", "AutoUpload Attachments - Identify footer images from attachment list", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyPrefixAndOrderOfAttachments).test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"}, dependsOnMethods = {})
    public void uploadDocumentFromEmailViewerPageTest(final Map<String, String> testData) {
        doTest("Auto upload Upload documents test", "Verify selected attachment is uploaded as document", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyAutoUploadSelectedAttachments).test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void verifyThePropertiesOfUploadDocument(final Map<String, String> testData) {
        doTest("Auto upload uploaded document properties Test", "Verify the Attachment properties correspondence added has a “Party” or “Case” or BOTH linked to the source", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyPropertiesOfUploadedAttachments).test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void verifyDocumentPropertiesTest(final Map<String, String> testData) {
        doTest("Verify Documents Properties", "Verify the documents properties after upload a document", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(this::verifyDocumentProperties).test();
        });
    }

    private void verifyUploadAndViewButton(final TestCaseContext tc) {
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), false);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), false);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), false);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
    }

    private void verifyDocumentProperties(final TestCaseContext tc) {
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        final DisplayCasePageNotificationBase displayCasePageNotificationBase = tc.getPage(DisplayCasePageNotificationBase.class);
        final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = new DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest(tc);
        final DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest(tc);
        final DisplayCasePageBenefitBase displayCasePageBenefitBase = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(tc);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
        gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForUploadProcessToFinish(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc,NOTIFICATION);
        displayCasePageNotificationBase.clickDocumentsTab(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.clickTitleFilter(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.getFilter().inputFilterText(displayCasePageNotificationBaseRequest, displayCasePageNotificationBaseRequest.getTitle());
        displayCasePageNotificationBase.getFilter().clickApplyButton(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.selectFirstCheckbox(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.clickOnDocumentPropertiesButton(displayCasePageNotificationBaseRequest);
        documentPropertiesCheck(tc);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
        gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForUploadProcessToFinish(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc,CLAIM);
        displayCasePageClaimBase.clickDocumentsTab(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.clickTitleFilter(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.getFilter().inputFilterText(displayCasePageClaimBaseRequest,displayCasePageClaimBaseRequest.getTitle());
        displayCasePageClaimBase.getFilter().clickApplyButton(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.selectFirstCheckbox(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.clickOnDocumentPropertiesButton(displayCasePageClaimBaseRequest);
        documentPropertiesCheck(tc);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
        gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForUploadProcessToFinish(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc,BENEFIT);
        displayCasePageBenefitBase.clickDocumentsTab(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.clickTitleFilter(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.getFilter().inputFilterText(displayCasePageBenefitBaseRequest, displayCasePageBenefitBaseRequest.getTitle());
        displayCasePageBenefitBase.getFilter().clickApplyButton(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.selectFirstCheckbox(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.clickOnDocumentPropertiesButton(displayCasePageBenefitBaseRequest);
        documentPropertiesCheck(tc);
    }

    private void verifyPropertiesOfUploadedAttachments(final TestCaseContext tc) {
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        final DocumentPropertiesPage documentPropertiesPage = tc.getPage(DocumentPropertiesPage.class);
        final DocumentPropertiesPage.DocumentPropertiesPageRequest documentPropertiesPageRequest = new DocumentPropertiesPage.DocumentPropertiesPageRequest(tc);
        final DisplayCasePageNotificationBase displayCasePageNotificationBase = tc.getPage(DisplayCasePageNotificationBase.class);
        final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = new DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest(tc);
        final DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest(tc);
        final DisplayCasePageBenefitBase displayCasePageBenefitBase = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(tc);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        gbEmailViewerPage.selectMultipleAttachments(gbEmailViewerPageRequest);
        gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForUploadProcessToFinish(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc,NOTIFICATION);
        displayCasePageNotificationBase.clickDocumentsTab(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.clickTitleFilter(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.getFilter().inputFilterText(displayCasePageNotificationBaseRequest, displayCasePageNotificationBaseRequest.getTitle());
        displayCasePageNotificationBase.getFilter().clickApplyButton(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.selectFirstCheckbox(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.clickOnDocumentPropertiesButton(displayCasePageNotificationBaseRequest);
        Assert.assertTrue(documentPropertiesPage.getClientLinkBeanParty().isDisplayed(), "Party Not Linked");
        Assert.assertTrue(documentPropertiesPage.getCaseLinkBeanCase().isDisplayed(), "Case Not Linked");
        documentPropertiesPage.clickOk(documentPropertiesPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        gbEmailViewerPage.selectMultipleAttachments(gbEmailViewerPageRequest);
        gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForUploadProcessToFinish(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        displayCasePageClaimBase.clickDocumentsTab(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.clickTitleFilter(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.getFilter().inputFilterText(displayCasePageClaimBaseRequest, displayCasePageClaimBaseRequest.getTitle());
        displayCasePageClaimBase.getFilter().clickApplyButton(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.selectFirstCheckbox(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.clickOnDocumentPropertiesButton(displayCasePageClaimBaseRequest);
        Assert.assertTrue(documentPropertiesPage.getClientLinkBeanParty().isDisplayed(), "Party Not Linked");
        Assert.assertTrue(documentPropertiesPage.getCaseLinkBeanCase().isDisplayed(), "Case Not Linked");
        documentPropertiesPage.clickOk(documentPropertiesPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        gbEmailViewerPage.selectMultipleAttachments(gbEmailViewerPageRequest);
        gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForUploadProcessToFinish(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        displayCasePageBenefitBase.clickDocumentsTab(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.clickTitleFilter(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.getFilter().inputFilterText(displayCasePageBenefitBaseRequest, displayCasePageBenefitBaseRequest.getTitle());
        displayCasePageBenefitBase.getFilter().clickApplyButton(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.selectFirstCheckbox(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.clickOnDocumentPropertiesButton(displayCasePageBenefitBaseRequest);
        Assert.assertTrue(documentPropertiesPage.getClientLinkBeanParty().isDisplayed(), "Party Not Linked");
        Assert.assertTrue(documentPropertiesPage.getCaseLinkBeanCase().isDisplayed(), "Case Not Linked");
        documentPropertiesPage.clickOk(documentPropertiesPageRequest);
    }

    public void verifyAutoUploadSelectedAttachments(final TestCaseContext tc) {
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        uploadSelectedAttachments(tc, gbEmailViewerPage, gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        uploadSelectedAttachments(tc, gbEmailViewerPage, gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        uploadSelectedAttachments(tc, gbEmailViewerPage, gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
    }

    public void verifyPrefixAndOrderOfAttachments(final TestCaseContext tc) {
        //open the attached document in GB email viewer
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        gbEmailViewerPage.verifyOrderOfAttachments(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        gbEmailViewerPage.verifyOrderOfAttachments(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        gbEmailViewerPage.verifyOrderOfAttachments(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
    }

    private void verifyViewMultipleAttachments(final TestCaseContext tc) {
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        gbEmailViewerPage.selectAllAttachments(gbEmailViewerPageRequest);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), false);
        Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), true);
        gbEmailViewerPage.assertMultipleAttachmentsSelectedToViewMessage(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        gbEmailViewerPage.selectAllAttachments(gbEmailViewerPageRequest);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), false);
        Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), true);
        gbEmailViewerPage.assertMultipleAttachmentsSelectedToViewMessage(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        gbEmailViewerPage.selectAllAttachments(gbEmailViewerPageRequest);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), false);
        Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), true);
        gbEmailViewerPage.assertMultipleAttachmentsSelectedToViewMessage(gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
    }

    private void verifyViewOneAttachment(final TestCaseContext tc) {
        final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
        final GBEmailViewerPage gbEmailViewerPage = tc.getPage(GBEmailViewerPage.class);
        SearchUtils.searchCase(tc, NOTIFICATION);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, NOTIFICATION);
        gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
        gbEmailViewerPage.clickViewButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForFileDownload(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
        Assert.assertTrue(gbEmailViewerPage.fileDownloaded(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest));
        gbEmailViewerPage.deleteDownloadedFiles(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, CLAIM);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, CLAIM);
        gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
        gbEmailViewerPage.clickViewButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForFileDownload(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
        Assert.assertTrue(gbEmailViewerPage.fileDownloaded(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest));
        gbEmailViewerPage.deleteDownloadedFiles(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
        SearchUtils.searchCase(tc, BENEFIT);
        DocumentUtils.navigateToGBEmailViewerPage(gbEmailViewerPageRequest, tc, BENEFIT);
        gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
        Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
        gbEmailViewerPage.clickViewButton(gbEmailViewerPageRequest);
        gbEmailViewerPage.waitForFileDownload(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
        Assert.assertTrue(gbEmailViewerPage.fileDownloaded(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest));
        gbEmailViewerPage.deleteDownloadedFiles(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
        gbEmailViewerPage.close(gbEmailViewerPageRequest);
    }

    private void uploadSelectedAttachments(final TestCaseContext tc, final GBEmailViewerPage page, final GBEmailViewerPage.GBEmailViewerPageRequest pageRequest) {
        page.selectMultipleAttachments(pageRequest);
        page.clickUploadButton(pageRequest);
        if (page.getUploadingMessage(pageRequest).isEmpty()) {
            pageRequest.log("GB Email Viewer Page Request", "Uploading document confirmation message is not displayed with in the execution time");
        } else {
            assertEquals(page.getUploadingMessage(pageRequest), pageRequest.getUploadingMessage(), "Uploading document confirmation message ");
        }
        ScreenCapture.logScreenshot("Document Upload", LogStatus.INFO);
        page.waitForUploadProcessToFinish(pageRequest);
        if (page.getUploadMessage(pageRequest).equalsIgnoreCase(pageRequest.getConfirmationMessage())) {
            pageRequest.log("GB Email Viewer Page Request", "Uploaded document confirmation message : " + page.getUploadMessage(pageRequest));
        } else {
            pageRequest.log("GB Email Viewer Page Request", "Uploaded document confirmation message : " + page.getUploadMessage(pageRequest));

            ScreenCapture.logScreenshot("GB Email Viewer Page", LogStatus.FAIL);
            junit.framework.Assert.fail();
        }
    }

    private void documentPropertiesCheck(final TestCaseContext tc) {
            final DocumentPropertiesPage documentPropertiesPage = tc.getPage(DocumentPropertiesPage.class);
            final DocumentPropertiesPage.DocumentPropertiesPageRequest documentPropertiesPageRequest = new DocumentPropertiesPage.DocumentPropertiesPageRequest(tc);
            documentPropertiesPage.isMetaDataDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getDocumentType(), documentPropertiesPageRequest.getDocumentType());
            documentPropertiesPage.isPartialTextDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getParty(), documentPropertiesPageRequest.getNotifier());
            documentPropertiesPage.isMetaDataDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getLinkedDoc(), documentPropertiesPageRequest.getLinkedDocument());
            documentPropertiesPage.isTitleTextDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getTitle(), documentPropertiesPageRequest.getTitle());
            documentPropertiesPage.isMetaDataDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getCreator(), documentPropertiesPageRequest.getFullName());
            documentPropertiesPage.isMetaDataDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getLastUpdatedBy(), documentPropertiesPageRequest.getFullName());
            documentPropertiesPage.isDateDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getCreationDate());
            documentPropertiesPage.isDateDisplayed(documentPropertiesPageRequest, documentPropertiesPage.getLastUpdatedDate());
            documentPropertiesPage.clickCancel(documentPropertiesPageRequest);
        }
}
