package com.test.dto.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportHandler {
    static ExtentTest testStep;

    public static void scenarioDetails(ExtentReports reporter, String scenario, String details) {
        ExtentTest testCase = reporter.createTest(scenario);
        testStep = testCase.createNode(scenario, details);
    }

    public static void logPassDetails(String details) {
        testStep.log(Status.PASS, details);
    }

    public static void logFailDetails(String details) {
        testStep.log(Status.FAIL, details);
    }

    public static void logErrorDetails(String details) {
        testStep.log(Status.ERROR, details);
    }

    public static void logStepDetails(String details) {
        testStep.log(Status.INFO, details);
    }

    public static void logWarningDetails(String details) {
        testStep.log(Status.WARNING, details);
    }

    protected static void logInfoDetails(String details) {
        testStep.log(Status.INFO, details);
    }
}
