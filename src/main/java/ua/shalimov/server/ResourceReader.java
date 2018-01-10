package ua.shalimov.server;

import java.io.*;

public class ResourceReader {
    private String resourcePath;

    public ResourceReader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String readContent(String url) throws IOException {
        String path = "src/main/" + resourcePath + url;
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReaderFromFile = new BufferedReader(fileReader);
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = bufferedReaderFromFile.readLine()) != null) {
            content.append(line).append("\n");
        }
        fileReader.close();
        return content.toString();
    }
}
