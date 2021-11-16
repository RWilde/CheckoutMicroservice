package poc.graphql.resolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import poc.graphql.models.Cart;
import poc.graphql.repository.CartRepository;
import poc.graphql.repository.DummyCartRepository;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CartsQueryResolver implements GraphQLQueryResolver {

    private final CartRepository cartsRepository;

    public CartsQueryResolver(CartRepository cartsRepository) {
        this.cartsRepository = cartsRepository;
    }

    public List<Cart> carts(){
        return cartsRepository.findAll();
    }

    public Cart cart(String id){
        return cartsRepository.findById(id);
    }
}
