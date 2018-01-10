package ua.shalimov.server;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {
    private BufferedWriter writer;

    public ResponseWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public void writeSuccessResponse(String content) throws IOException {
        writeStatusLine();
        writer.write(content);
        writer.newLine();
        writer.close();
    }

    public void writeNotFoundResponse() throws IOException {
        writer.write("HTTP/1.1 404 Not Found");
        writer.newLine();
        writer.newLine();
        String content = "<html>\n" +
                "<head><title>404 Not Found</title></head>\n" +
                "<center><h1>404 Not Found</h1></center>\n";
        writer.write(content);
        writer.close();
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

    private void writeStatusLine() throws IOException {
        writer.write("HTTP/1.1 200 OK");
        writer.newLine();
        writer.newLine();
    }
}
