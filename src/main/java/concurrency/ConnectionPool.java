package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    public static final int POOL_THREADS = 6;
    private static ConnectionPool connectionPool;
    private BlockingQueue<Runnable> connections;
    private int connectionsCount;

    private ConnectionPool(){
        connections = new LinkedBlockingQueue<Runnable>(POOL_THREADS);
    }


    // lazy initialization & synchronized for threadsafe
    public static ConnectionPool getInstance(){
        if(connectionPool == null){
            synchronized (ConnectionPool.class){
                connectionPool = new ConnectionPool();
            }
        }
        return connectionPool;
    }

    private void innitConnection(Runnable connection){
        connections.add(connection);
    }

    public Runnable getConnection() throws InterruptedException {
        System.out.println("Amount of connections: " + (++connectionsCount) + " maximum threads: " + POOL_THREADS);
        if(connections.size() == 0 && connectionsCount <= POOL_THREADS){
            synchronized (ConnectionPool.class){
                if (connections.size() == 0 && connectionsCount <= POOL_THREADS){
                    innitConnection(new Thread(new concurrency.Connection("Thread"+connectionsCount)));
                }
            }
        }

        return connections.take();
    }

    public void releaseConnection(Connection connection){
        connections.offer(connection);
    }
}
