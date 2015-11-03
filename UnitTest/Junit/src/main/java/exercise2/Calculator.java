package exercise2;

public class Calculator {
    public Long devide(Long val1, Long val2) {
        if (val1 == null || val2 == null || val2.equals(0L)) {
            throw new IllegalArgumentException("null or devide by zero.");
        }
        return val1 / val2;
    }
}
