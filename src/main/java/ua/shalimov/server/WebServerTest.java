package ua.shalimov.server;

import java.io.IOException;

public class WebServerTest {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.setResourcePath("resources/resourcesPath");

        server.start();

    }
}
