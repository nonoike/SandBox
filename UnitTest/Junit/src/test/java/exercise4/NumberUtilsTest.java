package exercise4;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class NumberUtilsTest {

    @DataPoints
    public static final Fixture[] PARAMS = { new Fixture(0L, true),
                                             new Fixture(11L, false),
                                             new Fixture(12L, true) };

    @Theory
    public void isEvenは偶数ならtrue奇数ならfalseを返す(Fixture param) throws Exception {
        boolean actual = NumberUtils.isEven(param.val);
        boolean expected = param.expected;
        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEvenはnullを渡すと例外を投げる() {
        NumberUtils.isEven(null);
    }

    private static class Fixture {
        Long val;
        boolean expected;

        public Fixture(Long val, boolean expected) {
            this.val = val;
            this.expected = expected;
        }
    }
}
