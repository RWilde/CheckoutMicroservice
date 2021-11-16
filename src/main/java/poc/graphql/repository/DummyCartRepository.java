package poc.graphql.repository;

import jakarta.inject.Singleton;
import poc.graphql.models.Cart;
import poc.graphql.models.Item;
import poc.graphql.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DummyCartRepository {
    private HashMap<String, Cart> carts = new HashMap<String, Cart>();
    private List<Product> products = List.of(new Product("1", "jeans", 19.99F, "jeans", "ST123"),
            new Product("2", "t-shirt", 9.99F, "t-shirt", "ST124"),
            new Product("3", "jumper", 19.99F, "jumper", "ST125"));

    DummyCartRepository(){
        carts.put("1", new Cart("1", "cart", "1", new ArrayList(Arrays.asList(new Item(1, products.get(0))))));
        carts.put("2",new Cart("2", "wish list", "1", new ArrayList(Arrays.asList(new Item(2, products.get(2)), new Item(3, products.get(0))))));
        carts.put("3",new Cart("3", "christmas",  "1", new ArrayList(Arrays.asList(new Item(1, products.get(2)), new Item(2, products.get(1))))));
        carts.put("4",new Cart("4","birthday", "1",new ArrayList(Arrays.asList(new Item(1, products.get(0)), new Item(1, products.get(1)), new Item(1, products.get(2))))));
    }

    public List<Cart> findAll() {
        return carts.values().stream().collect(Collectors.toList());
    }

    public Cart findById(String id){
        return carts.values().stream().filter(cart -> cart.id.equals(id)).findFirst().get();
    }

    public Cart save(Cart cart) {
        return carts.put(cart.id, cart);
    }

    public Boolean emptyCart(String cartId) {
        List<Item> items = findById(cartId).getItems();
        items.clear();
        return items.isEmpty();
    }

    public Boolean deleteCart(String cartId) {
        return carts.values().removeIf(cart -> cart.id.equals(cartId));
    }
}
