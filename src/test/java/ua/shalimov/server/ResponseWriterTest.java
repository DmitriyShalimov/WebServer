package ua.shalimov.server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.BufferedWriter;
import static org.mockito.Mockito.times;

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

        responseWriter.writeSuccessResponse("content");
        Mockito.verify(bufferedWriter).write("HTTP/1.1 200 OK");
        Mockito.verify(bufferedWriter, times(3)).newLine();
        Mockito.verify(bufferedWriter).write("content");

    }

    @Test
    public void testWriteNotFoundResponse() throws Exception {
        responseWriter.writeNotFoundResponse();
        Mockito.verify(bufferedWriter).write("HTTP/1.1 404 Not Found");
        Mockito.verify(bufferedWriter, times(2)).newLine();
        Mockito.verify(bufferedWriter).write("<html>\n" +
                "<head><title>404 Not Found</title></head>\n" +
                "<center><h1>404 Not Found</h1></center>\n");
    }

    @Test
    public void testWriteBadRequestResponse() throws Exception {
        responseWriter.writeBadRequestResponse();
        Mockito.verify(bufferedWriter).write("HTTP/1.1 400 Bad Request");
        Mockito.verify(bufferedWriter, times(2)).newLine();
        Mockito.verify(bufferedWriter).write( "<html>\n" +
                "<head><title>400 Bad Request </title></head>\n" +
                "<center><h1>400 Bad Request </h1></center>\n");

    }


}