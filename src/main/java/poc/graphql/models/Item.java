package poc.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    public String id;
    @NonNull
    public Integer quantity;
    @NonNull
    public Product product;
}
