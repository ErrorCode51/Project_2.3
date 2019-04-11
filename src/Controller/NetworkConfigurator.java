package Controller;

// The goal of this class is to add a layer of abstaction between the network.conf file and the rest of the framework

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class NetworkConfigurator {

    static private Properties networkProps;

    public static void loadPropFile() {
        try {
            networkProps = new Properties();
            FileInputStream fis = new FileInputStream("conf/network.conf");
            networkProps.load(fis);
        } catch (FileNotFoundException fnfe) {
            System.err.println("ERROR: can not find file network.conf");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static String getProperty(String propName) {
        return networkProps.get(propName).toString();
    }

}
