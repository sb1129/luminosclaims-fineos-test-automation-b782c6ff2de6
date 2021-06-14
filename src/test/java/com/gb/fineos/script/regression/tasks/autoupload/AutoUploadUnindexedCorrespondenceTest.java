package com.gb.fineos.script.regression.tasks.autoupload;

import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.sharedpages.correspondence.GBEmailViewerPage;
import com.gb.fineos.page.sharedpages.documentmanager.DocumentPropertiesPage;
import com.gb.fineos.page.sharedpages.workmanager.ActivityPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import com.gb.fineos.page.utils.SearchUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AutoUploadUnindexedCorrespondenceTest extends BaseTest {


    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.icare", "regression.nz"})
    public void uploadButtonNotDisplayedTest(final Map<String, String> testData) {
        doTest("Verify Unindexed correspondence Upload Button Test", "Auto Upload test to verify the unindexed correspondence that upload button is not displayed", testData, tc -> {
            //Verify GB Email viewer
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.uploadButtonIsDisplayed(gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);

            //close screen
            final ActivityPage activityPage = tc.getPage(ActivityPage.class);
            final ActivityPage.ActivityPageRequest activityPageRequest = new ActivityPage.ActivityPageRequest(tc);
            activityPage.clickOnCloseScreenButton(activityPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.icare", "regression.nz"})
    public void viewZeroAttachmentTest(final Map<String, String> testData) {
        doTest("Verify Unindexed correspondence Upload Button Test", "Auto Upload test to verify the unindexed correspondence that upload button is not displayed", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.clickViewButton(gbEmailViewerPageRequest);
            gbEmailViewerPage.waitForFileDownload(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
            Assert.assertTrue(gbEmailViewerPage.fileDownloaded(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest));
            gbEmailViewerPage.deleteDownloadedFiles(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);

            final ActivityPage activityPage = tc.getPage(ActivityPage.class);
            final ActivityPage.ActivityPageRequest activityPageRequest = new ActivityPage.ActivityPageRequest(tc);
            activityPage.clickOnCloseScreenButton(activityPageRequest);
        });

    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.icare", "regression.nz"})
    public void viewOneAttachmentTest(final Map<String, String> testData) {
        doTest("Verify Unindexed correspondence Upload Button Test", "Auto Upload test to verify the unindexed correspondence that upload button is not displayed", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertViewButtonIsEnabled(gbEmailViewerPageRequest);
            gbEmailViewerPage.clickViewButton(gbEmailViewerPageRequest);
            gbEmailViewerPage.waitForFileDownload(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
            Assert.assertTrue(gbEmailViewerPage.fileDownloaded(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest));
            gbEmailViewerPage.deleteDownloadedFiles(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });

    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.icare", "regression.nz"})
    public void viewMultipleAttachmentTest(final Map<String, String> testData) {
        doTest("Verify Unindexed correspondence Upload Button Test", "Auto Upload test to verify the unindexed correspondence that upload button is not displayed", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectAllAttachments(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertViewButtonIsDisabled(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertMultipleAttachmentsSelectedToViewMessage(gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });

    }

    private GBEmailViewerPage navigateToGBEmailViewerPage(final GBEmailViewerPage.GBEmailViewerPageRequest pageRequest) {
        SearchUtils.searchAndOpenTask(pageRequest.getContext());
        //Open Correspondence link to the task
        final ActivityPage activityPage = pageRequest.getContext().getPage(ActivityPage.class);
        final ActivityPage.ActivityPageRequest activityPageRequest = new ActivityPage.ActivityPageRequest(pageRequest.getContext());
        activityPage.clickOnActivityDocumentsTab(activityPageRequest);
        activityPage.openCorrespondenceDocument(activityPageRequest);

        return GBEmailViewerPage.switchTo(pageRequest);
    }
}
