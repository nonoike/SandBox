package exercise19_4;

public class Range {
    private Double min;
    private Double max;

    public Range(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public boolean contains(Double decimal) {
        return min <= decimal && decimal <= max;
    }
}
