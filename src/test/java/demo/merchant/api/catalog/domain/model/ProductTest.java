package demo.merchant.api.catalog.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class ProductTest {

    @Test
    void canUpdateProductPrice() {
        var product = new Product("1", "Product 1", "Our best Product n°1", 12.98);

        product.updatePrice(45.78);

        assertThat(product.getPrice()).isEqualTo(45.78);
    }

    @Test
    void canChangeDescription() {
        var product = new Product("1","Product 1", "Our best Product n°1", 12.98);

        product.updateDescription("A better desc 4 a better product");

        assertThat(product.getDescription()).isEqualTo("A better desc 4 a better product");
    }

    @Test
    void canCreateAProductWithAValidUUID() {
        var productCreation = Product.createProduct("Product 1", "Our best Product n°1", 12.98);

        Product product = productCreation._2;

        assertThat(product.getId()).isNotNull();
        assertThatNoException().isThrownBy(() -> UUID.fromString(product.getId()));
    }
}