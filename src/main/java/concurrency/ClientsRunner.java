package concurrency;

import homework2.multiThreadingExample.CustomThread;

import java.util.concurrent.*;

public class ClientsRunner {

    public static final int MAX_THREADS = 5;

    public static void main(String[] args) throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

//        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
//
//        pool.execute(connectionPool.thread1);
//        pool.execute(connectionPool.thread2);
//        pool.execute(connectionPool.thread3);
//        pool.execute(connectionPool.thread4);
//        pool.execute(connectionPool.thread5);
//        pool.execute(connectionPool.thread6);
//        pool.execute(connectionPool.thread7);
//
//        pool.shutdown();


        ThreadPoolExecutor executor = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 7000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        //A ThreadPoolExecutor will automatically adjust the pool size according to the bounds set by corePoolSize and maximumPoolSize.
        // When a new task is submitted in method execute(java.lang.Runnable), and fewer than corePoolSize threads are running, a new thread is created to handle the request,
        // even if other worker threads are idle.
        // If there are more than corePoolSize but less than maximumPoolSize threads running, a new thread will be created only if the queue is full.
        // By setting corePoolSize and maximumPoolSize the same, you create a fixed-size thread pool;
        executor.execute(new CustomThread("thread 0"));
        for (int i = 0; i <= MAX_THREADS; i++) {
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS); // will wait until all tasks are completed or 1 second elapses which will be the case here;
            executor.execute(new Connection("thread " + (i + 1))); // executes the given task sometime in the future;
            System.out.println("Thread" + (i + 1) + " was submitted");
        }
        System.out.println("[DONE]. no more tasks will me submitted to the queue");
        executor.shutdown(); //Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.;

        try {
            executor.awaitTermination(1500, TimeUnit.MILLISECONDS); //Blocks until all tasks have completed execution after a shutdown request,
            // or the timeout occurs, or the current thread is interrupted, whichever happens first.
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        if (executor.isTerminated()) { // Returns true if all tasks have completed following shut down.;
            // Note that isTerminated is never true unless either shutdown or shutdownNow was called first;
            // modify timeout on line 30 to execute this block;
            System.out.println("all threads terminated");
        } else {
            System.out.println("threads still running");
        }
    }
}
