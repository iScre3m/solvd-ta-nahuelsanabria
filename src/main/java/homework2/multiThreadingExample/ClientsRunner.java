package homework2.multiThreadingExample;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientsRunner {

    public static final int MAX_THREADS = 5;

    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 7000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        executor.execute(new CustomThread(" Thread 1 "));






    }

}
