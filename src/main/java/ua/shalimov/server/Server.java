package ua.shalimov.server;

import ua.shalimov.server.requesthandler.RequestHandler;
import ua.shalimov.server.resourcehandler.ResourceReader;

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
        ResourceReader resourceReader = new ResourceReader(resourcePath);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try (
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, resourceReader);
                requestHandler.handle();
            }
        }
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
