package demo.merchant.api.catalog.domain.event.handler;

import demo.merchant.api.catalog.domain.event.ProductPriceUpdated;
import demo.merchant.api.tech.domain.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class ProductPriceUpdatedHandler implements EventHandler<ProductPriceUpdated> {

    private static final Logger log = LogManager.getLogger();

    @Inject
    public ProductPriceUpdatedHandler() {}

    @Override
    public Void handle(ProductPriceUpdated event) {

        log.info("Pour le produit {} : Nouveau prix : {}, ancien prix {}", event.productId(), event.newPrice(), event.oldPrice());

        return null;
    }
}
