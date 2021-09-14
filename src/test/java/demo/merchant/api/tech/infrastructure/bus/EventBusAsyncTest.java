package demo.merchant.api.tech.infrastructure.bus;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.util.concurrent.MoreExecutors;
import demo.merchant.api.tech.domain.event.Event;
import demo.merchant.api.tech.domain.event.EventHandler;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventBusAsyncTest {

  private ExecutorService executor;

  @BeforeEach
  void setup() {
    executor = MoreExecutors.newDirectExecutorService();
  }

  @Test
  void canDispatchEventToHandler() {

    var fakeEvent = new FakeEvent();
    var fakeEventHandler = new FakeEventHandler();
    var eventBus = new EventBusAsync(Set.of(fakeEventHandler), executor);

    eventBus.dispatch(fakeEvent);

    assertThat(fakeEventHandler.hasHandledEvent()).isTrue();
  }

  @Test
  void canDispatchSameEventToMultipleHandlers() {

    var fakeEvent = new FakeEvent();
    var fakeEventHandler = new FakeEventHandler();
    var otherFakeEventHandler = new OtherFakeEventHandler();
    var notRelevantFakeEventHandler = new NotRelevantFakeEventHandler();
    var eventBus = new EventBusAsync(Set.of(fakeEventHandler, otherFakeEventHandler), executor);

    eventBus.dispatch(fakeEvent);

    assertThat(fakeEventHandler.hasHandledEvent()).isTrue();
    assertThat(otherFakeEventHandler.hasHandledEvent()).isTrue();
    assertThat(notRelevantFakeEventHandler.hasHandledEvent()).isFalse();
  }

  private class FakeEvent implements Event {

  }

  private class FakeEventHandler implements EventHandler<FakeEvent> {

    private boolean handled = false;

    boolean hasHandledEvent() {
      return handled;
    }

    @Override
    public Void handle(FakeEvent event) {
      handled = true;
      return null;
    }
  }

  private class OtherFakeEventHandler implements EventHandler<FakeEvent> {

    private boolean handled = false;

    boolean hasHandledEvent() {
      return handled;
    }

    @Override
    public Void handle(FakeEvent event) {
      handled = true;
      return null;
    }
  }

  private class NotRelevantFakeEvent implements Event {

  }

  private class NotRelevantFakeEventHandler implements EventHandler<NotRelevantFakeEvent> {

    private boolean handled = false;

    boolean hasHandledEvent() {
      return handled;
    }

    @Override
    public Void handle(NotRelevantFakeEvent event) {
      handled = true;
      return null;
    }
  }
}