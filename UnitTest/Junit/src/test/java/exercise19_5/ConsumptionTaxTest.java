package exercise19_5;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.*;
import org.junit.runner.*;

@RunWith(Theories.class)
public class ConsumptionTaxTest {
    private ConsumptionTax sut;

    @DataPoints
    public static final Fixture[] PARAMS =
            {new Fixture(5, 0L, 0L), new Fixture(10, 100L, 110L), new Fixture(8, 110L, 118L)};


    @Theory
    public void calcTaxIncludedPriceは税込み金額を返す(Fixture param) throws Exception {
        sut = new ConsumptionTax(param.taxRate);
        Long actual = sut.calcTaxIncludedPrice(param.exPrice);
        assertThat(actual, is(param.expected));
    }

    private static class Fixture {
        private int taxRate;
        private Long exPrice;
        private Long expected;

        public Fixture(int taxRate, Long exPrice, Long expected) {
            super();
            this.taxRate = taxRate;
            this.exPrice = exPrice;
            this.expected = expected;
        }
    }
}
