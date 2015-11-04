package exercise18_1;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import exercise18_1.Exercise1;

@RunWith(Theories.class)
public class Exercise1Test {
    @DataPoints
    public static final Fixture[] PARAMS = { new Fixture(null, ""),
                                             new Fixture("", ""),
                                             new Fixture("abcd1あ e", "abcd1あ e"),
                                             new Fixture("aBcD1あ e", "a_bc_d1あ e"),
                                             new Fixture("ABCD1あ e", "_a_b_c_d1あ e"),
                                             new Fixture("_ABCD1あ e", "__a_b_c_d1あ e") };

    @Theory
    public void toSnakeCaseはスネークケースに変換する(Fixture param) throws Exception {
        String actual = Exercise1.toSnakeCase(param.str);
        String expected = param.expected;
        String msg = "when str = " + param.str;
        assertThat(msg, actual, is(expected));
    }

    private static class Fixture {
        String str;
        String expected;

        public Fixture(String str, String expected) {
            this.str = str;
            this.expected = expected;
        }
    }
}
