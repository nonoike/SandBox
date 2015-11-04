package exercise18_5;

import java.util.HashMap;
import java.util.Map;

public class ProductStock {
    private Map<String, Product> productMap;

    public ProductStock() {
        productMap = new HashMap<>();
    }

    public void add(Product product) {
        productMap.put(product.name, product);
    }
}
