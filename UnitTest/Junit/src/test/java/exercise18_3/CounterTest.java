package exercise18_3;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import exercise18_3.Counter;

@RunWith(Enclosed.class)
public class CounterTest {
    public static class increment初回 {
        Counter sut;

        @Before
        public void setUp() throws Exception {
            sut = new Counter();
        }

        @Test
        public void incrementは初回は1を返す() throws Exception {
            int actual = sut.increment();
            int expected = 1;
            assertThat(actual, is(expected));
        }
    }

    public static class increment1回実行後 {
        Counter sut;

        @Before
        public void setUp() throws Exception {
            sut = new Counter();
            sut.increment();
        }

        @Test
        public void incrementは2回目は2を返す() throws Exception {
            int actual = sut.increment();
            int expected = 2;
            assertThat(actual, is(expected));
        }
    }
}
