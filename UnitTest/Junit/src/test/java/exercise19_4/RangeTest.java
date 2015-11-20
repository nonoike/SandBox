package exercise19_4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.*;
import org.junit.runner.*;

@RunWith(Theories.class)
public class RangeTest {

    @DataPoints
    public static final Fixture[] PARAMS = {new Fixture(-1.5, -0.5, -1.0, true),
            new Fixture(-0.5, 0.5, 0.0, true), new Fixture(0.5, 1.5, 1.0, true),
            new Fixture(-0.5, 0.5, -0.51, false), new Fixture(-0.5, 0.5, 0.51, false)};

    @Theory
    public void containsは値が範囲内にあるかを判定する(Fixture param) throws Exception {
        Range sut = new Range(param.min, param.max);
        boolean actual = sut.contains(param.decimal);
        boolean expected = param.expected;
        assertThat(actual, is(expected));
    }

    private static class Fixture {
        private Double min;
        private Double max;
        private Double decimal;
        private Boolean expected;

        public Fixture(Double min, Double max, Double decimal, Boolean expected) {
            this.min = min;
            this.max = max;
            this.decimal = decimal;
            this.expected = expected;
        }
    }
}
