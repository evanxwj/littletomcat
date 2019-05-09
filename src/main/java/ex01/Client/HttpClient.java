package ex01.Client;

import util.ConnectUtil;

import java.io.*;
import java.net.Socket;

public class HttpClient {

    static class MyThread extends Thread {
        @Override
        public void run() {
            try (Socket socket = new Socket("127.0.0.1", 8080)) {
                OutputStream os = socket.getOutputStream();
                PrintWriter out = new PrintWriter(os, false);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("GET /index.html HTTP/1.1");
                out.println("Host: localhost:8080");
                out.println("Connection: Close");
                out.println();

                out.flush();
                // read the response
//                String responseResult = ConnectUtil.readResponse(in);
//                System.out.println(responseResult);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
