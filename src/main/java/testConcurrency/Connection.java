package testConcurrency;

import homework2.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection{

    final Logger LOG = LogManager.getLogger(Connection.class.getSimpleName());

    private final String name;
    private boolean isAvailable;

    public Connection(String name){
        this.name = name;
        LOG.info("Connection was created: " + name);
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    void connect(){
        LOG.info("Connecting " + name);
        isAvailable = false;
    }

    void disconnect(){
        LOG.info("Disconnecting " + name);
        isAvailable=true;
    }
}
