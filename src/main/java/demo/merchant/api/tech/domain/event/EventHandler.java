package demo.merchant.api.tech.domain.event;

import com.google.common.reflect.TypeToken;

public interface EventHandler<EventType extends Event> {

  Void handle(EventType event);

  default Class<EventType> listenTo() {
    TypeToken<EventType> type = new TypeToken<>(getClass()) {
    };

    return (Class<EventType>) type.getRawType();
  }
}
