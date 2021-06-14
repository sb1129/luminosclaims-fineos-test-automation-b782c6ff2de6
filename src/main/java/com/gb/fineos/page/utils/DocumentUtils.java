package com.gb.fineos.page.utils;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.sharedpages.correspondence.GBEmailViewerPage;
import com.gb.fineos.page.widgets.documentmanager.ExtraDataPage;
import com.gb.fineos.page.widgets.documentmanager.doctype.DocumentTypeManagerSearchPage;
import com.gb.fineos.page.widgets.documentmanager.fileupload.UploadDocumentPage;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;

public final class DocumentUtils {
    private static final int RETRY_LIMIT = 3;

    private DocumentUtils() {
        // Do nothing.
    }

    public static GBEmailViewerPage navigateToGBEmailViewerPage(final GBEmailViewerPage.GBEmailViewerPageRequest pageRequest, final TestCaseContext tc, final CaseType caseType) {
        final DisplayCasePageBenefitBase displayCasePageBenefitBase = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(tc);
        final DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest(tc);
        final DisplayCasePageNotificationBase displayCasePageNotificationBase = tc.getPage(DisplayCasePageNotificationBase.class);
        final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = new DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest(tc);
        switch (caseType) {
            case BENEFIT:
                displayCasePageBenefitBase.clickDocumentsTab(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.clickOnDocumentAddButton(displayCasePageBenefitBaseRequest);
                uploadDocument(tc);
                displayCasePageBenefitBase.clickOutboundCorrespondenceDocumentLinkToOpen(displayCasePageBenefitBaseRequest);
                return GBEmailViewerPage.switchTo(pageRequest);
            case CLAIM:
                displayCasePageClaimBase.clickDocumentsTab(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.clickOnDocumentAddButton(displayCasePageClaimBaseRequest);
                uploadDocument(tc);
                displayCasePageClaimBase.clickOutboundCorrespondenceDocumentLinkToOpen(displayCasePageClaimBaseRequest);
                return GBEmailViewerPage.switchTo(pageRequest);
            case NOTIFICATION:
                displayCasePageNotificationBase.clickDocumentsTab(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.clickOnDocumentAddButton(displayCasePageNotificationBaseRequest);
                uploadDocument(tc);
                displayCasePageNotificationBase.clickOutboundCorrespondenceDocumentLinkToOpen(displayCasePageNotificationBaseRequest);
                return GBEmailViewerPage.switchTo(pageRequest);
            default:
                throw new AssertionError("This Case Type do not exists: " + caseType);
        }
    }

    public static void uploadDocument(final TestCaseContext tc) {
        final ExtraDataPage extraDataPage = tc.getPage(ExtraDataPage.class);
        final ExtraDataPage.ExtraDataPageRequest extraDataPageRequest = new ExtraDataPage.ExtraDataPageRequest(tc);
        final DocumentTypeManagerSearchPage documentTypeManagerSearchPage = tc.getPage(DocumentTypeManagerSearchPage.class);
        final DocumentTypeManagerSearchPage.DocumentTypeManagerSearchPageRequest documentTypeManagerSearchPageRequest = new DocumentTypeManagerSearchPage.DocumentTypeManagerSearchPageRequest(tc);
        final UploadDocumentPage uploadDocumentPage = tc.getPage(UploadDocumentPage.class);
        final UploadDocumentPage.UploadDocumentPageRequest uploadDocumentPageRequest = new UploadDocumentPage.UploadDocumentPageRequest(tc);
        int retry = 0; //retrying the file upload 3 times.
        boolean success = false; //capturing result outcome True/ False.
        documentTypeManagerSearchPage.clickOnSearchTab(documentTypeManagerSearchPageRequest);
        documentTypeManagerSearchPage.selectCategoryAsDefault(documentTypeManagerSearchPageRequest);
        documentTypeManagerSearchPage.enterBusinessTypeName(documentTypeManagerSearchPageRequest);
        documentTypeManagerSearchPage.clickOnSearchButton(documentTypeManagerSearchPageRequest);
        documentTypeManagerSearchPage.clickOnOutboundCorrespondence(documentTypeManagerSearchPageRequest);
        documentTypeManagerSearchPage.clickOnOkButton(documentTypeManagerSearchPageRequest);
        while (!success && retry < RETRY_LIMIT) {
            uploadDocumentPage.uploadDocument(uploadDocumentPageRequest);
            uploadDocumentPage.selectPrivacyTag(uploadDocumentPageRequest);
            ScreenCapture.logScreenshot("Document Upload", LogStatus.INFO);
            uploadDocumentPage.clickOnOkButton(uploadDocumentPageRequest);
            extraDataPage.clickOKOrClose(extraDataPageRequest);
            if (uploadDocumentPage.isValidationMessageExists(uploadDocumentPageRequest, uploadDocumentPage.getWarningMessage())) {
                uploadDocumentPage.clickCloseWarningMessage(uploadDocumentPageRequest);
            } else {
                success = true;
                break;
            }
            retry++;
        }
        if (success) {
            uploadDocumentPageRequest.log("UPLOAD SUCCESS", "file uploaded");
        } else {
            uploadDocumentPageRequest.log("UPLOAD FAIL", "Upload Failed");
        }
    }
}
