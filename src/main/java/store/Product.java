package store;

import lombok.Data;

@Data
public class Product {
    private String productName;
    private Float productPrice;

    public Product(String name, Float productPrice) {
        this.productName = name;
        this.productPrice = productPrice;
    }
}
