package testConcurrency;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Vector;



public class ConnectionPool{
    final Logger LOG = LogManager.getLogger(ConnectionPool.class.getName());
    private int size;
    private static ConnectionPool connectionPool;
    private Vector<Connection> connections;

    public ConnectionPool(int size){
        this.size = size;
        connections = new Vector<Connection>();
    }

    private void innitConnection(Connection connection){
        connections.add(connection);
    }
    public void releaseConnection(Connection connection){
        connections.remove(connection);
    }

    public synchronized Connection getConnection(){
        Connection connection = null;
        if (connections.size() < size){
            connection = new Connection("Thread#" + (connections.size()+1));
            innitConnection(connection);
            return connection;
            }
        else {
            LOG.info("There are no free slots, connection requests will get to queue...");
            int maxAttemptsCount = 10;
            while(maxAttemptsCount-- > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (connections.size() < size){
                    connection = new Connection("Thread#" + (connections.size()+1));
                    innitConnection(connection);
                    return connection;
                }
            }
            throw new RuntimeException("No connections available after 10 seconds");
        }
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

}

