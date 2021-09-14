package demo.merchant.api.catalog;

import dagger.BindsInstance;
import dagger.Component;
import demo.merchant.api.catalog.application.CatalogResource;
import demo.merchant.api.tech.injection.CQRSModule;
import demo.merchant.api.tech.injection.ExecutorModule;
import org.jooq.DSLContext;

@Component(modules = { CatalogModule.class, CQRSModule.class, ExecutorModule.class })
public interface CatalogComponent {

    CatalogResource catalogResource();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder dslContext(DSLContext dslContext);
        CatalogComponent build();
    }
}
