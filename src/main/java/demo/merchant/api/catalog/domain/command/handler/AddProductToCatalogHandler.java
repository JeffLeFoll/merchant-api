package demo.merchant.api.catalog.domain.command.handler;

import demo.merchant.api.catalog.domain.command.AddProductToCatalog;
import demo.merchant.api.catalog.domain.event.ProductAddedToCatalog;
import demo.merchant.api.catalog.domain.model.Product;
import demo.merchant.api.catalog.domain.model.repository.ProductRepository;
import demo.merchant.api.tech.domain.command.CommandHandler;
import demo.merchant.api.tech.domain.event.Event;
import io.vavr.Tuple2;

import javax.inject.Inject;

public class AddProductToCatalogHandler implements CommandHandler<AddProductToCatalog> {

    private final ProductRepository repository;

    @Inject
    public AddProductToCatalogHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event execute(AddProductToCatalog command) throws Exception {

        Tuple2<ProductAddedToCatalog, Product> creation = Product.createProduct(command.name(), command.description(), command.price());

        repository.add(creation._2);

        return creation._1;
    }
}
