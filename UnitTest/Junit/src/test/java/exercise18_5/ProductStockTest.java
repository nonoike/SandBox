package exercise18_5;

import static exercise18_5.ProductStockTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import exercise18_5.Product;
import exercise18_5.ProductStock;

public class ProductStockTest {
    private ProductStock sut;
    private Field field;

    @Before
    public void setUp() throws Exception {
        sut = new ProductStock();
        field = ProductStock.class.getDeclaredField("productMap");
        field.setAccessible(true);
    }

    @Test
    public void addで商品が追加される() throws Exception {
        sut.add(new Product("dummy1", 100));
        @SuppressWarnings("unchecked")
        Map<String, Product> productMap = (Map<String, Product>) field.get(sut);

        Product actual = productMap.get("dummy1");
        Product expected = Productの生成_dummy1();

        assertThat(actual.name, is(expected.name));
        assertThat(actual.price, is(expected.price));
    }
}
