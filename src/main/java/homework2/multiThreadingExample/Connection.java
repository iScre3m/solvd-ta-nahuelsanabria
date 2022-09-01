package homework2.multiThreadingExample;

public class Connection implements Runnable{

    private final String name;
    private boolean isAvailable;

    public Connection(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    void connect(){
        System.out.println("Connecting");
        isAvailable = false;
    }

    void disconnect(){
        System.out.println("Disconnecting");
        isAvailable=true;
    }


    @Override
    public void run() {
        String myConnection = "Connection";

        connect();

        System.out.println(name + " uses " + myConnection);
        System.out.println("<<<<< Started run() of " + name);

        try {
                Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("<<<<< Finished run() of " + name);

        disconnect();
    }
}
