package ua.shalimov.server;

import org.junit.Test;

import static org.junit.Assert.*;



public class ResourceReaderTest {
    @Test
    public void readContent() throws Exception {
       String content="<!doctype html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <meta name=\"viewport\"\n" +
               "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
               "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
               "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
               "    <title>Document</title>\n" +
               "</head>\n" +
               "<body><h1>Hello World!</h1></body>\n" +
               "</html>\n";
               ResourceReader resourceReader=new ResourceReader("resources/resourcesPath");
               String st=resourceReader.readContent("/index.html");
        assertEquals(resourceReader.readContent("/index.html"),content);
    }

}