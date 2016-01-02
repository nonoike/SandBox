package jp.co.trump.blackjack;

import static java.util.Arrays.*;
import static jp.co.trump.Rank.*;
import static jp.co.trump.Suit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import jp.co.trump.Card;
import lombok.Value;

/**
 * {@link Player}のテストクラス
 *
 * @author user
 */
@SuppressWarnings("javadoc")
@RunWith(Enclosed.class)
public class PlayerTest {
    @RunWith(Theories.class)
    public static class 数値検証 {
        Player sut;
        @DataPoints
        public static final Fixture[] PARAMS =
                {new Fixture(Arrays.asList(new Card(HEART, TWO), new Card(SPADE, THREE)), 5),
                        new Fixture(asList(new Card(HEART, TWO), new Card(SPADE, ACE)), 13),
                        new Fixture(asList(new Card(HEART, ACE), new Card(SPADE, KING)), 21),
                        new Fixture(asList(new Card(HEART, ACE), new Card(SPADE, ACE)), 12),
                        new Fixture(asList(new Card(HEART, TWO), new Card(SPADE, TWO), new Card(CLUB, TWO)),
                                6),
                new Fixture(asList(new Card(HEART, TWO), new Card(SPADE, ACE), new Card(CLUB, FOUR)), 17),
                new Fixture(asList(new Card(HEART, ACE), new Card(SPADE, KING), new Card(CLUB, ACE)), 12),
                new Fixture(asList(new Card(HEART, ACE), new Card(SPADE, ACE), new Card(CLUB, ACE)), 13)};

        @Theory
        public void calcScoreで所持カードの点数を計算する(Fixture param) throws Exception {
            Field field = sut.getClass().getDeclaredField("cards");
            field.setAccessible(true);
            field.set(sut, param.getCards());
            int actual = sut.calcScore();
            int expected = param.getExpected();
            String msg =
                    "when " + param.getCards() + " expected is " + expected + ", but was " + actual + ".";
            assertThat(msg, actual, is(expected));
        }

        @Before
        public void setUp() throws Exception {
            sut = new Player();
        }

        @Value
        private static class Fixture {
            private List<Card> cards;
            private int expected;
        }
    }

    @RunWith(Theories.class)
    public static class 文字列検証 {
        Player sut;
        @DataPoints
        public static final Fixture[] PARAMS =
                {new Fixture(Arrays.asList(new Card(HEART, TWO), new Card(SPADE, THREE)), true, "TWO, THREE"),
                        new Fixture(asList(new Card(HEART, TWO), new Card(SPADE, ACE)), false, "TWO, ???"),
                        new Fixture(asList(new Card(HEART, ACE), new Card(SPADE, KING), new Card(CLUB, ACE)),
                                true, "ACE, KING, ACE"),
                new Fixture(asList(new Card(HEART, ACE), new Card(SPADE, ACE), new Card(CLUB, ACE)), false,
                        "ACE, ???, ???")};

        @Theory
        public void calcScoreで所持カードの点数を計算する(Fixture param) throws Exception {
            Field field = sut.getClass().getDeclaredField("cards");
            field.setAccessible(true);
            field.set(sut, param.getCards());

            String actual = sut.joinRanks(param.isFullOpen());
            String expected = param.getExpected();
            String msg =
                    "when " + param.getCards() + " expected is " + expected + ", but was " + actual + ".";
            assertThat(msg, actual, is(expected));
        }

        @Before
        public void setUp() throws Exception {
            sut = new Player();
        }

        @Value
        private static class Fixture {
            private List<Card> cards;
            private boolean isFullOpen;
            private String expected;
        }
    }
}
