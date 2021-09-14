package demo.merchant.api.tech.injection;

import demo.merchant.api.tech.domain.event.EventBus;
import demo.merchant.api.tech.domain.command.CommandBus;
import demo.merchant.api.tech.domain.query.QueryBus;
import demo.merchant.api.tech.infrastructure.bus.*;
import dagger.Binds;
import dagger.Module;
import demo.merchant.api.tech.infrastructure.bus.EventBusAsync;

@Module
public abstract class CQRSModule {

    @Binds
    abstract CommandBus bindCommandBus(CommandBusAsync impl);

    @Binds
    abstract EventBus bindEventBus(EventBusAsync impl);

    @Binds
    abstract QueryBus bindQueryBus(QueryBusAsync impl);
}
