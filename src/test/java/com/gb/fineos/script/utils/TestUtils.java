package com.gb.fineos.script.utils;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.validation.ValidationException;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {

    private TestUtils() {
        // do nothing
    }

    public static void validateErrorMessages(final TestCaseContext tc, final List<String> expectedValidationMessages, final List<String> actualValidationMessages, final LogStatus logStatus) {
        try {
            final List<String> trimmedExpected = expectedValidationMessages.stream().map(String::trim).collect(Collectors.toList());
            final List<String> trimmedActual = actualValidationMessages.stream().map(String::trim).collect(Collectors.toList());
            List<String> expectedNotFound = validateMissingMessages(tc, "The following expected validation messages were not found: ", trimmedExpected, trimmedActual);
            List<String> actualNotExpected = validateMissingMessages(tc, "The following actual validation messages were not expected: ", trimmedActual, trimmedExpected);

            if (!expectedNotFound.isEmpty() || !actualNotExpected.isEmpty()) {
                ScreenCapture.logScreenshot("MESSAGE VALIDATION", logStatus);
                if (logStatus.equals(LogStatus.FAIL) || logStatus.equals(LogStatus.ERROR)) {
                    throw new ValidationException("The messages were not successfully validated");
                }
            } else {
                tc.log("MESSAGE VALIDATION", "Expected and actual validations match.");
            }
        } catch (Exception e) {
            tc.error("MESSAGE VALIDATION", "There was an issue when attempting to validate the error messages");
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private static List<String> validateMissingMessages(final TestCaseContext tc, final String errorMessageOnFail, final List<String> expectedMessages, final List<String> actualMessages) {
        List<String> expectedMessagesNotFound = new ArrayList<>();
        if (!expectedMessages.isEmpty()) {
            expectedMessagesNotFound = ListUtils.subtract(expectedMessages, actualMessages);

            if (!expectedMessagesNotFound.isEmpty()) {
                StringBuilder expectedValidations = new StringBuilder();
                expectedValidations.append(errorMessageOnFail);

                for (String message : expectedMessagesNotFound) {
                    expectedValidations.append("'").append(message).append("', ");
                }
                tc.log("MESSAGE VALIDATION", expectedValidations.toString());
            }
        } else {
            tc.warning("MESSAGE VALIDATION", "No expected messages were provided", true);
        }
        return expectedMessagesNotFound;
    }

    public static void compareLabels(final TestCaseContext tc, final List<String> expectedLabels, final List<String> actualLabels, final LogStatus logStatus) {
        try {
            final List<String> trimmedExpected = expectedLabels.stream().map(String::trim).collect(Collectors.toList());
            final List<String> trimmedActual = actualLabels.stream().map(String::trim).collect(Collectors.toList());
            List<String> expectedNotFound = verifyMissingLabels(tc, "The following expected field labels  were not found: ", trimmedExpected, trimmedActual);
            List<String> actualNotExpected = verifyMissingLabels(tc, "The following actual field labels were not expected: ", trimmedActual, trimmedExpected);

            if (!expectedNotFound.isEmpty() || !actualNotExpected.isEmpty()) {
                ScreenCapture.logScreenshot("FIELD LABEL VERIFICATION", logStatus);
                if (logStatus.equals(LogStatus.FAIL) || logStatus.equals(LogStatus.ERROR)) {
                    throw new ValidationException("The labels were not successfully verified");
                }
            } else {
                tc.log("FIELD LABEL VERIFICATION", "Expected and actual labels are matched.");
            }
        } catch (Exception e) {
            tc.error("FIELD LABEL VERIFICATION", "There was an issue when attempting to verify labels");
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private static List<String> verifyMissingLabels(final TestCaseContext tc, final String errorMessageOnFail, final List<String> expectedLabels, final List<String> actualLabels) {
        List<String> expectedLabelsNotFound = new ArrayList<>();
        if (!expectedLabels.isEmpty()) {
            expectedLabelsNotFound = ListUtils.subtract(expectedLabels, actualLabels);

            if (!expectedLabelsNotFound.isEmpty()) {
                StringBuilder expectedValidations = new StringBuilder();
                expectedValidations.append(errorMessageOnFail);

                for (String message : expectedLabelsNotFound) {
                    expectedValidations.append("'").append(message).append("', ");
                }
                tc.log("FIELD LABEL VERIFICATION", expectedValidations.toString());
            }
        } else {
            tc.warning("FIELD LABEL VERIFICATION", "No expected messages were provided", true);
        }
        return expectedLabelsNotFound;
    }




}
