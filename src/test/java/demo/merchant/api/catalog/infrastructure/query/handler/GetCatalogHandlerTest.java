package demo.merchant.api.catalog.infrastructure.query.handler;

import demo.merchant.api.catalog.infrastructure.query.GetCatalog;
import demo.merchant.api.codegen.catalog.Tables;
import demo.merchant.api.codegen.catalog.tables.records.ProductRecord;
import demo.merchant.api.tech.infrastructure.query.MockDSLContext;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.*;

class GetCatalogHandlerTest {

    @Test
    void canRetrieveProductForTheCatalog() throws Exception {
        AtomicReference<String> sqlQuery = new AtomicReference<>();
        var mockedJooq = new MockDSLContext<ProductRecord>().getDSLContext(Tables.PRODUCT, sqlQuery);
        var query = new GetCatalog();
        var handler = new GetCatalogHandler(mockedJooq);

        handler.execute(query);

        assertThat(sqlQuery).hasValue(""" 
        select "catalog"."product"."id", "catalog"."product"."name", "catalog"."product"."description", "catalog"."product"."price" \
        from "catalog"."product"\
        """);
    }

}