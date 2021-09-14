package demo.merchant.api.catalog.domain.model;

import demo.merchant.api.catalog.domain.command.UpdateProductPrice;
import demo.merchant.api.catalog.domain.event.ProductAddedToCatalog;
import demo.merchant.api.catalog.domain.event.ProductPriceUpdated;
import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.UUID;

public class Product {

    private final String id;
    private final String name;
    private String description;
    private Double price;

    public Product(String id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Tuple2<ProductAddedToCatalog, Product> createProduct(String name, String description, Double price) {
        String id = UUID.randomUUID().toString();

        var product = new Product(id, name, description, price);
        var event = new ProductAddedToCatalog(product.getId());

        return Tuple.of(event, product);
    }

    public Tuple2<ProductPriceUpdated, Product> updatePrice(Double newPrice) {
        var event = new ProductPriceUpdated(this.id, this.price, newPrice);
        this.price = newPrice;

        return Tuple.of(event, this);
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }


}
