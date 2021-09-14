package demo.merchant.api.catalog.domain.command;

import demo.merchant.api.tech.domain.command.Command;

public record UpdateProductPrice(String productId, Double price) implements Command {
}
