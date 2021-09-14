package demo.merchant.api.catalog;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import demo.merchant.api.catalog.domain.command.handler.AddProductToCatalogHandler;
import demo.merchant.api.catalog.domain.command.handler.UpdateProductPriceHandler;
import demo.merchant.api.catalog.domain.event.handler.ProductPriceUpdatedHandler;
import demo.merchant.api.catalog.domain.model.repository.ProductRepository;
import demo.merchant.api.catalog.infrastructure.query.handler.GetCatalogHandler;
import demo.merchant.api.catalog.infrastructure.repository.ProductRepositoryJooq;
import demo.merchant.api.tech.domain.command.CommandHandler;
import demo.merchant.api.tech.domain.event.EventHandler;
import demo.merchant.api.tech.domain.query.QueryHandler;


@Module
public abstract class CatalogModule {

    @Binds
    abstract ProductRepository bindProductRepositoryJooq(ProductRepositoryJooq repository);


    @Binds @IntoSet
    abstract CommandHandler bindAddProductToCatalogHandler(AddProductToCatalogHandler addProductToCatalogHandler);

    @Binds @IntoSet
    abstract CommandHandler bindUpdateProductPriceHandler(UpdateProductPriceHandler updateProductPriceHandler);


    @Binds @IntoSet
    abstract EventHandler bindProductPriceUpdatedHandler(ProductPriceUpdatedHandler productPriceUpdatedHandler);


    @Binds @IntoSet
    abstract QueryHandler bindGetCatalogHandler(GetCatalogHandler getCatalogHandler);
}
