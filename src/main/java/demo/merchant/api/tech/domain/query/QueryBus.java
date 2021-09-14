package demo.merchant.api.tech.domain.query;

import java.util.concurrent.CompletableFuture;

public interface QueryBus {

  <ResponseType> CompletableFuture<ResponseType> send(Query<ResponseType> query);
}
