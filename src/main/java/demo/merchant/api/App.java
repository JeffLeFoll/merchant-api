package demo.merchant.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.merchant.api.catalog.CatalogComponent;
import demo.merchant.api.catalog.DaggerCatalogComponent;
import demo.merchant.api.tech.PingController;
import demo.merchant.api.tech.jooby.JooqModule;
import io.jooby.Jooby;
import io.jooby.MediaType;
import io.jooby.hikari.HikariModule;
import io.jooby.json.JacksonModule;
import io.vavr.jackson.datatype.VavrModule;
import org.apache.logging.log4j.ThreadContext;
import org.jooq.DSLContext;

public class App extends Jooby {

    {
        install(new JacksonModule());
        require(ObjectMapper.class).registerModule(new VavrModule());
        install(new HikariModule());
        install(new JooqModule());

        mvc(new PingController());

        CatalogComponent catalogComponent = DaggerCatalogComponent.builder().dslContext(require(DSLContext.class)).build();
        mvc(catalogComponent.catalogResource());

        after((ctx, result, failure) -> ThreadContext.clearAll());
        after((ctx, result, failure) -> ctx.setResponseType(MediaType.json));

    }

    public static void main(final String[] args) {
        runApp(args, App::new);
    }

}
