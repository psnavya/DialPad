package com.test.dto;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.test.dto.utilities.ExcelHandler;
import com.test.dto.utilities.ReportHandler;
import com.test.dto.utilities.TestCaseDescription;
import org.assertj.core.api.SoftAssertions;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TelephoneDialPadTest extends ReportHandler {
    static ExcelHandler excelHandler = new ExcelHandler();
    static SoftAssertions softAssertions;
    static ExtentReports reporter = new ExtentReports();

    private void verifyCalculationMethods(String digit, LinkedList<String> expected) {

        Collections.sort(expected);
        ReportHandler.logInfoDetails("Expected value: " + expected);
        LinkedList<String> combinationList = new LinkedList<>();
        TelephoneDialPad.calculateAlphabetCombinations(combinationList, "", digit);
        Collections.sort(combinationList);
        ReportHandler.logInfoDetails("Values returned by code: " + combinationList);
        Collections.sort(TelephoneDialPad.retrieveCombinations(digit));
        try {
           // Assert.assertEquals(expected, combinationList);
            //Assert.assertEquals(expected, TelephoneDialPad.retrieveCombinations(digit));
            if(!expected.equals(combinationList))
                ReportHandler.logFailDetails("Expected and actual value not the same");
            else {
                softAssertions.assertThat(expected).isEqualTo(combinationList);
                softAssertions.assertThat(expected).isEqualTo(TelephoneDialPad.retrieveCombinations(digit));
                ReportHandler.logPassDetails("Single digit scenario works as expected.");
            }
        } catch (AssertionError e) {
            ReportHandler.logFailDetails("Assertion error: expected and actual value not the same");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            ReportHandler.logFailDetails("Only numbers to be entered.");
            e.printStackTrace();
        }


    }

    @BeforeClass
    public static void setUp() {
        System.out.println("-------------------------------------------");
        String reportPath = "./reports/dialPadReports.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        reporter.attachReporter(htmlReporter);
        softAssertions = new SoftAssertions();
        reporter.setSystemInfo("Author", "Navya PS");
        reporter.setSystemInfo("Project", "TelephoneDialPad");
        reporter.setSystemInfo("Type", "Unit Testing - JUnit Framework");
        File file = new File("./src/test/java/com/test/dto/reports/extent_config.xml");
        htmlReporter.loadXMLConfig(file);


    }

    @Test
    public void singleDigit() throws IOException {
        System.out.println("----------singleDigit---------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Single Digit Scenario", "Values like 0/1/2 considered from excel");

        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO1.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO1.getTestCaseDescription()));//0,1,2
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);
        LinkedList<String> expectedCombinationList;

        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO1.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO1.getTestCaseDescription()));

                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------singleDigit---------COMPLETE------------------------");

    }

    @Test
    public void multipleDigits() throws IOException {
        System.out.println("----------multipleDigits---------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Multiple Digits Scenaraio", "Values like 01/1256/234 considered from excel");
        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO2.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO2.getTestCaseDescription()));
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);
        LinkedList<String> expectedCombinationList;
        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO2.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO2.getTestCaseDescription()));
                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------multipleDigits---------COMPLETE------------------------");
    }


    @Test
    public void singleRepeatedDigits() throws IOException {
        System.out.println("---------singleRepeatedDigits----------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Single Repeated Digits Scenario", "Values like 011/12256/2342 considered from excel");

        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO3.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO3.getTestCaseDescription()));
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);
        LinkedList<String> expectedCombinationList;

        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO3.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO3.getTestCaseDescription()));
                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------singleRepeatedDigits---------COMPLETE------------------------");
    }

    @Test
    public void multipleRepeatedDigits() throws IOException {
        System.out.println("----------multipleRepeatedDigits---------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Multiple Repeated Digits Scenario", "Values like 01122/152256/233442 considered from excel");
        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO4.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO4.getTestCaseDescription()));
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);
        LinkedList<String> expectedCombinationList;

        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO4.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO4.getTestCaseDescription()));

                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------multipleRepeatedDigits---------COMPLETE------------------------");

    }

    @Test
    public void invalidInput() throws IOException  //Space or special charecters
    {
        System.out.println("----------invalidInput---------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Special charecters/ Space not considered", "Values like 0 122/1*2256/-90 considered from excel");

        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO5.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO5.getTestCaseDescription()));
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);
        LinkedList<String> expectedCombinationList;

        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO5.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO5.getTestCaseDescription()));

                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------invalidInput---------COMPLETE------------------------");
    }


    @Test
    public void containsZeroDigits() throws IOException {
        System.out.println("----------containsZeroDigits---------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Contains Zero Scenario", "Values like 040/008/099 considered from excel");
        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO6.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO6.getTestCaseDescription()));
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);
        LinkedList<String> expectedCombinationList;

        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO6.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO6.getTestCaseDescription()));
                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------containsZeroDigits---------COMPLETE------------------------");
    }


    @Test
    public void containsOneDigits() throws IOException {
        System.out.println("----------containsOneDigits---------IN PROGRESS------------------------");
        ReportHandler.scenarioDetails(reporter, "Contains One Scenario", "Values like 141/118/199 considered from excel");

        List<String> inputsList;
        inputsList = excelHandler.getInputList(TestCaseDescription.SCENARIO7.getTestCaseDescription(), excelHandler.getRowRange(TestCaseDescription.SCENARIO7.getTestCaseDescription()));
        ReportHandler.logInfoDetails("Input list from excel" + inputsList);

        LinkedList<String> expectedCombinationList;

        for (String inputValue : inputsList) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(inputValue);
            if (inputValue.isEmpty() || inputValue.contains(" ") || matcher.find()) {
                ReportHandler.logFailDetails("Space and special characters are invalid entries");
                throw new NumberFormatException(" contains special character/ space");
            }
            else {
                expectedCombinationList = excelHandler.getExpectedValues(TestCaseDescription.SCENARIO7.getTestCaseDescription(), inputValue, excelHandler.getRowRange(TestCaseDescription.SCENARIO7.getTestCaseDescription()));

                try {
                    verifyCalculationMethods(inputValue, expectedCombinationList);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    ReportHandler.logErrorDetails("Array index out of bound. (An extra element to be present)");
                }

            }
        }
        System.out.println("----------containsOneDigits---------COMPLETE------------------------");
    }


    @AfterClass
    public static void tearDown() {

        reporter.flush();
        try {
            softAssertions.assertAll();
        } catch (AssertionError e) {
            e.printStackTrace();
            ReportHandler.logWarningDetails("assertAll method error.");
        }
        System.out.println("--------------------Execution ends-----------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("---------Have a look at the report in src/test/java/com/test/dto/reports/dialPadReports.html------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }
}
