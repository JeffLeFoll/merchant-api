package demo.merchant.api.tech.domain.command;

public class UnknownCommand extends RuntimeException {

  private static final long serialVersionUID = 3602051560307150975L;

  public UnknownCommand(Class<?> ofType) {
    super(String.format("UNKNOWN_COMMAND : %s", ofType));
  }
}
