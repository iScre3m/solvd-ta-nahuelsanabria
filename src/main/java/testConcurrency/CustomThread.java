package testConcurrency;

public class CustomThread extends Thread{
    protected final String name;

    private boolean isAvailable;
    public CustomThread(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    void connect(){
        System.out.println("Connecting thread " + name);
        isAvailable = false;
    }

    void disconnect(){
        System.out.println("Disconnecting thread " + name);
        isAvailable=true;
    }

    @Override
    public void run() {
        String myConnection = "Connection to DB";

        connect();

        System.out.println(name + " uses " + myConnection);
        System.out.println("<<<<< Started run() of " + name);

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("<<<<< Finished run() of " + name);

        disconnect();


    }
}