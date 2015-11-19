package exercise19_3;

import org.hamcrest.*;

public class MultiLineStringMatcher extends BaseMatcher<String> {

    private static final String LINEFEED = System.lineSeparator();

    private String expected;
    private String field;
    private String expectedValue;
    private String actualValue;

    public static Matcher<String> isSameAs(String expected) {
        return new MultiLineStringMatcher(expected);
    }

    private MultiLineStringMatcher(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actualObj) {
        if (expected == null) {
            return actualObj == null;
        }
        if (!(actualObj instanceof String)) {
            return false;
        }

        String actual = (String) actualObj;
        if (notEquals(expected, actual)) {
            setFields(expected, actual);
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description desc) {
        if (expected == null || field == null) {
            desc.appendValue(expected);
        } else {
            desc.appendText("is ").appendValue(expectedValue).appendText(", but ")
                    .appendValue(actualValue).appendText(" is " + field);
        }
    }

    private boolean notEquals(Object expected, Object actual) {
        if (expected == null) {
            return actual != null;
        }
        return !expected.equals(actual);
    }

    private void setFields(String expected, String actual) {
        final String[] expecteds = expected.split(LINEFEED, -1);
        final String[] actuals = actual.split(LINEFEED, -1);
        if (expecteds.length != actuals.length) {
            field = "lineNumber";
            expectedValue = String.valueOf(expecteds.length);
            actualValue = String.valueOf(actuals.length);
            return;
        }

        for (int i = 0; i < expecteds.length; i++) {
            if (notEquals(expecteds[i], actuals[i])) {
                field = "line: " + (i + 1);
                expectedValue = expecteds[i];
                actualValue = actuals[i];
                return;
            }
        }
        field = "cause unknown";
        expectedValue = expected;
        actualValue = actual;
    }
}
