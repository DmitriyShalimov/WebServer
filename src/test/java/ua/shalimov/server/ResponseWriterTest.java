package ua.shalimov.server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.shalimov.server.entity.Request;
import ua.shalimov.server.requesthandler.ResponseWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ResponseWriterTest {
    private BufferedWriter bufferedWriter;
    private ResponseWriter responseWriter;

    @Before
    public void setUpApplicationContext() {
        bufferedWriter = Mockito.mock(BufferedWriter.class);
        responseWriter = new ResponseWriter(bufferedWriter);
    }


    @Test
    public void testWriteSuccessResponse() throws Exception {
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn("content");
        responseWriter.writeSuccessResponse(bufferedReader);
        verify(bufferedWriter).write("HTTP/1.1 200 OK");
        verify(bufferedWriter, times(2)).newLine();
      //  verify(bufferedWriter).write("content");

    }

    @Test
    public void testWriteNotFoundResponse() throws Exception {
        responseWriter.writeNotFoundResponse();
        verify(bufferedWriter).write("HTTP/1.1 404 Not Found");
        verify(bufferedWriter, times(2)).newLine();
        verify(bufferedWriter).write("<html>\n" +
                "<head><title>404 Not Found</title></head>\n" +
                "<center><h1>404 Not Found</h1></center>\n");
    }

    @Test
    public void testWriteBadRequestResponse() throws Exception {
        responseWriter.writeBadRequestResponse();
        verify(bufferedWriter).write("HTTP/1.1 400 Bad Request");
        verify(bufferedWriter, times(2)).newLine();
        verify(bufferedWriter).write("<html>\n" +
                "<head><title>400 Bad Request </title></head>\n" +
                "<center><h1>400 Bad Request </h1></center>\n");
    }
}