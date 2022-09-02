package multiThreading;

public class ClientsRunner {

    public static void main(String[] args) throws Exception {

        ConnectionPool connectionPool = new ConnectionPool(3, 10);

        for(int i=1; i<=10; i++) {

            int taskNo = i;
            connectionPool.execute( () -> {
                String message =
                        Thread.currentThread().getName()
                                + " Connecting" ;
                System.out.println(message);
            });
        }

        connectionPool.waitUntilAllTasksFinished();
        connectionPool.stop();

    }
}