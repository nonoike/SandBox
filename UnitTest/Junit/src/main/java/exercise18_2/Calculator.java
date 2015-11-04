package exercise18_2;

public class Calculator {
    public Long divide(Long val1, Long val2) {
        if (val1 == null || val2 == null || val2.equals(0L)) {
            throw new IllegalArgumentException("null or divide by zero.");
        }
        return val1 / val2;
    }
}
