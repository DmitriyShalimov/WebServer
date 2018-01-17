package ua.shalimov.server.requesthandler;

import ua.shalimov.server.entity.HttpMethod;
import ua.shalimov.server.entity.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public static Request parseRequest(BufferedReader reader) throws IOException {
        Request request = new Request();
        injectUrlAndMethod(request, reader.readLine());
        injectHeaders(request, reader);
        return request;
    }

    private static void injectUrlAndMethod(Request request, String requestStartingLine) {
        String[] list = requestStartingLine.split(" ");
        if (list[1].contains("?")) {
            request.setUrl(list[1].substring(0, list[1].indexOf("?")));
            String parametersString = list[1].substring(list[1].indexOf("?") + 1);
            String[] parametersList = parametersString.split("&");
            Map<String, String> parameters = new HashMap<>();
            for (String parameter : parametersList) {
                String parametersKey = parameter.substring(0, parameter.indexOf("="));
                String parametersValue = parameter.substring(parameter.indexOf("=") + 1);
                parameters.put(parametersKey, parametersValue);
            }
            request.setParameters(parameters);
        } else {
            request.setUrl(list[1]);
        }

        request.setMethod(HttpMethod.getHttpMethodByName(list[0]));
    }

    private static void injectHeaders(Request request, BufferedReader reader) throws IOException {
        String line;
        Map<String, String> headers = new HashMap<>();
        while (!(line = reader.readLine()).isEmpty()) {
            String[] list = line.split(": ");
            headers.put(list[0], list[1]);
        }
        request.setHeaders(headers);
    }
}
