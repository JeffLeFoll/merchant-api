package demo.merchant.api.tech.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import demo.merchant.api.tech.domain.event.Event;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CommandHandlerTest {

  @Test
  void listenToCorrectCommandType() {
    var fakeCommandHandler = new FakeCommandHandler();

    Condition<Class> aFakeCommandClass = new Condition<>("check canonical name") {
      @Override
      public boolean matches(Class value) {
        return FakeCommand.class.getCanonicalName().matches(value.getCanonicalName());
      }
    };

    assertThat(fakeCommandHandler.getCommandType()).is(aFakeCommandClass);
  }


  private class FakeCommandHandler implements CommandHandler<FakeCommand> {

    @Override
    public Event execute(FakeCommand command) {
      return null;
    }
  }

  private class FakeCommand implements Command {

  }
}