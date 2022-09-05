package store;

public class Product {

    private final String productName;
    private final Float productPrice;

    public Product(String name, Float productPrice) {
        this.productName = name;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }
}
