package myserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import handler.ExecuteHandler;
import handler.ParseIntHandler;
import handler.ToLowerCaseHandler;
import handler.TrimHandler;

/**
 * Created by tyler on 2/13/2017.
 * This class calls our handlers to process requests
 * Gets the IP address and JSON and sends data to appropriate Handler
 * Uses HttpServer class
 */

public class ServerCommunicator {
    private static ServerCommunicator ourInstance = new ServerCommunicator();

    public static ServerCommunicator getInstance() {
        return ourInstance;
    }

    private ServerCommunicator() {
    }

    private static final int MAX_WAITING_CONNECTIONS = 10;

    /**
     * Runs our server
     * */
    public void run(String portNumber) {
        HttpServer server;
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // use the default executor
        server.createContext("/parseInt",new ParseIntHandler());
        server.createContext("/toLowerCase",new ToLowerCaseHandler());//gives personID
        server.createContext("/trim",new TrimHandler());//for CSS and index handler
        server.createContext("/execute",new ExecuteHandler());//for CSS and index handler

        server.start();
    }

    public static void main(String[] args) {
        String portNumber = args[0];
        new ServerCommunicator().run(portNumber);
    }
}
