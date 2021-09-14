package demo.merchant.api.catalog.domain.command.handler;

import demo.merchant.api.catalog.domain.command.UpdateProductPrice;
import demo.merchant.api.catalog.domain.event.ProductPriceUpdated;
import demo.merchant.api.catalog.domain.model.Product;
import demo.merchant.api.catalog.domain.model.exception.UnknownProductException;
import demo.merchant.api.catalog.domain.model.repository.ProductRepository;
import demo.merchant.api.tech.domain.command.CommandHandler;
import demo.merchant.api.tech.domain.event.Event;
import io.vavr.Tuple2;

import javax.inject.Inject;
import java.util.Optional;

public class UpdateProductPriceHandler implements CommandHandler<UpdateProductPrice> {

    private final ProductRepository repository;

    @Inject
    public UpdateProductPriceHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event execute(UpdateProductPrice command) throws Exception {

        Optional<Product> eventualProduct = repository.get(command.productId());

        if (eventualProduct.isPresent()) {
            var product = eventualProduct.get();

            Tuple2<ProductPriceUpdated, Product> updatedProduct = product.updatePrice(command.price());

            repository.update(updatedProduct._2);

            return updatedProduct._1;
        }

        throw new UnknownProductException(command.productId());
    }
}
