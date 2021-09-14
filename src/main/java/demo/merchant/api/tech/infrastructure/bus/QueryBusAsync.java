package demo.merchant.api.tech.infrastructure.bus;

import demo.merchant.api.tech.domain.command.UnknownCommand;
import demo.merchant.api.tech.domain.query.Query;
import demo.merchant.api.tech.domain.query.QueryBus;
import demo.merchant.api.tech.domain.query.QueryHandler;
import demo.merchant.api.tech.domain.query.UnknownQuery;
import io.vavr.Tuple;
import io.vavr.collection.Map;
import io.vavr.collection.Vector;
import io.vavr.concurrent.Future;

import javax.inject.Inject;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class QueryBusAsync implements QueryBus {

    private final ExecutorService executor;
    private final Map<Class, QueryHandler> handlers;

    @Inject
    public QueryBusAsync(Set<QueryHandler> handlers,
                         ExecutorService executor) {
        this.executor = executor;
        this.handlers = Vector.ofAll(handlers)
                .toMap(handler -> Tuple.of(handler.getQueryType(), handler));
    }

    @Override
    public <ResponseType> CompletableFuture<ResponseType> send(Query<ResponseType> query) {
        return handlers.get(query.getClass())
                .map(handler -> Future.of(executor, () ->  (ResponseType) handler.execute(query)))
                .getOrElseThrow(() -> new UnknownQuery(query.getClass()))
                .toCompletableFuture();
    }
}
