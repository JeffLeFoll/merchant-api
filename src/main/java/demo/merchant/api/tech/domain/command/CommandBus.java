package demo.merchant.api.tech.domain.command;

import demo.merchant.api.tech.domain.event.Event;

import java.util.concurrent.CompletableFuture;

public interface CommandBus {

  CompletableFuture<Event> send(Command command);

}
