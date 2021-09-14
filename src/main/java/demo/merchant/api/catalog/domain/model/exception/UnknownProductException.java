package demo.merchant.api.catalog.domain.model.exception;

public class UnknownProductException extends Exception {

    public UnknownProductException(String productId) {
        super("Product with id %s does not exist !".formatted(productId));
    }
}
