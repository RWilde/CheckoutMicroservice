package poc.graphql.resolvers;


import graphql.kickstart.tools.GraphQLMutationResolver;
import poc.graphql.models.Cart;
import poc.graphql.models.Item;
import poc.graphql.models.Product;
import poc.graphql.repository.CartRepository;
import poc.graphql.repository.DummyCartRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CartMutationResolver implements GraphQLMutationResolver {

private final CartRepository cartRepository;

    public CartMutationResolver(CartRepository cartsRepository) {
        this.cartRepository = cartsRepository;
    }

    public boolean createCart(String userId, String name){
        return cartRepository.save(new Cart(name + userId, name, userId));
    }

    public Boolean emptyCart(String cartId){
        Cart cart = cartRepository.findById(cartId);
        List<Item> items = cart.getItems();
        items.clear();
        return cartRepository.save(cart);
    }

    public Boolean deleteCart(String cartId){
        return cartRepository.deleteCart(cartId);
    }

    public Boolean updateCare(String cartId, String name){
        Cart cart = cartRepository.findById(cartId);
        cart.setName(name);
        return cartRepository.save(cart);
    }

    public boolean addItem(String cartId, String productId, Integer quantity){
        Cart cart = cartRepository.findById(cartId);
        Item item = cart.getItems().stream()
                .filter(p -> p.getProduct().getId().equals(productId))
                .findFirst()
                .orElseGet(() -> {
                    Product product = new Product(productId, "dummy", 29.00F, "dummy", "ST125");
                    Item newItem = new Item(1, product);
                    cart.getItems().add(newItem);
                    return newItem;
                });
        item.setQuantity(item.getQuantity() + quantity);
        return cartRepository.save(cart);
    }

    public Cart deleteItem(String cartId, String productId){
        Cart cart = cartRepository.findById(cartId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.findById(cartId);
    }

    public Cart updateItem(String cartId, String productId, Integer quantity){
        Cart cart = cartRepository.findById(cartId);

        Item updatedItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().get();
        updatedItem.setQuantity(quantity);

        return cartRepository.findById(cartId);
    }
}
