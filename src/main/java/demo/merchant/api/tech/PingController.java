package demo.merchant.api.tech;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;

@Path("/")
public class PingController {

  @GET
  public String sayHi() {
    return "I'm alive !!";
  }

}
