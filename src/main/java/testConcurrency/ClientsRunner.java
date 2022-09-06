package testConcurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class ClientsRunner {

    private static  ConnectionPool connectionPool = new ConnectionPool(5);

    static final int TPE_MAX_C = 5;

    static final Logger LOG = LogManager.getLogger(Connection.class.getSimpleName());


    public static void main(String[] args) throws InterruptedException, ExecutionException {


        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(TPE_MAX_C);

        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(TPE_MAX_C, TPE_MAX_C, 4, TimeUnit.SECONDS, queue);

        ClientsRunner clientsRunner = new ClientsRunner();



        CompletableFuture<Void> future = CompletableFuture.runAsync(()->PoolTask(executor1));
        CompletableFuture<Void> futureRunner = CompletableFuture.runAsync(clientsRunner.new RunnerTask(), executor1);
        futureRunner.join();
        CompletableFuture<Void> futureRunnerTaskThread = CompletableFuture.runAsync(clientsRunner.new RunnerTaskThread(), executor1);
        futureRunnerTaskThread.join();


        future.whenComplete((x,y)-> executor1.shutdown()).thenRun(()-> LOG.info("Finished all threads"));


    }

    class RunnerTask implements Runnable{
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
    }

    class RunnerTaskThread extends Thread{
        public void run(){
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
    }

    public static void PoolTask(ThreadPoolExecutor executor1){
        for (int i = 0; i < TPE_MAX_C; i++)
        {
            executor1.execute(() -> {
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
            });
        }
    }
}
