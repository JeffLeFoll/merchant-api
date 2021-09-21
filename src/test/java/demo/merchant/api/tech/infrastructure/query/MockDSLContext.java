package demo.merchant.api.tech.infrastructure.query;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockResult;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicReference;

public class MockDSLContext<TypeRecord extends Record> {

    public DSLContext getDSLContext(TableImpl<TypeRecord> table, AtomicReference<String> sqlQueryRef) {
        MockDataProvider provider = context -> {

            DSLContext create = DSL.using(SQLDialect.POSTGRES);

            Result<TypeRecord> result = create.newResult(table);
            result.add(create.newRecord(table));
            sqlQueryRef.set(context.sql());

            return new MockResult[]{
                    new MockResult(1, result)
            };
        };

        Connection connection = new MockConnection(provider);
        return DSL.using(connection, SQLDialect.POSTGRES);
    }
}
