package com.test.example;

public class CheckingParamater {
    private String waitUntilXPath;
    private String actualXPath;
    private String expected;

    public String getWaitUntilXPath() {
        return waitUntilXPath;
    }

    public String getActualXPath() {
        return actualXPath;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public CheckingParamater(String waitUntilXPath, String actualXPath, String expected) {
        this.waitUntilXPath = waitUntilXPath;
        this.actualXPath = actualXPath;
        this.expected = expected;
    }
    // Builder Class
    public static class Builder {
        private String waitUntilXPath;
        private String actualXPath;
        private String expected;

        public Builder setWaitUntilXPath(String waitUntilXPath) {
            this.waitUntilXPath = waitUntilXPath;
            return this;
        }

        public Builder setActualXPath(String actualXPath) {
            this.actualXPath = actualXPath;
            return this;
        }

        public Builder setExpected(String expected) {
            this.expected = expected;
            return this;
        }

        public CheckingParamater build() {
            return new CheckingParamater(waitUntilXPath, actualXPath, expected);
        }
    }
}
