package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    public static final int POOL_THREADS = 2;
    private static ConnectionPool connectionPool;
    private BlockingQueue<Runnable> connections;
    private Connection connection = new Connection("Thread");

    private ConnectionPool(){
        connections = new LinkedBlockingQueue<Runnable>(POOL_THREADS);
    }


    // lazy initialization & synchronized for threadsafe
    public static ConnectionPool getConnectionPool(){
        if(connectionPool == null){
            synchronized (ConnectionPool.class){
                connectionPool = new ConnectionPool();
            }
        }
        return connectionPool;
    }

    public Connection getConnection() throws Exception{
        return connection;
    }




}
