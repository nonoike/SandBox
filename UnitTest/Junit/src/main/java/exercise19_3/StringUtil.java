package exercise19_3;

import java.util.*;
import java.util.stream.*;

public class StringUtil {
    public static String joinLineFeed(String... strings) {
        List<String> list = (strings == null)
                ? Arrays.asList("")
                : Stream.of(strings).map(e -> (e == null) ? "" : e).collect(Collectors.toList());
        return String.join(System.lineSeparator(), list) + System.lineSeparator();
    }
}
