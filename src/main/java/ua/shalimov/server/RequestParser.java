package ua.shalimov.server;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {
    public static Request parseRequest(BufferedReader reader) throws IOException {
        Request request = new Request();
        injectUrlAndMethod(request, reader.readLine());
        return request;
    }

    private static void injectUrlAndMethod(Request request, String requestStartingLine) {
        String[] list = requestStartingLine.split(" ");
        request.setUrl(list[1]);
        request.setMethod(HttpMethod.getHttpMethodByName(list[0]));
    }
}
