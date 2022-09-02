package testConcurrency;

import concurrency.Connection;

import java.util.concurrent.*;

public class ClientsRunner {

    static final int MAX_T = 5;

    public static void main(String[] args) throws InterruptedException {

//        ExecutorService executor = Executors.newFixedThreadPool(MAX_T);
//        for (int i = 0; i < 7; i++) {
//            Runnable connection = new Connection("" + i);
//            executor.execute(connection);
//        }
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
//        System.out.println("Finished all threads");

        // replace new LinkedBlockingQueue with ConnectionPool ??
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(MAX_T, MAX_T, 4000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 7; i++) {
            Runnable connection = new testConcurrency.Connection("" + (i+1));
            executor2.execute(connection);
        }
        executor2.shutdown();
        while (!executor2.isTerminated()){

        }
        System.out.println("Finished all threads");


    }
}