package ua.shalimov.server.requesthandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter implements AutoCloseable {
    private BufferedWriter writer;

    public ResponseWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public void writeSuccessResponse(BufferedReader content) throws IOException {
        writeStatusLine();
        String line;
        while ((line = content.readLine()) != null) {
            writer.write(line);
        }
    }

    public void writeNotFoundResponse() throws IOException {
        writer.write("HTTP/1.1 404 Not Found");
        writer.newLine();
        writer.newLine();
        String content = "<html>\n" +
                "<head><title>404 Not Found</title></head>\n" +
                "<center><h1>404 Not Found</h1></center>\n";
        writer.write(content);
    }

    public void writeBadRequestResponse() throws IOException {
        writer.write("HTTP/1.1 400 Bad Request");
        writer.newLine();
        writer.newLine();
        String content = "<html>\n" +
                "<head><title>400 Bad Request </title></head>\n" +
                "<center><h1>400 Bad Request </h1></center>\n";
        writer.write(content);
        writer.close();
    }

    public void writeUnsupportedMethod() throws IOException {
        writer.write("HTTP/1.1 405 Method Not Allowed");
        writer.newLine();
        writer.newLine();
        String content = "<html>\n" +
                "<head><title>405 Method Not Allowed</title></head>\n" +
                "<center><h1>405 Method Not Allowed</h1></center>\n";
        writer.write(content);
    }

    private void writeStatusLine() throws IOException {
        writer.write("HTTP/1.1 200 OK");
        writer.newLine();
        writer.newLine();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
