package exercise19_3;

import static exercise19_3.MultiLineStringMatcher.isSameAs;
import static org.junit.Assert.assertThat;

import org.junit.*;

public class StringUtilTest {

    @Test
    public void joinLineFeedは文字列を一つ渡されると改行コードを加えて返す() throws Exception {
        String actual = StringUtil.joinLineFeed("aaa");
        String expected = "aaa" + System.lineSeparator();
        assertThat(actual, isSameAs(expected));
    }

    @Test
    public void joinLineFeedは文字列を複数渡されると改行コードで結合して返す() throws Exception {
        String[] strings = {"aaa", "", "ccc"};
        String actual = StringUtil.joinLineFeed(strings);
        String linefeed = System.lineSeparator();
        String expected = "aaa" + linefeed + "" + linefeed + "ccc" + linefeed;
        assertThat(actual, isSameAs(expected));
    }


    @Test
    public void joinLineFeedはnullを渡されると空文字と改行コードを返す() throws Exception {
        String actual = StringUtil.joinLineFeed(null);
        String expected = "" + System.lineSeparator();
        assertThat(actual, isSameAs(expected));
    }

    @Test
    public void joinLineFeedはnullを含む引数を渡されるとnullを空文字に変換して結合して返す() throws Exception {
        String[] strings = {"aaa", null, "ccc", null};
        String actual = StringUtil.joinLineFeed(strings);
        String linefeed = System.lineSeparator();
        String expected = "aaa" + linefeed + "" + linefeed + "ccc" + linefeed + "" + linefeed;
        assertThat(actual, isSameAs(expected));
    }
}
