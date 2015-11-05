package exercise19_1;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ListMatcher extends BaseMatcher<List<?>> {

    private Object[] expected;
    private List<?> actual;
    private Integer index;

    @SafeVarargs
    public static <T> Matcher<List<?>> equalsOrderAndItems(T... expected) {
        return new ListMatcher(expected);
    }

    private ListMatcher(Object[] expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actualObj) {
        if (expected == null) {
            return actualObj == null;
        }
        if (!(actualObj instanceof List<?>)) {
            return false;
        }

        actual = (List<?>) actualObj;
        if (expected.length != actual.size()) {
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            if (notEquals(expected[i], actual.get(i))) {
                index = i;
                return false;
            }
        }

        return true;
    }

    @Override
    public void describeTo(Description desc) {
        if (actual == null) {
            desc.appendValue(expected);
        } else if (index == null) {
            desc.appendText("size is ").appendValue(expected.length).appendText(", but ").appendValue(actual.size());
        } else {
            desc.appendText("index:").appendValue(index).appendText(" is ").appendValue(expected[index]).appendText(", but ").appendValue(actual.get(index));
        }
    }

    private boolean notEquals(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }
        return !expected.equals(actual);
    }
}
