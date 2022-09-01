package homework2.multiThreading;

public class ThreadPoolMain {

    public static void main(String[] args) throws Exception {

        int maxNoOfTasks = 30;
        int noOfThreads = 5;
        ThreadPool threadPool = new ThreadPool(noOfThreads, maxNoOfTasks);

        for(int i = 0; i < maxNoOfTasks; i++) {

            int taskNo = i;
            threadPool.execute( () -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo ;
                System.out.println(message);
            });
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();

    }
}