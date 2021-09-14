package demo.merchant.api.tech.infrastructure.bus;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Sets;
import demo.merchant.api.tech.domain.command.Command;
import demo.merchant.api.tech.domain.command.CommandHandler;
import demo.merchant.api.tech.domain.event.Event;
import io.vavr.concurrent.Future;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandBusAsyncTest {

  private ExecutorService executor;

  @BeforeEach
  void setup() {
    executor = Executors.newCachedThreadPool();
  }

  @Test
  void canSendCommandToHandler() throws ExecutionException, InterruptedException {

    var handler = new FakeCommandHandler();
    var eventBus = new EventBusAsync(Sets.newHashSet(), executor);
    var commandBus = new CommandBusAsync(Set.of(handler), eventBus , executor);
    var command = new FakeCommand();

    CompletableFuture<Event> resultAsync = commandBus.send(command);

    FakeEvent result = (FakeEvent) resultAsync.get();
    assertThat(result.getPayload()).isEqualTo("Hello !!");
  }

  private class FakeCommandHandler implements CommandHandler<FakeCommand> {

    @Override
    public Event execute(FakeCommand command) {
      return new FakeEvent("Hello !!");
    }
  }

  private class FakeEvent implements Event {

    private final String payload;

    private FakeEvent(String payload) {
      this.payload = payload;
    }

    String getPayload() {
      return payload;
    }
  }

  private class FakeCommand implements Command {

  }

}