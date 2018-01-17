package ua.shalimov.server;

import org.junit.Test;
import org.mockito.Mockito;
import ua.shalimov.server.entity.HttpMethod;
import ua.shalimov.server.entity.Request;
import ua.shalimov.server.requesthandler.RequestParser;

import java.io.BufferedReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RequestParserTest {
    @Test
    public void parseRequest() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("GET /url HTTP/1.1");
        Request request = RequestParser.parseRequest(reader);
        assertEquals(request.getMethod(), HttpMethod.GET);
        assertEquals(request.getUrl(), "/url");
    }
}