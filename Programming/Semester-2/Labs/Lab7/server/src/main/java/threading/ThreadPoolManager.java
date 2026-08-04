package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ThreadPoolManager {
    // ForkJoinPool for reading requests (non-blocking)
    private static final ForkJoinPool readingPool = ForkJoinPool.commonPool();

    // Fixed thread pool for processing commands (10 threads)
    private static final ExecutorService processingPool = Executors.newFixedThreadPool(10);

    public static ForkJoinPool getReadingPool() {
        return readingPool;
    }

    public static ExecutorService getProcessingPool() {
        return processingPool;
    }

    // For sending responses – create a new Thread each time
    public static void sendResponseAsync(Runnable responseTask) {
        new Thread(responseTask).start();
    }
}