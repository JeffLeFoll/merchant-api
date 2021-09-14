package demo.merchant.api.tech;

import demo.merchant.api.tech.PingController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PingControllerTest {
  @Test
  public void welcome() {
    PingController controller = new PingController();
    assertEquals("I'm alive !!", controller.sayHi());
  }
}
