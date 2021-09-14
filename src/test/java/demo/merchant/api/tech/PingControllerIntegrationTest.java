package demo.merchant.api.tech;

import demo.merchant.api.App;
import io.jooby.JoobyTest;
import io.jooby.StatusCode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JoobyTest(App.class)
public class PingControllerIntegrationTest {

  static OkHttpClient client = new OkHttpClient();

  @Test
  public void shouldSayHi(int serverPort) throws IOException {
    Request req = new Request.Builder()
        .url("http://localhost:" + serverPort)
        .build();

    try (Response rsp = client.newCall(req).execute()) {
      assertEquals("I'm alive !!", rsp.body().string());
      assertEquals(StatusCode.OK.value(), rsp.code());
    }
  }
}
