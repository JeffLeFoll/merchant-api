package demo.merchant.api.catalog.domain.event;

import demo.merchant.api.tech.domain.event.Event;

public record ProductPriceUpdated(String productId, Double oldPrice, Double newPrice) implements Event {
}
