package demo.merchant.api.tech.infrastructure.bus;

import demo.merchant.api.tech.domain.event.Event;
import demo.merchant.api.tech.domain.event.EventBus;
import demo.merchant.api.tech.domain.event.EventHandler;
import io.vavr.concurrent.Future;

import javax.inject.Inject;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class EventBusAsync implements EventBus {

  private final Set<EventHandler> handlers;
  private final ExecutorService executorService;

  @Inject
  public EventBusAsync(Set<EventHandler> handlers, ExecutorService executorService) {
    this.executorService = executorService;
    this.handlers = handlers;
  }

  @Override
  public void dispatch(Event event) {
    handlers.stream()
        .filter(handler -> handler.listenTo().equals(event.getClass()))
        .forEach(handler -> Future.of(executorService, () -> handler.handle(event)));
  }

}
