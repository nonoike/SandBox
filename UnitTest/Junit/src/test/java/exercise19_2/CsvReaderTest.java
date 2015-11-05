package exercise19_2;

import static exercise19_2.CsvReaderTestHelper.*;
import static exercise19_2.CustomerMatcher.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.Test;

public class CsvReaderTest {
    private CsvReader sut;

    @Before
    public void setUp() throws Exception {
        sut = new CsvReader();
    }

    @Test(expected = NullPointerException.class)
    public void readCustomersに存在しないファイル名を渡すと例外() throws Exception {
        sut.readCustomers("test.csv");
    }

    @Test
    public void readCustomersは複数行を読み込む() throws Exception {
        List<Customer> actuals = sut.readCustomers("19_2.txt");
        assertThat(actuals.get(0), SamePropertyValuesAs.samePropertyValuesAs(customerの生成_19_2_1行目()));
        assertThat(actuals.get(1), SamePropertyValuesAs.samePropertyValuesAs(customerの生成_19_2_2行目()));
        assertThat(actuals.size(), is(2));
    }

    @Test
    public void readCustomersは複数行を読み込む_カスタムMatcher() throws Exception {
        List<Customer> actuals = sut.readCustomers("19_2.txt");
        assertThat(actuals.get(0), valuesAs(customerの生成_19_2_1行目()));
        assertThat(actuals.get(1), valuesAs(customerの生成_19_2_2行目()));
        assertThat(actuals.size(), is(2));
    }
}
