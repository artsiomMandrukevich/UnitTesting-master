package store;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Product> cartList;
    private Float totalPrice = (float) 0;

    private Cart() {
        this.cartList = new ArrayList<>();
    }

    private static class CartHelper {
        private static final Cart cartInstance = new Cart();
    }

    public static Cart getInstance() {
        return CartHelper.cartInstance;
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public void setProductToCart(Product product) {
        this.cartList.add(product);
    }

    public Float getTotalPrice() {
        for (Product product : cartList) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }
}
