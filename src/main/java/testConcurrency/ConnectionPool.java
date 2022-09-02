package testConcurrency;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private final int MAX_T = 3;

    private static ConnectionPool connectionPool;
    BlockingQueue<Connection> connections;

    private int connectionsCount;

    public ConnectionPool(){
        connections = new LinkedBlockingQueue<Connection>(MAX_T);
    }

    public static ConnectionPool getInstance(){
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

    public Connection getConnection() throws InterruptedException {
        System.out.println("Amount of connections: " + (++connectionsCount) + " maximum threads: " + MAX_T);
        if (connections.size()==0 && connectionsCount <= MAX_T){
            synchronized (ConnectionPool.class){
                    innitConnection(new Connection("Thread"+(connectionsCount)));
                }
            }
        return connections.take();
    }

    public void releaseConnection(Connection connection){
        connections.offer(connection);
    }

}

