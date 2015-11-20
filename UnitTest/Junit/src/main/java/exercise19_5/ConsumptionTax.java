package exercise19_5;

public class ConsumptionTax {
    private final int taxRate;

    public ConsumptionTax(int taxRate) {
        this.taxRate = taxRate;
    }

    public Long calcTaxIncludedPrice(Long exPrice) {
        long taxPrice = (long) Math.floor(exPrice * ((double) taxRate / 100));
        return exPrice + taxPrice;
    }
}
