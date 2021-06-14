package com.gb.fineos.script.regression.notification.validation;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase;
import com.gb.fineos.page.utils.CaseUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.RandomData;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import com.gb.fineos.script.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CreateNotificationIntakeValidationTest extends BaseTest {

    private static final String NOTIFICATION_INTAKE_VALIDATION_TEST = "Notification Intake Validation Test";
    private static int tenDays = 10;
    private static int fiveDays = 5;
    private static int current = 0;
    private static int past = -1;


    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.notification.icare")
    public void incidentDateValidationTest(final Map<String, String> testData) {
        doTest(NOTIFICATION_INTAKE_VALIDATION_TEST, "Verify All and Incident Date Fields", testData, tc ->
            FineosDSL.getInstance(tc).getNotification().withImplementation(this::incidentDateValidationsOnNotificationIntake).test()
        );
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.notification.icare")
    public void notifierDateValidationTest(final Map<String, String> testData) {
        doTest(NOTIFICATION_INTAKE_VALIDATION_TEST, "Verify Notifier Date Fields", testData, tc ->
            FineosDSL.getInstance(tc).getNotification().withImplementation(this::notifierDateValidationsOnNotificationIntake).test()
        );
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.notification.icare")
    public void gbDateValidationTest(final Map<String, String> testData) {
        doTest(NOTIFICATION_INTAKE_VALIDATION_TEST, "Verify GB Date Fields", testData, tc ->
            FineosDSL.getInstance(tc).getNotification().withImplementation(this::gbDateValidationsOnNotificationIntake).test()
        );
    }

    public void incidentDateValidationsOnNotificationIntake(final TestCaseContext tc) {
        SearchUtils.searchParty(tc);
        CaseUtils.navigateToCreateNotificationIntake(tc);
        final ProcessStatusPageBase notificationIntakeProcessPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);
        //Test 1 blank form validation errors check
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        final List<String> expectedValidationMessagesAllFields = notificationIntakeProcessPageRequest.getExpectedValidationMessages("AllFieldValidationMessage", ";;");
        final List<String> actualValidationMessagesAllFields = notificationIntakeProcessPage.getValidationMessages(notificationIntakeProcessPageRequest,notificationIntakeProcessPage.getErrorValidationText(notificationIntakeProcessPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessagesAllFields, actualValidationMessagesAllFields, LogStatus.ERROR);
        notificationIntakeProcessPage.closeValidationErrorMessage(notificationIntakeProcessPageRequest);
        //Test 2 If Incident date is in future
        CaseUtils.completeCreateNotificationForm(tc, false);
        notificationIntakeProcessPage.inputIncidentDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(tenDays));
        notificationIntakeProcessPage.inputNotifierDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(current));
        notificationIntakeProcessPage.inputGBDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(current));
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        final List<String> expectedValidationMessagesIncidentDate = notificationIntakeProcessPageRequest.getExpectedValidationMessages("IncidentDateValidationMessage", ";;");
        final List<String> actualValidationMessagesIncidentDate = notificationIntakeProcessPage.getValidationMessages(notificationIntakeProcessPageRequest,notificationIntakeProcessPage.getErrorValidationText(notificationIntakeProcessPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessagesIncidentDate, actualValidationMessagesIncidentDate, LogStatus.ERROR);
        notificationIntakeProcessPage.closeValidationErrorMessage(notificationIntakeProcessPageRequest);
        //After test complete the form with valid details and submit notification
        CaseUtils.completeCreateNotificationForm(tc, false);
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        notificationIntakeProcessPage.verifyCaseNumber(notificationIntakeProcessPageRequest);
    }

    public void notifierDateValidationsOnNotificationIntake(final TestCaseContext tc) {
        SearchUtils.searchParty(tc);
        CaseUtils.navigateToCreateNotificationIntake(tc);
        final ProcessStatusPageBase notificationIntakeProcessPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        CaseUtils.completeCreateNotificationForm(tc, false);
        //Test 3 Notifier Future date
        notificationIntakeProcessPage.inputIncidentDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(past));
        notificationIntakeProcessPage.inputNotifierDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(tenDays));
        notificationIntakeProcessPage.inputGBDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(current));
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        final List<String> expectedValidationMessagesNotifierDate = notificationIntakeProcessPageRequest.getExpectedValidationMessages("NotifierFutureDateValidationMessage", ";;");
        final List<String> actualValidationMessagesNotifierDate = notificationIntakeProcessPage.getValidationMessages(notificationIntakeProcessPageRequest,notificationIntakeProcessPage.getErrorValidationText(notificationIntakeProcessPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessagesNotifierDate, actualValidationMessagesNotifierDate, LogStatus.ERROR);
        notificationIntakeProcessPage.closeValidationErrorMessage(notificationIntakeProcessPageRequest);
        //Test 4 Notifier date before incident date
        notificationIntakeProcessPage.inputIncidentDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(past));
        notificationIntakeProcessPage.inputNotifierDate(notificationIntakeProcessPageRequest,RandomData.getCalculatedDateAndTime(-tenDays));
        notificationIntakeProcessPage.inputGBDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(current));
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        final List<String> expectedValidationMessagesNotifierPastDate = notificationIntakeProcessPageRequest.getExpectedValidationMessages("NotifierDateBeforeIncidentDateValidationMessage", ";;");
        final List<String> actualValidationMessagesNotifierPastDate = notificationIntakeProcessPage.getValidationMessages(notificationIntakeProcessPageRequest,notificationIntakeProcessPage.getErrorValidationText(notificationIntakeProcessPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessagesNotifierPastDate, actualValidationMessagesNotifierPastDate, LogStatus.ERROR);
        notificationIntakeProcessPage.closeValidationErrorMessage(notificationIntakeProcessPageRequest);

        //After test complete the form and submit notification
        CaseUtils.completeCreateNotificationForm(tc, false);
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        notificationIntakeProcessPage.verifyCaseNumber(notificationIntakeProcessPageRequest);
    }

    public void gbDateValidationsOnNotificationIntake(final TestCaseContext tc) {
        SearchUtils.searchParty(tc);
        CaseUtils.navigateToCreateNotificationIntake(tc);
        final ProcessStatusPageBase notificationIntakeProcessPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        CaseUtils.completeCreateNotificationForm(tc, false);
        //Test 5 GB Past date
        notificationIntakeProcessPage.inputIncidentDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(-fiveDays));
        notificationIntakeProcessPage.inputNotifierDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(current));
        notificationIntakeProcessPage.inputGBDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(-tenDays));
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        final List<String> expectedValidationMessagesGBPastDate = notificationIntakeProcessPageRequest.getExpectedValidationMessages("GBPastDateValidationMessage", ";;");
        final List<String> actualValidationMessagesGBPastDate = notificationIntakeProcessPage.getValidationMessages(notificationIntakeProcessPageRequest,notificationIntakeProcessPage.getErrorValidationText(notificationIntakeProcessPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessagesGBPastDate, actualValidationMessagesGBPastDate, LogStatus.ERROR);
        notificationIntakeProcessPage.closeValidationErrorMessage(notificationIntakeProcessPageRequest);
        //Test 6 GB Future date
        notificationIntakeProcessPage.inputIncidentDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(past));
        notificationIntakeProcessPage.inputNotifierDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(current));
        notificationIntakeProcessPage.inputGBDate(notificationIntakeProcessPageRequest, RandomData.getCalculatedDateAndTime(tenDays));
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        final List<String> expectedValidationMessagesGBFutureDate = notificationIntakeProcessPageRequest.getExpectedValidationMessages("GBFutureDateValidationMessage", ";;");
        final List<String> actualValidationMessagesGBFutureDate = notificationIntakeProcessPage.getValidationMessages(notificationIntakeProcessPageRequest,notificationIntakeProcessPage.getErrorValidationText(notificationIntakeProcessPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessagesGBFutureDate, actualValidationMessagesGBFutureDate, LogStatus.ERROR);
        notificationIntakeProcessPage.closeValidationErrorMessage(notificationIntakeProcessPageRequest);

        //After test complete the form and submit notification
        CaseUtils.completeCreateNotificationForm(tc, false);
        notificationIntakeProcessPage.doSubmitNotificationIntakeForm(notificationIntakeProcessPageRequest);
        notificationIntakeProcessPage.verifyCaseNumber(notificationIntakeProcessPageRequest);
    }

}
