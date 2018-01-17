package ua.shalimov.server.requesthandler;

import ua.shalimov.server.resourcehandler.ResourceReader;
import ua.shalimov.server.entity.HttpMethod;
import ua.shalimov.server.entity.Request;

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
        try (ResponseWriter responseWriterWrapper = responseWriter) {
            Request request = RequestParser.parseRequest(reader);
            if (HttpMethod.GET.equals(request.getMethod())) {
                BufferedReader content;
                try {
                    content = resourceReader.readContent(request.getUrl());
                } catch (IOException e) {
                    responseWriterWrapper.writeNotFoundResponse();
                    return;
                }
                responseWriterWrapper.writeSuccessResponse(content);
            } else {
                responseWriterWrapper.writeUnsupportedMethod();
            }
        } catch (IOException e) {
            responseWriter.writeBadRequestResponse();
        }
    }
}
