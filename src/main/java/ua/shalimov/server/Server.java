package ua.shalimov.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String resourcePath;

    public void setPort(int port) {
        this.port = port;

    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            ResourceReader resourceReader = new ResourceReader(resourcePath);
            RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, resourceReader);
            requestHandler.handle();
        }
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
