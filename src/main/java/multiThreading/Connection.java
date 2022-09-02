package multiThreading;

import java.util.concurrent.BlockingQueue;

public class Connection implements Runnable {

    private Thread thread = null;
    private BlockingQueue taskQueue = null;
    private boolean isAvailable = false;

    public Connection(BlockingQueue queue){
        taskQueue = queue;
    }

    public void run(){
        this.thread = Thread.currentThread();
        while(!isAvailable()){
            try{
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void disconnect() throws InterruptedException {
        isAvailable = true;
        //break pool thread out of dequeue() call.
        System.out.println("Disconnecting " + thread.getName());
        Thread.sleep(500);
        this.thread.interrupt();
    }

    public synchronized boolean isAvailable(){
        return isAvailable;
    }
}
