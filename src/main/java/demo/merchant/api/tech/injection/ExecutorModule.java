package demo.merchant.api.tech.injection;

import demo.merchant.api.tech.thread.ThreadPoolExecutorWithThreadContext;
import dagger.Module;
import dagger.Provides;

import java.util.concurrent.ExecutorService;

@Module
public abstract class ExecutorModule {

    @Provides
    static ExecutorService provideExecutorService(){
        return ThreadPoolExecutorWithThreadContext.newCachedThreadPoolWithThreadContext();
    }
}
