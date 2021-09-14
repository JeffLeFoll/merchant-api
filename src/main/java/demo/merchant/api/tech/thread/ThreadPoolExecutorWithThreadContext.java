package demo.merchant.api.tech.thread;

import org.apache.logging.log4j.ThreadContext;

import java.util.Map;
import java.util.concurrent.*;

public class ThreadPoolExecutorWithThreadContext extends ThreadPoolExecutor {

  public ThreadPoolExecutorWithThreadContext(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                             TimeUnit unit,
                                             BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  public ThreadPoolExecutorWithThreadContext(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                             BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
  }

  public ThreadPoolExecutorWithThreadContext(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                             BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
  }

  public ThreadPoolExecutorWithThreadContext(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                             BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                             RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
  }

  public static ExecutorService newCachedThreadPoolWithThreadContext() {
    return new ThreadPoolExecutorWithThreadContext(0, Integer.MAX_VALUE,
        60L, TimeUnit.SECONDS,
        new SynchronousQueue<>());
  }

  @Override
  public void execute(Runnable task) {
    super.execute(new RunnableWrapperWithThreadContext(task));
  }

  @Override
  public <T> Future<T> submit(Callable<T> task) {
    return super.submit(new CallableWrapperWithThreadContext<>(task));
  }

  @Override
  public Future<?> submit(Runnable task) {
    return super.submit(new RunnableWrapperWithThreadContext(task));
  }

  @Override
  public <T> Future<T> submit(Runnable task, T result) {
    return super.submit(new RunnableWrapperWithThreadContext(task), result);
  }

  static class RunnableWrapperWithThreadContext implements Runnable {

    private final Runnable wrapped;
    private final Map<String, String> map;

    public RunnableWrapperWithThreadContext(Runnable wrapped) {
      this.wrapped = wrapped;
      map = ThreadContext.getContext();
    }

    @Override
    public void run() {
      ThreadContext.putAll(map);
      wrapped.run();
    }
  }

  static class CallableWrapperWithThreadContext<T> implements Callable<T> {

    private final Callable<T> wrapped;
    private final Map<String, String> map;

    public CallableWrapperWithThreadContext(Callable<T> wrapped) {
      this.wrapped = wrapped;
      map = ThreadContext.getContext();
    }

    @Override
    public T call() throws Exception {
      ThreadContext.putAll(map);
      return wrapped.call();
    }
  }
}
