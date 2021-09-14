package demo.merchant.api.catalog.application;

import demo.merchant.api.catalog.domain.command.AddProductToCatalog;
import demo.merchant.api.catalog.domain.command.UpdateProductPrice;
import demo.merchant.api.catalog.infrastructure.query.GetCatalog;
import demo.merchant.api.catalog.infrastructure.query.projection.ProductProjection;
import demo.merchant.api.tech.domain.command.CommandBus;
import demo.merchant.api.tech.domain.event.Event;
import demo.merchant.api.tech.domain.query.QueryBus;
import io.jooby.annotations.*;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Path("/catalog")
public class CatalogResource {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @Inject
    CatalogResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @POST
    public CompletableFuture<Event> addProduct(AddProductToCatalog command) {
        return commandBus.send(command);
    }

    @PATCH
    @Path("/{productId}")
    public CompletableFuture<Event> updateProductPrice(@PathParam String productId, @QueryParam Double price) {
        UpdateProductPrice command = new UpdateProductPrice(productId, price);

        return commandBus.send(command);
    }

    @GET
    public CompletableFuture<List<ProductProjection>> getCatalog() {
        var query = new GetCatalog();

        return queryBus.send(query);
    }
}
