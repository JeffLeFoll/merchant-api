package demo.merchant.api.catalog.infrastructure.query;

import demo.merchant.api.catalog.infrastructure.query.projection.ProductProjection;
import demo.merchant.api.tech.domain.query.Query;

import java.util.List;


public record GetCatalog() implements Query<List<ProductProjection>> {
}
