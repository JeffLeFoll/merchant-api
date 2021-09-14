package demo.merchant.api.catalog.infrastructure.query.handler;

import demo.merchant.api.catalog.infrastructure.query.GetCatalog;
import demo.merchant.api.catalog.infrastructure.query.projection.ProductProjection;
import demo.merchant.api.tech.domain.query.QueryHandler;
import org.jooq.DSLContext;

import javax.inject.Inject;
import java.util.List;

import static demo.merchant.api.codegen.catalog.Tables.PRODUCT;

public class GetCatalogHandler implements QueryHandler<GetCatalog, List<ProductProjection>> {

    private final DSLContext jooq;

    @Inject
    public GetCatalogHandler(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public List<ProductProjection> execute(GetCatalog query) throws Exception {

        return jooq.select(PRODUCT.ID, PRODUCT.NAME, PRODUCT.DESCRIPTION, PRODUCT.PRICE)
                .from(PRODUCT)
                .fetchInto(ProductProjection.class);
    }
}
