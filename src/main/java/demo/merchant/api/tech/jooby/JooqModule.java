package demo.merchant.api.tech.jooby;

import io.jooby.Extension;
import io.jooby.Jooby;
import io.jooby.ServiceRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.annotation.Nonnull;
import javax.sql.DataSource;

public class JooqModule implements Extension {

    private static final Logger log = LogManager.getLogger();
    private final SQLDialect sqlDialect;

    public JooqModule() {
        this(SQLDialect.POSTGRES);
    }

    public JooqModule(@Nonnull SQLDialect sqlDialect) {
        this.sqlDialect = sqlDialect;
    }

    @Override
    public void install(@Nonnull Jooby application) throws Exception {
        log.info("Install Jooq with {} dialect", sqlDialect.getName());

        ServiceRegistry registry = application.getServices();

        DataSource dataSource = registry.get(DataSource.class);

        registry.put(DSLContext.class, DSL.using(dataSource, sqlDialect));
    }
}
