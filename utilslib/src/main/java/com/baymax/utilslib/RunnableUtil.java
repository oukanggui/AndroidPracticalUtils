package com.baymax.utilslib;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author oukanggui
 * @date 2018/10/16
 * 描述 Runnable执行操作工具类
 */

public class RunnableUtil {

    private static final int THREADPOOL_SIZE_QUEUE = 20;
    private final static Object OBJECT_LOCK = new Object();
    private static ExecutorService mExecutorServiceQueue;
    private static ExecutorService mExecutorServiceImmediate;

    /**
     * 执行Runnable任务，默认为任务排队执行
     *
     * @param task 任务Runnable
     */
    public static void runTask(Runnable task) {
        runTask(task, false);
    }

    /**
     * 执行Runnable任务
     *
     * @param task      任务Runnable
     * @param immediate 是否立即执行，true：任务立即执行，false：任务排队执行（默认值）
     */
    public static void runTask(Runnable task, boolean immediate) {
        if (mExecutorServiceQueue == null) {
            synchronized (OBJECT_LOCK) {
                if (mExecutorServiceQueue == null) {
                    mExecutorServiceQueue = new ThreadPoolExecutor(THREADPOOL_SIZE_QUEUE / 10 + 1,
                            THREADPOOL_SIZE_QUEUE, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                            new CustomThreadFactory("fixed"), new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
        ThreadPoolExecutor executor = (ThreadPoolExecutor) mExecutorServiceQueue;
        BlockingQueue<Runnable> queue = executor.getQueue();
        if (!immediate || queue == null || queue.size() < executor.getCorePoolSize()) {
            executor.execute(task);
        } else {
            runWork(task);
        }
    }

    /**
     * 立刻在线程池运行线程
     *
     * @param task
     */
    private static void runWork(Runnable task) {
        if (mExecutorServiceImmediate == null) {
            synchronized (OBJECT_LOCK) {
                if (mExecutorServiceImmediate == null) {
                    mExecutorServiceImmediate = Executors.newCachedThreadPool(new CustomThreadFactory("cached"));
                }
            }
        }
        mExecutorServiceImmediate.submit(task);
    }

    private static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger mPoolSize = new AtomicInteger(1);
        private String mThreadName;

        public CustomThreadFactory(String threadName) {
            this.mThreadName = threadName;
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            int size = mPoolSize.getAndIncrement();
            Thread thread = new Thread(r, mThreadName + ":" + size);
            return thread;
        }
    }
}
