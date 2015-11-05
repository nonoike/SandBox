package exercise19_1;

import static exercise19_1.ListMatcher.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FizzBuzzTest {
    private FizzBuzz sut;

    @Before
    public void setUp() throws Exception {
        sut = new FizzBuzz();
    }

    @Test
    public void toFizzBuzzListはリストを返す() throws Exception {
        List<String> actual = sut.toFizzBuzzList(15L);
        List<String> expected = FizzBuzzTestHelper.FizzBuzzListの生成_15();
        assertThat(actual, is(expected));
    }

    @Test
    public void toFizzBuzzListはリストを返す_matcher() throws Exception {
        List<String> actual = sut.toFizzBuzzList(15L);
        List<String> expected = FizzBuzzTestHelper.FizzBuzzListの生成_15();
        assertThat(actual, equalsOrderAndItems(expected.toArray()));
    }
}
