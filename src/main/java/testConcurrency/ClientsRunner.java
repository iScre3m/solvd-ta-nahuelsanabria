package testConcurrency;

import java.util.concurrent.*;

public class ClientsRunner {

    private static  ConnectionPool connectionPool = new ConnectionPool(5);
    public static void main(String[] args) throws InterruptedException{

        final int TPE_MAX_C = 7;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(TPE_MAX_C);

        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(TPE_MAX_C, TPE_MAX_C, 4, TimeUnit.SECONDS, queue);
        for (int i = 0; i < TPE_MAX_C; i++) {
            Runnable connection = new Runnable() {
                @Override
                public void run() {
                    try{
                        Connection connection = connectionPool.getConnection();
                        connection.connect();
                        try{
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        connection.disconnect();
                        connectionPool.releaseConnection(connection);
                    }catch (RuntimeException e){
                        System.err.println(e.getMessage());
                    }
                }
            };
            executor1.execute(connection);
        }
        executor1.shutdown();
        while (!executor1.isTerminated()){

        }
        System.out.println("Finished all threads");
    }
}
