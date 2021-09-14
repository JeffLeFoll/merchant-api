package demo.merchant.api.catalog.domain.command;

import demo.merchant.api.tech.domain.command.Command;

public record AddProductToCatalog(String name, String description, Double price) implements Command {
}
