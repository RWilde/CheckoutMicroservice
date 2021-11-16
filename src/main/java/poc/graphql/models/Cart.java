package poc.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    @NonNull
    public String id;
    @NonNull
    public String name;
    @NonNull
    public String userId;

    public List<Item> items;

    public Cart(String name, String userId, List<Item> items){
        this.name = name;
        this.userId = userId;
        this.items = items;
    }
}
