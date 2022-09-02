package testConcurrency;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private final int MAX_T = 5;

    private static ConnectionPool connectionPool;
    private BlockingQueue<Runnable> connections;

    private int connectionsCount;

    public ConnectionPool(){
        connections = new LinkedBlockingQueue<Runnable>(MAX_T);
    }


    public ConnectionPool getInstance(){
        if(connectionPool == null){
            synchronized (ConnectionPool.class){
                connectionPool = new ConnectionPool();
            }
        }
        return connectionPool;
    }

    private void innitConnection(Connection connection){
        connections.add(connection);
    }
    public void releaseConnection(Connection connection){
        connections.offer(connection);
    }

    public Runnable getConnection() throws InterruptedException {
        System.out.println("Amount of connections: " + (++connectionsCount) + " maximum threads: " + MAX_T);
        if (connections.size()==0 && connectionsCount <= MAX_T){
            synchronized (ConnectionPool.class){
                    innitConnection(new Connection("Thread"+(connectionsCount)));
                }
            }
        return connections.take();
    }


    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public BlockingQueue<Runnable> getConnections() {
        return connections;
    }

    public int getConnectionsCount() {
        return connectionsCount;
    }

}

