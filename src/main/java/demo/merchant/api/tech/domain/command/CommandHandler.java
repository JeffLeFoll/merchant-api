package demo.merchant.api.tech.domain.command;

import com.google.common.reflect.TypeToken;
import demo.merchant.api.tech.domain.event.Event;

public interface CommandHandler<CommandType extends Command> {

  Event execute(CommandType command) throws Exception;

  default Class<CommandType> getCommandType() {
    TypeToken<CommandType> type = new TypeToken<>(getClass()) {
    };

    return (Class<CommandType>) type.getRawType();
  }
}
