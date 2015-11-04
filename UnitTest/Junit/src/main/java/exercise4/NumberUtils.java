package exercise4;

public class NumberUtils {
    public static boolean isEven(Long val) {
        if (val == null) {
            throw new IllegalArgumentException("args is null.");
        }
        return val % 2 == 0;
    }
}
