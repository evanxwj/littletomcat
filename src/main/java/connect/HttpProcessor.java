package connect;

import main.ServletProcessor;
import main.StaticResourceProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpProcessor {


    public HttpRequest getRequest(InputStream input) {
        return new HttpRequest(input);
    }

    public HttpResponse getResponse(OutputStream output, HttpRequest request) {
        HttpResponse response = new HttpResponse(output);
        response.setRequest(request);
        return response;
    }

    public void process(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            SocketInputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            HttpRequest request = getRequest(input);
            HttpResponse response = getResponse(output, request);

            if (request.getUri().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
