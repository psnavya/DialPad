package com.test.dto.utilities;

public enum TestCaseDescription {
    SCENARIO1("SingleDigit"),
    SCENARIO2("MultipleUniqueDigits"),
    SCENARIO3("SingleRepeatedDigits"),
    SCENARIO4("MultipleRepeatedDigits"),
    SCENARIO5("InvalidInputs"),
    SCENARIO6("ContainsZero"),
    SCENARIO7("ContainsOne");

    TestCaseDescription(final String scenario) {
        this.scenario = scenario;
    }

    private String scenario;

    public String getTestCaseDescription() {
        return scenario;
    }
}
