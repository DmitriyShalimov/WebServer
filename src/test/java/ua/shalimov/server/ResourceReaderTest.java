package ua.shalimov.server;

import org.junit.Test;
import ua.shalimov.server.resourcehandler.ResourceReader;

import static org.junit.Assert.*;


public class ResourceReaderTest {
    @Test
    public void readContent() throws Exception {
        String content = "first line\nsecond line\n";
        ResourceReader resourceReader = new ResourceReader("src/test/resources/webapp");
        assertEquals(resourceReader.readContent("/index.html"), content);
    }

}