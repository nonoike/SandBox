package exercise18_2;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import exercise18_2.Calculator;

@RunWith(Enclosed.class)
public class CalculatorTest {
    @RunWith(Theories.class)
    public static class 正常系 {
        private Calculator sut;

        @DataPoints
        public static final Fixture[] PARAMS = { new Fixture(10L, 5L, 2L),
                                                 new Fixture(10L, 3L, 3L),
                                                 new Fixture(0L, 1L, 0L) };

        @Before
        public void setUp() throws Exception {
            sut = new Calculator();
        }

        @Theory
        public void divideは小数点以下を切り捨てて商を返す(Fixture param) throws Exception {
            Long actual = sut.divide(param.val1, param.val2);
            Long expected = param.expected;
            String msg = "when val1 = " + param.val1 + ", val2 = " + param.val2;
            assertThat(msg, actual, is(expected));
        }
    }

    @RunWith(Theories.class)
    public static class 異常系 {
        private Calculator sut;

        @Rule
        public ExpectedException expectedException = ExpectedException.none();
        @DataPoints
        public static final Long[][] PARAMS = { { null, 1L }, { 1L, null }, { 1L, 0L } };

        @Before
        public void setUp() throws Exception {
            sut = new Calculator();
        }

        @Theory
        public void divideはnullを渡すか0で除算すると例外を返す(Long[] param) throws Exception {
            expectedException.expect(IllegalArgumentException.class);
            expectedException.expectMessage("null or divide by zero.");
            sut.divide(param[0], param[1]);
        }
    }

    private static class Fixture {
        Long val1;
        Long val2;
        Long expected;

        public Fixture(Long val1, Long val2, Long expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }
    }
}
