package demo.merchant.api.tech.domain.event;

public interface EventBus {

  void dispatch(Event event);
}
