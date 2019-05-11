package connect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class HttpConnector implements Runnable {

    private boolean stopped;
    private String scheme = "HTTP";

    public String getScheme() {
        return this.scheme;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        HttpProcessor processor = new HttpProcessor();
        while (!stopped) {
            processor.process(serverSocket);
        }
    }
}
