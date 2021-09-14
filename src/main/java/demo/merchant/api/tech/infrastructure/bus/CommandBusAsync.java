package demo.merchant.api.tech.infrastructure.bus;

import demo.merchant.api.tech.domain.command.UnknownCommand;
import demo.merchant.api.tech.domain.command.Command;
import demo.merchant.api.tech.domain.command.CommandBus;
import demo.merchant.api.tech.domain.command.CommandHandler;
import demo.merchant.api.tech.domain.event.Event;
import demo.merchant.api.tech.domain.event.EventBus;
import io.vavr.Tuple;
import io.vavr.collection.Map;
import io.vavr.collection.Vector;
import io.vavr.concurrent.Future;

import javax.inject.Inject;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class CommandBusAsync implements CommandBus {

    private final EventBus eventBus;
    private final ExecutorService executorService;
    private final Map<Class, CommandHandler> handlers;

    @Inject
    public CommandBusAsync(Set<CommandHandler> handlers, EventBus eventBus,
                           ExecutorService executorService) {
        this.eventBus = eventBus;
        this.executorService = executorService;
        this.handlers = Vector.ofAll(handlers)
                .toMap(handler -> Tuple.of(handler.getCommandType(), handler));
    }
    @Override
    public CompletableFuture<Event> send(Command command) {
        var event= handlers.get(command.getClass())
                .map(handler -> Future.of(executorService, () -> handler.execute(command)))
                .getOrElseThrow(() -> new UnknownCommand(command.getClass()));

        event.andThen(it -> eventBus.dispatch(it.get()));

        return event.toCompletableFuture();
    }
}
