package poc.graphql.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import poc.graphql.models.Cart;
import poc.graphql.models.Item;
import poc.graphql.models.Product;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CartRepository {

    private final MongoCollection<Cart> carts;

    public CartRepository(MongoCollection<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> findAll() {
        var c = carts.find();
        List<Cart> returned = new ArrayList<>();
        for (var cart: c){
            returned.add(cart);
        }
        return returned;
    }

    public Cart findById(String id) {
        Cart cart = carts.find(eq("_id", id)).first();
        return cart;
    }

    public boolean save(Cart cart) {
        carts.insertOne(cart);
        return true;
    }

    public boolean deleteCart(String cartId) {
        return carts.findOneAndDelete(eq("_id", cartId)) != null;
    }

    private Cart cart(Document doc){
        return new Cart(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("userId"),
                item(doc));
    }

    private List<Item> item(Document doc){
        List<Item> items = new ArrayList<Item>();
        items.add( new Item(
                doc.get("_id").toString(),
                doc.getInteger("quantity"),
                product(doc)));
        return new ArrayList<>();
    }

    private Product product(Document doc){
        return new Product(
                doc.get("_id").toString(),
                doc.getString("title"),
                Float.parseFloat(doc.getString("price")),
                doc.getString("description"),
                doc.getString("sku"));

    }
}
