package demo.merchant.api.catalog.infrastructure.repository;

import static demo.merchant.api.codegen.catalog.Tables.PRODUCT;

import demo.merchant.api.catalog.domain.model.Product;
import demo.merchant.api.catalog.domain.model.repository.ProductRepository;
import org.jooq.DSLContext;

import javax.inject.Inject;
import java.util.Optional;

public class ProductRepositoryJooq implements ProductRepository {

    private final DSLContext jooq;

    @Inject
    public ProductRepositoryJooq(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public void update(Product product) {
        jooq.update(PRODUCT)
                .set(PRODUCT.DESCRIPTION, product.getDescription())
                .set(PRODUCT.PRICE, product.getPrice())
                .where(PRODUCT.ID.eq(product.getId()))
                .execute();
    }

    @Override
    public void add(Product element) {
        jooq.insertInto(PRODUCT, PRODUCT.ID, PRODUCT.NAME, PRODUCT.DESCRIPTION, PRODUCT.PRICE)
                .values(element.getId(), element.getName(), element.getDescription(), element.getPrice())
                .execute();
    }

    @Override
    public void delete(Product element) {
        jooq.delete(PRODUCT)
                .where(PRODUCT.ID.eq(element.getId()))
                .execute();
    }

    @Override
    public Optional<Product> get(String id) {
        return jooq
                .select(PRODUCT.ID, PRODUCT.NAME, PRODUCT.DESCRIPTION, PRODUCT.PRICE)
                .from(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .fetchOptional(record -> new Product(record.getValue(PRODUCT.ID), record.getValue(PRODUCT.NAME),
                        record.getValue(PRODUCT.DESCRIPTION), record.getValue(PRODUCT.PRICE)));
    }
}
