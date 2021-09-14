package demo.merchant.api.tech.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class EventHandlerTest {

  @Test
  void listenToCorrectEventType() {
    var fakeEventHandler = new FakeEventHandler();

    Condition<Class> aFakeEventClass = new Condition<>("check canonical name") {
      @Override
      public boolean matches(Class value) {
        return FakeEvent.class.getCanonicalName().matches(value.getCanonicalName());
      }
    };

    assertThat(fakeEventHandler.listenTo()).is(aFakeEventClass);
  }

  private class FakeEventHandler implements EventHandler<FakeEvent> {

    @Override
    public Void handle(FakeEvent event) {
      return null;
    }
  }

  private class FakeEvent implements Event {

  }
}