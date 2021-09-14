package demo.merchant.api.tech.domain.query;

public class UnknownQuery extends RuntimeException {

  private static final long serialVersionUID = 8963638895075347681L;

  public UnknownQuery(Class<?> ofType) {
    super(String.format("UNKNOWN_Query : %s", ofType));
  }
}
