package poc.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Product {
    public String id;

    @NonNull
    public String title;

    @NonNull
    public Float price;

    @NonNull
    public String description;

    @NonNull
    public String sku;

}
