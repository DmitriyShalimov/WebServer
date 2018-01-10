package ua.shalimov.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RequestHandler {
    private BufferedWriter writer;
    private BufferedReader reader;
    private ResourceReader resourceReader;

    public RequestHandler(BufferedReader bufferedReader, BufferedWriter bufferedWriter, ResourceReader resourceReader) {
        this.writer = bufferedWriter;
        this.reader = bufferedReader;
        this.resourceReader = resourceReader;
    }

    public void handle() throws IOException {
        ResponseWriter responseWriter = new ResponseWriter(writer);
        Request request = null;
        try {
            request = RequestParser.parseRequest(reader);
        } catch (IOException e) {
            responseWriter.writeBadRequestResponse();
        }

        if (HttpMethod.GET.equals(request.getMethod())) {
            String content;
            try {
                content = resourceReader.readContent(request.getUrl());
            } catch (IOException e) {
                responseWriter.writeNotFoundResponse();
                return;
            }
            responseWriter.writeSuccessResponse(content);
        } else {
            responseWriter.writeBadRequestResponse();
        }
    }
}
