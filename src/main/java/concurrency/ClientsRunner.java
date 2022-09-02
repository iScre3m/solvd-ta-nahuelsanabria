package concurrency;


import java.util.concurrent.*;

public class ClientsRunner {

    public static final int MAX_THREADS = 5;

    public static void main(String[] args) throws InterruptedException {

        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(MAX_THREADS, MAX_THREADS, 7000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());



    }
}
