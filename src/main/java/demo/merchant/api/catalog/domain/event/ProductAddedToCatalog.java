package demo.merchant.api.catalog.domain.event;

import demo.merchant.api.tech.domain.event.Event;

public record ProductAddedToCatalog(String productId) implements Event {
}
