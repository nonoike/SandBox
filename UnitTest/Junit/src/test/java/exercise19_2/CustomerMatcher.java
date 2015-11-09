package exercise19_2;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomerMatcher extends BaseMatcher<Customer> {

    private Customer expected;
    private String field;
    private Object expectedValue;
    private Object actualValue;

    public static Matcher<Customer> valuesAs(Customer expected) {
        return new CustomerMatcher(expected);
    }

    private CustomerMatcher(Customer expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actualObj) {
        if (expected == null) {
            return actualObj == null;
        }
        if (!(actualObj instanceof Customer)) {
            return false;
        }

        Customer actual = (Customer) actualObj;
        if (notEquals(expected.getFirstName(), actual.getFirstName())) {
            setFields("firstName", expected.getFirstName(), actual.getFirstName());
            return false;
        }
        if (notEquals(expected.getLastName(), actual.getLastName())) {
            setFields("lastName", expected.getLastName(), actual.getLastName());
            return false;
        }
        if (notEquals(expected.getEmail(), actual.getEmail())) {
            setFields("email", expected.getEmail(), actual.getEmail());
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description desc) {
        if (expected == null || field == null) {
            desc.appendValue(expected);
        } else {
            desc.appendText(field + " is ").appendValue(expectedValue)
            .appendText(", but ").appendValue(actualValue);
        }
    }

    private boolean notEquals(Object expected, Object actual) {
        if (expected == null) {
            return actual != null;
        }
        return !expected.equals(actual);
    }

    private void setFields(String field, Object expectedValue, Object actualValue) {
        this.field = field;
        this.expectedValue = expectedValue;
        this.actualValue = actualValue;
    }
}
