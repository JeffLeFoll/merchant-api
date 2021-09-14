package demo.merchant.api.catalog.domain.model.repository;

import demo.merchant.api.catalog.domain.model.Product;
import demo.merchant.api.tech.domain.model.Repository;

public interface ProductRepository extends Repository<String, Product> {

    void update(Product product);
}
